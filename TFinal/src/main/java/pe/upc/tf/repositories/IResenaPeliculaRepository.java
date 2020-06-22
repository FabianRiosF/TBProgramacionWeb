package pe.upc.tf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.ResenaPelicula;

@Repository
public interface IResenaPeliculaRepository extends JpaRepository<ResenaPelicula, Integer>{
	
	@Query("from ResenaPelicula rp where rp.pelicula.idPelicula = :idPelicula")
	List<ResenaPelicula> ListarResenasPelicula(@Param("idPelicula")int idPelicula);
}

	
