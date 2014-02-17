package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.SolicitaRpRfDAO;
import pe.com.fondam.sgp.core.domain.SolicitaRpRf;

@Repository
public class SolicitaRpRfDAOImpl extends JpaBaseDAOImpl implements
		SolicitaRpRfDAO {

	//************** inyecciones ********************//
	@Autowired
	public SolicitaRpRfDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	
	//************** metodos ********************//
	@Override
	public List<SolicitaRpRf> findSolicitaRpRfByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}


	@Override
	public SolicitaRpRf updateSolicitaRpRf(SolicitaRpRf solicitaRpRf) {
		return super.update(solicitaRpRf);
	}


	@Override
	public SolicitaRpRf findSolicitaRpRfById(Integer solicitaRpRfId) {
		return super.findById(SolicitaRpRf.class, solicitaRpRfId);
	}
}
