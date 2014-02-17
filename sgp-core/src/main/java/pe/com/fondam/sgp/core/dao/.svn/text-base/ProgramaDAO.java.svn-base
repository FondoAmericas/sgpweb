package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Programa;

/**
 * 
 * @author Zolanch Távara
 */
public interface ProgramaDAO {

	Integer savePrograma(Programa programa);

	Programa updatePrograma(Programa programa);

	void deletePrograma(Programa programa);

	Programa findProgramaById(Integer id);

	List<Programa> findProgramas();
	
	List<Programa>findProgramaByFiltro(Programa programa,Integer idFiltro);
	
	public List<Programa> findProgramaByNombre(String nombrePrograma);
	
	public List<Programa> findProgramaByModFinan(int modFinan);
	
	public List<Programa> findProgramaByTipoCuenta(int tipoCuenta);


}
