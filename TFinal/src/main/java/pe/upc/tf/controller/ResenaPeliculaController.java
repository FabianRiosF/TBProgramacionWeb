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
import pe.upc.tf.entities.ResenaPelicula;
import pe.upc.tf.entities.Usuario;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.service.ListaService;
import pe.upc.tf.service.PeliculaService;
import pe.upc.tf.service.ResenaPeliculaService;
import pe.upc.tf.service.UsuarioService;

@Controller
public class ResenaPeliculaController {
	
	@Autowired
	private ResenaPeliculaService rpService;
	
	@Autowired
	private ListaService lService;
	
	
	@Autowired
	private PeliculaService pService;
	
	@Autowired
	private UsuarioService uService;
	
	@Autowired
	private ActorService aService;
	
	
	@GetMapping("/peliculasLista")
	public String lista(Model model) {
		try {
			Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = uService.findByUsername(autenticado.getName()); 
			model.addAttribute("pelicula", new Pelicula());
			model.addAttribute("listPeliculas", pService.ListarPeliculas());
			model.addAttribute("actor", new Actor());
			model.addAttribute("listActores", aService.ListarActores());
			model.addAttribute("usuario", usuario.getId());
			model.addAttribute("lista", new Lista());
			model.addAttribute("listListas", lService.ListarListasUsuario(usuario.getId()));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "peliculasLista";
	}
	
	@RequestMapping("/add-resena-{idPelicula}-{idUsuario}")
	public String InterPeliculaResena(@PathVariable("idPelicula") Integer idPelicula, @PathVariable("idUsuario") Integer idUsuario, Model model) {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = uService.findByUsername(autenticado.getName());
		Pelicula pelicula = pService.BuscarPeliculaID(idPelicula);
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("usuario", usuario);
		model.addAttribute("pelicula", pelicula);
		ResenaPelicula resena = new ResenaPelicula();
		model.addAttribute("resenapelicula", resena);
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("listResenas", rpService.ListarResenasPelicula(idPelicula));
        return "resenapelicula";
	}
	
	@PostMapping("/resena-pelicula-{idPelicula}")
	public String savePelicula(@PathVariable("idPelicula") Integer idPelicula, @Valid ResenaPelicula resenapelicula, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = uService.findByUsername(autenticado.getName());
		Pelicula pelicula = pService.BuscarPeliculaID(idPelicula);
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("usuario", usuario);
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("resenapelicula", new ResenaPelicula());
		model.addAttribute("listActores", aService.ListarActores());
		model.addAttribute("listResenas", rpService.ListarResenasPelicula(idPelicula));
		
		if (result.hasErrors()) {
			model.addAttribute("listActores", aService.ListarActores());
			model.addAttribute("mensaje", "Debe Ingresar Reseña*");
			return "resenapelicula";
		} 
		else {
			rpService.CrearResenaPelicula(resenapelicula);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			}
		model.addAttribute("listPeliculas", pService.ListarPeliculas());
		model.addAttribute("listResenas", rpService.ListarResenasPelicula(resenapelicula.getPelicula().getIdPelicula()));
		model.addAttribute("listActores", aService.ListarActores());
		return "resenapelicula";
	}
	
}
