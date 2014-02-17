package pe.com.fondam.sgp.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.EstadoTransaccionDAO;
import pe.com.fondam.sgp.core.domain.EstadoTransaccion;

@Repository
public class EstadoTransaccionDAOImpl extends JpaBaseDAOImpl implements
		EstadoTransaccionDAO {

	@Autowired
	public EstadoTransaccionDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveEstadoTransaccion(EstadoTransaccion estadoTransaccion) {
		super.save(estadoTransaccion);
	}

}
