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
import javax.persistence.Entity;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="Pelicula")
public class Pelicula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPelicula;
	
	@NotEmpty(message = "Debe ingresar nombre de pel[icula*")
	@Column(name = "namePelicula", length = 100, nullable = false)
	private String namePelicula;
	
	@NotNull
	@Past(message = "La fecha debe estar en el pasado")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datePelicula")
	private Date datePelicula;
	
	@Column(name = "imagePelicula", length = 300)
	private String imagePelicula;
	
	@ManyToMany
	@JoinTable (
		name = "Actor_Pelicula",
		joinColumns = @JoinColumn(name = "idPelicula", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "idActor" , nullable = false))
	private List<Actor> Actores;
	
	@ManyToMany (mappedBy = "Lista_Pelicula")
	private List<Lista> Listas;
	
	@OneToMany(mappedBy = "pelicula")
	private List<ResenaPelicula> peliculas;

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
	
	public List<Lista> getListas() {
		return Listas;
	}


	public void setListas(List<Lista> listas) {
		Listas = listas;
	}


	public List<ResenaPelicula> getPeliculas() {
		return peliculas;
	}


	public void setPeliculas(List<ResenaPelicula> peliculas) {
		this.peliculas = peliculas;
	}


	public int getIdPelicula() {
		return idPelicula;
	}
	


	public String getImagePelicula() {
		return imagePelicula;
	}
	public void setImagePelicula(String imagePelicula) {
		this.imagePelicula = imagePelicula;
	}
	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}


	public String getNamePelicula() {
		return namePelicula;
	}


	public void setNamePelicula(String namePelicula) {
		this.namePelicula = namePelicula;
	}


	public Date getDatePelicula() {
		return datePelicula;
	}


	public void setDatePelicula(Date datePelicula) {
		this.datePelicula = datePelicula;
	}


	public List<Actor> getActores() {
		return Actores;
	}


	public void setActores(List<Actor> actores) {
		Actores = actores;
	}
	
	

	public Pelicula(int idPelicula, String namePelicula, Date datePelicula, String imagePelicula,
			List<Actor> actores, List<Lista> listas, List<ResenaPelicula> peliculas) {
		super();
		this.idPelicula = idPelicula;
		this.namePelicula = namePelicula;
		this.datePelicula = datePelicula;
		this.imagePelicula = imagePelicula;
		Actores = actores;
		Listas = listas;
		this.peliculas = peliculas;
	}
	public Pelicula() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPelicula;
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
		Pelicula other = (Pelicula) obj;
		if (idPelicula != other.idPelicula)
			return false;
		return true;
	}


	
}





