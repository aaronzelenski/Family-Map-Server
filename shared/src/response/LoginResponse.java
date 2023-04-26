package response;

/**
 * response class for : LoginResponse
 */

public class LoginResponse {

  public LoginResponse() {

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
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

  /**
   * the unique token associated with the user
   */
  String authtoken;

  /**
   * the unique username for the user
   */
  String username;

  /**
   * password of the user
   */
  private String password;

  /**
   * username for the specific person
   */
  String personID;

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }

  /**
   * tells whether the function was able to be performed
   */
  boolean success;

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;


  /**
   * constructor for LoginResponse
   *
   * @param authtoken the unique token associated with the user
   * @param username the unique username for the user
   * @param personID username for the specific person
   * @param success tells whether the function was able to be performed
   */
  public LoginResponse(String authtoken, String username, String personID, boolean success) {
    this.authtoken=authtoken;
    this.username=username;
    this.personID=personID;
    this.success=success;
  }

  public LoginResponse(boolean success, String message){
    this.success=success;
    this.message=message;
  }

}
