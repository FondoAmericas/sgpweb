package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.MetaPorActividadDAO;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;

@Repository
public class MetaPorActividadDAOImpl extends JpaBaseDAOImpl implements MetaPorActividadDAO {

	//*************** inyecciones ******************************//
	@Autowired
	public MetaPorActividadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//*************** metodos ******************************//
	@Override
	public void saveMetaPorActividad(MetaPorActividad metaPorActividad) {
		super.save(metaPorActividad);
	}

	@Override
	public MetaPorActividad updateMetaPorActividad(
			MetaPorActividad metaPorActividad) {
		return super.update(metaPorActividad);
	}

	@Override
	public void deleteMetaPorActividad(MetaPorActividad metaPorActividad) {
		super.delete(metaPorActividad);
	}

	@Override
	public MetaPorActividad findMetaPorActividadById(Integer id) {
		return super.findById(MetaPorActividad.class, id);
	}

	@Override
	public MetaPorActividad findMetaPorActividadByActividadId(
			Integer actividadID) {
		
		MetaPorActividad metaPorActividad = null;
		
		String queryString = "from MetaPorActividad where actividad.actividadID = ? ";
		Object[] params = new Object[1];
		params[0] = actividadID;
		
		List<MetaPorActividad> listMetaPorActividad = super.find(queryString, params);
		if(!listMetaPorActividad.isEmpty()){
			metaPorActividad = listMetaPorActividad.get(0);
		}
		return metaPorActividad;
	}

	@Override
	public List<MetaPorActividad> findMetaPorActividad() {
		return super.find("from MetaPorActividad");
	}

	@Override
	public List<MetaPorActividad> findMetasPorActividadByActividadId(Integer actividadID) {
		
		String queryString = "from MetaPorActividad where actividad.actividadID = ? ";
		Object[] params = new Object[1];
		params[0] = actividadID;
		
		return super.find(queryString, params);
	}

}
