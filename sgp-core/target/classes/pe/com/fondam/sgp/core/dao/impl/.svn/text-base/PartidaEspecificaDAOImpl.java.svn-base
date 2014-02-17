package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PartidaEspecificaDAO;
import pe.com.fondam.sgp.core.domain.PartidaEspecifica;

@Repository
public class PartidaEspecificaDAOImpl extends JpaBaseDAOImpl implements
		PartidaEspecificaDAO {

	@Autowired
	public PartidaEspecificaDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public PartidaEspecifica findPartidaEspecificaById(Integer id) {
		return super.findById(PartidaEspecifica.class, id);
	}

	@Override
	public List<PartidaEspecifica> findPartidaEspecificaByPartidaGenericaID(
			Integer partidaGenericaID) {
		
		String queryString = "from PartidaEspecifica where partidaGenerica.partidaGenericaID = ? ";
		Object[] params = new Object[1];
		params[0] = partidaGenericaID;

		return super.find(queryString, params);
	}

}
