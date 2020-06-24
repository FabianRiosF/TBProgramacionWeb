package pe.upc.tf.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="ResenaPelicula")
public class ResenaPelicula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idResenaPelicula;
	
	@NotEmpty(message = "Debe ingresar una resena*")
	@Column(name = "textResenaPelicula", length = 1000, nullable = false)
	private String textResenaPelicula;
	
	@ManyToOne
    @JoinColumn(name = "idPelicula")
	private Pelicula pelicula;
 
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuariopeli;
	
    
    
	public int getIdResenaPelicula() {
		return idResenaPelicula;
	}


	public void setIdResenaPelicula(int idResenaPelicula) {
		this.idResenaPelicula = idResenaPelicula;
	}


	public String getTextResenaPelicula() {
		return textResenaPelicula;
	}


	public void setTextResenaPelicula(String textResenaPelicula) {
		this.textResenaPelicula = textResenaPelicula;
	}


	public Pelicula getPelicula() {
		return pelicula;
	}


	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}


	public Usuario getUsuariopeli() {
		return usuariopeli;
	}


	public void setUsuario(Usuario usuariopeli) {
		this.usuariopeli = usuariopeli;
	}

	
	public ResenaPelicula(int idResenaPelicula, String textResenaPelicula, Pelicula pelicula, Usuario usuariopeli) {
		super();
		this.idResenaPelicula = idResenaPelicula;
		this.textResenaPelicula = textResenaPelicula;
		this.pelicula = pelicula;
		this.usuariopeli = usuariopeli;
	}


	public ResenaPelicula() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idResenaPelicula;
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
		ResenaPelicula other = (ResenaPelicula) obj;
		if (idResenaPelicula != other.idResenaPelicula)
			return false;
		return true;
	}

	
	
}





