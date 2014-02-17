package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.InspectorDAO;
import pe.com.fondam.sgp.core.domain.Inspector;
/**
*
* @author Zolanch Távara
*/
@Repository
public class InspectorDAOImpl extends JpaBaseDAOImpl  implements InspectorDAO {

	@Autowired
	public InspectorDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	@Override
	public void saveInspector(Inspector inspector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inspector updateInspector(Inspector inspector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInspector(Inspector inspector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Inspector findInspectorById(Integer id) {
	
		return findById(Inspector.class, id);
	}

	@Override
	public List<Inspector> findInspectores() {
		// TODO Auto-generated method stub
		return null;
	}

}
