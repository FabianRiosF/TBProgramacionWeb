package pe.upc.tf.serviceimpl;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.repositories.IPeliculaRepository;
import pe.upc.tf.service.PeliculaService;

@Service
public class PeliculaServiceImpl implements PeliculaService{

	@Autowired
	private IPeliculaRepository peliculaR;
	
	@Override
	public Integer RegistrarPelicula(Pelicula pelicula) {
		int rpta = peliculaR.buscarNombrePelicula(pelicula.getNamePelicula());
		if (rpta == 0) {
			peliculaR.save(pelicula);
		}
		return rpta;
	}
	
	@Transactional
	@Override
	public void EliminarPelicula(int idPelicula) {
		peliculaR.deleteById(idPelicula);
		
	}

	@Override
	public void ActualizarPelicula(Pelicula pelicula) {
		peliculaR.save(pelicula);
		
	}

	@Override
	public void InsertarActorPelicula(int idPelicula, Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EliminarActorPelicula(int idPelicula, Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Pelicula> BuscarPelicula(String namePelicula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> ListarActorPeliculas(int idPelicula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pelicula> ListarPeliculas() {
		return (List<Pelicula>)peliculaR.findAll();
	}

	@Override
	public Pelicula BuscarPeliculaID(int idPelicula) {
		return peliculaR.findById(idPelicula).orElseThrow(() -> new IllegalArgumentException("Pelicula invalido"));
	}

	@Override
	public List<Pelicula> ListarBusquedaPeliculas(String busqueda) {
		return (List<Pelicula>)peliculaR.busquedapelicula(busqueda);
	}

}
