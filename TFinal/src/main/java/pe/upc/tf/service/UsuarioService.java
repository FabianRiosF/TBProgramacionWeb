package pe.upc.tf.service;

import java.util.List;

import pe.upc.tf.entities.Usuario;

public interface UsuarioService {
	public Integer RegistrarUsuario(Usuario usuario);
	public void EliminarUsuario(int idUsuario);
	public void RegistrarAdministrador(Usuario usuario);
	public void EliminarAdministrador(int idAdministrador);
	public boolean AutenticarUsuario(Usuario usuario);
	public List<Usuario> ListarUsuarios();
}

