package response;

import model.Event;
import model.Person;
import model.User;

/**
 * response class for : LoadResponse
 */

public class LoadResponse {
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  /**
   * two messages can occur, a success message or message describing failure
   */
  String message;

  public LoadResponse(boolean success, String message) {
    this.success=success;
    this.message=message;
  }





}
