package pe.upc.tf.controller;

import java.io.File;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.annotations.ParamDef;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.service.ActorService;
import pe.upc.tf.serviceimpl.ActorServiceImpl;

@Controller
@RequestMapping("/actores")
public class ActorController {

	@Autowired
	private ActorService aService;
	
	@Value("${app.ruta.imagenes}")
	private String ruta;
	//pruebita
	@GetMapping("/actoresList")
	public String lista(Model model) {
		try {
			model.addAttribute("actor", new Actor());
			model.addAttribute("listActores", aService.ListarActores());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "actor/listActores";
	}
	
	@RequestMapping("/add-actor")
	public String nuevoActor(Model model) {
		model.addAttribute("actor", new Actor());
		model.addAttribute("listActores", aService.ListarActores());
		return "actor/actor";
	}
	
	@PostMapping("/actor")
	public String savePelicula(@Valid Actor actor, BindingResult result, 
			Model model, SessionStatus status, RedirectAttributes attributes,  @RequestParam("imageActor")MultipartFile multiPart)
			throws Exception {
		if (result.hasFieldErrors("nameActor") || result.hasFieldErrors("dateActor")) {
			return "actor/actor";
		} 
		else {
			String nombreImagen = null;
			if (!multiPart.isEmpty()) {
				nombreImagen = actor.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null){ 
					System.out.println("Imagen subida");
					System.out.println("Nombre Final:" + nombreImagen);
				}
			}
			System.out.println("../images/"+ nombreImagen);
			
			actor.setImageActor("../images/"+ nombreImagen);
			int rpta = aService.RegistrarActor(actor);
			
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "actor/actor";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
		}
	}
		
		return "actor/actor";
	}
	
	@RequestMapping("/delete")
	public String deleteActor(Map<String, Object> model, @RequestParam(value = "idActor") Integer idActor) {
		try {
			if (idActor != null && idActor > 0) {
				aService.EliminarActor(idActor);
				model.put("mensaje", "Se eliminó correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar el actor");
		}
		model.put("listActores", aService.ListarActores());

		return "actor/listActores";
	}
	
	 @GetMapping("/edit-{idActor}")
	    public String showUpdateForm(@PathVariable("idActor") Integer idActor, Model model) {
	        Actor actor = aService.buscar(idActor);
	        model.addAttribute("actor", actor);
	        model.addAttribute("listActores", aService.ListarActores());
	        return "actor/update-actor";
	    }
	   
	   
	    
	    @PostMapping("/update-{idActor}")
	    public String updateActor(@PathVariable("idActor") Integer idActor, @Valid Actor actor, BindingResult result, Model model, @RequestParam("imageActor") MultipartFile multiPart) {
	        
	    	if (result.hasFieldErrors("nameActor") || result.hasFieldErrors("dateActor")) {
				return "actor/actor";
			} 
	    	else {

	        String nombreImagen = null;
			if (!multiPart.isEmpty()) {
				nombreImagen = actor.guardarArchivo(multiPart, ruta);
				if (nombreImagen != null){ 
					// Procesamos la variable nombreImagen
					System.out.println("Imagen subida");
					System.out.println("Nombre Final:" + nombreImagen);
				}
			}
			model.addAttribute("rutaWeb","../images/"+ nombreImagen);
			System.out.println("../images/"+ nombreImagen);
			
			if(!multiPart.isEmpty()) {
				actor.setImageActor("../images/"+ nombreImagen);
			}
			
			else {
				Actor actor2 = aService.buscar(idActor);
				actor.setImageActor(actor2.getImageActor());
			}
			
	        aService.ActualizarActor(actor);
	        model.addAttribute("listActores", aService.ListarActores());
	        model.addAttribute("mensaje", "Se actualizó correctamente");
	    	}
	        return "actor/update-actor";
	    }
	
}