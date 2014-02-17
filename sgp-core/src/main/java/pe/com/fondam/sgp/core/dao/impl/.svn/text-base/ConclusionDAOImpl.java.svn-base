package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ConclusionDAO;
import pe.com.fondam.sgp.core.domain.Conclusion;

@Repository
public class ConclusionDAOImpl extends JpaBaseDAOImpl implements ConclusionDAO {

	//********* inyecciones  **************//
	@Autowired
	public ConclusionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//********* metodos **************//
	@Override
	public void saveConclusion(Conclusion conclusion) {
		super.save(conclusion);
	}

	@Override
	public Conclusion updateConclusion(Conclusion conclusion) {
		return super.update(conclusion);
	}

	@Override
	public void deleteConclusion(Conclusion conclusion) {
		super.delete(conclusion);
	}

	@Override
	public Conclusion findConclusionById(Integer id) {
		return super.findById(Conclusion.class, id);
	}

	@Override
	public List<Conclusion> findConclusionByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}


}
