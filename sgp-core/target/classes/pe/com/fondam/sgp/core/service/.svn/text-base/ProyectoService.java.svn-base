package pe.com.fondam.sgp.core.service;



import java.util.List;
import java.util.Map;

import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Institucion;

public interface ProyectoService {

	ImagenOArchivo findProyetoByArchivoImagen(Integer datoProyectoID);

	void saveProyetoByArchivoImagen(ImagenOArchivo imagenOArchivo);

	DatoProyecto findDatoProyectoById(Integer datoProyectoID);

	boolean saveGestionProyecto(DatoProyecto datoProyecto, Map<String , String> params);

	List<Institucion> getInstitucionesFinanciadorasByProyectoId(Integer datoProyectoID);





}
