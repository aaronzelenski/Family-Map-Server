package service;

import dao.*;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import response.*;


/**
 * service class for : LoginService
 */
public class LoginService {

  User user;


//  public LoginService() throws DataAccessException {
//  }

  /**
   * service for function: login
   *
   * @param request clear request
   * @return clear response
   */
  public LoginResponse login(LoginRequest request){

    Database db = new Database();
    try{
      db.openConnection();

      String username =request.getUsername();
      String password =request.getPassword();


      UserDAO userDAO = new UserDAO(db.getConnection());
      AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());

      user = userDAO.find(username);

      if(user == null){
        db.closeConnection(false);
        return new LoginResponse(false, "Error: user is null");
      }

      if(!user.getPassword().equals(password)){
        db.closeConnection(false);
        return new LoginResponse(false, "Error: password is wrong");
      }

      String theNewToken = authTokenDAO.generateAuthtoken();

      authTokenDAO.insert(new AuthToken(theNewToken, user.getUsername()));

      db.closeConnection(true);
      return new LoginResponse(theNewToken, username, user.getPersonID(), true);

    }
    catch (Exception e) {
      e.printStackTrace();
      db.closeConnection(false);
      LoginResponse response = new LoginResponse(false, "Error: Login failed");
      return response;
    }
  }
}
