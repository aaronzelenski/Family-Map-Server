package service;

import dao.*;
import model.AuthToken;
import model.Event;
import request.EventRequestSingle;
import response.*;



/**
 * service class for : EventSingleServer
 */

public class EventServiceSingle {

  public EventServiceSingle() throws DataAccessException {
  }

  /**
   * service for function: EventSingle
   *
   * @param request clear request
   * @return clear response
   */
  public EventResponseSingle EventSingle(EventRequestSingle request){
    Database db=new Database();

    try {
      db.openConnection();

      EventDAO eventDAO=new EventDAO(db.getConnection());

      UserDAO userDAO = new UserDAO(db.getConnection());

      AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());

      AuthToken authToken = authTokenDAO.find(request.getAuthToken());

      if(authToken == null){
        db.closeConnection(false);
        return new EventResponseSingle("Error: Invalid authtoken", false);

      }

      String authtokenUsername =authToken.getUsername();

      String eventUsername = eventDAO.find(request.getEventID()).getAssociatedUsername();


      if (authtokenUsername == null || eventUsername == null) {
        db.closeConnection(false);
        return new EventResponseSingle("Error: Invalid username", false);
      }

      if(!eventUsername.equals(authtokenUsername)){
        db.closeConnection(false);
        EventResponseSingle response = new EventResponseSingle("Error: password is wrong", false);
        return response;
      }


      Event event=eventDAO.find(request.getEventID());

      if (event == null) {
        db.closeConnection(false);
        return new EventResponseSingle("Error: Invalid EventID", false);
      }

      db.closeConnection(true);

      return new EventResponseSingle(event.getAssociatedUsername(), event.getEventID(), event.getPersonID(),
              event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(),
              event.getYear(), true);


    } catch (Exception e) {
      e.printStackTrace();
      db.closeConnection(false);
      EventResponseSingle response=new EventResponseSingle("Error: An error occurred while accessing the database", false);
      return response;
    }
  }
}
