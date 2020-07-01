package pe.upc.tf.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Lista;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.ResenaSerie;
import pe.upc.tf.entities.Serie;
import pe.upc.tf.entities.Usuario;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.service.ListaService;
import pe.upc.tf.service.PeliculaService;
import pe.upc.tf.service.ResenaPeliculaService;
import pe.upc.tf.service.ResenaSerieService;
import pe.upc.tf.service.SerieService;
import pe.upc.tf.service.UsuarioService;

@Controller
public class ResenaSerieController {
	
	@Autowired
	private ResenaSerieService rsService;
	
	@Autowired
	private ListaService lService;
	
	
	@Autowired
	private SerieService sService;
	
	@Autowired
	private UsuarioService uService;
	
	@Autowired
	private ActorService aService;
	
	
	@GetMapping("/seriesLista")
	public String lista(Model model) {
		try {
			model.addAttribute("pelicula", new Pelicula());
			Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = uService.findByUsername(autenticado.getName()); 
			model.addAttribute("serie", new Serie());
			model.addAttribute("listSeries", sService.ListarSeries());
			model.addAttribute("actor", new Actor());
			model.addAttribute("listActores", aService.ListarActores());
			model.addAttribute("usuario", usuario.getId());
			model.addAttribute("lista", new Lista());
			model.addAttribute("listListas", lService.ListarListasUsuario(usuario.getId()));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "seriesLista";
	}
	
	@RequestMapping("/add-resenaserie-{idSerie}-{idUsuario}")
	public String InterSerieResena(@PathVariable("idSerie") Integer idSerie, @PathVariable("idUsuario") Integer idUsuario, Model model) {
		model.addAttribute("pelicula", new Pelicula());
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = uService.findByUsername(autenticado.getName());
		Serie serie = sService.BuscarSerieID(idSerie);
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("usuario", usuario);
		model.addAttribute("serie", serie);
		ResenaSerie resena = new ResenaSerie();
		model.addAttribute("resenaserie", resena);
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("listResenas", rsService.ListarResenasSerie(idSerie));
        return "resenaserie";
	}
	
	@PostMapping("/resena-serie-{idSerie}")
	public String saveSerie(@PathVariable("idSerie") Integer idSerie, @Valid ResenaSerie resenaserie, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		model.addAttribute("pelicula", new Pelicula());
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = uService.findByUsername(autenticado.getName());
		Serie serie = sService.BuscarSerieID(idSerie);
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("usuario", usuario);
		model.addAttribute("serie", serie);
		model.addAttribute("resenaserie", new ResenaSerie());
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("listResenas", rsService.ListarResenasSerie(idSerie));
		
		if (result.hasErrors()) {
			model.addAttribute("listActores", aService.ListarActores());
			model.addAttribute("mensaje", "Debe Ingresar Reseña*");
			return "resenaserie";
		} 
		else {
			rsService.CrearResenaSerie(resenaserie);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			}
		model.addAttribute("listSeries", sService.ListarSeries());
		model.addAttribute("listResenas", rsService.ListarResenasSerie(resenaserie.getSerie().getIdSerie()));
		model.addAttribute("listActores", aService.ListarActores());
		return "resenaserie";
	}
	
}
