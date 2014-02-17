package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PartidaGenericaDAO;
import pe.com.fondam.sgp.core.domain.PartidaGenerica;

@Repository
public class PartidaGenericaDAOImpl extends JpaBaseDAOImpl implements
		PartidaGenericaDAO {

	@Autowired
	public PartidaGenericaDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public PartidaGenerica findPartidaGenericaById(Integer id) {
		return super.findById(PartidaGenerica.class, id);
	}

	@Override
	public List<PartidaGenerica> findPartidaGenericaByCategoriaActividadID(
			Integer categoriaActividadID) {

		String queryString = "from PartidaGenerica where categoriaActividad.categoriaActividadID = ? ";
		Object[] params = new Object[1];
		params[0] = categoriaActividadID;

		return super.find(queryString, params);
	}

}
