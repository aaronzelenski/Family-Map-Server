package dao;

import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;


public class PersonDAOTest {

  private Database db;
  private Person bestPerson;
  private PersonDAO pDAO;

  @BeforeEach
  public void setUp() throws DataAccessException {
    db = new Database();

    bestPerson = new Person("abc123", "thisgirldebz", "Debbie",
            "Zelenski", "f", "def456", "ghi789",
            "lmn101112");

    Connection conn = db.getConnection();
    pDAO = new PersonDAO(conn);
    pDAO.clear();
  }

  @AfterEach
  public void tearDown() {
    db.closeConnection(false);
  }


  @Test
  public void insertPass() throws DataAccessException {
    pDAO.insert(bestPerson);
    Person compareTest = pDAO.find(bestPerson.getPersonID());
    assertNotNull(compareTest);
    assertEquals(bestPerson, compareTest);
  }


  @Test
  public void insertFail() throws DataAccessException {
    pDAO.insert(bestPerson);
    assertThrows(DataAccessException.class, () -> pDAO.insert(bestPerson));
  }


  @Test
  public void findPass() throws DataAccessException {
    pDAO.insert(bestPerson);
    Person compareTest = pDAO.find(bestPerson.getPersonID());
    assertNotNull(compareTest);
    assertEquals(bestPerson, compareTest);


  }

  @Test
  public void findFail() throws DataAccessException {
    pDAO.insert(bestPerson);
    assertNull(pDAO.find("sdfghsdifu"));
  }

  @Test
  public void clearPass() throws DataAccessException {
    pDAO.insert(bestPerson);
    pDAO.clear();

    assertNull(pDAO.find(bestPerson.getPersonID()));

  }

}


//