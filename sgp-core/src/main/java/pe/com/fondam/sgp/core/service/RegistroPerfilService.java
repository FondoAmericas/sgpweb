package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;

public interface RegistroPerfilService {

	public List<DepProvDist> findDepProvDistritos(int modo,String idDepartamento,String idProvincia,String idDistrito);
	public List<Programa> findProgramas();
	public Perfil findPerfilByDatoProyectoID(int proyectoID);
	public List<ResumenProyecto> findResumenProyecto();
	public Perfil updatePerfil(Perfil perfil);
	public void deleteResumenProyecto(Integer idResumenProyecto);
	public void saveResumenProyecto(ResumenProyecto resumenProyecto);
	public ImagenOArchivo findImagenOarchivoByIdDatoProyecto(int idProyecto);
	public ImagenOArchivo  findImagenOarchivoByPerfilId(int perfilId);
	public void deleteImagenOArchivo(ImagenOArchivo imagenOArchivo);
	public ImagenOArchivo findImagenOArchivoById(int id);
	public List<ResumenProyecto> findResumenProyectoByIdDatoProy(
			Integer datoProyectoID);
	public List<ResumenProyecto> findResumenProyectoByIdDatoProyByTablaGeneralId(
			Integer datoProyectoID,Integer tablaGeneralId);
}
