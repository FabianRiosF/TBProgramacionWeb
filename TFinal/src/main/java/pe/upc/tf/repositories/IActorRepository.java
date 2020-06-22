package pe.upc.tf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.Actor;

@Repository
public interface IActorRepository extends JpaRepository<Actor, Integer>{
	@Query("from Actor a where a.nameActor like %:nameActor%")
	List<Actor> BuscarActor(@Param("nameActor") String nameActor);
	
	@Query("select count(a.nameActor) from Actor a where a.nameActor =:nameActor")
	public int buscarNombreActor(@Param("nameActor") String nameActor);
}

	
