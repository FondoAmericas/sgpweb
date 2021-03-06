package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CronogramaMetaPorActividadDAO;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;

@Repository
public class CronogramaMetaPorActividadDAOImpl extends JpaBaseDAOImpl implements
		CronogramaMetaPorActividadDAO {

	/****************************** inyecciones ***************************/
	@Autowired
	public CronogramaMetaPorActividadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	/****************************** metodos *****************************/
	@Override
	public void saveCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad) {
		super.save(cronorgramaMetaPorActividad);
		
	}

	@Override
	public CronogramaMetaPorActividad updateCronorgramaMetaPorActividad(CronogramaMetaPorActividad cronorgramaMetaPorActividad) {
		return super.update(cronorgramaMetaPorActividad);
	}

	@Override
	public void deleteCronorgramaMetaPorActividad(
			CronogramaMetaPorActividad cronogramaMetaPorActividad) {
		super.delete(cronogramaMetaPorActividad);
		
	}

	@Override
	public CronogramaMetaPorActividad findCronorgramaMetaPorActividadById(
			Integer id) {
		return super.findById(CronogramaMetaPorActividad.class, id);
	}

	@Override
	public List<CronogramaMetaPorActividad> findCronorgramaMetaPorActividades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CronogramaMetaPorActividad> findCronogramaMetaPorActividadXConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	@Override
	public List<CronogramaMetaPorActividad> findCronorgramaMetaPorActividadesByMetaPorActivdadId(Integer metaPorActivadId) {
		String queryString = "from CronogramaMetaPorActividad where metaPorActividad.metaPorActividadID = ?";
		Object[] params = new Object[1];
		params[0] = metaPorActivadId;
		return super.find(queryString, params);
	}

}
