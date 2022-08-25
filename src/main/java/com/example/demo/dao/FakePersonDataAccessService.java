package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
Esta clase implementa los métodos de la interfaz PersonDao.

Con repository se dice a spring que esta clase, que es la que contiene los datos en el arraylist, puede ser inyectada en otras. Por tanto va a actuar como un repositorio de datos. Entre paréntesis se le puede asignar un nombre
 */
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    // inserta una persona en el arraylist
    @Override
    public int insertPerson(UUID personId, Person person) {
        DB.add(new Person(personId, person.getfName(), person.getlName1(), person.getlName2()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    //para borrar la persona, evalúa si existe. Si existe la borra, si no devuelve 0
    @Override
    public int deletePersonById(UUID personId) {
        Optional<Person> personExists = selectPersonById(personId);
        if (personExists.isEmpty()) {
            return 0;
        }
        DB.remove(personExists.get());
        return 1;
    }

    // con el método map recorremos el objeto persona y si su indice es mayor o igual a 0 la persona existe, y se crea un nuevo objeto para ese id con los datos recibidos por PUT request, reescribiendo los datos anteriores
    @Override
    public int updatePersonById(UUID personId, Person person) {
        return selectPersonById(personId)
                .map(p -> {
                    int indexOfPersonToUpdate = DB.indexOf(p);
                    if (indexOfPersonToUpdate >= 0) {
                        DB.set(indexOfPersonToUpdate, new Person(personId, person.getfName(), person.getlName1(), person.getlName2()));
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .orElse(0);
    }

    // desarrolla el método de la interfaz, y filtra las personas para devolver aquella persona con el ID proporcionado
    @Override
    public Optional<Person> selectPersonById(UUID personId) {
        return DB.stream()
                .filter(person -> person.getPersonId().equals(personId))
                .findFirst();
    }


}
