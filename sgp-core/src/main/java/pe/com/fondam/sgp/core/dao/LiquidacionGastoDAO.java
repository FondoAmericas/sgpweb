package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.LiquidacionGasto;

public interface LiquidacionGastoDAO {

	void saveLiquidacionGasto(LiquidacionGasto liquidacionGasto);

	public LiquidacionGasto updateLiquidacionGasto(LiquidacionGasto liquidacionGasto);

	void deleteLiquidacionGasto(LiquidacionGasto liquidacionGasto);

	public LiquidacionGasto findLiquidacionGastoById(Integer id);

	//public List<LiquidacionGasto> findLiquidacionGastosByFuenteFinanciadora(LiquidacionGasto liquidacionGasto);

	public List<LiquidacionGasto> findLiquidacionGastosByFuenteFinanciadora(Integer datoProyectoID,Integer fuenteFinanciadoraID);
	
	List<LiquidacionGasto> findLiquidacionesGasto(String consulta,Object[] params); 

	public int getCantidadLiquidacionGastosByProyectoIdByEstadoLiqGenerada(Integer datoProyectoID);

	public List<LiquidacionGasto> findLiquidacionGastosByProyectoIdByEstadoLiqGenerada(Integer datoProyectoID);
}
