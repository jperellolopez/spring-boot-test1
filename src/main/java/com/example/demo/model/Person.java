package com.example.demo.model;

/*
Model is a person with these properties: DNI (personId), First Name (fName), Last Name 1 (lName1), Last Name 2 (lName2)
 */

/*
Ejecutar el jar en el servidor: click pestaña "maven" arriba la der, abrir lifecycle, doble click en install, click der en target, open in terminal, escribir java -jar .\demo-0.0.1-SNAPSHOT.jar + enter, escribir la ruta de la app en el navegador (http://localhost:8080/api/v1/person)
 */

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {

    private final UUID personId;

    // no se permite que nombre y primer apellido estén en blanco ("")
    @NotBlank
    private final String fName;

    @NotBlank
    private final String lName1;

    private final String lName2;

    // se definen las JsonProperty en el constructor para poder hacer las operaciones desde el cliente (ej: postman). El nombre entre comillas debe ser el mismo que se define en JSON del cliente.
    public Person(@JsonProperty("personId") UUID personId, @JsonProperty("fName") String fName, @JsonProperty("lName1") String lName1, @JsonProperty("lName2") String lName2) {
        this.personId = personId;
        this.fName = fName;
        this.lName1 = lName1;
        this.lName2 = lName2;
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getfName() {
        return fName;
    }


    public String getlName1() {
        return lName1;
    }

    public String getlName2() {
        return lName2;
    }


}
