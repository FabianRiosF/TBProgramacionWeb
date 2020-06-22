package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Serie;
import pe.upc.tf.repositories.IPeliculaRepository;
import pe.upc.tf.repositories.ISerieRepository;
import pe.upc.tf.service.SerieService;

@Service
public class SerieServiceImpl implements SerieService{

	@Autowired
	private ISerieRepository serieR;
	
	@Override
	public Integer RegistrarSerie(Serie serie) {
		int rpta = serieR.buscarNombreSerie(serie.getNameSerie());
		if (rpta == 0) {
			serieR.save(serie);
		}
		return rpta;
	}

	@Override
	public void EliminarSerie(int idSerie) {
		serieR.deleteById(idSerie);
	}

	@Override
	public void ActualizarSerie(Serie serie) {
		serieR.save(serie);
	}

	@Override
	public void InsertarActorSerie(int idSerie, Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EliminarActorSerie(int idSerie, Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Serie> BuscarSerie(String nameSerie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serie BuscarSerieID(int idSerie) {
		return serieR.findById(idSerie).orElseThrow(() -> new IllegalArgumentException("Serie invalida"));
	}

	@Override
	public List<Actor> ListarActorSerie(int idSerie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Serie> ListarSeries() {
		return (List<Serie>)serieR.findAll();
	}

}
