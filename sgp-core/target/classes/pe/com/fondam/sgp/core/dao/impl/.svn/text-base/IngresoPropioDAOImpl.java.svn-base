package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.IngresoPropioDAO;
import pe.com.fondam.sgp.core.domain.IngresoPropio;
/**
*
* @author Zolanch Távara
*/

@Repository
public class IngresoPropioDAOImpl extends JpaBaseDAOImpl implements IngresoPropioDAO {

	@Autowired
	public IngresoPropioDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveIngresoPropio(IngresoPropio ingresoPropio) {
		super.save(ingresoPropio);		
	}

	@Override
	public IngresoPropio updateIngresoPropio(IngresoPropio ingresoPropio) {
		
		return super.update(ingresoPropio);
	}

	@Override
	public void deleteIngresoPropio(IngresoPropio ingresoPropio) {
		IngresoPropio ingresoPropioNew= findIngresoPropioById(ingresoPropio.getIngresoPropioID());
		super.delete(ingresoPropioNew)	;	
	}

	@Override
	public IngresoPropio findIngresoPropioById(Integer id) {
		return super.findById(IngresoPropio.class, id);
	}

	@Override
	public List<IngresoPropio> findIngresoPropio(String queryString,Object[] params) {
		return super.find(queryString, params);
	}
	

}
