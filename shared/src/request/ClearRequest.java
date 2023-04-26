package request;

/**
 * request class for : ClearRequest
 */

public class ClearRequest {

  /**
   * tells whether the function was able to be performed
   */
  boolean success;

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;


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
   * constructor for ClearRequest
   *
   * @param success tells whether the function was able to be performed
   * @param message two messages can occur, a success message or message describing failure
   */



  public ClearRequest(boolean success, String message) {
    this.success=success;
    this.message=message;
  }
}
