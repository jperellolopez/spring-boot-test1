package com.example.demo.api;

// correr esta clase, después enviar la request desde Postman, comprobar que el resultado es 200
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

// define la clase como un Rest controller, donde se hacen las operaciones CRUD.
// con RequestMapping definimos el endpoint (path) al que llamaremos desde el cliente (ej: localhost:8080/api/v1/person)
@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

    // inicia instancia de la clase PersonService
    private final PersonService personService;

    //constructor de la clase personController. Inyecta PersonService a este constructor.
    @Autowired
    public PersonController(PersonService personService) {

        this.personService = personService;
    }

    //queremos que este método se use como un POST request (POST = create, GET = read, PUT = update, DELETE = delete). Para eso se define PostMapping.
    /*
    Ejemplo de POST request desde postman (el id no se requiere porque se autogenera)
    {
    "fName": "James",
    "lName1": "Bond",
    "lName2": "López"
    }

    Para indicar que se quiere insertar un body desde la POST request, se usa RequestBody
    Se pone antes Valid para importar la condición (en este caso NotBlank) que se ha puesto en la clase Person
    Se pone antes NonNull para indicar que a los campos afectados por Valid (nombre+1r apellido) no se les puede poner valor nulo en la request
    Por tanto ningún campo puede ser nulo, y nombre+1r apellido tampoco pueden estar en blanco ("")
     */
    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    //indica que se quiere usar como un GET request (read) para obtener la lista de todas las personas
    @GetMapping
    public List<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    // GET request para obtener una persona por ID o un null si no se encuentra
    // PathVariable permite introducir una variable al final del path, en este caso una id
    // la variable path en getMapping equivale a añadir "/var" al final de la ruta
    @GetMapping(path = "{personId}")
    public Person getPersonById(@PathVariable("personId") UUID personId) {
        return personService.getPersonById(personId)
                .orElse(null);
    }

    // DELETE request para borrar una persona por id
    @DeleteMapping(path = "{personId}")
    public void deletePersonById(@PathVariable("personId") UUID personId) {
        personService.deletePerson(personId);
    }

    //PUT request para hacer un update. Hay que poner RequestBody porque se pasan nuevos datos en el body de la request.
    @PutMapping(path = "{personId}")
    public void updatePerson(@PathVariable("personId") UUID personId, @Valid @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(personId, personToUpdate);
    }


}
