package com.revature;

import com.revature.DAO.PersonDAO;
import com.revature.Exception.PersonException;
import com.revature.Model.Person;
import com.revature.Service.PersonService;
import com.revature.Util.ConnectionSingleton;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConnectionSingleton.getConnection();
        PersonDAO personDAO = new PersonDAO(conn);
        PersonService personService = new PersonService(personDAO);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("1: add new person 2: get all people 3: get person by id");
            String input = sc.nextLine();
            if(input.charAt(0) == '1'){
                try {
                    String id = sc.nextLine();
                    String name = sc.nextLine();
                    String active = sc.nextLine();
                    String cell = sc.nextLine();
                    Person person = new Person(
                            Integer.parseInt(id),
                            name,
                            Boolean.parseBoolean(active),
                            Long.parseLong(cell)
                    );
                    personService.addPerson(person);
                }catch (RuntimeException e){
                    System.out.println("Invalid input.");
                }catch(PersonException e){
                    System.out.println(e.getMessage());
                }
            }else if(input.charAt(0)=='2'){
                List<Person> allPerson = personService.getAllPerson();
                System.out.println(allPerson);
            }else if(input.charAt(0)=='3'){
                int id = Integer.parseInt(sc.nextLine());
                Person person = personService.getPersonById(id);
                System.out.println(person);
            }
        }
    }
}
/**
 *
 * The initial setup of our application - how do the different layers of the
 * application access each other's methods? Generally, we can use a strategy
 * called "dependency injection": We can provide all of the objects that a class
 * needs (eg the personservice needs to persondao) via the constructor.
 *
 * We recieve requests from the view layer
 * The view layer asks the service layer to perform some task
 * The service layer asks the data layer for any data that needs to be retrieved/modified
 *  and also performs any "side effects" (eg logging)
 * The data layer interacts with persisted data (sql), and returns the persisted data
 *  to service
 * The service layer returns the data to view layer
 * The view layer presents the data to the user in a sensible format
 */