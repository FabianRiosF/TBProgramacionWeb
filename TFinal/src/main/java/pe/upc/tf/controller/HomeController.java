package pe.upc.tf.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.service.PeliculaService;
import pe.upc.tf.service.SerieService;

import org.springframework.ui.Model;

@Controller
public class HomeController {
	
	@Autowired
	private ActorService aService;
	
	@Autowired
	private PeliculaService pService;
	
	@Autowired
	private SerieService sService;
	
	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		model.addAttribute("pelicula", new Pelicula());
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
	public String MostrarActor(Model model) {
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("pelicula", new Pelicula());
	    return "actor/listActoresUsuarios";
	}
	
	@GetMapping("/pelicula")
	public String MostrarPelicula(Model model) {
		model.addAttribute("listPeliculas", pService.ListarPeliculas());
		model.addAttribute("pelicula", new Pelicula());
		return "pelicula/listPeliculasUsuarios";
	}
	@GetMapping("/serie")
	public String MostrarSerie(Model model) {
		model.addAttribute("listSeries", sService.ListarSeries());
		model.addAttribute("pelicula", new Pelicula());
		return "serie/listSeriesUsuarios";
	}
}
