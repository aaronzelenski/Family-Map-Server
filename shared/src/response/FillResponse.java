package response;

/**
 * response class for : FillResponse
 */

public class FillResponse {

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
   * constructor for FillResponse
   *
   * @param success tells whether the function was able to be performed
   * @param message two messages can occur, a success message or message describing failure
   */
  public FillResponse(String message, boolean success) {
    this.success=success;
    this.message=message;
  }

}
