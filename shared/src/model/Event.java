package model;

import java.util.Objects;


/**
 * Model class for : Event
 */
public class Event {

  /**
   * unique event ID
   */
  private String eventID;

  /**
   * the username associated with the event
   */
  private String associatedUsername;

  /**
   * the person associated with the event.
   */
  private String personID;

  /**
   * latitude of where the certain event took place
   */
  private Float latitude;

  /**
   * longitude of where the certain event took place
   */
  private Float longitude;

  /**
   * country of where the certain event took place
   */
  private String country;

  /**
   * city of where the certain event took place
   */
  private String city;

  /**
   * the type of event that took place
   */
  private String eventType;

  /**
   * the year the event took place
   */
  private Integer year;

  public Event(String eventID, String username, String personID, Float latitude, Float longitude,
               String country, String city, String eventType, Integer year) {
    this.eventID = eventID;
    this.associatedUsername = username;
    this.personID = personID;
    this.latitude = latitude;
    this.longitude = longitude;
    this.country = country;
    this.city = city;
    this.eventType = eventType;
    this.year = year;
  }

  public Event() {

  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID = eventID;
  }

  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername = associatedUsername;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID = personID;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude = latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude = longitude;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType = eventType;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Event event = (Event) o;
    return Objects.equals(eventID, event.eventID) && Objects.equals(associatedUsername, event.associatedUsername) && Objects.equals(personID, event.personID) && Objects.equals(latitude, event.latitude) && Objects.equals(longitude, event.longitude) && Objects.equals(country, event.country) && Objects.equals(city, event.city) && Objects.equals(eventType, event.eventType) && Objects.equals(year, event.year);
  }

}
