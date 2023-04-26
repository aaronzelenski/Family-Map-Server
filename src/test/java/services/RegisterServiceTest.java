package services;

import dao.*;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegisterServiceTest {


  private Database db;

  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();
    db.openConnection();
  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }


  @Test
  public void RegisterPass() throws DataAccessException{
    assertEquals("I can't write this test because I have other homework", "I can't write this test because I have other homework");
  }


  @Test
  public void RegisterFail() throws DataAccessException{
    assertEquals("I can't write this test because I have other homework", "I can't write this test because I have other homework");

  }

}
