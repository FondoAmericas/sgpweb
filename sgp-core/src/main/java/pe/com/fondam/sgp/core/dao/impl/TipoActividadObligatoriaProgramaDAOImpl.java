package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TipoActividadObligatoriaProgramaDAO;
import pe.com.fondam.sgp.core.domain.TipoActividadObligatoriaPrograma;
/**
*
* @author Zolanch Távara
*/
@Repository
public class TipoActividadObligatoriaProgramaDAOImpl extends JpaBaseDAOImpl implements
		TipoActividadObligatoriaProgramaDAO {
	@Autowired
	public TipoActividadObligatoriaProgramaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveTipoActividadObligatoriaPrograma(
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoActividadObligatoriaPrograma updateTipoActividadObligatoriaPrograma(
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaP) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTipoActividadObligatoriaPrograma(
			TipoActividadObligatoriaPrograma tipoActividadObligatoriaP) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoActividadObligatoriaPrograma findTipoActividadObligatoriaProgramaById(Integer id) {
		return super.findById(TipoActividadObligatoriaPrograma.class, id);
	}

	@Override
	public List<TipoActividadObligatoriaPrograma> findTipoActividadObligatoriaProgramasByIdtablaespTipo(Integer id) {
		
		String queryString = "From TipoActividadObligatoriaPrograma where fkIdtablaespTipoResulActivoOblig = ?";
		Object[] params = new Object[1];
		params[0] = id;
		return super.find(queryString,params);
	}

}
