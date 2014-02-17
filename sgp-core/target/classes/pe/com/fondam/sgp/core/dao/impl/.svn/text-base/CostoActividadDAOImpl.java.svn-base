package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.domain.CostoActividad;

@Repository
public class CostoActividadDAOImpl extends JpaBaseDAOImpl implements
		CostoActividadDAO {

	/****************************** inyecciones ***************************/
	@Autowired
	public CostoActividadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	/****************************** metodos *****************************/
	@Override
	public void saveCostoActividad(CostoActividad costoActividad) {
		super.save(costoActividad);
	}

	@Override
	public CostoActividad updateCostoActividad(CostoActividad costoActividad) {
		return super.update(costoActividad);
	}

	@Override
	public CostoActividad findCostoActividadById(Integer id) {
		return super.findById(CostoActividad.class, id);
	}

	@Override
	public List<CostoActividad> findCostoActividadByActividadID(
			Integer actividadID) {
		String queryString = "from CostoActividad where actividad.actividadID = ? ";
		Object[] params = new Object[1];
		params[0] = actividadID;
		return super.find(queryString, params);
	}

	@Override
	public List<CostoActividad> findCostoActividad(String queryString, Object[] params) {
		
		return super.find(queryString, params);
	}

	@Override
	public List<CostoActividad> findCostoActividadByActividadIDAndPartidaGenericaID(
			Integer actividadID, Integer partidaGenericaID) {
		String queryString = "from CostoActividad where actividad.actividadID = ?  and partidaGenerica.partidaGenericaID = ?";
		Object[] params = new Object[2];
		params[0] = actividadID;
		params[1] = partidaGenericaID;
		return super.find(queryString, params);
	}

	@Override
	public void deleteCostoActividad(CostoActividad costoActividad) {
		super.delete(costoActividad);		
	}
	


}
