package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.tf.entities.Lista;
import pe.upc.tf.repositories.IListaRepository;
import pe.upc.tf.service.ListaService;


@Service
public class ListaServiceImpl implements ListaService{

	@Autowired
	private IListaRepository listaR;
	
	@Override
	public void CrearLista(Lista lista) {
		listaR.save(lista);
	}
	
	@Override
	@Transactional
	public void EliminarLista(int idLista) {
		listaR.deleteById(idLista);
	}
	
	@Override
	@Transactional
	public void InsertarPeliculaLista(int idLista, int idPelicula) {
		
	}
	@Override
	public void InsertarSerieLista(int idLista, int idSerie) {
		
	}
	@Override
	public void EliminarPeliculaLista(int idLista, int idPelicula) {
		
	}
	
	@Override
	public void EliminarSerieLista(int idLista, int idSerie) {
		
	}
	
	@Override
	public List<Lista> ListarListasUsuario(int idUsuario) {
		return listaR.ListarListasUsuario(idUsuario);
	}

	@Override
	public Lista buscar(int idLista) {
		return listaR.findById(idLista).orElseThrow(() -> new IllegalArgumentException("lista invalido"));
	}
		
}
