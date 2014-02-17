package pe.com.fondam.sgp.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpInstitucionDAO;
import pe.com.fondam.sgp.core.domain.TmpInstitucion;

@Repository
public class TmpInstitucionDAOImpl extends JpaBaseDAOImpl implements TmpInstitucionDAO{

	@Autowired
	public TmpInstitucionDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpInstitucion(TmpInstitucion tmpInstitucion) {
		super.save(tmpInstitucion);

	}

	public TmpInstitucion updateTmpInstitucion(TmpInstitucion tmpInstitucion) {
	
		return super.update(tmpInstitucion);
	}

	
	public void deleteTmpInstitucion(TmpInstitucion tmpInstitucion) {
		super.delete(tmpInstitucion);

	}


	public TmpInstitucion findTmpInstitucionById(Integer id) {
		return super.findById(TmpInstitucion.class, id);
	}

	

}
