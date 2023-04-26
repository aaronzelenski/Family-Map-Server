package dao;

import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

  private Database db;
  private User bestUser;
  private UserDAO uDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();

    bestUser = new User("thisguyaaron", "starcraft", "thisguyaaron@msn.com",
            "Aaron", "Zelenski", "m", "abc123");

    Connection conn = db.getConnection();
    uDAO = new UserDAO(conn);
    uDAO.clear();
  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(true);
  }


  @Test
  public void insertPass() throws DataAccessException {
    uDAO.insert(bestUser);
    User compareTest = uDAO.find(bestUser.getUsername());
    assertNotNull(compareTest);
    assertEquals(bestUser, compareTest);

  }

  @Test
  public void insertFail() throws DataAccessException {
    uDAO.insert(bestUser);
    assertThrows(DataAccessException.class, () -> uDAO.insert(bestUser));
  }

  @Test
  public void findPass() throws DataAccessException {
    uDAO.insert(bestUser);
    User compareTest = uDAO.find(bestUser.getUsername());
    assertNotNull(compareTest);
    assertEquals(bestUser, compareTest);
  }

  @Test
  public void findFail() throws DataAccessException {
    uDAO.insert(bestUser);
    assertNull(uDAO.find("sdfgsadfsdhhsdifu"));

  }

  @Test
  public void clearPass() throws DataAccessException {
    uDAO.insert(bestUser);
    uDAO.clear();

    assertNull(uDAO.find(bestUser.getUsername()));
  }



}
