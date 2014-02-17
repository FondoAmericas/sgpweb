package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DetalleConcluIfDAO;
import pe.com.fondam.sgp.core.domain.DetalleConcluIf;

@Repository
public class DetalleConcluIfDAOImpl extends JpaBaseDAOImpl implements
		DetalleConcluIfDAO {

	// ***************** inyecciones **********************//
	@Autowired
	public DetalleConcluIfDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	// ***************** metodos **********************//
	@Override
	public void saveDetalleConcluIf(DetalleConcluIf detalleConcluIf) {
super.save(detalleConcluIf);
	}

	@Override
	public DetalleConcluIf updateDetalleConcluIf(DetalleConcluIf detalleConcluIf) {
		return super.update(detalleConcluIf);
	}

	@Override
	public void deleteDetalleConcluIf(DetalleConcluIf detalleConcluIf) {
		// TODO Auto-generated method stub

	}

	@Override
	public DetalleConcluIf findDetalleConcluIfById(Integer id) {
		return super.findById(DetalleConcluIf.class, id);
	}

	@Override
	public List<DetalleConcluIf> findDetalleConcluIfByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}

	
}
