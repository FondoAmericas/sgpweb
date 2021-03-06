package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActividadDetallePago;

public interface ActividadDetallePagoService {

	List<ActividadDetallePago> findActividadDetallePagoByDatoProyectoId(
			Integer datoProyectoID);

	List<ActividadDetallePago> findActividadDetallePagoByCronogramaCostoActividadId(
			Integer cronogramaCostoActividadID);

	List<ActividadDetallePago> findActividadDetallePagoByDatoProyectoIdByFuenteFinanciadoraId(
			Integer datoProyectoId, Integer fuenteFinanciadoraId);

	List<ActividadDetallePago> findActividadDetallePagoByDetallePagoLiquidacion(
			Integer detallePagoLiquidacionID);

	ActividadDetallePago updateActividadDetallePago(
			ActividadDetallePago actividadDetallePago);

}
