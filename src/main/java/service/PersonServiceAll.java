package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import request.PersonRequestAll;
import response.PersonResponseAll;

import java.util.ArrayList;

public class PersonServiceAll {


  public PersonResponseAll AllPersonService(PersonRequestAll r) {

    Database db=new Database();

    try {
      db.openConnection();

      AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());
      PersonDAO personDAO = new PersonDAO(db.getConnection());
      String username = authTokenDAO.RetrieveAuthtoken(r.getAuthToken());

      if(username == null){
        db.closeConnection(false);
        return new PersonResponseAll("Error: Invalid AuthToken", false);
      }

      ArrayList<Person> people = personDAO.RetrieveAllPeople(username);


      db.closeConnection(true);

      return new PersonResponseAll(people, true);

    } catch (DataAccessException ex) {
      ex.printStackTrace();
      db.closeConnection(false);
      PersonResponseAll response = new PersonResponseAll("Error: Error encountered while attempting to access data from database", false);
      return response;
    }
  }
}
