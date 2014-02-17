package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;

public interface DatoProyectoDAO {

	Integer saveDatoProyecto(DatoProyecto datoProyecto);

	DatoProyecto updateDatoProyecto(DatoProyecto datoProyecto);

	void deleteDatoProyecto(DatoProyecto datoProyecto);

	DatoProyecto findDatoProyectoById(Integer id);

	List<DatoProyecto> findDatoProyecto();
	
	List<DatoProyecto> findDatoProyectoByProgramaID(Integer id);
	
	public List<DatoProyecto> findDatoProyectoByProgramaIDbyNomProy(Integer idPrograma,String nomProy);
	
	public List<DatoProyecto> findDatoProyectoByCodProy(Integer idPrograma,String CodProy);

	ImagenOArchivo findImagenOarchivoByProyecto(int proyecto);
	
	public List<DatoProyecto> findDatoProyectoByNomProyByProgramaID(String nomProy, int programaID);
	
	public List<DatoProyecto> findDatoProyectoByProgramaIDbyCodProyecto(Integer idPrograma,String codProy); 
	
	public List<DatoProyecto> findDatoProyectoByCodProyByProgramaID(String CodProy, int programaID); 

}
