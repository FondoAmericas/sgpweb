package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DetalleDesembolsoDAO;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.DetalleDesembolso;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.DetalleDesembolsoService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.util.UtilList;

@Service
public class DetalleDesembolsoServiceImpl implements DetalleDesembolsoService {

	/************** inyecciones ***************/
	@Resource
	DetalleDesembolsoDAO detalleDesembolsoDAO;
	
	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;
	
	@Resource
	CostoActividadService costoActividadService;

	@Resource
	ActividadService actividadService;
	
	@Resource
	ResultadoService resultadoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	
	/************** metodos ***************/
	@Override
	public List<DetalleDesembolso> findDetalleDesembolsoByDesemboloId(
			Integer desembolsoID) {
		
		return detalleDesembolsoDAO.findDetalleDesembolsoByDesembolsoID(desembolsoID);
	}


	@Override
	public DetalleDesembolso updateDetalleDesembolso(DetalleDesembolso detalleDesembolso) {
		return detalleDesembolsoDAO.updateDetalleDesembolso(detalleDesembolso);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetalleDesembolso> llenaListDetalleDesembolso(
			List<DetalleDesembolso> listDetalleDesembolso) {

		for (DetalleDesembolso detalleDesembolso : listDetalleDesembolso) {
			detalleDesembolso
					.setCronogramaCostoActividadID(cronogramaCostoActividadService
							.findCronogramaCostoActividadById(detalleDesembolso
									.getCronogramaCostoActividadID()
									.getCronogramaCostoActividadID()));
			detalleDesembolso
					.setCostoActividad(llenaInfoCostoActividad(costoActividadService
							.findCostoActividadById(detalleDesembolso
									.getCronogramaCostoActividadID()
									.getCostoActividad().getCostoActividadID())));
			detalleDesembolso.setActividad(actividadService
					.findActividadById(detalleDesembolso.getCostoActividad()
							.getActividad().getActividadID()));
			detalleDesembolso.setResultado(resultadoService
					.findResultadoByID(detalleDesembolso.getActividad()
							.getResultado().getResultadoID()));
			detalleDesembolso.setDescripcionTipoMonedaMs(tablaEspecificaService
					.findTablaEspecificaById(
							detalleDesembolso.getFkIdtablaespTipoMonedaMs())
					.getDescripcionCabecera());
		}
		return (List<DetalleDesembolso>) UtilList.orderAscList(
				listDetalleDesembolso, "cronogramaCostoActividadID.periodo");
	}
	
	private CostoActividad llenaInfoCostoActividad(CostoActividad costoActividad) {
		costoActividad.setDescripcionUnidadMedida(tablaEspecificaService
				.findTablaEspecificaById(
						costoActividad.getFkIdtablaespUnidadMedida())
				.getDescripcionCabecera());
		costoActividad.setDescripcionTipoMoneda(tablaEspecificaService
				.findTablaEspecificaById(
						costoActividad.getFkIdtablaespTipoMoneda())
				.getDescripcionCabecera());
		return costoActividad;
	}
}
