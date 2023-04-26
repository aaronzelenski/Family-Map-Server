package request;

/**
 * request class for : EventRequestSingle
 */

public class EventRequestSingle {

  private String authToken;
  /**
   * unique event ID
   */
  private String eventID;

  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

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

  /**
   * tells whether the function was able to be performed
   */
  boolean success;

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
  }

  public String getAssociatedUsername() {
    return associatedUsername;
  }

  public void setAssociatedUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public Float getLatitude() {
    return latitude;
  }

  public void setLatitude(Float latitude) {
    this.latitude=latitude;
  }

  public Float getLongitude() {
    return longitude;
  }

  public void setLongitude(Float longitude) {
    this.longitude=longitude;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country=country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city=city;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType=eventType;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year=year;
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
   * constructor for EventRequestSingle
   *
   * @param eventID unique event ID
   * @param associatedUsername the username associated with the event
   * @param latitude latitude of where the certain event took place
   * @param longitude longitude of where the certain event took place
   * @param country city of where the certain event took place
   * @param city city of where the certain event took place
   * @param eventType the type of event that took place
   * @param year tells whether the function was able to be performed
   * @param success tells whether the function was able to be performed
   * @param message two messages can occur, a success message or message describing failure
   */

  public EventRequestSingle(String eventID, String associatedUsername, String personID, Float latitude, Float longitude, String country, String city, String eventType, Integer year, boolean success, String message) {
    this.eventID=eventID;
    this.associatedUsername=associatedUsername;
    this.personID=personID;
    this.latitude=latitude;
    this.longitude=longitude;
    this.country=country;
    this.city=city;
    this.eventType=eventType;
    this.year=year;
    this.success=success;
    this.message=message;
  }


  public EventRequestSingle(String authToken, String eventID) {
    this.authToken=authToken;
    this.eventID=eventID;
  }

  public EventRequestSingle(String authToken) {
    this.authToken=authToken;
  }
}
