package pe.upc.tf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.upc.tf.entities.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
  List<Usuario> findByNombre(String name);
  @Query("SELECT u.id FROM Usuario u WHERE u.username =:name")
  int ReturnID(@Param("name") String name);
}
