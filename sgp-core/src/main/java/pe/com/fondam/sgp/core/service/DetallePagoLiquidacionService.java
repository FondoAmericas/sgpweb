package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;

public interface DetallePagoLiquidacionService {

	List<DetallePagoLiquidacion> llenaDetallePagoLiquidacionCompleto(
			List<DetallePagoLiquidacion> listDetallePagoLiquidacion);

	List<DetallePagoLiquidacion> findDetallePagoLiquidacionByPagoLiquidacionId(
			Integer pagoLiquidacionID);

}
