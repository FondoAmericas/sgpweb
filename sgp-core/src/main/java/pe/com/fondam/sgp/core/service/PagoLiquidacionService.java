package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.PagoLiquidacion;

public interface PagoLiquidacionService {

	List<PagoLiquidacion> llenaPagoLiquidacionCompletoConListas(
			List<PagoLiquidacion> listPagoLiquidacion);

	PagoLiquidacion findPagoLiquidacionById(int pagoLiquidacionID);

	PagoLiquidacion updatePagoLiquidacion(PagoLiquidacion pagoLiquidacion);

	List<PagoLiquidacion> findPagoLiquidacionByLiquidacionGastoId(
			Integer liquidacionGastoID);

}
