package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.MarcoLogicoDAO;
import pe.com.fondam.sgp.core.domain.MarcoLogico;
/**
*
* 
*/
@Repository
public class MarcoLogicoDAOImpl extends JpaBaseDAOImpl  implements MarcoLogicoDAO {
	
	/****************************** inyecciones ***************************/
	@Autowired
	public MarcoLogicoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	/****************************** metodos *****************************/
	@Override
	public void saveMarcoLogico(MarcoLogico marcoLogico) {
		super.save(marcoLogico);
		
	}

	@Override
	public MarcoLogico updateMarcoLogico(MarcoLogico marcoLogico) {
		return super.update(marcoLogico);
	}

	@Override
	public void deleteMarcoLogico(MarcoLogico marcoLogico) {
			super.delete(marcoLogico);
		
	}

	@Override
	public MarcoLogico findMarcoLogicoById(Integer id) {
		return super.findById(MarcoLogico.class, id);
	}

	@Override
	public List<MarcoLogico> findMarcoLogico() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MarcoLogico> findMarcoLogicoByConsulta(String queryString,
			Object[] params) {
		return super.find(queryString, params);
	}
	
}
