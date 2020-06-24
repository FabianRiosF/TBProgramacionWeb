package pe.upc.tf.entities;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
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
@Table(name="Serie")
public class Serie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSerie;
	
	@NotEmpty(message = "Debe ingresar nombre de serie*")
	@Column(name = "nameSerie", length = 100, nullable = false)
	private String nameSerie;
	
	@NotNull
	@Past(message = "La fecha debe estar en el pasado")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateSerie")
	private Date dateSerie;
	
	@Column(name = "imageSerie", length = 300)
	private String imageSerie;
	
	@ManyToMany
	@JoinTable (
		name = "Actor_Serie",
		joinColumns = @JoinColumn(name = "idSerie", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "idActor" , nullable = false))
	private List<Actor> Actores;
	
	@ManyToMany (mappedBy = "Lista_Serie")
	private List<Lista> Listas;
	
	@OneToMany(mappedBy = "serie")
	private List<ResenaSerie> series;
	
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
	
	public int getIdSerie() {
		return idSerie;
	}


	public List<Lista> getListas() {
		return Listas;
	}


	public void setListas(List<Lista> listas) {
		Listas = listas;
	}


	public String getImageSerie() {
		return imageSerie;
	}
	public void setImageSerie(String imageSerie) {
		this.imageSerie = imageSerie;
	}
	public List<ResenaSerie> getSeries() {
		return series;
	}


	public void setSeries(List<ResenaSerie> series) {
		this.series = series;
	}


	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}


	public String getNameSerie() {
		return nameSerie;
	}


	public void setNameSerie(String nameSerie) {
		this.nameSerie = nameSerie;
	}


	public Date getDateSerie() {
		return dateSerie;
	}


	public void setDateSerie(Date dateSerie) {
		this.dateSerie = dateSerie;
	}


	public List<Actor> getActores() {
		return Actores;
	}


	public void setActores(List<Actor> actores) {
		Actores = actores;
	}
	
	
	

	

	public Serie(int idSerie,String nameSerie,Date dateSerie, String imageSerie,
			List<Actor> actores, List<Lista> listas, List<ResenaSerie> series) {
		super();
		this.idSerie = idSerie;
		this.nameSerie = nameSerie;
		this.dateSerie = dateSerie;
		this.imageSerie = imageSerie;
		Actores = actores;
		Listas = listas;
		this.series = series;
	}
	public Serie() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSerie;
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
		Serie other = (Serie) obj;
		if (idSerie != other.idSerie)
			return false;
		return true;
	}
	
}





