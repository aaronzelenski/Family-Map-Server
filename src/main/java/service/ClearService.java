package service;

import dao.*;
import request.ClearRequest;
import request.RegisterRequest;
import response.*;

/**
 * service class for : ClearService
 */

public class ClearService {

  public ClearService() throws DataAccessException {
  }

    /**
     * service for function: clear
     *
     * @param request clear request
     * @return clear response
     */
    public ClearResponse clear (ClearRequest request){
      Database db=new Database();
      try {
        db.openConnection();
        String successMessage="Clear succeeded.";


        UserDAO userDAO = new UserDAO(db.getConnection());
        PersonDAO personDAO = new PersonDAO(db.getConnection());
        AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());
        EventDAO eventDAO = new EventDAO(db.getConnection());

        userDAO.clear();
        personDAO.clear();
        authTokenDAO.clear();
        eventDAO.clear();


        db.closeConnection(true);
        ClearResponse response=new ClearResponse(true, successMessage);
        return response;


      } catch (Exception e) {
        e.printStackTrace();

        db.closeConnection(false);
        ClearResponse response=new ClearResponse(false, "Error when attempting to clear");
        return response;
      }
    }

}
