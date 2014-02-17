package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.EfectoProyectoDAO;
import pe.com.fondam.sgp.core.domain.EfectoProyecto;


@Repository
public class EfectoProyectoDAOImpl extends JpaBaseDAOImpl implements EfectoProyectoDAO {

//***************** inyecciones  *****************//
	@Autowired
	public EfectoProyectoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//***************** metodos *****************//
	@Override
	public void saveEfectoProyecto(EfectoProyecto efectoProyecto) {
		super.save(efectoProyecto);
		
	}

	@Override
	public EfectoProyecto updateEfectoProyecto(EfectoProyecto efectoProyecto) {
		return super.update(efectoProyecto);
	}

	@Override
	public void deleteEfectoProyecto(EfectoProyecto efectoProyecto) {
		super.delete(efectoProyecto);
		
	}

	@Override
	public EfectoProyecto findEfectoProyectoById(Integer id) {
		return super.findById(EfectoProyecto.class, id);
	}

	@Override
	public List<EfectoProyecto> findEfectoProyectosByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
