package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.domain.Actividad;

@Repository
public class ActividadDAOImpl extends JpaBaseDAOImpl implements ActividadDAO {

	@Autowired
	public ActividadDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveActividad(Actividad actividad) {
		super.save(actividad);
	}

	@Override
	public Actividad updateActividad(Actividad actividad) {
		return super.update(actividad);
	}

	@Override
	public void deleteActividad(Actividad actividad) {
		super.delete(actividad);
	}

	@Override
	public Actividad findActividadById(Integer id) {
		return super.findById(Actividad.class, id);
	}

	@Override
	public List<Actividad> findActividadByResultadoID(Integer resultadoID) {
		String queryString = "from Actividad actividad where actividad.resultado.resultadoID = ? order by actividad.codigoActividad desc";
		Object[] params = new Object[1];
		params[0] = resultadoID;
		return super.find(queryString, params);
	}
	
	@Override
	public List<Actividad> findActividad() {
		return super.find("from Actividad");
	}

}
