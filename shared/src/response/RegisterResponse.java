package response;

/**
 * response class for : RegisterResponse
 */

public class RegisterResponse {

  /**
   * the unique token associated with the user
   */

  String authtoken;

  /**
   * username for the specific person
   */
  String username;

  /**
   * unique ID for the person
   */
  String personID;


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


  /**
   * constructor for RegisterResponse
   *
   * @param authtoken the unique token associated with the user
   * @param username username for the specific person
   * @param personID unique ID for the person
   * @param success tells whether is successful or not.
   *
   */

  public RegisterResponse(boolean success, String authtoken, String username, String personID) {
    this.authtoken=authtoken;
    this.username=username;
    this.personID=personID;
    this.success=success;
  }

  public RegisterResponse(boolean success, String message) {
    this.success=success;
    this.message=message;
  }

  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }
}
