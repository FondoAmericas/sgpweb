package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RestricionProgramaDAO;
import pe.com.fondam.sgp.core.domain.RestricionPrograma;
/**
*
*/
@Repository
public class RestricionProgramaDAOImpl extends JpaBaseDAOImpl implements RestricionProgramaDAO {
	
	@Autowired
	public RestricionProgramaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	protected final Log logger = LogFactory.getLog(RestricionProgramaDAOImpl.class);

	@Override
	public void saveRestricionPrograma(RestricionPrograma restricionPrograma) {
		super.save(restricionPrograma);
		
	}

	@Override
	public RestricionPrograma updateRestricionPrograma(RestricionPrograma restricionPrograma) {
		
		return super.update(restricionPrograma);
	}

	@Override
	public List<RestricionPrograma> findListRestricionProgramaById(Integer id) {

		String queryString = "from RestricionPrograma where programa.programaID= ?";
		Object[] params = new Object[1];
		params[0] = id;
		
		logger.info("Valor queryString" + queryString);
		List<RestricionPrograma> listRestriccionP = super.find(queryString, params);

		return listRestriccionP;
	}

	@Override
	public void deleteRestricionPrograma(RestricionPrograma restricionPrograma) {
		
		RestricionPrograma restricionP =super.findById(RestricionPrograma.class, restricionPrograma.getRestricionProgramaID());
		super.delete(restricionP);
	}
	
	public List<RestricionPrograma> findRestriccionProgramaByFuenteFinanciadoraID(Integer fuenteFinanID) {
		String queryString = "from RestricionPrograma where fuenteFinanciadora.fuenteFinanciadoraID = ? ";
		Object[] params = new Object[1];
		params[0] = fuenteFinanID;

		return super.find(queryString, params);
	}

}
