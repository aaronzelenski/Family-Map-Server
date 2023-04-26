package model;

import java.util.Objects;

/**
 * Model class for : Person
 */
public class Person {

  /**
   * unique ID for the person
   */
  private String personID;

  /**
   * username for the specific person
   */
  private String associatedUsername;

  /**
   * first name of the person
   */
  private String firstName;

  /**
   * last name of the person
   */
  private String lastName;

  /**
   * gender of the person
   */
  private String gender;

  /**
   * unique ID for the father
   */
  private String fatherID;

  /**
   * unique ID for the mother
   */
  private String motherID;

  /**
   * unique ID for the spouse
   */
  private String spouseID;


  public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
    this.personID=personID;
    this.associatedUsername=associatedUsername;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
  }


  public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
    this.personID=personID;
    this.associatedUsername=associatedUsername;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
  }

  public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID) {
    this.personID=personID;
    this.associatedUsername=associatedUsername;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
  }

  public Person() {

  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName=firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName=lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender=gender;
  }

  public String getFatherID() {
    return fatherID;
  }

  public void setFatherID(String fatherID) {
    this.fatherID=fatherID;
  }

  public String getMotherID() {
    return motherID;
  }

  public void setMotherID(String motherID) {
    this.motherID=motherID;
  }

  public String getSpouseID() {
    return spouseID;
  }

  public void setSpouseID(String spouseID) {
    this.spouseID=spouseID;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Person person=(Person) o;

    if (!Objects.equals(personID, person.personID)) return false;
    if (!Objects.equals(associatedUsername, person.associatedUsername))
      return false;
    if (!Objects.equals(firstName, person.firstName)) return false;
    if (!Objects.equals(lastName, person.lastName)) return false;
    if (!Objects.equals(gender, person.gender)) return false;
    if (!Objects.equals(fatherID, person.fatherID)) return false;
    if (!Objects.equals(motherID, person.motherID)) return false;
    return Objects.equals(spouseID, person.spouseID);
  }

}
