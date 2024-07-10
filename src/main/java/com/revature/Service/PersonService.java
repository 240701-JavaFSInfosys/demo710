package com.revature.Service;

import com.revature.DAO.PersonDAO;
import com.revature.Model.Person;

import java.util.List;

/**
 * A service class is used to perform actions that are programming-logic related
 * (business logic) that are not applicable strictly to interacting with the
 * view/presentation or for data interaction
 *
 * Good examples:
 * input validation (making sure that the data is OK for xyz operation)
 * logging (log all behavior to help us catch bugs later)
 * side effects (eg adding a new user triggers the sending of a text to that user)
 *
 * By convention, the view layer never interacts directly with the DAO. They always
 * go through the service, even when it's not strictly necessary.
 *
 * n-layer architecture (you realistically can have any amount of service classes)
 */
public class PersonService {
    PersonDAO personDAO;
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> getAllPerson(){
        List<Person> allPerson = personDAO.getAllPerson();
        return allPerson;
    }

    public Person getPersonById(int id){
        Person person = personDAO.getPersonById(id);
        return person;
    }

    public void addPerson(Person person){
        personDAO.addPerson(person);
    }
}
