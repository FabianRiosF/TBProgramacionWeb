package pe.upc.tf.controller;

import java.util.List;
import java.util.Map;

import javax.validation.ReportAsSingleViolation;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Serie;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.service.SerieService;
import pe.upc.tf.repositories.ISerieRepository;

@Controller
@RequestMapping("/series")
public class SerieController{

	@Autowired
	private SerieService sService;
	
	@Value("${app.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private ISerieRepository sRepository;
	
	@Autowired
	private ActorService aService;
	
	@GetMapping("/seriesList")
	public String lista(Model model) {
		try {
			model.addAttribute("serie", new Serie());
			model.addAttribute("listSeries", sService.ListarSeries());
			model.addAttribute("actor", new Actor());
			model.addAttribute("listActores", aService.ListarActores());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "serie/listSeries";
	}
	
	  @GetMapping("/add-serie")
	    public String showSerieForm(Model model) {
		  model.addAttribute("serie", new Serie());
			model.addAttribute("listActores", aService.ListarActores());
			model.addAttribute("listSeries", sService.ListarSeries());
	        return "serie/serie";
	    }
	
	
	@PostMapping("/serie")
	public String saveSerie(@Valid Serie serie, BindingResult result, Model model, SessionStatus status, @RequestParam("imageSerie")MultipartFile multiPart)
			throws Exception {
		
		if (result.hasFieldErrors("nameSerie") || result.hasFieldErrors("Actores") ||  result.hasFieldErrors("dateSerie") || result.hasFieldErrors("descSerie)) {
			model.addAttribute("listActores", aService.ListarActores());
			return "serie/serie";
		} 
		else {
			String nombreImagen = null;
			if (!multiPart.isEmpty()) {
				nombreImagen = serie.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null){ 
					System.out.println("Imagen subida");
					System.out.println("Nombre Final:" + nombreImagen);
				}
			}
			System.out.println("../images/"+ nombreImagen);
			
			serie.setImageSerie("../images/"+ nombreImagen);
			int rpta = sService.RegistrarSerie(serie);
		
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listActores", aService.ListarActores());
				return "serie/serie";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		
		model.addAttribute("listSeries", sService.ListarSeries());
		model.addAttribute("listActores", aService.ListarActores());
		return "serie/serie";
	}
	
	@RequestMapping("/delete")
	public String deleteSerie(Map<String, Object> model, @RequestParam(value = "idSerie") Integer idSerie) {
		try {
			if (idSerie != null && idSerie > 0) {
				sService.EliminarSerie(idSerie);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar la serie");
		}
		model.put("listSeries", sService.ListarSeries());

		return "serie/listSeries";
	}
	
	 @GetMapping("/edit-{idSerie}")
	    public String showUpdateForm(@PathVariable("idSerie") Integer idSerie, Model model) {
	        Serie serie = sService.BuscarSerieID(idSerie);
	        model.addAttribute("serie", serie);
	        model.addAttribute("listActores", aService.ListarActores());
	        return "serie/update-serie";
	    }
	   
	   
	    
	    @PostMapping("/update-{idSerie}")
	    public String updateSerie(@PathVariable("idSerie") Integer idSerie, @Valid Serie serie, BindingResult result, Model model, @RequestParam("imageSerie")MultipartFile multiPart){
		     
	        if (result.hasFieldErrors("nameSerie") || result.hasFieldErrors("Actores") ||  result.hasFieldErrors("dateSerie") || result.hasFieldErrors("descSerie)) {
			model.addAttribute("listActores", aService.ListarActores());
			return "serie/serie";
		} 
	        else {
	        	String nombreImagen = null;
				if (!multiPart.isEmpty()) {
					nombreImagen = serie.guardarArchivo(multiPart, ruta);
					if (nombreImagen != null){ 
						// Procesamos la variable nombreImagen
						System.out.println("Imagen subida");
						System.out.println("Nombre Final:" + nombreImagen);
					}
				}
				model.addAttribute("rutaWeb","../images/"+ nombreImagen);
				System.out.println("../images/"+ nombreImagen);
				
				
				if(!multiPart.isEmpty()) {
					serie.setImageSerie("../images/"+ nombreImagen);
				}
				
				else {
					Serie serie2 = sService.BuscarSerieID(idSerie);
					serie.setImageSerie(serie2.getImageSerie());
				}
				
				model.addAttribute("listActores", aService.ListarActores());
			    sService.ActualizarSerie(serie);
			    model.addAttribute("mensaje", "Se actualizó correctamente");
	        }
	       
	        return "serie/update-serie";
	    }
	
}
