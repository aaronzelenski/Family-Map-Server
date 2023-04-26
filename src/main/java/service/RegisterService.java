package service;

import dao.*;
import model.AuthToken;
import model.Person;
import model.User;
import request.RegisterRequest;
import response.*;


/**
 * service class for : RegisterService
 */
public class RegisterService {
  public RegisterService(){
  }

  /**
   * service for function: register
   *
   * @param request clear request
   * @return clear response
   */
  public RegisterResponse register(RegisterRequest request){

    Database db = new Database();
    try{
      db.openConnection();

      AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());
      String theNewToken = authTokenDAO.generateAuthtoken();
      PersonDAO personDAO = new PersonDAO(db.getConnection());
      String uniquePersonID = personDAO.generatePersonID();
      String username = request.getUsername();

      UserDAO userDAO = new UserDAO(db.getConnection());

      authTokenDAO.insert(new AuthToken(theNewToken, username));

      userDAO.insert(new User(username, request.getPassword(), request.getEmail(), request.getFirstName(), request.getLastName(), request.getGender(), uniquePersonID));

      Person person = new Person(uniquePersonID, request.getUsername(), request.getFirstName(), request.getLastName(), request.getGender());

      db.closeConnection(true);
      db.openConnection();

      FillService fillService = new FillService();

      fillService.fill(person, request.getUsername(), 4);

      db.closeConnection(true);
      RegisterResponse response = new RegisterResponse(true, theNewToken, username , uniquePersonID);
      return response;



    } catch (Exception e) {
      e.printStackTrace();

      db.closeConnection(false);
      RegisterResponse response = new RegisterResponse(false, "Error message");
      return response;
    }
  }

}
