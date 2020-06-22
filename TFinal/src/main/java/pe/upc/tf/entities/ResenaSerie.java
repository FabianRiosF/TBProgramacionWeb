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
@Table(name="ResenaSerie")
public class ResenaSerie implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idResenaSerie;
	
	@NotEmpty(message = "Debe ingresar una resena*")
	@Column(name = "textResenaSerie", length = 1000, nullable = false)
	private String textResenaSerie;
	
	@ManyToOne
    @JoinColumn(name = "idSerie")
	private Serie serie;
 
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuarioserie;

	public int getIdResenaSerie() {
		return idResenaSerie;
	}

	public void setIdResenaSerie(int idResenaSerie) {
		this.idResenaSerie = idResenaSerie;
	}

	public String getTextResenaSerie() {
		return textResenaSerie;
	}

	public void setTextResenaSerie(String textResenaSerie) {
		this.textResenaSerie = textResenaSerie;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Usuario getUsuarioserie() {
		return usuarioserie;
	}

	public void setUsuarioserie(Usuario usuarioserie) {
		this.usuarioserie = usuarioserie;
	}

	public ResenaSerie(int idResenaSerie, String textResenaSerie, Serie serie, Usuario usuarioserie) {
		super();
		this.idResenaSerie = idResenaSerie;
		this.textResenaSerie = textResenaSerie;
		this.serie = serie;
		this.usuarioserie = usuarioserie;
	}

	public ResenaSerie() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idResenaSerie;
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
		ResenaSerie other = (ResenaSerie) obj;
		if (idResenaSerie != other.idResenaSerie)
			return false;
		return true;
	}
	
	
}





