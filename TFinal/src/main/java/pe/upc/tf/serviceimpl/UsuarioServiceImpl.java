package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.tf.entities.Usuario;
import pe.upc.tf.repositories.UsuarioRepository;
import pe.upc.tf.service.UsuarioService;

@Service 
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;   
	
	@Override
	@Transactional
	public Usuario insert(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Override
	@Transactional
	public Usuario insert(int idUsuario) {
		Usuario usuario = usuarioRepository.findById(idUsuario).get();	
		if(usuario!=null)
			return usuarioRepository.save(usuario);
		else
			return null;
	}

	@Override
	public List<Usuario> list() {
		return (List<Usuario>)usuarioRepository.findAll();
	}

	@Override
	public Usuario search(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario invalido"));
	}

	@Override
	public List<Usuario> findByName(String name) {
		return usuarioRepository.findByNombre(name);
	}

	@Override
	@Transactional
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);	
	}
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findById(usuarioRepository.ReturnID(username)).orElseThrow(() -> new IllegalArgumentException("Usuario invalido"));
	}

}
