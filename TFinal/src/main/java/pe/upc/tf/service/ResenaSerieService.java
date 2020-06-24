package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.ResenaSerie;


public interface ResenaSerieService {	
	public void CrearResenaSerie(int idSerie, int idUsuario, ResenaSerie resenaserie);
	public void EliminarResenaSerie(int idSerie, int idUsuario, ResenaSerie resenaserie);
	public void ActualizarResenaSerie(ResenaSerie resenaserie);
	public List<ResenaSerie> ListarResenasSerie(int idSerie);
}
