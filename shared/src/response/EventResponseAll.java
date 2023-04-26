package response;

import model.*;

import java.util.ArrayList;

/**
 * response class for : EventResponseAll
 */

public class EventResponseAll {




  /**
   * json array of Event objects
   */
  Event[] data;


  /**
   * tells whether the function was able to be performed
   */
  boolean success;

  public EventResponseAll() {

  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;


  /**
   * constructor for EventResponseAll
   *
   * @param events json array of Event objects
   * @param success tells whether the function was able to be performed
   */
  public EventResponseAll(Event[] events, boolean success) {
    this.data=events;
    this.success=success;
  }

  public EventResponseAll(String message, boolean success) {
    this.success=success;
    this.message=message;
  }

  public EventResponseAll(ArrayList<Event> events, boolean success) {
    this.data=events.toArray(new Event[0]);
    this.success=success;
  }


  public Event[] getData() {
    return data;
  }

  public void setData(Event[] data) {
    this.data=data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }
}
