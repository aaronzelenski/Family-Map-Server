package request;

import model.Event;

/**
 * request class for : EventRequestAll
 */

public class EventRequestAll {

  private String authToken;

  public EventRequestAll() {

  }


  public String getAuthToken() {
    return authToken;
  }


  String personID;

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  /**
   * json array of Event objects
   */
  Event[] data;

  /**
   * tells whether the function was able to be performed
   */
  boolean success;

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;

  public Event[] getData() {
    return data;
  }

  public void setData(Event[] data) {
    this.data=data;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  /**
   * constructor for EventRequestAll
   *
   * @param data json array of Event objects
   * @param success tells whether the function was able to be performed
   * @param message two messages can occur, a success message or message describing failure
   */
  public EventRequestAll(Event[] data, boolean success, String message) {
    this.data=data;
    this.success=success;
    this.message=message;
  }

  public EventRequestAll(String authToken) {
    this.authToken=authToken;
  }


}
