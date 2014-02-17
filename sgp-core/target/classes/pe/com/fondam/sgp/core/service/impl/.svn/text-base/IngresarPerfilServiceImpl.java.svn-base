package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActividadPerfilDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.ImagenOArchivoDAO;
import pe.com.fondam.sgp.core.dao.PerfilDAO;
import pe.com.fondam.sgp.core.dao.SubAreaTematicaDAO;
import pe.com.fondam.sgp.core.dao.TmpDatoProyectoDAO;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.Perfil;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.domain.TablaEspecifica;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;
import pe.com.fondam.sgp.core.service.IngresarPerfilService;

@Service
public class IngresarPerfilServiceImpl implements IngresarPerfilService {

	@Resource
	private DatoProyectoDAO datoProyectoDAO;

	@Resource
	private SubAreaTematicaDAO subAreaTematicaDAO;

	@Resource
	private PerfilDAO perfilDAO;

	@Resource
	private ActividadPerfilDAO actividadPerfilDAO;

	@Resource
	private ImagenOArchivoDAO imagenOArchivoDAO;
	
	@Resource
	private TmpDatoProyectoDAO tmpDatoProyectoDAO;
	
	public DatoProyectoDAO getDatoProyectoDAO() {
		return datoProyectoDAO;
	}

	public void setDatoProyectoDAO(DatoProyectoDAO datoProyectoDAO) {
		this.datoProyectoDAO = datoProyectoDAO;
	}

	public SubAreaTematicaDAO getSubAreaTematicaDAO() {
		return subAreaTematicaDAO;
	}

	public void setSubAreaTematicaDAO(SubAreaTematicaDAO subAreaTematicaDAO) {
		this.subAreaTematicaDAO = subAreaTematicaDAO;
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public ActividadPerfilDAO getActividadPerfilDAO() {
		return actividadPerfilDAO;
	}

	public void setActividadPerfilDAO(ActividadPerfilDAO actividadPerfilDAO) {
		this.actividadPerfilDAO = actividadPerfilDAO;
	}

	public ImagenOArchivoDAO getImagenOArchivoDAO() {
		return imagenOArchivoDAO;
	}

	public void setImagenOArchivoDAO(ImagenOArchivoDAO imagenOArchivoDAO) {
		this.imagenOArchivoDAO = imagenOArchivoDAO;
	}
	
	public void setTmpDatoProyectoDAO(TmpDatoProyectoDAO tmpDatoProyectoDAO) {
		this.tmpDatoProyectoDAO = tmpDatoProyectoDAO;
	}

	public TmpDatoProyectoDAO getTmpDatoProyectoDAO() {
		return tmpDatoProyectoDAO;
	}
	

	@Override
	public List<TablaEspecifica> listAreaTematica() {

		return null;
	}

	@Override
	public List<SubAreaTematica> listSubAreaTematica() {
		
		return subAreaTematicaDAO.findSubAreaTematica();
	}

	@Override
	public List<TipoActividadObligatoriaPrograma> findTipoActividadObligatoriaProgramasByIdtablaespTipo(
			Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TmpDatoProyecto> findTmpDatoProyectoByProgramaIDMaxNroOrdenDatoProyecto(Integer idPrograma) {
		return tmpDatoProyectoDAO.findTmpDatoProyectoByProgramaIDMaxNroOrdenDatoProyecto(idPrograma);
	}
	

		@Override
	public Perfil findPerfilByDatoProyectoID(int proyectoID) {
		
		return findPerfilByDatoProyectoID(proyectoID);
	}

		@Override
		public void saveRestriccionPrograma(
				RestricionPrograma restricionPrograma) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void saveActividadObligatoriaPrograma(
				ActividadObligatoriaPrograma actividadObligatoriaPrograma) {
			// TODO Auto-generated method stub
			
		}
		
		public List<TmpDatoProyecto> findTmpDatoProyectoByNomProyectoAndProgramaID(String nombreProyecto, Integer idPrograma){
			return tmpDatoProyectoDAO.findTmpDatoProyectoByNomProyectoAndProgramaID(nombreProyecto, idPrograma);
			
		}




	


}
