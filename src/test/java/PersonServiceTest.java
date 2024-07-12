import com.revature.DAO.PersonDAO;
import com.revature.Exception.PersonException;
import com.revature.Model.Person;
import com.revature.Service.PersonService;
import com.revature.Util.ConnectionSingleton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Connection;

/**
 * Unit testing is the testing of a single 'unit' of code (ie,  a single functionality,
 * usually is a single method.)
 */
public class PersonServiceTest {
    Connection conn;
    PersonDAO personDAO;
    PersonService personService;

    /**
     * Let's use a REAL personService, which is reliant on a FAKE personDAO.
     */
    @Before
    public void setUp(){
        personDAO = Mockito.mock(PersonDAO.class);
        personService = new PersonService(personDAO);
    }
    /**
     * mocking is the act of providing completely fake behavior to some
     * object, so that we can check how the rest of our code behaves in a
     * predefined scenario.
     *
     * it allows us to isolate the code we're testing to just a specific layer
     * of code. eg, we can test our service layer and mock the dao to make sure that
     * we're just testing service logic, without also having to worry about the
     * correct behavior of the dao.
     *
     * the idea is that we can write tests individually for each service, mock everything,
     * and then when we combine the full functionality in an end-to-end test, everything
     * will be formally proven to work.
     */
    /**
     * test that when no person with a specific id already exists, we should be able
     * to add a person.
     *
     * (happy path)
     */
    @Test
    public void personServiceAddPersonTest(){
//        arrange our values
//        let's setup our scenario : assume that no person with id 1 in persondao
        Mockito.when(personDAO.getPersonById(1)).thenReturn(null);
//        act by invoking the methods that we want to test the behavior of
//        run addperson using some test values
        try {
            personService.addPerson(new Person(1, "test", true, 111));
        }catch (PersonException e){
            Assert.fail("the personexception should not have been thrown.");
        }
//        assert by verifying the values produced are appropriate
//        the addperson method should have been invoked once with some person value
        Mockito.verify(personDAO, Mockito.times(1)).addPerson(Mockito.any());
    }
    /**
     * testing the inverse situation: assuming that the person with id 1 did already exist
     */
    @Test
    public void personAlreadyExistsTest(){
//        arrange
        Mockito.when(personDAO.getPersonById(1)).thenReturn(new Person(1, "test", true, 111));
//        act
        try{
            personService.addPerson(new Person(1, "test", true, 111));
        }catch (PersonException e){
//            expected to reach this catch block
        }
//        assert (that no person was attempted to be added)
        Mockito.verify(personDAO, Mockito.times(0)).addPerson(Mockito.any());
    }

}
