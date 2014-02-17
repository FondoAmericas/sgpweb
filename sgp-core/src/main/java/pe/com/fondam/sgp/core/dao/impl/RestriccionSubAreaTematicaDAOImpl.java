package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RestriccionSubAreaTematicaDAO;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;

@Repository
public class RestriccionSubAreaTematicaDAOImpl extends JpaBaseDAOImpl implements RestriccionSubAreaTematicaDAO {
	
	@Autowired
	public RestriccionSubAreaTematicaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	protected final Log logger = LogFactory.getLog(RestriccionSubAreaTematicaDAOImpl.class);

	@Override
	public void saveRestriccionSubAreaTematica(RestriccionSubAreaTematica restriccionSubAreaTematica) {
		super.save(restriccionSubAreaTematica);
		
	}

	@Override
	public RestriccionSubAreaTematica updateRestriccionSubAreaTematica(RestriccionSubAreaTematica restriccionSubAreaTematica) {
		
		return super.update(restriccionSubAreaTematica);
	}


	@Override
	public void deleteRestriccionSubAreaTematica(Integer id) {
		
		RestriccionSubAreaTematica restricionP =super.findById(RestriccionSubAreaTematica.class, id);
		super.delete(restricionP);
	}

	@Override
	public List<RestriccionSubAreaTematica> findListRestriccionSubAreaTematicayProgramaId(
			Integer idPrograma) {	
			
			String queryString = "from RestriccionSubAreaTematica where programa.programaID= ?";
			Object[] params = new Object[1];
			params[0] = idPrograma;
			return super.find(queryString,params);

	}

}
