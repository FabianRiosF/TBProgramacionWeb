package pe.upc.tf.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upc.tf.entities.Lista;
import pe.upc.tf.entities.Pelicula;
import pe.upc.tf.entities.Serie;
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
	
	public void InsertarPeliculaLista(int idLista, int idPelicula) {
		
	}
	
	public void InsertarSerieLista(int idLista, int idSerie) {
		
	}
	
	public void EliminarPeliculaLista(int idLista, int idPelicula) {
		
	}
	
	public void EliminarSerieLista(int idLista, int idSerie) {
		
	}
	
	public List<Lista> ListarListasUsuario(int idUsuario) {
		return null;
	}
		
}
