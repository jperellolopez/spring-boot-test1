package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//se le dice a spring boot que la clase es un servicio (puede usarse Component)
@Service
public class PersonService {

    // se instancia la interfaz PersonDao
    private final PersonDao personDao;

    //constructor de la clase PersonService. Se le inyectan los datos de FakePersonDataAccessService

    //autowired se usa para inyectar a esta instancia de la interfaz personDao los datos que están en el arraylist de la clase FakePersonDataAccessService (autowired inyecta un repository)

    // como puede haber distintas implementaciones de la interfaz PersonDao, se usa el Qualifier para distinguir entre estas, en este caso podríamos poner tanto "fakeDao" (de la clase FakePersonData...) como "postgres" (de la clase PersonData...). Asi se sabe de que Repository en concreto proviene el Autowired inyectado.
    @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {

        this.personDao=personDao;
    }

    //add person
    public int addPerson(Person person) {

        return personDao.insertPerson(person);
    }

    // select all people
    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    // select ONE people with given id
    public Optional<Person> getPersonById(UUID personId) {
        return personDao.selectPersonById(personId);
    }

    //delete one person by id
    public int deletePerson(UUID personId) {
        return personDao.deletePersonById(personId);
    }

    //update one person data
    public int updatePerson(UUID personId, Person newPerson) {
        return personDao.updatePersonById(personId, newPerson);
    }



}
