package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DetallePagoLiquidacionDAO;
import pe.com.fondam.sgp.core.domain.ActividadDetallePago;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;
import pe.com.fondam.sgp.core.service.ActividadDetallePagoService;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.DetallePagoLiquidacionService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class DetallePagoLiquidacionServiceImpl implements
		DetallePagoLiquidacionService {


	//******************* inyecciones ************************//
	@Resource
	ActividadDetallePagoService actividadDetallePagoService;
	
	@Resource
	ActivoService activoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	DetallePagoLiquidacionDAO detallePagoLiquidacionDao;
	
	//******************* metodos  ****************************//
	@Override
	public List<DetallePagoLiquidacion> llenaDetallePagoLiquidacionCompleto(
			List<DetallePagoLiquidacion> listDetallePagoLiquidacion) {

		for (DetallePagoLiquidacion detallePagoLiquidacion : listDetallePagoLiquidacion) {
			detallePagoLiquidacion.setActivo(activoService.findActivoById(detallePagoLiquidacion.getActivo().getActivoID()));
			detallePagoLiquidacion.setActivoDesc(activoService.findActivoById(detallePagoLiquidacion.getActivo().getActivoID()).getDescripcionActivo());
			detallePagoLiquidacion.setLstActividadDetallePago(llenaActividadDetallePagoCompleto(actividadDetallePagoService.findActividadDetallePagoByDetallePagoLiquidacion(detallePagoLiquidacion.getDetallePagoLiquidacionID())));
		}
		
		return listDetallePagoLiquidacion;
	}

	private List<ActividadDetallePago> llenaActividadDetallePagoCompleto(
			List<ActividadDetallePago> listActividadDetallePago) {

		for (ActividadDetallePago actividadDetallePago : listActividadDetallePago) {
			actividadDetallePago.getCronogramaCostoActividad().getCostoActividad().setDescripcionUnidadMedida(tablaEspecificaService.findTablaEspecificaById(actividadDetallePago.getCronogramaCostoActividad().getCostoActividad().getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
			actividadDetallePago.getCronogramaCostoActividad().getCostoActividad().setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(actividadDetallePago.getCronogramaCostoActividad().getCostoActividad().getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
			actividadDetallePago.setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(actividadDetallePago.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
		}

		return listActividadDetallePago;
	}

	@Override
	public List<DetallePagoLiquidacion> findDetallePagoLiquidacionByPagoLiquidacionId(
			Integer pagoLiquidacionID) {
		String consulta=" from DetallePagoLiquidacion where pagoLiquidacion.pagoLiquidacionID = ? ";
		
		Object[] params= new Object[1];
		params[0]= pagoLiquidacionID;
		
		return  detallePagoLiquidacionDao.findDetallePagoLiquidacion(consulta, params);

	}


}
