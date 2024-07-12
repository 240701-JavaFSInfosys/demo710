package com.revature.DAO;

import com.revature.Model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object
 *
 * Any operation that we would like to perform via JDBC is just a matter of changing
 * the SQL statement used.
 * EG: a where clause performs filtering and can be applied with AND / OR statements
 * Select * from person where active = true
 * Select * from person where name = 'john doe' and true
 * A 'order by' clause orders results.
 * Select * from person order by cell
 * Aggregate queries perform some sort of operation across many items (eg a count
 * of all items)
 * Select count(id) from person
 *
 * "select * from person where id = "+id
 * "select * from person where id = "+"1; drop table person;"
 *
 * Transactions in SQL.
 * A transaction is a way for us to say that a series of SQL statements
 * are "all or nothing" (Atomicity)
 *      Let's say that we have some sort of bulk operation that we're performing
 *      But: Something - either our server, database, network crashes midway through
 *          let's say that we're inserting songs and we only manage to insert half
 *          of the songs prior to the crash - this is worse than insering nothing,
 *          because we'd have to recover / clean up from the crash!
 *      ACID
 *      Atomicity - transactions are atomic : all or nothing
 *      Consistency - all constraints are always met on the db (eg referential integrity)
 *      Isolation - transactions which are halfway completed are not visible to other
 *      transactions in process
 *      Durability - we can recover from failures on the db (eg rollbacks)
 *
 *      Transactions must be started & committed. By default, JDBC will auto-commit
 *      every individual SQL statement, which isn't always what we want.
 */
public class PersonDAO {
    Connection conn;
    public PersonDAO(Connection conn){
        this.conn = conn;
    }

    public List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement("select * from person");
            ResultSet rs = ps.executeQuery();
//            extracting columns out of resultset into a java object.
            while(rs.next()){
                Person newPerson = new Person(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("active"),
                        rs.getLong("cell")
                );
                personList.add(newPerson);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return personList;
    }
    public void addPerson(Person person){
        try{
            PreparedStatement ps = conn.prepareStatement("insert into person (id, name, active, cell) values (?, ?, ?, ?)");
            ps.setInt(1, person.getId());
            ps.setString(2, person.getName());
            ps.setBoolean(3, person.isActive());
            ps.setLong(4, person.getCell());
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Person getPersonById(int id){
        try{
            PreparedStatement ps = conn.prepareStatement("select * from person where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
//            extracting columns out of resultset into a java object.
            while(rs.next()){
                Person newPerson = new Person(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("active"),
                        rs.getLong("cell")
                );
                return newPerson;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
