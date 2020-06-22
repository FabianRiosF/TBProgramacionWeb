package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.Serie;
import pe.upc.tf.entities.Actor;

public interface SerieService {
	public Integer RegistrarSerie(Serie serie);
	public void EliminarSerie(int idSerie);
	public void ActualizarSerie(Serie serie);
	public void InsertarActorSerie(int idSerie, Actor actor);
	public void EliminarActorSerie(int idSerie, Actor actor);
	public List<Serie> BuscarSerie(String nameSerie);
	public Serie BuscarSerieID(int idSerie);
	public List<Actor> ListarActorSerie(int idSerie);
	public List<Serie> ListarSeries();

}
