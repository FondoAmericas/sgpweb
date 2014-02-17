package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.AvanceResultadoActividadDAO;
import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;

@Repository
public class AvanceResultadoActividadDAOImpl extends JpaBaseDAOImpl implements
		AvanceResultadoActividadDAO {

	/************************* inyecciones *************************/
	@Autowired
	public AvanceResultadoActividadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	/************************* metodos *************************/
	@Override
	public void saveAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad) {

		super.save(avanceResultadoActividad);
	}

	@Override
	public List<AvanceResultadoActividad> findAvanceResultadoActividadXConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	@Override
	public AvanceResultadoActividad findAvanceResultadoActividadById(
			Integer avanceResultadoActividadId) {
		
		return super.findById(AvanceResultadoActividad.class,avanceResultadoActividadId);
	}

	@Override
	public AvanceResultadoActividad updateAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad) {
		
		return super.update(avanceResultadoActividad);
	}

	@Override
	public void deleteAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad) {
		super.delete(avanceResultadoActividad);
		
	}
}
