package handler;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import request.*;
import service.*;
import response.*;

import static java.nio.file.Files.readString;

public class LoginHandler implements HttpHandler {


  // Handles HTTP requests containing the "/routes/claim" URL path.
  // The "exchange" parameter is an HttpExchange object, which is
  // defined by Java.
  // In this context, an "exchange" is an HTTP request/response pair
  // (i.e., the client and server exchange a request and response).
  // The HttpExchange object gives the handler access to all of the
  // details of the HTTP request (Request type [GET or POST],
  // request headers, request body, etc.).
  // The HttpExchange object also gives the handler the ability
  // to construct an HTTP response and send it back to the client
  // (Status code, headers, response body, etc.).
  @Override
  public void handle(HttpExchange exchange) throws IOException {


    boolean success=false;

    try {
      // Determine the HTTP request type (GET, POST, etc.).
      // Only allow POST requests for this operation.
      // This operation requires a POST request, because the
      // client is "posting" information to the server for processing.
      if (exchange.getRequestMethod().toLowerCase().equals("post")) {

        InputStream reqBody = exchange.getRequestBody();

        String reqData = readString(reqBody);
        // Display/log the request JSON data
        System.out.println(reqData);

        Gson gson=new Gson();
        LoginRequest request=(LoginRequest) gson.fromJson(reqData, LoginRequest.class);

        LoginService service=new LoginService();
        LoginResponse response=service.login(request);

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
    } catch (Exception ex) {
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      exchange.getResponseBody().close();
      ex.printStackTrace();
    }
  }

  private String readString(InputStream is) throws IOException {
    StringBuilder sb = new StringBuilder();
    InputStreamReader sr = new InputStreamReader(is);
    char[] buf = new char[1024];
    int len;
    while ((len = sr.read(buf)) > 0) {
      sb.append(buf, 0, len);
    }
    return sb.toString();
  }
}
