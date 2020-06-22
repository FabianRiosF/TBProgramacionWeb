package pe.progra.tb.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.progra.tb.entity.Lista;
import pe.progra.tb.service.IListaService;

@Named
@RequestScoped
public class ListaController implements Serializable{
    @Inject
    private IListaService lService;  
    
    private Lista lista;
    List<Lista> listaListas;
    
    @PostConstruct
    public void init() {
    	
    }
   /*prueba*/ 
}
