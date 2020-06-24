package pe.upc.tf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		return "home.html";
	}
	@GetMapping("/listadeseos")
	public String ListaUsuario() {
	    return "ListaUsuario.html";
	}
	@GetMapping("/modificaru")
	public String ModificarUsuario() {
	    return "ModificarUsuario.html";
	}
	@GetMapping("/actore")
	public String MostrarActor() {
	    return "actor.html";
	}
	
	@GetMapping("/peliculaserie")
	public String MostrarPelicula() {
		return "pelicula_serie";
	}
}
