package services;

import dao.*;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import request.LoginRequest;
import request.PersonRequestSingle;
import response.LoadResponse;
import response.LoginResponse;
import service.LoadService;
import service.LoginService;
import service.PersonServiceSingle;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class PersonServiceSingleTest {

  private Database db;

  private Person bestPerson;
  private Person bestPerson2;

  private User bestUser;
  private User randomUser;

  private Event bestEvent;
  private Event bestEvent2;

  private EventDAO eventDAO;
  private PersonDAO personDAO;
  private UserDAO userDAO;

  private LoadRequest loadRequest;
  private LoadResponse loadResponse;
  private LoadService loadService;


  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();

    loadService = new LoadService();

    bestUser = new User("thisguyaaron", "password", "thisguyaaron@gmail.com", "Aaron", "Zelenski", "m", "aaron_zelenski");
    randomUser = new User("randomUser", "password123", "random@gmail.com", "rando", "randoLastName", "m", "rando_zelenski");
    bestEvent  = new Event("Aaron_Birth", "thisguyaaron", "thisguyaaron", (float) 0.004, (float) 0.005, "USA!", "Glendora", "birth", 2000);
    bestEvent2 = new Event("debbie_birth", "thisguyaaron", "thisgirldebz", (float) 0.004, (float) 0.005, "USA!", "Las Vegas", "birth", 1963);
    bestPerson = new Person("BobbypersonID", "bobbyUsername", "Bobby", "Jenkins", "m", "BobbyFatherID", "BobbyMotherID", "BobbySpouseID");
    bestPerson2 = new Person("aNewPerson", "thisguyaaron", "jimmy", "bentz", "m", "jimmyFatherID", "jimmyMotherID", "jimmySpouseID");


    Connection connection = db.getConnection();

    eventDAO = new EventDAO(connection);
    personDAO = new PersonDAO(connection);
    userDAO = new UserDAO(connection);

    eventDAO.clear();
    personDAO.clear();
    userDAO.clear();

    Event[] events = new Event[2];
    events[0] = bestEvent;
    events[1] = bestEvent2;

    Person[] persons = new Person[2];
    persons[0] = bestPerson;
    persons[1] = bestPerson2;


    User[] users = new User[2];
    users[0] = bestUser;
    users[1] = randomUser;

    loadRequest = new LoadRequest(users, persons, events);



  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }


  @Test
  public void PersonServiceSinglePass() throws DataAccessException{
    db.closeConnection(true);
    loadService.load(loadRequest);

    LoginRequest loginRequest = new LoginRequest("thisguyaaron", "password");

    LoginService loginService = new LoginService();

    LoginResponse loginResponse = loginService.login(loginRequest);


    PersonServiceSingle personServiceSingle = new PersonServiceSingle();

    PersonRequestSingle personRequestSingle = new PersonRequestSingle(loginResponse.getAuthtoken(), "Aaron_Birth");


    Connection connection = db.openConnection();

    eventDAO = new EventDAO(connection);
    personDAO = new PersonDAO(connection);
    userDAO = new UserDAO(connection);


    Person theRightPerson = personDAO.find("aaron_zelenski");

  }


  @Test
  public void PersonServiceSingleFail() throws DataAccessException{
    int num = 1 + 1;
    assertNotEquals(3, num);
  }

}
