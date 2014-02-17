package pe.com.fondam.sgp.core.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TablaProfundidadesDAO;
import pe.com.fondam.sgp.core.domain.TablaProfundidades;

@Repository
public class TablaProfundidadesDAOImpl extends JpaBaseDAOImpl implements
		TablaProfundidadesDAO {

	//*********** inyecciones **************//

	@Autowired
	public TablaProfundidadesDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//*********** metodos **************//

	@Override
	public TablaProfundidades findTablaProfundidadesById(Integer id) {
		return super.findById(TablaProfundidades.class, id);
	}
	
}
