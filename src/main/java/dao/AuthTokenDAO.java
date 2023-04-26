package dao;

import model.AuthToken;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import java.sql.Connection;


/**
 * class for Data Access Object : Authtoken
 */

public class AuthTokenDAO {

  private final Connection conn;

  public AuthTokenDAO(Connection conn) {
    this.conn=conn;
  }

  /**
   * generates the authtoken of a given user
   *
   *
   * @return return Authtoken String (unique)
   */
  public String generateAuthtoken(){
    UUID uuid = java.util.UUID.randomUUID();

    return uuid.toString();
  }

  public void insert(AuthToken authToken) throws DataAccessException {
    //We can structure our string to be similar to a sql command, but if we insert question
    //marks we can change them later with help from the statement
    String sql = "INSERT INTO Auth_tokens (authtoken, username) VALUES(?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      //Using the statements built-in set(type) functions we can pick the question mark we want
      //to fill in and give it a proper value. The first argument corresponds to the first
      //question mark found in our sql String
      stmt.setString(1, authToken.getAuthtoken());
      stmt.setString(2, authToken.getUsername());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting an authtoken into the database");
    }
  }


  /**
   * finds person by authtoken
   *
   * @param authTokenID person that we are trying to find based off of authtoken
   */
  public AuthToken find(String authTokenID) throws DataAccessException{
    AuthToken authToken;
    ResultSet rs;
    String sql = "SELECT * FROM Auth_tokens WHERE authtoken = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, authTokenID);
      rs = stmt.executeQuery();
      if (rs.next()) {
        authToken = new AuthToken(rs.getString("authtoken"), rs.getString("username"));
        return authToken;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding an event in the database");
    }



  }

  /**
   * clears authokens
   */
  public void clear() throws DataAccessException {
    String sql = "DELETE FROM Auth_tokens";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the Auth_tokens table");
    }
  }



  public String RetrieveAuthtoken(String newAuthToken) throws DataAccessException {
    AuthToken authToken;
    ResultSet rs;
    String sql = "SELECT * FROM Auth_tokens WHERE authtoken = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, newAuthToken);
      rs = stmt.executeQuery();
      if (rs.next()) {
        authToken = new AuthToken(rs.getString("authToken"), rs.getString("username"));
        return authToken.getUsername();
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding a username in the database");
    }
  }



}
