package generate;

import com.google.gson.Gson;
import dao.*;
import data.Location;
import data.LocationData;
import data.Names;
import model.Event;
import model.Person;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.Random;
import java.util.UUID;

public class Generator {


  private int numOfPeople = 0;
  private int numOfEvents = 0;

  private static String[] fnames;
  private static String[] mnames;
  private static String[] snames;

  private final Connection connection;

  AuthTokenDAO authTokenDAO;
  UserDAO userDAO;
  EventDAO eventDAO;
  PersonDAO personDAO;


  public Generator(Connection connection) throws DataAccessException {
    this.connection = connection;


    eventDAO = new EventDAO(connection);
    personDAO = new PersonDAO(connection);


    mnames = createName("json/mnames.json");
    fnames = createName("json/fnames.json");
    snames = createName("json/snames.json");
  }


  public String generatePersonID() {
    UUID uuid=UUID.randomUUID();
    return uuid.toString();
  }

  public String generateEventID() throws DataAccessException {
    UUID uuid=UUID.randomUUID();
    return uuid.toString();
  }

  public String[] createName(String jsonFilePath) throws DataAccessException {

    try{
      Gson gson = new Gson();

      Names names;

      Reader reader = new FileReader(jsonFilePath);
      names = gson.fromJson(reader, Names.class);

      return names.getData();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new DataAccessException("Error while attempting to load the name files");
    }

  }




  Person createPerson(String userName, String gender, String lastName, int seedYear ) {
    Person person=new Person();

    String uuid = generatePersonID();
    String personID=uuid;

    person.setAssociatedUsername(userName);
    person.setGender(gender);
    person.setPersonID(personID);



    Random random = new Random();
    int sLength = snames.length;
    int mLength = mnames.length;
    int fLength = fnames.length;


    if (lastName.equals("")) {
      person.setLastName(snames[random.nextInt(sLength)]);
    } else {
      person.setLastName(lastName);
    }


    if (person.getGender().equals("f")) {
      person.setFirstName(fnames[random.nextInt(fLength)]);
    }

    if (person.getGender().equals("m")) {
      person.setFirstName(mnames[random.nextInt(mLength)]);
    }

    Event birth=eventGenerator("Birth", userName, personID, seedYear);

    try {
      eventDAO.insert(birth);

    } catch (DataAccessException e) {
      e.printStackTrace();
    }

    return person;
  }


  public void generateFamily(int generations, int seedYear, Person person) throws DataAccessException {
    try {

      generatePerson(person, generations, person.getAssociatedUsername(), person.getLastName(), seedYear);

      Event birth=eventGenerator("Birth", person.getAssociatedUsername(), person.getPersonID(), seedYear);
      eventDAO.insert(birth);
    } catch (DataAccessException e) {
      e.printStackTrace();
    }
  }

  public void generatePerson(Person person, int generations, String userName, String lastName, int seedYear) throws DataAccessException {

    Person mother;
    Person father;

    if(person == null) {
      person = createPerson(userName, "m",  lastName, seedYear);

    }

    if (generations >= 1) {
      mother = createPerson(userName, "f", "",seedYear - 25);
      father = createPerson(userName, "m", lastName, seedYear - 25);


      mother.setSpouseID(father.getPersonID());
      father.setSpouseID(mother.getPersonID());

      person.setMotherID(mother.getPersonID());
      person.setFatherID(father.getPersonID());


      String uuid = generatePersonID();

      Event motherMarriage = eventGenerator("Marriage", userName, mother.getPersonID(), seedYear + 20);
      Event fatherMarriage = new Event(uuid, userName, father.getPersonID(), motherMarriage.getLatitude(), motherMarriage.getLongitude(), motherMarriage.getCountry(), motherMarriage.getCity(), "Marriage", motherMarriage.getYear());


      eventDAO.insert(motherMarriage);
      eventDAO.insert(fatherMarriage);

      Event motherDeath = eventGenerator("Death", userName, mother.getPersonID(), seedYear + 50);
      Event fatherDeath = eventGenerator("Death", userName, father.getPersonID(), seedYear + 50);

      eventDAO.insert(motherDeath);
      eventDAO.insert(fatherDeath);

      personDAO.insert(person);


      generatePerson(father, generations-1, userName, lastName, seedYear-25);
      generatePerson(mother,generations-1, userName, mother.getLastName(), seedYear-25);

    }
    else {
      personDAO.insert(person);
    }
  }

  Event eventGenerator(String eventType, String userName, String personID, int seedYear) {
    try {

      Gson gson=new Gson();

      Reader reader=new FileReader("json/locations.json");
      LocationData data=gson.fromJson(reader, LocationData.class);

      Random random=new Random();
      int length=data.getData().length;
      Location location=data.getData()[random.nextInt(length)];

      Event event=new Event();

      String uuid = generateEventID();

      String eventID=uuid;

      int year=random.nextInt(5) + seedYear;

      event.setEventID(eventID);
      event.setPersonID(personID);
      event.setAssociatedUsername(userName);
      event.setEventType(eventType);
      event.setYear(year);
      event.setCity(location.getCity());
      event.setLatitude(location.getLatitude());
      event.setLongitude(location.getLongitude());
      event.setCountry(location.getCountry());

      return event;


    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
      System.out.println("Error accessing Json files");
      return null;
    } catch (DataAccessException e) {
      throw new RuntimeException(e);
    }
  }



}
