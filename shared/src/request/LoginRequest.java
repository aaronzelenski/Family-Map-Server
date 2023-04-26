package request;


/**
 * request class for : LoginRequest
 */

public class LoginRequest {

  public LoginRequest() {
  }

  /**
   * the unique username for the user
   */
  String username;

  /**
   * password of the user
   */
  private String password;




  /**
   * constructor for LoginRequest
   *
   * @param username the unique username for the user
   * @param password password of the user
   */
  public LoginRequest(String username, String password) {
    this.username=username;
    this.password=password;
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
}
