package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.EvaluacionFinalDAO;
import pe.com.fondam.sgp.core.domain.EvaluacionFinal;


@Repository
public class EvaluacionFinalDAOImpl extends JpaBaseDAOImpl implements EvaluacionFinalDAO {

	//************* inyecciones *******************//
	@Autowired
	public EvaluacionFinalDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//************* inyecciones *******************//
	@Override
	public void saveEvaluacionFinal(EvaluacionFinal evaluacionFinal) {
		super.save(evaluacionFinal);
	}

	@Override
	public EvaluacionFinal updateEvaluacionFinal(EvaluacionFinal evaluacionFinal) {
		return super.update(evaluacionFinal);
	}

	@Override
	public void deleteEvaluacionFinal(EvaluacionFinal evaluacionFinal) {
		super.delete(evaluacionFinal);
		
	}

	@Override
	public EvaluacionFinal findEvaluacionFinalById(Integer id) {
		return super.findById(EvaluacionFinal.class, id);
	}

	@Override
	public List<EvaluacionFinal> findEvaluacionFinalesByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}