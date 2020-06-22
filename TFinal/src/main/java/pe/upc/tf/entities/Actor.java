package pe.upc.tf.entities;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="Actor")
public class Actor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idActor;
	
	@NotEmpty(message = "Debe ingresar nombre*")
	@Column(name = "nameActor", length = 100, nullable = false)
	private String nameActor;
	
	@Column(name = "imageActor", length = 300, nullable=true)
	private String imageActor;
	
	@NotNull(message = "Debe ingresar fecha*")
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateActor")
	private Date dateActor;
	
	
	@ManyToMany
	@JoinTable (
		name = "Actor_Serie",
		joinColumns = @JoinColumn(name = "idActor"),
		inverseJoinColumns = @JoinColumn(name = "idSerie"))
	private List<Serie> Actor_Serie;
	
	@ManyToMany
	@JoinTable (
		name = "Actor_Pelicula",
		joinColumns = @JoinColumn(name = "idActor", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "idPelicula" , nullable = false))
	private List<Pelicula> Actor_Pelicula;
	
	public void addPelicula(Pelicula pelicula) {
        if (this.Actor_Pelicula == null) {
            this.Actor_Pelicula = new ArrayList<>();
        }

        this.Actor_Pelicula.add(pelicula);
    }
	
	
	public static String guardarArchivo(MultipartFile multiPart, String ruta) {  
		// Obtenemos el nombre original del archivo.
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");
		Path p = Paths.get(nombreOriginal);
		String name = p.getFileName().toString();	
		String nombreFinal = randomAlphaNumeric(8)+name;
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro.
			File imageFile = new File(ruta+ nombreFinal);
			System.out.println("Archivo: " + imageFile.getAbsolutePath());
			//Guardamos fisicamente el archivo en HD.
			multiPart.transferTo(imageFile);
			return nombreFinal;
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
			return null;
		}
	}
	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));
		}
		return builder.toString();
	}
	
	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public String getNameActor() {
		return nameActor;
	}

	public void setNameActor(String nameActor) {
		this.nameActor = nameActor;
	}
	

	public String getImageActor() {
		return imageActor;
	}

	public void setImageActor(String imageActor) {
		this.imageActor = imageActor;
	}

	public Date getDateActor() {
		return dateActor;
	}

	public void setDateActor(Date dateActor) {
		this.dateActor = dateActor;
	}

	public List<Serie> getActor_Serie() {
		return Actor_Serie;
	}

	public void setActor_Serie(List<Serie> actor_Serie) {
		Actor_Serie = actor_Serie;
	}

	public List<Pelicula> getActor_Pelicula() {
		return Actor_Pelicula;
	}

	public void setActor_Pelicula(List<Pelicula> actor_Pelicula) {
		Actor_Pelicula = actor_Pelicula;
	}

	
	

	
	public Actor(int idActor, String nameActor, String imageActor,Date dateActor, List<Serie> actor_Serie,
			List<Pelicula> actor_Pelicula) {
		super();
		this.idActor = idActor;
		this.nameActor = nameActor;
		this.imageActor = imageActor;
		this.dateActor = dateActor;
		Actor_Serie = actor_Serie;
		Actor_Pelicula = actor_Pelicula;
	}

	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idActor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Actor other = (Actor) obj;
		if (idActor != other.idActor)
			return false;
		return true;
	}

	
	
}





