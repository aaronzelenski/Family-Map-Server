package model;

/**
 * Model class for : AuthToken
 */
public class AuthToken {
  /**
   * the unique token associated with the user
   */
  private String authtoken;

  /**
   * the unique username for the user
   */
  private String username;

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


  public AuthToken(String authtoken, String username) {
    this.authtoken=authtoken;
    this.username=username;
  }
}
