package pe.progra.tb.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name="Actor")
public class Actor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idActor;
	
	@Column(name = "nameActor", length = 100, nullable = false)
	private String nameActor;
	
	@NotNull
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
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
		joinColumns = @JoinColumn(name = "idActor"),
		inverseJoinColumns = @JoinColumn(name = "idPelicula"))
	private List<Pelicula> Actor_Pelicula;
	
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

	
	public Actor(int idActor, String nameActor,Date dateActor, List<Serie> actor_Serie, List<Pelicula> actor_Pelicula) {
		super();
		this.idActor = idActor;
		this.nameActor = nameActor;
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





