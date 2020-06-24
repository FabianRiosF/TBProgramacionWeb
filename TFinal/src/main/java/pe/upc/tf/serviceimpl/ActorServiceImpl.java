package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.repositories.IActorRepository;
import pe.upc.tf.service.ActorService;


@Service
public class ActorServiceImpl implements ActorService{

	@Autowired
	private IActorRepository actorR;
	
	@Override
	public Integer RegistrarActor(Actor actor) {
		int rpta = actorR.buscarNombreActor(actor.getNameActor());
		if (rpta == 0) {
			actorR.save(actor);
		}
		return rpta;
	}
	
	@Override
	public List<Actor> ListarActores() {
		return (List<Actor>)actorR.findAll();
	}
	
	@Override
	@Transactional
	public void EliminarActor(int idActor) {
		actorR.deleteById(idActor);
	}
	
	@Override
	public void ActualizarActor(Actor actor) {
		actorR.save(actor);
	}
	
	@Override
	public List<Actor> BuscarActor(String nameActor) {
		return actorR.BuscarActor(nameActor);
	}
	
	@Override
	public Actor buscar(int idActor) {
		return actorR.findById(idActor).orElseThrow(() -> new IllegalArgumentException("Actor invalido"));
	}
	
	
		
}
