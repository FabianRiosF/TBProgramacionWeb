package pe.upc.tf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.Actor;
import pe.upc.tf.entities.Pelicula;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer>{
	
	@Query("from Pelicula p where p.namePelicula like %:busqueda%")
	public List<Pelicula> busquedapelicula(@Param("busqueda") String busqueda);
	
	@Query("select count(p.namePelicula) from Pelicula p where p.namePelicula =:namePelicula")
	public int buscarNombrePelicula(@Param("namePelicula") String namePelicula);
}

	
