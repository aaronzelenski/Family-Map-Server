package dao;


import model.Event;

import java.sql.*;
import java.util.ArrayList;

/**
 * class for Data Access Object : Event
 */

public class EventDAO {
  private final Connection conn;

  public EventDAO(Connection conn) {
    this.conn = conn;
  }

  /**
   * inserts an event
   *
   * @param event event to be inserted
   * @throws DataAccessException
   */

  public void insert(Event event) throws DataAccessException {
    //We can structure our string to be similar to a sql command, but if we insert question
    //marks we can change them later with help from the statement
    String sql = "INSERT INTO Events (eventID, associatedUsername, personID, latitude, longitude, " +
            "country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      //Using the statements built-in set(type) functions we can pick the question mark we want
      //to fill in and give it a proper value. The first argument corresponds to the first
      //question mark found in our sql String
      stmt.setString(1, event.getEventID());
      stmt.setString(2, event.getAssociatedUsername());
      stmt.setString(3, event.getPersonID());
      stmt.setFloat(4, event.getLatitude());
      stmt.setFloat(5, event.getLongitude());
      stmt.setString(6, event.getCountry());
      stmt.setString(7, event.getCity());
      stmt.setString(8, event.getEventType());
      stmt.setInt(9, event.getYear());

      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while inserting an event into the database");
    }
  }


  /**
   * find a certain event that took place
   *
   * @param eventID
   * @return event
   * @throws DataAccessException
   */

  public Event find(String eventID) throws DataAccessException {
    Event event;
    ResultSet rs;
    String sql = "SELECT * FROM Events WHERE eventID = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, eventID);
      rs = stmt.executeQuery();
      if (rs.next()) {
        event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                rs.getInt("year"));
        return event;
      } else {
        return null;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding an event in the database");
    }

  }

  /**
   * clears events
   *
   * @throws DataAccessException
   */

  public void clear() throws DataAccessException {
    String sql = "DELETE FROM Events";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while clearing the event table");
    }
  }


  /**
   * returns events based upon the username
   *
   * @param username the person's username
   * @return list of events
   */

  public ArrayList<Event> RetrieveAllEvents(String username) throws DataAccessException{
    ArrayList<Event> event = new ArrayList<>();

    String sql = "select eventID, associatedUsername, personID, latitude, longitude, country, city, eventType, year from Events";


    try (PreparedStatement statement = conn.prepareStatement(sql)){
      ResultSet rs = statement.executeQuery();

      while(rs.next()){
        String eventID = rs.getString(1);
        String associatedUsername = rs.getString(2);
        String personID = rs.getString(3);
        Float latitude = rs.getFloat(4);
        Float longitude = rs.getFloat(5);
        String country = rs.getString(6);
        String city = rs.getString(7);
        String eventType = rs.getString(8);
        Integer year = rs.getInt(9);


        if(associatedUsername.equals(username)){
          event.add(new Event(eventID, associatedUsername,personID,latitude,longitude,country,city,eventType, year));
        }
      }
      return event;

    }catch (SQLException ex) {
      ex.printStackTrace();
      throw new DataAccessException("Error encountered while retrieving Event array from Events table");
    }
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
