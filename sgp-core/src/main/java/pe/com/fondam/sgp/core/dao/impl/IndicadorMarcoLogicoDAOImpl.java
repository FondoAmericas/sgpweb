package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.IndicadorMarcoLogicoDAO;
import pe.com.fondam.sgp.core.domain.IndicadorMarcoLogico;
/**
*
* 
*/

@Repository
public class IndicadorMarcoLogicoDAOImpl extends JpaBaseDAOImpl implements IndicadorMarcoLogicoDAO {
	//*****************  inyecciones  *******************//
	@Autowired
	public IndicadorMarcoLogicoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//*****************  metodos *******************//
	@Override
	public void saveIndicadorMarcoLogico(
			IndicadorMarcoLogico indicadorMarcoLogico) {
		super.save(indicadorMarcoLogico);
		
	}

	@Override
	public IndicadorMarcoLogico updateIndicadorMarcoLogico(
			IndicadorMarcoLogico indicadorMarcoLogico) {
		return super.update(indicadorMarcoLogico);
	}

	@Override
	public void deleteIndicadorMarcoLogico(
			IndicadorMarcoLogico indicadorMarcoLogico) {
		super.delete(indicadorMarcoLogico);
		
	}

	@Override
	public IndicadorMarcoLogico findIndicadorMarcoLogicoById(Integer id) {
		return super.findById(IndicadorMarcoLogico.class, id);
	}

	@Override
	public List<IndicadorMarcoLogico> findIndicadorMarcoLogicoByConsulta(
			String queryString, Object[] params) {
		return super.find(queryString, params);
	}
	
}
