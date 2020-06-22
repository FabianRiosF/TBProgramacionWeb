package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Actor;

public interface PeliculaService {	
	public Integer RegistrarPelicula(Pelicula pelicula);
	public void EliminarPelicula(int idPelicula);
	public void ActualizarPelicula(Pelicula pelicula);
	public void InsertarActorPelicula(int idPelicula, Actor actor);
	public void EliminarActorPelicula(int idPelicula, Actor actor);
	public List<Pelicula> BuscarPelicula(String namePelicula);
	public Pelicula BuscarPeliculaID(int idPelicula);
	public List<Actor> ListarActorPeliculas(int idPelicula);
	public List<Pelicula> ListarPeliculas();
}
