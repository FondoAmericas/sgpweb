package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.ImagenOArchivo;

/**
 * 
 * @author Zolanch Távara
 */
@Repository
public class DatoProyectoDAOImpl extends JpaBaseDAOImpl implements	DatoProyectoDAO {

	@Autowired
	public DatoProyectoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public Integer saveDatoProyecto(DatoProyecto datoProyecto) {
		super.save(datoProyecto);
		System.out.println("registe proyectooo"+datoProyecto.getDatoProyectoID());
		return datoProyecto.getDatoProyectoID();

	}

	@Override
	public DatoProyecto updateDatoProyecto(DatoProyecto datoProyecto) {
		return super.update(datoProyecto);
	}

	@Override
	public void deleteDatoProyecto(DatoProyecto datoProyecto) {
		super.delete(datoProyecto);

	}

	@Override
	public DatoProyecto findDatoProyectoById(Integer id) {
		
		return super.findById(DatoProyecto.class, id);
	}

	@Override
	public List<DatoProyecto> findDatoProyecto() {
		
		return super.find("from DatoProyecto");
	}

	@Override
	public List<DatoProyecto> findDatoProyectoByProgramaID(Integer id) {
		String queryString = "from DatoProyecto where programa.programaID = ? ";
		Object[] params = new Object[1];
		params[0] = id;
		return super.find(queryString, params);
	}
	
	
	public List<DatoProyecto> findDatoProyectoByProgramaIDbyNomProy(Integer idPrograma,String nomProy) {
		String queryString = "from DatoProyecto where programa.programaID= ? and nombreProyecto like '%"+nomProy+"%'";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString, params);
	}
	
	public List<DatoProyecto> findDatoProyectoByProgramaIDbyCodProyecto(Integer idPrograma,String codProy) {
		String queryString = "from DatoProyecto where programa.programaID= ? and codigoProyecto like '%"+codProy+"%'";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString, params);
	}
	
	
	public List<DatoProyecto> findDatoProyectoByNomProyByProgramaID(String nomProy, int programaID) {
		String queryString = "from DatoProyecto where programa.programaID = ? and nombreProyecto like '%"+nomProy+"%'";
		Object[] params = new Object[1];
		params[0] = programaID;
		//return super.find(queryString);
		return super.find(queryString, params);
	}
	
	public List<DatoProyecto> findDatoProyectoByCodProyByProgramaID(String CodProy, int programaID) {
		String queryString = "from DatoProyecto where programa.programaID = ? and codigoProyecto like '%"+CodProy+"%'";
		Object[] params = new Object[1];
		params[0] = programaID;
		//return super.find(queryString);
		return super.find(queryString, params);
	}
	
	public List<DatoProyecto> findDatoProyectoByCodProy(Integer idPrograma,String CodProy) {
		String queryString = "from DatoProyecto where programa.programaID= ? and codigoProyecto like '%"+CodProy+"%'";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString, params);
	}
	
	public ImagenOArchivo findImagenOarchivoByPrograma(int programa) {
		//verifico si existe proyectos
		String queryString1 = "from ImagenOArchivo where datoProyecto.datoProyectoID= ? and datoProyecto.datoProyectoID=null";
		Object[] params1 = new Object[1];
		params1[0] = programa;
		
		List<ImagenOArchivo> list=super.find(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
	@Override
	public ImagenOArchivo findImagenOarchivoByProyecto(int proyecto) {
		//verifico si existe proyectos
		String queryString1 = "from ImagenOArchivo where  datoProyecto.datoProyectoID=? and  informeVisitaCampo.informeVisitaCampoID=null and  programa.programaID= null and reporteAvance.reporteAvanceID=null and  pagoLiquidacion.pagoLiquidacionID=null and perfil.perfilID=null";
		Object[] params1 = new Object[1];
		params1[0] = proyecto;
		
		List<ImagenOArchivo> list=super.find(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}

}
