package pe.upc.tf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.ResenaSerie;

@Repository
public interface IResenaSerieRepository extends JpaRepository<ResenaSerie, Integer>{
	
	@Query("from ResenaSerie rs where rs.serie.idSerie = :idSerie")
	List<ResenaSerie> ListarResenasSerie(@Param("idSerie")int idSerie);
}

	
