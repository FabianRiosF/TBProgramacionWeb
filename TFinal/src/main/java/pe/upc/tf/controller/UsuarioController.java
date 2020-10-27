package pe.upc.tf.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Usuario;
import pe.upc.tf.service.UsuarioService;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/goadmin")
	public String home(Model model, HttpSession session) {
		model.addAttribute("nombre", "Carlos");
		model.addAttribute("users", userService.list());
		session.setAttribute("tienda", "Pelis Rate");
		return "administrador";
	}

	/**
	 * Método que muestra el formulario de login personalizado.
	 * 
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
	    return "formLogin";
	}

	/**
	 * Método personalizado para cerrar la sesión del usuario
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		try {
			//objeto de Spring especial para cerrar sesión
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		//cerrando sesión
		logoutHandler.logout(request, null, null);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return "redirect:/";
		}
		return "redirect:/";
	}
	
	@GetMapping("/addUser")
	public String add(Model model, HttpServletRequest request) {
		
		model.addAttribute("user", new Usuario());
		// le pasa un user sin datos al form en blanco en th:object="${user}" y así al
		// llenar el form
		// se pueda reconocer los datos que ingresa en adduserform - binding
		return "add-user";
	}
	
	@GetMapping("/register")
	public String register(Model model, HttpServletRequest request) {
		
		model.addAttribute("user", new Usuario());
		// le pasa un user sin datos al form en blanco en th:object="${user}" y así al
		// llenar el form
		// se pueda reconocer los datos que ingresa en adduserform - binding
		return "register";
	}

	@PostMapping("/adduserform")
	public String addUser(@Valid Usuario user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}
		// Recuperamos el password en texto plano
		String pwdPlano = user.getPassword();
		System.out.println("Texto: claro:" + pwdPlano);
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		// Hacemos un set al atributo password (ya viene encriptado)
		user.setPassword(pwdEncriptado);
		user.setEstatus(1); // Activado por defecto administrador
		user.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		// Creamos el Perfil que le asignaremos al usuario nuevo

		userService.insert(user);
		model.addAttribute("mensaje", "Se registró el usuario correctamente.");
		model.addAttribute("users", userService.list());
		return "administrador";
	}
	
	@PostMapping("/addusuarioform")
	public String registerusuario(@Valid Usuario user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "error";
		}
		// Recuperamos el password en texto plano
		String pwdPlano = user.getPassword();
		System.out.println("Texto: claro:" + pwdPlano);
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		// Hacemos un set al atributo password (ya viene encriptado)
		user.setPassword(pwdEncriptado);
		user.setEstatus(2); // Activado por defecto administrador
		user.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		// Creamos el Perfil que le asignaremos al usuario nuevo

		userService.insert(user);
		return "formLogin";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Usuario user = userService.search(id);
		user.setPassword("");
		model.addAttribute("user", user);
		return "update-user";
	}
	
	@GetMapping("/edituser")
	public String showupdateuserform(Model model) {
		Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
		Usuario user = userService.findByUsername(autenticado.getName());
		user.setPassword("");
		model.addAttribute("user", user);
		return "update-usuario";
	}

	@PostMapping("/updateform/{id}")
	public String updateUser(@PathVariable("id") int id, @Valid Usuario user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		// Recuperamos el password en texto plano
		String pwdPlano = user.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		// Hacemos un set al atributo password (ya viene encriptado)
		user.setPassword(pwdEncriptado);
		user.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		// Creamos el Perfil que le asignaremos al usuario nuevo

		userService.insert(user);
		model.addAttribute("users", userService.list());
		return "administrador";
	}
	
	@PostMapping("/updateusuarioform/{id}")
	public String updateUsuario(@PathVariable("id") int id, @Valid Usuario user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		// Recuperamos el password en texto plano
		String pwdPlano = user.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		// Hacemos un set al atributo password (ya viene encriptado)
		user.setPassword(pwdEncriptado);
		user.setEstatus(2); // Activado por defecto usuario
		user.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		// Creamos el Perfil que le asignaremos al usuario nuevo

		userService.insert(user);
		model.addAttribute("users", userService.list());
		return "formLogin";
	}

    @GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		Usuario user = userService.search(id);
		userService.delete(user);
		model.addAttribute("users", userService.list());
		return "administrador";
	}

	@PostMapping("/insert/{idUsuario}")
	public String asignar(@PathVariable("idUsuario") int idUsuario) {
		userService.insert(idUsuario);
		return "administrador";
	}
	@GetMapping("/error")
	public String error() {
		return "error";
	}
}
