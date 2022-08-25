package com.example.demo.dao;

/*
Desde esta clase se accede a los datos de la persona conectando a una DB
 */

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// para inyectar estos métodos que usan la DB, ir a PersonService y cambiar el Qualifier de "fakeDao" a "Postgres"
@Repository("postgres")
public class PersonDataAccessService implements PersonDao{

    @Override
    public int insertPerson(UUID personId, Person person) {
        return 0;
    }

    // lista con los datos que se mostrarán al hacer una petición GET
    @Override
    public List<Person> selectAllPeople() {
        return List.of(new Person(UUID.randomUUID(), "FROM POSTGRES DB", "surname1", "surname2"));
    }

    @Override
    public int deletePersonById(UUID personId) {
        return 0;
    }

    @Override
    public int updatePersonById(UUID personId, Person person) {
        return 0;
    }

    @Override
    public Optional<Person> selectPersonById(UUID personId) {
        return Optional.empty();
    }
}
