package dao;

// Dao classes are the only classes that can interact with the database


import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * class for Data Access Object : User
 */
public class UserDAO {

  private final Connection conn;

  public UserDAO(Connection conn) {
    this.conn=conn;
  }


  /**
   * inserts person into database
   *
   * @param user person we are inserting
   */
  public void insert(User user) throws DataAccessException {
    String sql =  "INSERT INTO Users (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      //Using the statements built-in set(type) functions we can pick the question mark we want
      //to fill in and give it a proper value. The first argument corresponds to the first
      //question mark found in our sql String
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getEmail());
      stmt.setString(4, user.getFirstName());
      stmt.setString(5, user.getLastName());
      stmt.setString(6, user.getGender());
      stmt.setString(7, user.getPersonID());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting a user into the database");
    }
  }

  /**
   * finds person in database
   *
   * @param username  we are trying to find
   */
  public User find(String username) throws DataAccessException {
    User user;
    ResultSet rs;

    String sql = "SELECT * FROM Users WHERE username = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, username);
      rs = stmt.executeQuery();
      if (rs.next()) {
        user = new User(rs.getString("username"), rs.getString("password"),
                rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"),
                rs.getString("gender"), rs.getString("personID"));
        return user;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding an event in the database");
    }
  }

  /**
   * clears all users
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM Users";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the user table");
    }
  }


  public void UsernameDelete(String username) throws DataAccessException {

    String sql = "DELETE FROM Users WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, username);

      stmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while attempting to clear people with associated username" + username);
    }
  }


}
