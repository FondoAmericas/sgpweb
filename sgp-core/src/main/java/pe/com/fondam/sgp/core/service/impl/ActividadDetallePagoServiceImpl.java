package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActividadDetallePagoDAO;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;

@Service
public class ActividadDetallePagoServiceImpl implements
		ActividadDetallePagoService {

	/************** inyecciones ***************/
	@Resource
	ActividadDetallePagoDAO actividadDetallePagoDAO;

	
	/************** metodos ***************/
	@Override
	public List<ActividadDetallePago> findActividadDetallePagoByDatoProyectoId(
			Integer datoProyectoID) {
		
		String consulta = "from ActividadDetallePago where datoProyectoID = ? and estadoEliminado = 1 ";
		
		Object[] params= new Object[1];
		params[0]= datoProyectoID;
		return actividadDetallePagoDAO.findActividadDetallePago(consulta, params);
	}


	@Override
	public List<ActividadDetallePago> findActividadDetallePagoByCronogramaCostoActividadId(
			Integer cronogramaCostoActividadID) {

		String consulta = "from ActividadDetallePago where cronogramaCostoActividad.cronogramaCostoActividadID = ?  and estadoEliminado = 1 ";
		
		Object[] params= new Object[1];
		params[0]= cronogramaCostoActividadID;
		return actividadDetallePagoDAO.findActividadDetallePago(consulta, params);
	}


	@Override
	public List<ActividadDetallePago> findActividadDetallePagoByDatoProyectoIdByFuenteFinanciadoraId(
			Integer datoProyectoId, Integer fuenteFinanciadoraId) {

		String consulta = "from ActividadDetallePago where datoProyectoID = ? and detallePagoLiquidacion.pagoLiquidacion.liquidacionGasto.fuenteFinanciadora.fuenteFinanciadoraID = ?  and estadoEliminado = 1 ";
		
		Object[] params= new Object[2];
		params[0]= datoProyectoId;
		params[1]=fuenteFinanciadoraId;
		return actividadDetallePagoDAO.findActividadDetallePago(consulta, params);
	}


	@Override
	public List<ActividadDetallePago> findActividadDetallePagoByDetallePagoLiquidacion(
			Integer detallePagoLiquidacionID) {
		
		String consulta = "from ActividadDetallePago where detallePagoLiquidacion.detallePagoLiquidacionID = ?  and estadoEliminado = 1 ";
		
		Object[] params= new Object[1];
		params[0]= detallePagoLiquidacionID;
		return actividadDetallePagoDAO.findActividadDetallePago(consulta, params);
	}


	@Override
	public ActividadDetallePago updateActividadDetallePago(
			ActividadDetallePago actividadDetallePago) {
		
		return actividadDetallePagoDAO.updateActividadDetallePago(actividadDetallePago);
	}
}
