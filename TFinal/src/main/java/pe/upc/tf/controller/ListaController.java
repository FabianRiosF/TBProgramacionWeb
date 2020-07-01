package pe.upc.tf.controller;

import java.util.Date;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Lista;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.ResenaPelicula;
import pe.upc.tf.entities.Usuario;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.service.ListaService;
import pe.upc.tf.service.PeliculaService;
import pe.upc.tf.service.SerieService;
import pe.upc.tf.service.UsuarioService;

@Controller
public class ListaController {
	
	@Autowired
	private ListaService lService;
	
	@Autowired
	private PeliculaService pService;
	
	
	@Autowired
	private SerieService sService;
	
	@Autowired
	private UsuarioService uService;
	
	@Autowired
	private ActorService aService;
	
	
	@GetMapping("/listas")
	public String lista(Model model) {
		try {
			model.addAttribute("lista", new Lista());
			Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = uService.findByUsername(autenticado.getName());
			int id = usuario.getId();
			model.addAttribute("usuario", id);
			model.addAttribute("pelicula", new Pelicula());
			model.addAttribute("listas", lService.ListarListasUsuario(id));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "listas";
	}
	
	@GetMapping("/add-lista")
    public String showListaForm(Model model) {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario usuario = uService.findByUsername(autenticado.getName());
		model.addAttribute("user", usuario);
	  	model.addAttribute("lista", new Lista());
		model.addAttribute("pelicula", new Pelicula());
	  	model.addAttribute("listPeliculas", pService.ListarPeliculas());
	  	model.addAttribute("listSeries", sService.ListarSeries());
        return "addlista";
    }
	
	@PostMapping("/lista")
		public String saveLista(@Valid Lista lista, BindingResult result, 
				Model model, SessionStatus status, RedirectAttributes attributes)
				throws Exception {
			if (result.hasFieldErrors("nameLista")) {
				Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuario = uService.findByUsername(autenticado.getName());
				model.addAttribute("user", usuario);
			  	model.addAttribute("lista", new Lista());
			  	
				return "addlista";
			} 
			else {
				model.addAttribute("pelicula", new Pelicula());
				Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuario = uService.findByUsername(autenticado.getName());
				model.addAttribute("user", usuario);
				int id = usuario.getId();
			  	model.addAttribute("lista", new Lista());
			  	lService.CrearLista(lista);
			  	model.addAttribute("listas", lService.ListarListasUsuario(id));
			  	model.addAttribute("listPeliculas", pService.ListarPeliculas());
			  	model.addAttribute("listSeries", sService.ListarSeries());
				model.addAttribute("mensaje", "Se creó correctamente");
				status.setComplete();
			}
			return "listas";
		}
	
	@RequestMapping("/lista-delete")
	public String deletePelicula(Map<String, Object> model, @RequestParam(value = "idLista") Integer idLista) {
		try {
			if (idLista != null && idLista > 0) {
				model.put("pelicula", new Pelicula());
				lService.EliminarLista(idLista);
				Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuario = uService.findByUsername(autenticado.getName());
				int id = usuario.getId();
				model.put("usuario", id);
				model.put("listas", lService.ListarListasUsuario(id));
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la lista");
		}
		

		return "listas";
	}
	
	 @GetMapping("/edit-lista-{idLista}")
	    public String showUpdateForm(@PathVariable("idLista") Integer idLista, Model model) {
			model.addAttribute("pelicula", new Pelicula());
		 Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
			Usuario usuario = uService.findByUsername(autenticado.getName());
			model.addAttribute("user", usuario);
	        Lista lista = lService.buscar(idLista);
	        model.addAttribute("lista", lista);
	        model.addAttribute("listPeliculas", pService.ListarPeliculas());
		  	model.addAttribute("listSeries", sService.ListarSeries());
	        return "update-lista";
	    }
	  
	 @PostMapping("/updatelista-{idLista}")
	    public String updateLista(@PathVariable("idLista") Integer idLista, @Valid Lista lista, BindingResult result, Model model){
	     
	        if (result.hasErrors()) {
	        	Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
	        	Usuario usuario = uService.findByUsername(autenticado.getName());
	        	model.addAttribute("user", usuario);
	            model.addAttribute("listPeliculas", pService.ListarPeliculas());
			  	model.addAttribute("listSeries", sService.ListarSeries());
				return "update-lista";
			} 
	        else {		
				model.addAttribute("pelicula", new Pelicula());
	        	Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuario = uService.findByUsername(autenticado.getName());
				model.addAttribute("user", usuario);
				int id = usuario.getId();
				model.addAttribute("listas", lService.ListarListasUsuario(id));
				
				lista = lService.buscar(idLista);
				
				

				lService.CrearLista(lista);
				
				model.addAttribute("mensaje", "Se actualizo correctamente");
	        }
	       
	        return "listas";
	    }
	   

}
