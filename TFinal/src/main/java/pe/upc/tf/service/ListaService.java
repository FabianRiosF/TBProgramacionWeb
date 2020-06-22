package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Serie;
import pe.upc.tf.entities.Lista;


public interface ListaService {	
	public void CrearLista(Lista lista);
	public void EliminarLista(int idLista);
	public void InsertarPeliculaLista(int idLista, int idPelicula);
	public void InsertarSerieLista(int idLista, int idSerie);
	public void EliminarPeliculaLista(int idLista, int idPelicula);
	public void EliminarSerieLista(int idLista, int idSerie);
	public List<Lista> ListarListasUsuario(int idUsuario);
}
