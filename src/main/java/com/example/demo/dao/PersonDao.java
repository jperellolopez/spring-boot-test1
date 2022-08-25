package com.example.demo.dao;

/*
El patrón DAO (Data Access Object) permite a la aplicación acceder a fuentes de datos distintas, por ejemplo, en una base de datos y en un archivo local. Permite separar la lógica de negocio de la lógica para acceder a los datos.
La capa DAO  contiene los métodos CRUD para acceder a los datos. Normalmente hay una DAO por cada tabla de la BD.

Interfaz donde se definen las operaciones permitidas para quien implemente esta inferfaz. Permite usar inyección de dependencias para cambiar entre distintas implementaciones.
 */

//importar modelo donde se definen las props de la persona
import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    //método para insertar una persona con un id
    int insertPerson(UUID personId, Person person);

    // método que inserta una persona sin un id (genera uno aleatorio)
    default int insertPerson(Person person) {
        UUID personId = UUID.randomUUID();
        return insertPerson(personId, person);
    }

    //método que devuelve una lista de personas
    List<Person> selectAllPeople();

     int deletePersonById(UUID personId);

    int updatePersonById(UUID personId, Person person);

    // método que devuelve los datos de una persona con un ID dado por parámetro. Se desarrolla en el método FakePersonDataAccessService, de manera que aquí sólo indicamos que lo tiene que implementar.
    //optional devuelve un valor, que puede estar o no pero nunca será nulo.
    Optional<Person> selectPersonById(UUID personId);



}
