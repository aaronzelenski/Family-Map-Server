package request;

/**
 * request class for : RegisterRequest
 */

public class RegisterRequest {

  public RegisterRequest() {
  }

  /**
   * username for the user
   */
  String username;

  /**
   * password of the user
   */
  String password;

  /**
   * unique email of the user
   */
  String email;

  /**
   * first name of the user
   */
  String firstName;

  /**
   * last name of the user
   */
  String lastName;

  /**
   * gender of the user (either 'm' or 'f')
   */
  String gender;

  /**
   * constructor for RegisterRequest
   *
   * @param username username for the user
   * @param password password of the user
   * @param email unique email of the user
   * @param firstName first name of the user
   * @param lastName last name of the user
   * @param gender gender of the user (either 'm' or 'f')
   */
  public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
    this.username=username;
    this.password=password;
    this.email=email;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email=email;
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
}
