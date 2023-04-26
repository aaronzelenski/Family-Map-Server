package service;

import dao.*;
import model.Person;
import model.User;
import response.*;
import generate.*;

/**
 * service class for : FillService
 */

public class FillService {

  User user;
  Person person;

  public FillService() throws DataAccessException {
  }

  /**
   * service for function: Fill
   *
   * @return clear response
   */
  public FillResponse fill(Person person, String username, int generations){

    Database db = new Database();
    try{
      db.openConnection();

      //clear all events by associated username
      // clear the person

      UserDAO userDAO = new UserDAO(db.getConnection());
      PersonDAO personDAO = new PersonDAO(db.getConnection());
      EventDAO eventDAO = new EventDAO(db.getConnection());

      user = userDAO.find(username);


      if(user == null){
        db.closeConnection(false);
        return new FillResponse("Error: Unregistered user", false);
      }


      if(person.getPersonID() == null){
        person= personDAO.find(user.getPersonID());
      }

      personDAO.UsernameDelete(username);
      eventDAO.UsernameDelete(username);


      Generator generator = new Generator(db.getConnection());

      generator.generateFamily(generations, 2000, person);

      int numPeople = (int)Math.pow(2, generations + 1) - 1;
      int numEvents = 3 * numPeople;

//      GeneratePerson generatePerson = new GeneratePerson(db.getConnection());
//      String theOutput = generatePerson.GenerateTree(username,generation);

      db.closeConnection(true);
      return new FillResponse("Successfully added " + numPeople + " persons and " + numEvents + " events to the database.", true);


    } catch (Exception e) {
      e.printStackTrace();

      db.closeConnection(false);
      FillResponse response = new FillResponse("Fill failed", false);
      return response;
    }
  }
}
