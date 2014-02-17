package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ObservacionDAO;
import pe.com.fondam.sgp.core.domain.Observacion;

@Repository
public class ObservacionDAOImpl extends JpaBaseDAOImpl implements ObservacionDAO {

	//******************* inyecciones *********************//
	@Autowired
	public ObservacionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//******************* metodos *********************//	
	@Override
	public void saveObservacion(Observacion observacion) {
		super.save(observacion);
	}

	@Override
	public Observacion updateObservacion(Observacion observacion) {
		return super.update(observacion);
	}

	@Override
	public void delete(Observacion observacion) {
		
	}

	@Override
	public Observacion findObservacionById(Integer id) {
		return super.findById(Observacion.class, id);
	}

	@Override
	public List<Observacion> findObservacionByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}

}
