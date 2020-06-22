package pe.upc.tf.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.Lista;

@Repository
public interface IListaRepository extends JpaRepository<Lista, Integer>{
	
	
	
}

	
