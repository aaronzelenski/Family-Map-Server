package request;

import model.*;

/**
 * request class for : LoadRequest
 */

public class LoadRequest {

  /**
   * json array of User objects
   */
  User[] users;

  /**
   * json array of Person objects
   */
  Person[] persons;


  boolean success;

  public LoadRequest() {
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }

  /**
   * json array of Event objects
   */
  Event[] events;

  public User[] getUsers() {
    return users;
  }

  public void setUsers(User[] users) {
    this.users=users;
  }

  public Person[] getPersons() {
    return persons;
  }

  public void setPersons(Person[] persons) {
    this.persons=persons;
  }

  public Event[] getEvents() {
    return events;
  }

  public void setEvents(Event[] events) {
    this.events=events;
  }

  /**
   * constructor for LoadRequest
   *
   * @param users json array of User objects
   * @param persons json array of Person objects
   * @param events json array of Event objects
   */
  public LoadRequest(User[] users, Person[] persons, Event[] events) {
    this.users=users;
    this.persons=persons;
    this.events=events;
  }
}
