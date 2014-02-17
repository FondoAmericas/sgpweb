package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RubroGenericoDAO;
import pe.com.fondam.sgp.core.domain.RubroGenerico;

@Repository
public class RubroGenericoDAOImpl extends JpaBaseDAOImpl implements
		RubroGenericoDAO {

	//************  inyecciones  *****************//
	@Autowired
	public RubroGenericoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//************  metodos *****************//
	@Override
	public RubroGenerico findRubroGenericoById(Integer id) {
		return super.findById(RubroGenerico.class, id);
	}

	@Override
	public List<RubroGenerico> findRubroGenericoByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
