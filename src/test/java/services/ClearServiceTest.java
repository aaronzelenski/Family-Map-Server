package services;

import dao.*;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClearServiceTest {

  private Database db;

  private Event bestEvent;
  private EventDAO eDao;
  private Person bestPerson;
  private PersonDAO pDao;
  private AuthToken bestAuthtoken;
  private AuthTokenDAO aDao;

  private UserDAO uDao;

  private User bestUser;


  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();

    bestEvent = new Event("Biking_123A", "Gale", "Gale123A",
            35.9f, 140.1f, "Japan", "Ushiku",
            "Biking_Around", 2016);

    bestPerson = new Person("abc123", "thisgirldebz", "Debbie",
            "Zelenski", "f", "def456", "ghi789",
            "lmn101112");

    bestAuthtoken = new AuthToken("1234567890", "the boogie man");

    bestUser = new User("thisguyaaron", "hello", "thisguyaaron@msn.com",
            "Aaron", "Zelenski", "m", "abc123");

    Connection conn = db.getConnection();
    eDao = new EventDAO(conn);
    pDao = new PersonDAO(conn);
    aDao = new AuthTokenDAO(conn);
    uDao = new UserDAO(conn);

    eDao.clear();
    pDao.clear();
    aDao.clear();
    uDao.clear();
  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }


  @Test
  public void clearPass() throws DataAccessException {
    eDao.insert(bestEvent);
    pDao.insert(bestPerson);
    aDao.insert(bestAuthtoken);
    uDao.insert(bestUser);

    eDao.clear();
    pDao.clear();
    aDao.clear();
    uDao.clear();

    assertNull(eDao.find(bestEvent.getEventID()));
    assertNull(pDao.find(bestPerson.getPersonID()));
    assertNull(aDao.find(bestAuthtoken.getAuthtoken()));
    assertNull(uDao.find(bestUser.getUsername()));
  }
}
