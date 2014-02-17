package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;
import pe.com.fondam.sgp.core.domain.ReporteAvance;

public interface EvalReporteAvanceLiquidacionGastosService {

	
	public String getListModalidadFinanciamientoToArrayJson();
	
	public String getListProgramaByModalidadFinanToArrayJson(int modalidadFinan);

	public List<DatoProyectoBean> getLstProyectosPorProgramaConReporteAvanceLiquidacionGastos(String modalidadFinanID, String ProgramaID,
			String codProyecto, String nomProyecto);
	
	public List<DatoProyectoBean> getLstProyectosPorProgramaConPropuestaEInforme(String modalidadFinanID, String ProgramaID,
			String codProyecto, String nomProyecto);
	
	public ReporteAvance getReporteAvanceById(Integer reporteAvanceId);

	public void saveEvalRepAvance(ReporteAvance reporteAvance,int estadoRepAvance);

	public void saveEvalLiqGasto(LiquidacionGasto liquidacionGasto,	int estadoLiqGasto);
	
	public List<PagoLiquidacion> findPagoLiquidacionByLiquidacionGastoID(Integer liquidacionGastoID);

	public List<PagoLiquidacion> serializarListPagoLiquidacion(List<PagoLiquidacion> lstPagoLiquidacion);

	public PagoLiquidacion serializarEstructuraPago(int pagoLiquidacionID);

	List<DatoProyectoBean> getLstProyectosPorProgramaConSolicitudRpRf(
			String modalidadFinanID, String ProgramaID, String codProyecto,
			String nomProyecto);
	

	
}
