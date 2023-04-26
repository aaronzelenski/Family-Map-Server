package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.net.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler implements HttpHandler {


  @Override
  public void handle(HttpExchange exchange) throws IOException {

    String urlPath = exchange.getRequestURI().toString();

    if(urlPath == null || urlPath.equals("/")){
      urlPath = "/index.html";
    }

    String filePath = "web" + urlPath;

    File myFile = new File(filePath);


    OutputStream responseBody = exchange.getResponseBody();

    if(!myFile.exists()){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
      Files.copy(Path.of("web/HTML/404.html"), responseBody);
    }
    else{
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
      Files.copy(myFile.toPath(), responseBody);
    }
    responseBody.close();
  }
}
