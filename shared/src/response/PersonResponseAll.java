package response;

import model.Person;

import java.util.ArrayList;

public class PersonResponseAll {

  /**
   * json array of Person objects
   */
  Person[] data;




  /**
   * tells whether the function was able to be performed
   */
  boolean success;

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

  public Person[] getPersons() {
    return data;
  }

  public void setPersons(Person[] persons) {
    this.data=persons;
  }

  ArrayList<Person> persons;

  public void setPersonsArray(ArrayList<Person> persons) {
    this.persons=persons;
  }

  public PersonResponseAll(ArrayList<Person> persons, boolean success) {
    this.data=persons.toArray(new Person[0]);
    this.success=success;
  }

  public PersonResponseAll(String message, boolean success) {
    this.message=message;
    this.success=success;
  }
}
