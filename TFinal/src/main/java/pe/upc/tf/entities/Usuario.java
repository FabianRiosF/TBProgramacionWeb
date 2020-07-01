package pe.upc.tf.entities;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment MySQL
	private Integer id;
	
	private String username;
	@NotBlank(message = "Nombre es mandatorio")
	private String nombre;
	@Email
	@NotBlank(message = "Email es mandatorio")
	private String email;
	@NotBlank(message = "Password es mandatorio")
	private String password;
	private Integer estatus;	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	
	@OneToMany(mappedBy = "usuario")
	private List<ResenaPelicula> resenas;

	public Integer getId() {
		return id;
	}

	
	
	public List<ResenaPelicula> getResenas() {
		return resenas;
	}



	public void setResenas(List<ResenaPelicula> resenas) {
		this.resenas = resenas;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", nombre=" + nombre + ", email=" + email
				+ ", password=" + password + ", estatus=" + estatus + ", fechaRegistro=" + fechaRegistro + "]";
	}
	
}
