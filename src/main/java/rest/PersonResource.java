package rest;

import entity.Person;
import facade.Facade;
import facade.IPersonFacade;
import java.util.List;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utility.JSONConverter;

/**
 * REST Web Service
 *
 * @author bearu
 */
@Path("person")
public class PersonResource {

    private IPersonFacade facade = new Facade();
    private JSONConverter converter = new JSONConverter();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
        //Persistence.generateSchema("pu", null);
        facade.addEntityManagerFactory(Persistence.createEntityManagerFactory("pu"));

    }

    /**
     * Method to return every person object on the mySQL database.
     *
     * @return A list with every object in JSON format.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersons() {

        List<Person> people = facade.getPersons();
        return converter.getJSONFromPerson(people);

    }

    /**
     * Method used to return a specific user by ID.
     *
     * @param id The ID of what user needs to be fetched.
     * @return Returns a JSON object with the given ID.
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") int id) {

        Person person = facade.getPerson(id);
        return converter.getJSONFromPerson(person);

    }

    /**
     * Method used to add a person to the database.
     *
     * @param jsonPerson String containing the new user data in JSON format.
     * @return Returns the user just added - kind a ;)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addPerson(String json) {

        Person person = converter.getPersonFromJson(json);

        facade.addPerson(person);

        return json;

    }

    /**
     * Method used to edit a person
     *
     * @param json takes the person to be edited in json format
     * @return returns the edited person
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String editPerson(String json) {

        Person p = facade.editPerson(converter.getPersonFromJson(json));

        return converter.getJSONFromPerson(p);

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deletePerson(@PathParam("id") int id) {

        facade.deletePerson(id);

        return "Successfully deleted person with id: " + id;

    }

}
