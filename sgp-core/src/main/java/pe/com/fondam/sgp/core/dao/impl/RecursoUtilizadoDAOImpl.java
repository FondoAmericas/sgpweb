package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RecursoUtilizadoDAO;
import pe.com.fondam.sgp.core.domain.RecursoUtilizado;

@Repository
public class RecursoUtilizadoDAOImpl extends JpaBaseDAOImpl implements RecursoUtilizadoDAO {

	//************** inyecciones ********************//
	@Autowired
	public RecursoUtilizadoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//************** metodos ********************//
	@Override
	public void saveRecursoUtilizado(RecursoUtilizado recursoUtilizado) {
		super.save(recursoUtilizado);
		
	}

	@Override
	public RecursoUtilizado updateRecursoUtilizado(
			RecursoUtilizado recursoUtilizado) {
		return super.update(recursoUtilizado);
	}

	@Override
	public void deleteRecursoUtilizado(RecursoUtilizado recursoUtilizado) {
		super.delete(recursoUtilizado);
		
	}

	@Override
	public RecursoUtilizado findRecursoUtilizadoById(Integer id) {
		
		return super.findById(RecursoUtilizado.class, id);
	}

	@Override
	public List<RecursoUtilizado> findRecursoUtilizadoByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	

}
