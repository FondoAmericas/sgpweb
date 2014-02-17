package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.domain.ActividadObligatoriaPrograma;

@Repository
public class ActividadObligatoriaProgramaDAOImpl extends JpaBaseDAOImpl
		implements ActividadObligatoriaProgramaDAO {

	protected final Log logger = LogFactory
			.getLog(ActividadObligatoriaProgramaDAOImpl.class);

	@Autowired
	public ActividadObligatoriaProgramaDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveActividadObligatoriaPrograma(
			ActividadObligatoriaPrograma actividadObligatoriaPrograma) {
		super.save(actividadObligatoriaPrograma);
	}

	@Override
	public ActividadObligatoriaPrograma updateActividadObligatoriaPrograma(
			ActividadObligatoriaPrograma actividadObligatoriaPrograma) {
		return super.update(actividadObligatoriaPrograma);
	}

	@Override
	public void deleteActividadObligatoriaPrograma(
			ActividadObligatoriaPrograma actividadObligatoriaPrograma) {
		ActividadObligatoriaPrograma actividadObligatoriaP = super.findById(
				ActividadObligatoriaPrograma.class,
				actividadObligatoriaPrograma
						.getActividadObligatoriaProgramaID());
		super.delete(actividadObligatoriaP);
	}

	@Override
	public List<ActividadObligatoriaPrograma> findActividadObligatoriaPrograma() {
		return super.find("from ActividadObligatoriaPrograma");
	}

	@Override
	public List<ActividadObligatoriaPrograma> findListActividadObligatoriaProgramaByProgramaId(
			Integer id) {

		String queryString = "from ActividadObligatoriaPrograma where programa.programaID= ? ";
		Object[] params = new Object[1];
		params[0] = id;

		//logger.info("Valor queryString" + queryString);
		List<ActividadObligatoriaPrograma> listActOP =super.find(queryString,params);
		return listActOP;
	}

}
