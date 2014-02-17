package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.service.CostoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaCostoActividadService;
import pe.com.fondam.sgp.core.service.PartidaEspecificaService;
import pe.com.fondam.sgp.core.service.PartidaGenericaService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class CostoActividadServiceImpl implements CostoActividadService {
	
	/******  inyecciones  ***************/
	@Resource
	CostoActividadDAO costoActividadDAO;

	@Resource
	CronogramaCostoActividadService cronogramaCostoActividadService;
	
	@Resource
	PartidaGenericaService partidaGenericaService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	PartidaEspecificaService partidaEspecificaService;
	
	/********* metodos *****************/
	@Override
	public List<CostoActividad> findCostoActividadByActividadID(Integer actividadID) {
		return costoActividadDAO.findCostoActividadByActividadID(actividadID);
	}

	@Override
	public CostoActividad findCostoActividadById(Integer costoActividadID) {
		return costoActividadDAO.findCostoActividadById(costoActividadID);
	}

	@Override
	public void deleteCostoActividad(CostoActividad costoActividad) {
		costoActividad = findCostoActividadById(costoActividad.getCostoActividadID());
		costoActividadDAO.deleteCostoActividad(costoActividad);		
	}

	@Override
	public CostoActividad updateCostoActividad(CostoActividad costoActividad) {
		
		return costoActividadDAO.updateCostoActividad(costoActividad);
	}

	@Override
	public List<CostoActividad> llenaCostoPorActividadCompletaCronograma(
			List<CostoActividad> listCostoActividad) {
		
		for (CostoActividad costoActividad : listCostoActividad) {
			costoActividad.setListCronogramaCostoActividad(cronogramaCostoActividadService.findCronogramaCostoActividadByCostoActividadID(costoActividad.getCostoActividadID()));
			costoActividad.setPartidaGenerica(partidaGenericaService.findPartidaGenericaById(costoActividad.getPartidaGenerica().getPartidaGenericaID()));
			costoActividad.setDescripcionUnidadMedida(tablaEspecificaService.findTablaEspecificaById(costoActividad.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
			costoActividad.setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(costoActividad.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
			costoActividad.setDetallePartidaGenerica(partidaGenericaService.findPartidaGenericaById(costoActividad.getPartidaGenerica().getPartidaGenericaID()).getDescripcionPartidaGenerica());
			costoActividad.setPartidaEspecifica(partidaEspecificaService.findPartidaEspecificaById(costoActividad.getPartidaEspecifica().getPartidaEspecificaID()));
		}
		
		return listCostoActividad;
	}

}
