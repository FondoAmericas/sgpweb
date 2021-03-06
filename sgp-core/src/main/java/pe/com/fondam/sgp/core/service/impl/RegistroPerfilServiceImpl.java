package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DepProvDistDAO;
import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.dao.PerfilDAO;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.dao.ResumenProyectoDAO;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
import pe.com.fondam.sgp.core.service.RegistroPerfilService;

@Service
public class RegistroPerfilServiceImpl implements RegistroPerfilService{
	
	@Resource
	private DepProvDistDAO depProvDistDAO;
    @Resource
	private  ProgramaDAO programaDAO;
    
    @Resource
	private  PerfilDAO perfilDAO;
    
    @Resource
	private  ImagenOArchivoDAO imagenOArchivoDAO;
    
    @Resource
	private  ResumenProyectoDAO resumenProyectoDAO ;
    
	public List<DepProvDist> findDepProvDistritos(int modo,String idDepartamento,String idProvincia,String idDistrito) {
	    return getDepProvDistDAO().findDepProvDistritos(modo,idDepartamento,idProvincia,idDistrito);
		
	}

	public void setProgramaDAO(ProgramaDAO programaDAO) {
		this.programaDAO = programaDAO;
	}

	public ProgramaDAO getProgramaDAO() {
		return programaDAO;
	}
	
	public void setDepProvDistDAO(DepProvDistDAO depProvDistDAO) {
		this.depProvDistDAO = depProvDistDAO;
	}

	public DepProvDistDAO getDepProvDistDAO() {
		return depProvDistDAO;
	}  
	
	public List<Programa> findProgramas() {
		return programaDAO.findProgramas();
	}

	@Override
	public Perfil findPerfilByDatoProyectoID(int proyectoID) {
		
		return perfilDAO.findPerfilByDatoProyectoID(proyectoID);
	}

	@Override
	public List<ResumenProyecto> findResumenProyecto() {
		
		return resumenProyectoDAO.findResumenProyecto();
	}

	@Override
	public Perfil updatePerfil(Perfil perfil) {
		return perfilDAO.updatePerfil(perfil);
		
	}

	@Override
	public void deleteResumenProyecto(Integer idResumenProyecto) {
		ResumenProyecto resumenProyecto=resumenProyectoDAO.findResumenProyectoById(idResumenProyecto);
		resumenProyectoDAO.deleteResumenProyecto(resumenProyecto);
		
	}

	@Override
	public void saveResumenProyecto(ResumenProyecto resumenProyecto) {
		resumenProyectoDAO.saveResumenProyecto(resumenProyecto);
		
	}

	@Override
	public ImagenOArchivo findImagenOarchivoByIdDatoProyecto(int idProyecto) {
	
		return imagenOArchivoDAO.findImagenOarchivoByIdDatoProyecto(idProyecto);
	}
	
	@Override
	public  ImagenOArchivo  findImagenOarchivoByPerfilId(int perfilId) {
		return imagenOArchivoDAO.findImagenOarchivoByPerfilId(perfilId);
	}

	@Override
	public void deleteImagenOArchivo(ImagenOArchivo imagenOArchivo) {
		ImagenOArchivo imagenOAr =imagenOArchivoDAO.findImagenOArchivoById(imagenOArchivo.getImagenOArchivoID());
		imagenOArchivoDAO.deleteImagenOArchivo(imagenOAr);
		
	}
	
	ImagenOArchivo findImagenOarchivoByIdDatoProyecto(Integer id){
		return imagenOArchivoDAO.findImagenOarchivoByIdDatoProyecto(id);
	}
	
	@Override
	public ImagenOArchivo findImagenOArchivoById(int id) {

		return imagenOArchivoDAO.findImagenOArchivoById(id);
	}

	@Override
	public List<ResumenProyecto> findResumenProyectoByIdDatoProy(
			Integer datoProyectoID) {
			return resumenProyectoDAO.findResumenProyectoByIdDatoProy(datoProyectoID);
	}
	
	@Override
	public List<ResumenProyecto> findResumenProyectoByIdDatoProyByTablaGeneralId(
			Integer datoProyectoID,Integer tablaGeneralId) {
		
		String consulta = "from ResumenProyecto where datoProyecto.datoProyectoID=? and fkIdTablaGeneral=? ";
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = tablaGeneralId;
		
		return resumenProyectoDAO.findResumenProyectoByConsulta(consulta, params);
	}

}
