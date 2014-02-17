package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ImagenOArchivo;

public interface ImagenOArchivoDAO {
	
	void saveImagenOArchivo(ImagenOArchivo imagenOArchivo);

	ImagenOArchivo updateImagenOArchivo(ImagenOArchivo imagenOArchivo);

	void deleteImagenOArchivo(ImagenOArchivo imagenOArchivo);

	ImagenOArchivo findImagenOArchivoById(Integer id);

	ImagenOArchivo findImagenOarchivoByIdDatoProyecto(int idProyecto);

	 ImagenOArchivo  findImagenOarchivoByPerfilId(int perfilId);
	
	ImagenOArchivo findImagenOarchivoByPrograma(int programa);

	List<ImagenOArchivo> findImagenOArchivo(String queryString, Object[] params);

	List<ImagenOArchivo> findConsulta(String queryString1, Object[] params1);
}
