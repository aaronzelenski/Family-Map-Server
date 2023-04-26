package response;


/**
 * response class for : PersonIDResponse
 */

public class PersonResponseSingle {

  /**
   * username for the specific person
   */
  String associatedUsername;


  /**
   * unique ID for the person
   */
  String personID;

  /**
   * first name of the person
   */
  String firstName;

  /**
   * last name of the person
   */
  String lastName;

  /**
   * gender of the person
   */
  String gender;

  /**
   * unique ID for the father
   */
  String fatherID; // OPTIONAL, can be missing

  /**
   * unique ID for the mother
   */
  String motherID; // OPTIONAL, can be missing

  /**
   * unique ID for the spouse
   */
  String spouseID; // OPTIONAL, can be missing

  /**
   * tells whether the function was able to be performed
   */
  boolean success;

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;


  /**
   * constructor for PersonIDResponse
   *
   * @param associatedUsername username for the specific person
   * @param personID unique ID for the person
   * @param firstName first name of the person
   * @param lastName last name of the person
   * @param gender gender of the person
   * @param fatherID unique ID for the father
   * @param motherID unique ID for the mother
   * @param spouseID unique ID for the spouse
   * @param success tells whether the function was able to be performed
   */

  public PersonResponseSingle(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
    this.associatedUsername=associatedUsername;
    this.personID=personID;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
    this.success=success;
  }

  public PersonResponseSingle(String message, boolean success){
    this.success=success;
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }
}
