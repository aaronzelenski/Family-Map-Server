package handler;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;
import dao.DataAccessException;
import model.Person;
import response.FillResponse;
import service.FillService;

public class FillHandler implements HttpHandler{

  @Override
  public void handle(HttpExchange exchange) throws IOException {

    // This handler allows a "Ticket to Ride" player to claim ability
    // route between two cities (part of the Ticket to Ride game).
    // The HTTP request body contains a JSON object indicating which
    // route the caller wants to claim (a route is defined by two cities).
    // This implementation is clearly unrealistic, because it
    // doesn't actually do anything other than print out the received JSON string.
    // It is also unrealistic in that it accepts only one specific
    // hard-coded auth token.
    // However, it does demonstrate the following:
    // 1. How to get the HTTP request type (or, "method")
    // 2. How to access HTTP request headers
    // 3. How to read JSON data from the HTTP request body
    // 4. How to return the desired status code (200, 404, etc.)
    //		in an HTTP response
    // 5. How to check an incoming HTTP request for an auth token

    boolean success = false;

    try {
      // Determine the HTTP request type (GET, POST, etc.).
      // Only allow POST requests for this operation.
      // This operation requires a POST request, because the
      // client is "posting" information to the server for processing.
      if (exchange.getRequestMethod().toLowerCase().equals("post")) {

            InputStream reqBody = exchange.getRequestBody();

            // Read JSON string from the input stream
            String reqData = readString(reqBody);

            // Display/log the request JSON data
            System.out.println(reqData);

        String requestLine=exchange.getRequestURI().toString();
        String[] arr=requestLine.split("/");

        Gson gson=new Gson();


        String username = arr[2];

        int generations = 4;

        if (arr.length > 3) {
          generations = Integer.parseInt(arr[3]);
        }

        Person person = new Person();
        FillService service = new FillService();
        FillResponse response = service.fill(person, username, generations);

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