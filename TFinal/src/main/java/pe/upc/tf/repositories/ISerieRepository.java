package pe.upc.tf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.Serie;

@Repository
public interface ISerieRepository extends JpaRepository<Serie, Integer>{
	
	@Query("from Serie p where p.nameSerie like %:nameSerie%")
	List<Serie> BuscarSerie(@Param("nameSerie")String nameSerie);
	
	@Query("select count(s.nameSerie) from Serie s where s.nameSerie =:nameSerie")
	public int buscarNombreSerie(@Param("nameSerie") String nameSerie);
}

	
