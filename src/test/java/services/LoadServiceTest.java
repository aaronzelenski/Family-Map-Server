package services;

import dao.*;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import request.LoadRequest;
import response.LoadResponse;
import service.LoadService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoadServiceTest {

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
    db=new Database();

    loadService=new LoadService();

    bestUser = new User("thisguyaaron", "password", "thisguyaaron@gmail.com", "Aaron", "Zelenski", "m", "aaron_zelenski");
    randomUser = new User("randomUser", "password123", "random@gmail.com", "rando", "randoLastName", "m", "rando_zelenski");
    bestEvent  = new Event("Aaron_Birth", "thisguyaaron", "thisguyaaron", (float) 0.004, (float) 0.005, "USA!", "Glendora", "birth", 2000);
    bestEvent2 = new Event("debbie_birth", "thisguyaaron", "thisgirldebz", (float) 0.004, (float) 0.005, "USA!", "Las Vegas", "birth", 1963);
    bestPerson = new Person("BobbypersonID", "bobbyUsername", "Bobby", "Jenkins", "m", "BobbyFatherID", "BobbyMotherID", "BobbySpouseID");
    bestPerson2 = new Person("aNewPerson", "thisguyaaron", "jimmy", "bentz", "m", "jimmyFatherID", "jimmyMotherID", "jimmySpouseID");


    Connection connection=db.getConnection();

    eventDAO=new EventDAO(connection);
    personDAO=new PersonDAO(connection);
    userDAO=new UserDAO(connection);

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

    loadRequest=new LoadRequest(users, persons, events);

  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }


  @Test
  public void LoadServicePass() throws DataAccessException {
    db.closeConnection(true);
    loadService.load(loadRequest);


    Connection connection = db.openConnection();

    eventDAO = new EventDAO(connection);
    personDAO = new PersonDAO(connection);
    userDAO = new UserDAO(connection);


    User theRightUser = userDAO.find("thisguyaaron");
    Event theRightEvent = eventDAO.find("Aaron_Birth");
    Person theRightPerson = personDAO.find("BobbypersonID");




    assertEquals(theRightUser.getUsername(), loadRequest.getUsers()[0].getUsername());
    assertEquals(theRightEvent.getEventID(), loadRequest.getEvents()[0].getEventID());
    assertEquals(theRightPerson.getPersonID(), loadRequest.getPersons()[0].getPersonID());



  }


  @Test
  public void LoadServiceFail() throws DataAccessException {

    db.closeConnection(true);
    loadService.load(loadRequest);


    Connection connection = db.openConnection();

    eventDAO = new EventDAO(connection);
    personDAO = new PersonDAO(connection);
    userDAO = new UserDAO(connection);




    User theRightUser = userDAO.find("thisguyaaron");
    Event theRightEvent = eventDAO.find("Aaron_Birth");
    Person theRightPerson = personDAO.find("BobbypersonID");




    assertNotEquals(theRightUser.getUsername(), loadRequest.getUsers()[1].getUsername());
    assertNotEquals(theRightEvent.getEventID(), loadRequest.getEvents()[1].getEventID());
    assertNotEquals(theRightPerson.getPersonID(), loadRequest.getPersons()[1].getPersonID());

  }
}
