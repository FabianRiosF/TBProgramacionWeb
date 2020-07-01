package pe.upc.tf.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Serie;
import pe.upc.tf.entities.Usuario;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.service.PeliculaService;
import pe.upc.tf.service.SerieService;
import pe.upc.tf.service.UsuarioService;
import pe.upc.tf.repositories.IPeliculaRepository;
import pe.upc.tf.repositories.UsuarioRepository;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

	@Value("${app.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private PeliculaService pService;
	
	@Autowired
	private IPeliculaRepository pRepository;
	
	@Autowired
	private UsuarioService uService;
	
	@Autowired
	private SerieService sService;
	
	@Autowired
	private ActorService aService;
	
	@GetMapping("/peliculasList")
	public String lista(Model model) {
		try {
			model.addAttribute("pelicula", new Pelicula());
			model.addAttribute("listPeliculas", pService.ListarPeliculas());
			model.addAttribute("actor", new Actor());
			model.addAttribute("listActores", aService.ListarActores());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "pelicula/listPeliculas";
	}
	
	  @GetMapping("/add-pelicula")
	    public String showPeliculaForm(Model model) {
		  	model.addAttribute("pelicula", new Pelicula());
			model.addAttribute("listActores", aService.ListarActores());
			model.addAttribute("listPeliculas", pService.ListarPeliculas());
	        return "pelicula/pelicula";
	    }
	
	
	@PostMapping("/pelicula")
	public String savePelicula(@Valid Pelicula pelicula, BindingResult result, Model model, SessionStatus status, @RequestParam("imagePelicula")MultipartFile multiPart)
			throws Exception {
		
		if (result.hasFieldErrors("namePelicula") || result.hasFieldErrors("datePelicula") || result.hasFieldErrors("descPelicula")) {
			model.addAttribute("listActores", aService.ListarActores());
			return "pelicula/pelicula";
		} 
		else {
			String nombreImagen = null;
			if (!multiPart.isEmpty()) {
				nombreImagen = pelicula.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null){ 
					System.out.println("Imagen subida");
					System.out.println("Nombre Final:" + nombreImagen);
				}
			}
			System.out.println("../images/"+ nombreImagen);
			
			pelicula.setImagePelicula("../images/"+ nombreImagen);
			int rpta = pService.RegistrarPelicula(pelicula);
		
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listActores", aService.ListarActores());
				return "pelicula/pelicula";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		
		model.addAttribute("listPeliculas", pService.ListarPeliculas());
		model.addAttribute("listActores", aService.ListarActores());
		return "pelicula/pelicula";
	}
	
	@RequestMapping("/delete")
	public String deletePelicula(Map<String, Object> model, @RequestParam(value = "idPelicula") Integer idPelicula) {
		try {
			if (idPelicula != null && idPelicula > 0) {
				pService.EliminarPelicula(idPelicula);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la pelicula");
		}
		model.put("listPeliculas", pService.ListarPeliculas());

		return "pelicula/listPeliculas";
	}
	
	 @GetMapping("/edit-{idPelicula}")
	    public String showUpdateForm(@PathVariable("idPelicula") Integer idPelicula, Model model) {
	        Pelicula pelicula = pService.BuscarPeliculaID(idPelicula);
	        model.addAttribute("pelicula", pelicula);
	        model.addAttribute("listActores", aService.ListarActores());
	        return "pelicula/update-pelicula";
	    }
	   
	   
	    
	    @PostMapping("/update-{idPelicula}")
	    public String updatePelicula(@PathVariable("idPelicula") Integer idPelicula, @Valid Pelicula pelicula, BindingResult result, Model model, @RequestParam("imagePelicula")MultipartFile multiPart){
	     
	        if (result.hasFieldErrors("namePelicula") || result.hasFieldErrors("datePelicula")|| result.hasFieldErrors("descPelicula")) {
	        	pelicula.setIdPelicula(idPelicula);
				model.addAttribute("listActores", aService.ListarActores());
				return "update-pelicula";
			} 
	        else {
	        	String nombreImagen = null;
				if (!multiPart.isEmpty()) {
					nombreImagen = pelicula.guardarArchivo(multiPart, ruta);
					if (nombreImagen != null){ 
						// Procesamos la variable nombreImagen
						System.out.println("Imagen subida");
						System.out.println("Nombre Final:" + nombreImagen);
					}
				}
				model.addAttribute("rutaWeb","../images/"+ nombreImagen);
				System.out.println("../images/"+ nombreImagen);
				
				
				if(!multiPart.isEmpty()) {
					pelicula.setImagePelicula("../images/"+ nombreImagen);
				}
				
				else {
					Pelicula pelicula2 = pService.BuscarPeliculaID(idPelicula);
					pelicula.setImagePelicula(pelicula2.getImagePelicula());
				}
				
				model.addAttribute("listActores", aService.ListarActores());
			    pService.ActualizarPelicula(pelicula);
			    model.addAttribute("mensaje", "Se actualizó correctamente");
	        }
	       
	        return "pelicula/update-pelicula";
	    }
	    
	    @RequestMapping("/buscarpelicula")
	    public String busquedapelicula(@ModelAttribute Pelicula pelicula, Map < String, Object> model, HttpServletRequest request) {
	    	String tipo = request.getParameter("tipo");
	    	pelicula.setNamePelicula(pelicula.getNamePelicula());
	    	switch (tipo) {
			case "Peliculas":
				Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuario = uService.findByUsername(autenticado.getName()); 
				model.put("usuario", usuario.getId());
	    		model.put("listPeliculas", pService.ListarBusquedaPeliculas(pelicula.getNamePelicula()));
	    		model.put("pelicula", new Pelicula());
	    		return "pelicula/listbusqueda";
			case "Series":
				Authentication autenticado1 = SecurityContextHolder.getContext().getAuthentication();
				Usuario usuario1 = uService.findByUsername(autenticado1.getName()); 
				model.put("usuario", usuario1.getId());
				model.put("listSeries", sService.BuscarSerie(pelicula.getNamePelicula()));
				model.put("pelicula", new Pelicula());
	    		return "serie/listbusqueda";
			default:
				System.out.println(tipo.toString());
	    		return "home";
			}
	    }
	
}
