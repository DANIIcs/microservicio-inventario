package com.universidad.biblioteca.microservicioinventariobiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class DemandaLibro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tituloLibro;   // Título del libro solicitado
    private String carrera;       // Carrera de los estudiantes que más solicitan el libro
    private int vecesSolicitado;  // Número de veces que se ha solicitado el libro

}
