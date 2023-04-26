package response;

/**
 * response class for : ClearResponse
 */

public class ClearResponse {

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
   * constructor for ClearResponse
   *
   * @param success tells whether the function was able to be performed
   * @param message two messages can occur, a success message or message describing failure
   */

  public ClearResponse(boolean success, String message) {
    this.success=success;
    this.message=message;
  }
}
