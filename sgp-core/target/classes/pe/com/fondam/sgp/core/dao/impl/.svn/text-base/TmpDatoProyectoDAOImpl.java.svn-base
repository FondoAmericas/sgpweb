package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpDatoProyectoDAO;
import pe.com.fondam.sgp.core.domain.TmpDatoProyecto;

@Repository
public class TmpDatoProyectoDAOImpl extends JpaBaseDAOImpl implements TmpDatoProyectoDAO{

	@Autowired
	public TmpDatoProyectoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpDatoProyecto(TmpDatoProyecto tmpDatoProyecto) {
		super.save(tmpDatoProyecto);

	}

	public TmpDatoProyecto updateTmpDatoProyecto(TmpDatoProyecto tmpDatoProyecto) {
		return super.update(tmpDatoProyecto) ;
	}

	
	public void deleteTmpDatoProyecto(TmpDatoProyecto tmpDatoProyecto) {
		delete(tmpDatoProyecto);

	}


	public TmpDatoProyecto findTmpDatoProyectoById(Integer id) {	
		
		return super.findById(TmpDatoProyecto.class, id);
		
	}
	
	public List<TmpDatoProyecto> findTmpDatoProyecto() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TmpDatoProyecto> findTmpDatoProyectoByProgramaID(Integer idPrograma) {
		String queryString = "from TmpDatoProyecto where programa.programaID= ?";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString,params);
		
	}
	                    
	public List<TmpDatoProyecto> findTmpDatoProyectoByProgramaIDMaxNroOrdenDatoProyecto(Integer idPrograma) {
		String queryString = "FROM TmpDatoProyecto WHERE programa.programaID=? ORDER BY numeroOrdenDatoProyecto DESC LIMIT 1";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString,params);
		
	}
	
	public List<TmpDatoProyecto> findTmpDatoProyectoByNomProyectoAndProgramaID(String nombreProyecto, Integer idPrograma) {
		String queryString = "FROM TmpDatoProyecto WHERE nombreProyecto=? and programa.programaID=?  ORDER BY codigoProyecto ASC LIMIT 1";
		Object[] params = new Object[2];
		params[0] = nombreProyecto;
		params[1] = idPrograma;
		return super.find(queryString,params);
		
	}
}
