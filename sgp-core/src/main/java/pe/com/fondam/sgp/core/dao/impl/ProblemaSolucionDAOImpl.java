package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ProblemaSolucionDAO;
import pe.com.fondam.sgp.core.domain.ProblemaSolucion;



@Repository
public class ProblemaSolucionDAOImpl extends JpaBaseDAOImpl implements ProblemaSolucionDAO {

	//********** inyecciones ****************//
	@Autowired
	public ProblemaSolucionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//********** metodos ****************//
	@Override
	public void saveProblemaSolucion(ProblemaSolucion problemaSolucion) {
		super.update(problemaSolucion);
	}

	@Override
	public ProblemaSolucion updateProblemaSolucion(
			ProblemaSolucion problemaSolucion) {
		return super.update(problemaSolucion);
	}

	@Override
	public void deleteProblemaSolucion(ProblemaSolucion problemaSolucion) {
		super.delete(problemaSolucion);
		
	}

	@Override
	public ProblemaSolucion findProblemaSolucionById(Integer problemaSolucionId) {
		return super.findById(ProblemaSolucion.class, problemaSolucionId);
	}

	@Override
	public List<ProblemaSolucion> findProblemaSolucionXConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
