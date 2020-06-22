package pe.upc.tf.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


@Entity
@Table(name="Lista")
public class Lista implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLista;
	
	@NotEmpty(message = "Debe ingresar nombre*")
	@Column(name = "nameLista", length = 50, nullable = false)
	private String nameLista;
	
	@NotNull
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateLista")
	private Date dateLista;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToMany
	@JoinTable (
		name = "Lista_Serie",
		joinColumns = @JoinColumn(name = "idLista"),
		inverseJoinColumns = @JoinColumn(name = "idSerie"))
	private List<Serie> Lista_Serie;
	
	@ManyToMany
	@JoinTable (
		name = "Lista_Pelicula",
		joinColumns = @JoinColumn(name = "idLista"),
		inverseJoinColumns = @JoinColumn(name = "idPelicula"))
	private List<Pelicula> Lista_Pelicula;

	
	public int getIdLista() {
		return idLista;
	}


	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}


	public String getNameLista() {
		return nameLista;
	}


	public void setNameLista(String nameLista) {
		this.nameLista = nameLista;
	}


	public Date getDateLista() {
		return dateLista;
	}


	public void setDateLista(Date dateLista) {
		this.dateLista = dateLista;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Serie> getLista_Serie() {
		return Lista_Serie;
	}


	public void setLista_Serie(List<Serie> lista_Serie) {
		Lista_Serie = lista_Serie;
	}


	public List<Pelicula> getLista_Pelicula() {
		return Lista_Pelicula;
	}


	public void setLista_Pelicula(List<Pelicula> lista_Pelicula) {
		Lista_Pelicula = lista_Pelicula;
	}
	
	
	public Lista(int idLista, String nameLista,Date dateLista, Usuario usuario,List<Serie> lista_Serie, List<Pelicula> lista_Pelicula) {
		super();
		this.idLista = idLista;
		this.nameLista = nameLista;
		this.dateLista = dateLista;
		this.usuario = usuario;
		Lista_Serie = lista_Serie;
		Lista_Pelicula = lista_Pelicula;
	}

	
	public Lista() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLista;
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
		Lista other = (Lista) obj;
		if (idLista != other.idLista)
			return false;
		return true;
	}

	
}





