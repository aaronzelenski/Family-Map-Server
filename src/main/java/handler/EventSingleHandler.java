package handler;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.DataAccessException;
import request.EventRequestSingle;
import response.EventResponseSingle;
import service.EventServiceSingle;

public class EventSingleHandler implements HttpHandler{


  @Override
  public void handle(HttpExchange exchange) throws IOException {

    boolean success = false;

    try {

      if (exchange.getRequestMethod().toLowerCase().equals("get")) {
        Headers reqHeaders=exchange.getRequestHeaders();
        String authToken=null;

        if (reqHeaders.containsKey("Authorization")) {
          authToken=reqHeaders.getFirst("Authorization");
        }

        String requestLine=exchange.getRequestURI().toString();
        String[] arr=requestLine.split("/");

        Gson gson=new Gson();

        if (arr.length != 3) {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }

        String eventID = arr[2];

        EventRequestSingle request=new EventRequestSingle(authToken, eventID);

        EventServiceSingle service=new EventServiceSingle();
        EventResponseSingle response=service.EventSingle(request);


        if (!response.isSuccess()) {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
          Writer resBody=new OutputStreamWriter(exchange.getResponseBody());
          gson.toJson(response, resBody);
          resBody.close();
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          Writer resBody=new OutputStreamWriter(exchange.getResponseBody());
          gson.toJson(response, resBody);
          resBody.close();
        }
      }
    } catch (IOException | DataAccessException ex) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
      exchange.getResponseBody().close();
      ex.printStackTrace();
    }
  }

  private void writeString(String str, OutputStream os) throws IOException {
    OutputStreamWriter sw = new OutputStreamWriter(os);
    sw.write(str);
    sw.flush();
  }
}
