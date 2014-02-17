package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;



@Repository
public class CronogramaCostoActividadDAOImpl extends JpaBaseDAOImpl implements
		CronogramaCostoActividadDAO {

	//***************** inyecciones **********************//
	@Autowired
	public CronogramaCostoActividadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}


	//***************** metodos **********************//
	@Override
	public void saveCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {
		super.save(cronogramaCostoActividad);
	}

	@Override
	public CronogramaCostoActividad updateCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {
		return super.update(cronogramaCostoActividad);
	}

	@Override
	public CronogramaCostoActividad findCronogramaCostoActividadById(Integer id) {
		return super.findById(CronogramaCostoActividad.class, id);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadID(Integer costoActividadID) {
		String queryString = "from CronogramaCostoActividad where costoActividad.costoActividadID = ?  and estadoEliminado = 1 ";
		Object[] params = new Object[1];
		params[0] = costoActividadID;
		return super.find(queryString, params);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByFuenteFinanciadoraID(
			Integer fuenteFinanciadoraID) {
		String queryString = "from CronogramaCostoActividad where fuenteFinanciadora.fuenteFinanciadoraID = ?  and estadoEliminado = 1 ";
		Object[] params = new Object[1];
		params[0] = fuenteFinanciadoraID;
		return super.find(queryString, params);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(
			Integer costoActividadID, Integer fuenteFinanciadoraID) {
		String queryString = "from CronogramaCostoActividad where costoActividad.costoActividadID =? and fuenteFinanciadora.fuenteFinanciadoraID = ?  and estadoEliminado = 1 ";
		Object[] params = new Object[2];
		params[0] = costoActividadID;
		params[1] = fuenteFinanciadoraID;
		return super.find(queryString, params);
	}
	
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIDNroPeriodo(Integer costoActividadID, String nroPeriodo) {
		String queryString = "from CronogramaCostoActividad where costoActividad.costoActividadID = ? and periodo = ?  and estadoEliminado = 1 ";
		Object[] params = new Object[2];
		params[0] = costoActividadID;
		params[1] = nroPeriodo;
		return super.find(queryString, params);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividad(
			String queryString, Object[] params) {
		return super.find(queryString, params);
	}

	@Override
	public void deleteCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {
		super.delete(cronogramaCostoActividad);
		
	}


	
	/*
	
	public List findCronogramaCostoActividadByCostoActividadID_(Integer costoActividadID) {
		Session session = null;
		List lstUa = null;
		try {
			session = HibernateUtil.currentSession();
			String strQuery="select " +
					"cca.Cronograma_Costo_Actividad_ID, " +
					"cca.Costo_Actividad_ID, " +
					"cca.periodo, " +
					"cca.estado_liquidacion "+
                    " from cronograma_costo_actividad cca " +
                    "where cca.Costo_Actividad_ID = ? " +
                    "                              order by periodo ";
			Query objQuery = session.createSQLQuery(strQuery);
			int i = 0;
			objQuery.setParameter(i++, costoActividadID);
			lstUa=objQuery.list();
		}catch (RuntimeException e) {
		    
		} finally {
			HibernateUtil.closeSession();
		}
		return lstUa;
	}*/
}
