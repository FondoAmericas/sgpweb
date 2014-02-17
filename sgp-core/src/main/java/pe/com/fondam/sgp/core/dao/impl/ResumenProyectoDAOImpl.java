package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ResumenProyectoDAO;
import pe.com.fondam.sgp.core.domain.ResumenProyecto;
/**
*
* 
*/
@Repository
public class ResumenProyectoDAOImpl extends JpaBaseDAOImpl  implements ResumenProyectoDAO {
	//**********inyecciones ***********/
	@Autowired
	public ResumenProyectoDAOImpl (@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//**********inyecciones ***********/
	@Override
	public void saveResumenProyecto(ResumenProyecto resumenProyecto) {
		super.save(resumenProyecto);
		
	}

	@Override
	public ResumenProyecto updateResumenProyecto(ResumenProyecto resumenProyecto) {
		return super.update(resumenProyecto);
	}

	@Override
	public void deleteResumenProyecto(ResumenProyecto resumenProyecto) {
		delete(resumenProyecto);
		
	}

	@Override
	public ResumenProyecto findResumenProyectoById(Integer id) {

		return super.findById(ResumenProyecto.class, id);
	}

	@Override
	public List<ResumenProyecto> findResumenProyecto() {
		
		return super.find("from ResumenProyecto");
	}
	
	@Override
	public List<ResumenProyecto> findResumenProyectoByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}
	
	public List<ResumenProyecto> findResumenProyectoByIdDatoProy(Integer id) {
		String queryString = "from ResumenProyecto where datoProyecto.datoProyectoID=?";
		Object[] params = new Object[1];
		params[0] = id;
		return super.find(queryString, params);
		
	}

}
