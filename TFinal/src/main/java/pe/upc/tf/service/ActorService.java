package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.Actor;


public interface ActorService {	
	public Integer RegistrarActor(Actor actor);
	public List<Actor> ListarActores();
	public void EliminarActor(int idActor);
	public void ActualizarActor(Actor actor);
	public List<Actor> BuscarActor(String nameActor);
	public Actor buscar(int idActor);
}
