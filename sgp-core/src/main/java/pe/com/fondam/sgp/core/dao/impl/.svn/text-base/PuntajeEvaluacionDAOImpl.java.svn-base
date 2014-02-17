package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PuntajeEvaluacionDAO;
import pe.com.fondam.sgp.core.domain.PuntajeEvaluacion;
/**
*
*/
@Repository
public class PuntajeEvaluacionDAOImpl extends JpaBaseDAOImpl implements PuntajeEvaluacionDAO {
	protected final Log logger = LogFactory.getLog(PuntajeEvaluacionDAOImpl.class);

	@Autowired
	public PuntajeEvaluacionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	@Override
	public void savePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion) {
	super.save(puntajeEvaluacion);
		
	}

	@Override
	public PuntajeEvaluacion updatePuntajeEvaluacion(
			PuntajeEvaluacion puntajeEvaluacion) {
	
		return super.update(puntajeEvaluacion);
	}

	@Override
	public void deletePuntajeEvaluacion(PuntajeEvaluacion puntajeEvaluacion) {
		PuntajeEvaluacion puntajE =super.findById(PuntajeEvaluacion.class, puntajeEvaluacion.getPuntajeEvaluacionID());
		super.delete(puntajE);
		
	}

	@Override
	public PuntajeEvaluacion findPuntajeEvaluacionBytipoEvaluacionByProgramaID(Integer ProgramaID,Integer tipoEvaluacion) {
		String queryString = "from PuntajeEvaluacion where programa.programaID= ? and  fkIdtablaespTipoEvaluacion=?";
		Object[] params = new Object[2];
		params[0] = ProgramaID;
		params[1] = tipoEvaluacion;
		List<PuntajeEvaluacion> listPuntaje = super.find(queryString, params);
		
		if (listPuntaje!=null && listPuntaje.size()!=0) {

			return listPuntaje.get(0);
		}
	

		return null;
	}

	@Override
	public List<PuntajeEvaluacion> findPuntajeEvaluacion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<PuntajeEvaluacion> findPuntajeEvaluacionByProgramaID(Integer id) {

		String queryString = "from PuntajeEvaluacion where programa.programaID= ? ";
		Object[] params = new Object[1];
		params[0] = id;
		
		logger.info("Valor queryString" + queryString);
		List<PuntajeEvaluacion> listPuntaje = super.find(queryString, params);

		return listPuntaje;
	}

}
