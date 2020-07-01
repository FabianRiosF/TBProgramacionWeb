package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.ResenaPelicula;
import pe.upc.tf.repositories.IResenaPeliculaRepository;
import pe.upc.tf.service.ResenaPeliculaService;

@Service
public class ResenaPeliculaServiceImpl implements ResenaPeliculaService{

	@Autowired
	private IResenaPeliculaRepository resenaPR;

	@Override
	public void CrearResenaPelicula(ResenaPelicula resenapelicula) {
		resenaPR.save(resenapelicula);
		
	}

	@Override
	public void EliminarResenaPelicula(int idPelicula, int idUsuario, ResenaPelicula resenapelicula) {
		resenaPR.delete(resenapelicula);
		
	}

	@Override
	public void ActualizarResenaPelicula(ResenaPelicula resenapelicula) {
		resenaPR.save(resenapelicula);
		
	}

	@Override
	public List<ResenaPelicula> ListarResenasPelicula(int idPelicula) {
		return resenaPR.ListarResenasPelicula(idPelicula);
	}
	
	
}
