package handler;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.DataAccessException;
import request.PersonRequestAll;
import request.PersonRequestSingle;
import response.PersonResponseAll;
import response.PersonResponseSingle;
import service.PersonServiceAll;
import service.PersonServiceSingle;

public class PersonAllHandler implements HttpHandler {


  @Override
  public void handle(HttpExchange exchange) throws IOException {

    boolean success=false;

    try {

      if (exchange.getRequestMethod().toLowerCase().equals("get")) {
        Headers reqHeaders=exchange.getRequestHeaders();
        String authToken=null;
        if (reqHeaders.containsKey("Authorization")) {
          authToken=reqHeaders.getFirst("Authorization");
        }

        Gson gson=new Gson();

        String url=exchange.getRequestURI().toString();

        String[] urlParse=url.split("/");

        if (urlParse.length == 3) {
          String personID=urlParse[2];

          PersonRequestSingle request=new PersonRequestSingle(authToken, personID);

          PersonServiceSingle service=new PersonServiceSingle();
          PersonResponseSingle response=service.personID(request);

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
        } else {


          PersonRequestAll request=new PersonRequestAll(authToken);

          PersonServiceAll service=new PersonServiceAll();

          PersonResponseAll response=service.AllPersonService(request);

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
      }
      } catch(IOException ex){
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        exchange.getResponseBody().close();
        ex.printStackTrace();
      } catch(DataAccessException e){
        throw new RuntimeException(e);
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
