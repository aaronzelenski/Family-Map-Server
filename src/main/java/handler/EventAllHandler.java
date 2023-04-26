package handler;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.DataAccessException;
import request.EventRequestAll;
import response.EventResponseAll;
import service.EventServiceAll;

public class EventAllHandler implements HttpHandler{
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

        Gson gson=new Gson();

        EventRequestAll request=new EventRequestAll(authToken);

        EventServiceAll service=new EventServiceAll();

        EventResponseAll response=service.EventAll(request);

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
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      exchange.getResponseBody().close();
      ex.printStackTrace();
    }
  }

  /*
      The writeString method shows how to write a String to an OutputStream.
  */
  private void writeString(String str, OutputStream os) throws IOException {
    OutputStreamWriter sw = new OutputStreamWriter(os);
    sw.write(str);
    sw.flush();
  }
}
