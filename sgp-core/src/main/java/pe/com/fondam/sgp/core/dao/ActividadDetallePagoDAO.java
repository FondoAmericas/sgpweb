package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActividadDetallePago;

/**
*
* @author Zolanch Távara
*/
public interface ActividadDetallePagoDAO {

	void saveActividadDetallePago(ActividadDetallePago actividadDetallePago);
	
	ActividadDetallePago updateActividadDetallePago(ActividadDetallePago actividadDetallePago);
	
	void deleteActividadDetallePago(ActividadDetallePago actividadDetallePago);
	
	ActividadDetallePago findActividadDetallePagoById(Integer id);
	
	public List<ActividadDetallePago> findActividadDetallePagoByCronogramaCostoAct(Integer cronoCostoActID);
	
	public List<ActividadDetallePago> findActividadDetallePagoByDesembolsoID(Integer desembolsoID);

	List<ActividadDetallePago> findActividadDetallePago(String queryString,
			Object[] params);
	
	public List<ActividadDetallePago> findActividadDetallePagoByDetallePagoLiquidacionID(Integer detallePagoLiquidacionID);
}
