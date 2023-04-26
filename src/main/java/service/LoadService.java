package service;

import dao.*;
import model.Event;
import model.Person;
import model.User;
import request.LoadRequest;
import response.*;

/**
 * service class for : LoadService
 */

public class LoadService {

  private int numEvents = 0;
  private int numUsers = 0;
  private int numPeople = 0;


  public LoadService() throws DataAccessException {
  }

  /**
   * service for function: load
   *
   * @param request clear request
   * @return clear response
   */
  public LoadResponse load(LoadRequest request){



    Database db = new Database();
    try{
      db.openConnection();

      UserDAO userDAO = new UserDAO(db.getConnection());
      PersonDAO personDAO = new PersonDAO(db.getConnection());
      AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());
      EventDAO eventDAO = new EventDAO(db.getConnection());

      userDAO.clear();
      personDAO.clear();
      authTokenDAO.clear();
      eventDAO.clear();

      for(int i = 0; i < request.getUsers().length; i++){
        User user = request.getUsers()[i];
        userDAO.insert(new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName(), user.getGender(), user.getPersonID()));
        numUsers++;
      }

      for(int i = 0; i < request.getPersons().length; i++){
        Person person = request.getPersons()[i];
        personDAO.insert(new Person(person.getPersonID(), person.getAssociatedUsername(), person.getFirstName(),
                person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID()));
        numPeople++;
      }



      for(int i = 0; i < request.getEvents().length; i++){
        Event event = request.getEvents()[i];

        eventDAO.insert(new Event(event.getEventID(), event.getAssociatedUsername(), event.getPersonID(),
                event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(),
                event.getYear()));
        numEvents++;
      }


      db.closeConnection(true);
      LoadResponse response = new LoadResponse(true, "Successfully added " + numUsers + " users, " + numPeople + " persons, and " + numEvents + " events to the database.");
      return response;


    } catch (Exception e) {
      e.printStackTrace();

      db.closeConnection(false);
      LoadResponse response = new LoadResponse(false, "Loading failed");
      return response;
    }
  }
}
