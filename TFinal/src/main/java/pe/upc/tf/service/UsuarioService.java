package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.Usuario;

public interface UsuarioService {
   public Usuario insert(Usuario usuario);
   public List<Usuario> list();
   public Usuario search(Integer id);
   public List<Usuario> findByName(String name);
   public void delete(Usuario usuario);
   public Usuario insert(int idUsuario);
   public Usuario findByUsername(String username);
}
