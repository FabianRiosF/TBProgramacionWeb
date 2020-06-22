package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.ResenaPelicula;


public interface ResenaPeliculaService {	
	public void CrearResenaPelicula(int idPelicula, int idUsuario, ResenaPelicula resenapelicula);
	public void EliminarResenaPelicula(int idPelicula, int idUsuario, ResenaPelicula resenapelicula);
	public void ActualizarResenaPelicula(ResenaPelicula resenapelicula);
	public List<ResenaPelicula> ListarResenasPelicula(int idPelicula);
}
