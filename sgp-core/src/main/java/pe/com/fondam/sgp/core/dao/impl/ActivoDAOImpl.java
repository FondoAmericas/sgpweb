package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ActivoDAO;
import pe.com.fondam.sgp.core.domain.Activo;

@Repository
public class ActivoDAOImpl extends JpaBaseDAOImpl implements ActivoDAO {

	//**********  inyecciones  *******************//
	@Autowired
	public ActivoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//**********  metodos *******************//
	@Override
	public void saveActivo(Activo activo) {
		this.save(activo);
	}

	@Override
	public Activo updateActivo(Activo activo) {
		return super.update(activo);
	}

	@Override
	public void deleteActivo(Activo activo) {
		super.delete(activo);
	}

	@Override
	public Activo findActivoById(Integer id) {
		return super.findById(Activo.class, id);
	}

	@Override
	public List<Activo> findActivos() {
		return super.find("from Activo");
	}

	@Override
	public List<Activo> findActivoByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
