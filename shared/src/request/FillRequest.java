package request;

/**
 * request class for : FillRequest
 */

public class FillRequest {

  int generations;
  String username;

  public FillRequest(String username) {
    this.username=username;
  }

  public FillRequest() {

  }

  public int getGenerations() {
    return generations;
  }

  public void setGenerations(int generations) {
    this.generations=generations;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
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
   * tells whether the function was able to be performed
   */
  boolean success;

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;


  /**
   * constructor for FillRequest
   *
   * @param success tells whether the function was able to be performed
   * @param message two messages can occur, a success message or message describing failure
   */
  public FillRequest(boolean success, String message) {
    this.success=success;
    this.message=message;
  }

  public FillRequest(String username, int generations) {
    this.username=username;
    this.generations=generations;
  }

}
