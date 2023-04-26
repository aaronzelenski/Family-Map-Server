package service;

import dao.AuthTokenDAO;
import dao.DataAccessException;
import dao.Database;
import dao.PersonDAO;
import model.Person;
import request.PersonRequestSingle;
import response.*;

/**
 * service class for : PersonIDService
 */

public class PersonServiceSingle {

  public PersonServiceSingle() throws DataAccessException {
  }

  /**
   * service for function: PersonID
   *
   * @param request clear request
   * @return clear response
   */
  public PersonResponseSingle personID(PersonRequestSingle request) {
    Database db=new Database();

    try {
      db.openConnection();

      AuthTokenDAO authTokenDAO=new AuthTokenDAO(db.getConnection());
      String username=authTokenDAO.RetrieveAuthtoken(request.getAuthToken());

      if (username == null) {
        db.closeConnection(false);
        return new PersonResponseSingle("Error: Invalid Authtoken", false);
      }

      PersonDAO personDAO=new PersonDAO(db.getConnection());
      Person person=personDAO.find(request.getPersonID());

      if (person.getAssociatedUsername() == null) {
        db.closeConnection(false);
        return new PersonResponseSingle("Error: Invalid PersonID", false);
      }

      if (!username.equals(person.getAssociatedUsername())) {
        db.closeConnection(false);
        return new PersonResponseSingle("Error: This Person does not belong to this user", false);
      }

      db.closeConnection(true);

      return new PersonResponseSingle(username, person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(), person.getMotherID(), person.getSpouseID(), true);


    } catch (DataAccessException e) {
      e.printStackTrace();
      db.closeConnection(false);
      PersonResponseSingle response=new PersonResponseSingle("Error: An error occurred while accessing the database", false);
      return response;
    }
  }
}
