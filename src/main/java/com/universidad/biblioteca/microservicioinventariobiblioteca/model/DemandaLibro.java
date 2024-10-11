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

    private String tituloLibro;        
    private String autorLibro;         
    private int vecesSolicitado;        
    private String carreraMasSolicitante; 
    private boolean esLibroDeReferencia; 
}
