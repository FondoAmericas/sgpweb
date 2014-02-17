package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;

@Service
public class CronogramaCostoActividadServiceImpl implements CronogramaCostoActividadService {


	/************** inyecciones ***************/
	@Resource
	CronogramaCostoActividadDAO cronogramaCostoActividadDAO;
	
	@Resource
	CostoActividadService costoActividadService;
	
	@Resource
	ActividadDetallePagoService actividadDetallePagoService;

	
	/************** metodos ***************/
	@Override
	public CronogramaCostoActividad findCronogramaCostoActividadById(Integer cronogramaCostoActividadID) {
		return cronogramaCostoActividadDAO.findCronogramaCostoActividadById(cronogramaCostoActividadID);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadIdByFuenteFinanciadoraIdByCantidadMayorCero(Integer costoActividadId,Integer fuenteFinanciadoraId) {

		String consulta =" from CronogramaCostoActividad where costoActividad.costoActividadID = ? and fuenteFinanciadora.fuenteFinanciadoraID = ? and cantidad > 0  and estadoEliminado = 1 ";
		Object[] variable= new Object[2];
		variable[0]=costoActividadId;
		variable[1]=fuenteFinanciadoraId;
		
		//return llenaCronogramaCostoActividadConCostoActividad(cronogramaCostoActividadDAO.findCronogramaCostoActividad(consulta, variable));
		return cronogramaCostoActividadDAO.findCronogramaCostoActividad(consulta, variable);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByCostoActividadID(Integer costoActividadID) {
		return cronogramaCostoActividadDAO.findCronogramaCostoActividadByCostoActividadID(costoActividadID);
	}

	@Override
	public List<CronogramaCostoActividad> findCronogramaCostoActividadByFuenteFinanciamientoIDMenosCostoActividadId(
			Integer fuenteFinanciadoraID, Integer costoActividadID) {
		String consulta =" from CronogramaCostoActividad where fuenteFinanciadora.fuenteFinanciadoraID = ? and costoActividad.costoActividadID <> ? and cantidad > 0  and estadoEliminado = 1 ";
		Object[] variable= new Object[2];
		variable[0]=fuenteFinanciadoraID;
		variable[1]=costoActividadID;
		
		return cronogramaCostoActividadDAO.findCronogramaCostoActividad(consulta, variable);
	}

	@Override
	public CronogramaCostoActividad llenaCronogramaCostoActividadCompleto(
			CronogramaCostoActividad cronogramaCostoActividad) {

		cronogramaCostoActividad.setCostoActividad(costoActividadService.findCostoActividadById(cronogramaCostoActividad.getCostoActividad().getCostoActividadID()));
		return cronogramaCostoActividad;
	}

	@Override
	public CronogramaCostoActividad updateCronogramaCostoActividad(
			CronogramaCostoActividad cronogramaCostoActividad) {
		
		return cronogramaCostoActividadDAO.updateCronogramaCostoActividad(cronogramaCostoActividad);
	}

	@Override
	public void deleteCronogramaCostoActividad(
			Integer cronogramaCostoActividadID) {
		cronogramaCostoActividadDAO.deleteCronogramaCostoActividad(cronogramaCostoActividadDAO.findCronogramaCostoActividadById(cronogramaCostoActividadID));
		
	}

	@Override
	public List<CronogramaCostoActividad> llenaMontoLiquidadoHastaElMomentoDeCCA(
			List<CronogramaCostoActividad> listCronogramaCostoActividad) {

		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			double montoLiquidadoHastaElMomento = 0;
			List<ActividadDetallePago> listActividadDetallePago = actividadDetallePagoService.findActividadDetallePagoByCronogramaCostoActividadId(cronogramaCostoActividad.getCronogramaCostoActividadID());
			for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
				montoLiquidadoHastaElMomento += actividadDetallePago.getMontoGastado();
			}
			cronogramaCostoActividad.setMontoLiquidadoHastaElMomento(montoLiquidadoHastaElMomento);
		}
		return listCronogramaCostoActividad;
	}

	//******************** listas *************************//
	/*private List<CronogramaCostoActividad> llenaCronogramaCostoActividadConCostoActividad(
			List<CronogramaCostoActividad> listCronogramaCostoActividad) {
		
		for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
			cronogramaCostoActividad.setCostoActividad(costoActividadService.findCostoActividadById(cronogramaCostoActividad.getCostoActividad().getCostoActividadID()));
		}
		
		return listCronogramaCostoActividad;
	}
	*/
}
