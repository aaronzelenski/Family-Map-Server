package dao;

import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import java.util.UUID;

/**
 * class for Data Access Object : person
 */

public class PersonDAO {


  private final Connection conn;

  public PersonDAO(Connection conn) {
    this.conn=conn;
  }


  /**
   * inserts person into database
   *
   * @param person person we are inserting
   */
  public void insert(Person person) throws DataAccessException {


    String sql = "INSERT INTO Persons (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1,person.getPersonID());
      stmt.setString(2,person.getAssociatedUsername());
      stmt.setString(3,person.getFirstName());
      stmt.setString(4,person.getLastName());
      stmt.setString(5,person.getGender());
      stmt.setString(6,person.getFatherID());
      stmt.setString(7,person.getMotherID());
      stmt.setString(8,person.getSpouseID());

      stmt.executeUpdate();
    }catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting a Person into the database");
    }
  }

  /**
   * finds person in database
   *
   * @param personID personID we are trying to find
   */
  public Person find(String personID) throws DataAccessException {
    Person person;
    ResultSet rs;
    String sql = "SELECT * FROM Persons WHERE personID = ?;";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, personID);
      rs = stmt.executeQuery();
      if (rs.next()) {
        person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                rs.getString("fatherID"), rs.getString("motherID") , rs.getString("spouseID"));
        return person;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding a person in the database");
    }

  }

  public ArrayList<Person> RetrieveAllPeople(String username) throws DataAccessException{
    ArrayList<Person> people = new ArrayList<>();

    String sql = "select personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID from Persons";


    try (PreparedStatement statement = conn.prepareStatement(sql)){
      ResultSet rs = statement.executeQuery();

      while(rs.next()){
        String personID = rs.getString(1);
        String associatedUsername = rs.getString(2);
        String firstName = rs.getString(3);
        String lastName = rs.getString(4);
        String gender = rs.getString(5);
        String fatherID = rs.getString(6);
        String motherID = rs.getString(7);
        String spouseID = rs.getString(8);

        if(associatedUsername.equals(username)){
          people.add(new Person(personID, associatedUsername,firstName,lastName,gender,fatherID,motherID,spouseID));
        }
      }

      return people;

    }catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while retrieving Person array from Person table");
    }
  }

  /**
   * clears person (singular)
   *
   * @param username the username for the person we want to clear.
   */
  public void clearPersonSingular(String username){

  }

  /**
   * clear all people based upon the username
   */

  public void clear() throws DataAccessException {
    String sql = "DELETE FROM Persons";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the Person table");
    }
  }


  public String generatePersonID(){
    UUID uuid = java.util.UUID.randomUUID();
    return uuid.toString();
  }


  public void UsernameDelete(String username) throws DataAccessException {

    String sql = "DELETE FROM Persons WHERE associatedUsername = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, username);

      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while attempting to clear people with associated username" + username);
    }
  }



}
