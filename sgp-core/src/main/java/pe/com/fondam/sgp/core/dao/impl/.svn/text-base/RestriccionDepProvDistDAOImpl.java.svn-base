package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RestriccionDepProvDistDAO;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;

@Repository
public class RestriccionDepProvDistDAOImpl extends JpaBaseDAOImpl implements RestriccionDepProvDistDAO {
	
	@Autowired
	public RestriccionDepProvDistDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	protected final Log logger = LogFactory.getLog(RestriccionDepProvDistDAOImpl.class);

	@Override
	public void saveRestriccionDepProvDist(RestriccionDepProvDist restriccionDepProvDist) {
		super.save(restriccionDepProvDist);
		
	}

	@Override
	public RestriccionDepProvDist updateRestriccionDepProvDist(RestriccionDepProvDist restriccionDepProvDist) {
		
		return super.update(restriccionDepProvDist);
	}


	@Override
	public List<RestriccionDepProvDist> findListRestriccionDepProvDistByProgramaId(
			Integer idPrograma) {
		
		String queryString = "from RestriccionDepProvDist where programa.programaID= ?";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString,params);
	}

	@Override
	public void deleteRestriccionDepProvDist(int idRestriccionDepProvDist) {
		RestriccionDepProvDist restricionP =super.findById(RestriccionDepProvDist.class, idRestriccionDepProvDist);
		super.delete(restricionP);
	}

}
