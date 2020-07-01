package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.upc.tf.entities.ResenaSerie;
import pe.upc.tf.repositories.IResenaSerieRepository;
import pe.upc.tf.service.ResenaSerieService;

@Service
public class ResenaSerieServiceImpl implements ResenaSerieService{

	@Autowired
	private IResenaSerieRepository resenaSR;
	
	@Override
	public void CrearResenaSerie(ResenaSerie resenaserie) {
		resenaSR.save(resenaserie);
	}

	@Override
	public void EliminarResenaSerie(int idSerie, int idUsuario, ResenaSerie resenaserie) {
		resenaSR.delete(resenaserie);
		
	}

	@Override
	public void ActualizarResenaSerie(ResenaSerie resenaserie) {
		resenaSR.save(resenaserie);
		
	}

	@Override
	public List<ResenaSerie> ListarResenasSerie(int idSerie) {
		return resenaSR.ListarResenasSerie(idSerie);
	}

}
