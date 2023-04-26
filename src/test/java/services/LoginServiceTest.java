package services;

import dao.*;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoginRequest;
import request.RegisterRequest;
import response.LoginResponse;
import service.LoginService;
import service.RegisterService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

  private Database db;

  private Event bestEvent;
  private Person bestPerson;
  private AuthToken bestAuthtoken;
  private User user;


  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();

    user = new User("thisguyaaron", "password", "thisguyaaron@gmail.com", "Aaron", "Zelenski", "m", "aaron_zelenski");
    RegisterRequest request = new RegisterRequest(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender());
    RegisterService registerService = new RegisterService();
    registerService.register(request);

    Connection connection = db.openConnection();
    UserDAO userDAO = new UserDAO(connection);
    AuthTokenDAO authTokenDAO = new AuthTokenDAO(connection);


  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }


  @Test
  public void LoginServicePass() throws DataAccessException{

    LoginRequest request = new LoginRequest(user.getUsername(), user.getPassword());
    LoginService loginService = new LoginService();
    LoginResponse response = loginService.login(request);

    AuthToken token = new AuthToken(response.getAuthtoken(), user.getUsername());
    assertNotNull(token);
    assertEquals(token.getUsername(), user.getUsername());
  }


  @Test
  public void LoginServiceFail() throws DataAccessException{

    LoginResponse response = new LoginResponse();
    LoginService loginService = new LoginService();

    try{
      User newUser = new User("a", "aaaa", "aaaaa@gmail.com", "a", "aa", "m", "aaaaaaaaaa");
      LoginRequest request = new LoginRequest(newUser.getUsername(), newUser.getPassword());
      response = loginService.login(request);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assertNotNull(response);
    assertFalse(response.isSuccess());
  }
}
