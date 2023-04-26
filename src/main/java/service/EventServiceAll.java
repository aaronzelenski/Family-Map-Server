package service;

import dao.*;
import model.Event;
import request.EventRequestAll;
import response.*;

import java.util.ArrayList;


/**
 * service class for : EventAllServer
 */

public class EventServiceAll {

  public EventServiceAll() throws DataAccessException {
  }

  /**
   * service for function: EventAll
   *
   * @param request clear request
   * @return clear response
   */
  public EventResponseAll EventAll(EventRequestAll request){

    Database db = new Database();
    try{
      db.openConnection();

      AuthTokenDAO authTokenDAO = new AuthTokenDAO(db.getConnection());
      EventDAO eventDAO = new EventDAO(db.getConnection());
      String username = authTokenDAO.RetrieveAuthtoken(request.getAuthToken());

      if(username == null){
        db.closeConnection(false);
        return new EventResponseAll("Error: Invalid username", false);
      }


      ArrayList<Event> events = eventDAO.RetrieveAllEvents(username);

      if(events == null){
        db.closeConnection(false);
        return new EventResponseAll("Error: events are null", false);
      }


      db.closeConnection(true);

      return new EventResponseAll(events, true);

    } catch (Exception e) {
      e.printStackTrace();
      db.closeConnection(false);
      EventResponseAll response = new EventResponseAll("Error: Error encountered while attempting to access data from database", false);
      return response;
    }
  }
}
