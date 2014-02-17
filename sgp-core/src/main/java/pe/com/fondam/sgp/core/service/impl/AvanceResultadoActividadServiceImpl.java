package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.AvanceResultadoActividadBean;
import pe.com.fondam.sgp.core.dao.AvanceResultadoActividadDAO;
import pe.com.fondam.sgp.core.domain.AvanceResultadoActividad;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.AvanceResultadoActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.ReporteAvanceService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class AvanceResultadoActividadServiceImpl implements
		AvanceResultadoActividadService {

	/************************* inyecciones ************************/
	@Resource
	AvanceResultadoActividadDAO avanceResultadoActividadDAO;
	
	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;
	
	@Resource
	MetaPorActividadService metaPorActividadService;
	
	@Resource
	ActividadService actividadService;
	
	@Resource
	ResultadoService resultadoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	ReporteAvanceService reporteAvanceService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	
	/************************* metodos ************************/
	@Override
	public void saveAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad) {
		avanceResultadoActividadDAO.saveAvanceResultadoActividad(avanceResultadoActividad);
		
	}

	@Override
	public List<AvanceResultadoActividad> findAvanceResultadoActividadXReporteAvanceId(
			Integer reporteAvanceId) {
		String consulta = " from AvanceResultadoActividad where reporteAvance.reporteAvanceID = ? and estadoEliminado = 1 ";
		
		Object[] params= new Object[1];
		params[0]=reporteAvanceId;
		
		return avanceResultadoActividadDAO.findAvanceResultadoActividadXConsulta(consulta, params);
	}

	@Override
	public AvanceResultadoActividad findAvanceResultadoActividadById(
			Integer avanceResultadoActividadId) {
		return avanceResultadoActividadDAO.findAvanceResultadoActividadById(avanceResultadoActividadId);
	}
	
	public List<AvanceResultadoActividadBean> llenaListAvanceResultadoActividadBean(
			List<AvanceResultadoActividad> listAvanceResultadoActividad){
		
		List<AvanceResultadoActividadBean> listAvanceResultadoActividadBean= new ArrayList<AvanceResultadoActividadBean>();
		for (AvanceResultadoActividad avanceResultadoActividad : listAvanceResultadoActividad) {
			AvanceResultadoActividadBean avanceResultadoActividadBean = llenaAvanceResultadoActividadBean(avanceResultadoActividad);
	
			listAvanceResultadoActividadBean.add(avanceResultadoActividadBean);
		}
		return listAvanceResultadoActividadBean;
	}
	
	public AvanceResultadoActividadBean llenaAvanceResultadoActividadBean(
			AvanceResultadoActividad avanceResultadoActividad) {

		AvanceResultadoActividadBean avanceResultadoActividadBean = new AvanceResultadoActividadBean();
		
		avanceResultadoActividadBean.setAvanceResultadoActividadID(avanceResultadoActividad.getAvanceResultadoActividadID());
		avanceResultadoActividadBean.setCantidadAvanceEjecutado(avanceResultadoActividad.getCantidadAvanceEjecutado());
		
		MetaPorActividad metaPorActividad=avanceResultadoActividad.getMetaPorActividad();//metaPorActividadService.findMetaPorActividadXActividadId(avanceResultadoActividad.getMetaPorActividad().getActividad().getActividadID()).get(0);
		//cronogramaMetaPorActividad.setMetaPorActividad(metaPorActividadService.findMetaPorActividadById(cronogramaMetaPorActividad.getMetaPorActividad().getMetaPorActividadID()));
		metaPorActividad.setActividad(actividadService.findActividadById(metaPorActividad.getActividad().getActividadID()));
		metaPorActividad.getActividad().setResultado(resultadoService.findResultadoByID(metaPorActividad.getActividad().getResultado().getResultadoID()));
			
		avanceResultadoActividadBean.setMetaPorActividad(metaPorActividad);
		
		avanceResultadoActividadBean.setDescripcionUnidadMedida(tablaEspecificaService.findTablaEspecificaById(metaPorActividad.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
		//avanceResultadoActividadBean.setPeriodo(avanceResultadoActividadBean.getCronogramaMetaPorActividad().getPeriodo());
		
		avanceResultadoActividadBean.setDescripcionActividad(avanceResultadoActividad.getDescripcionActividad().length() < 200 ? avanceResultadoActividad.getDescripcionActividad(): avanceResultadoActividad.getDescripcionActividad().substring(0, 200));
		avanceResultadoActividadBean.setEjecutado(avanceResultadoActividad.getEjecutado());
		avanceResultadoActividadBean.setElementoVerificacion(avanceResultadoActividad.getElementoVerificacion().length() < 200 ? avanceResultadoActividad.getElementoVerificacion():avanceResultadoActividad.getElementoVerificacion().substring(0, 200));
		avanceResultadoActividadBean.setObservaciones(avanceResultadoActividad.getObservaciones().length() < 200 ? avanceResultadoActividad.getObservaciones():avanceResultadoActividad.getObservaciones().substring(0,200));
		
		avanceResultadoActividadBean.setReporteAvance(reporteAvanceService.findReporteAvanceById(avanceResultadoActividad.getReporteAvance().getReporteAvanceID()));
		avanceResultadoActividadBean.setResumenEjecutivoPeriodo(avanceResultadoActividad.getResumenEjecutivoPeriodo().length() < 200 ? avanceResultadoActividad.getResumenEjecutivoPeriodo():avanceResultadoActividad.getResumenEjecutivoPeriodo().substring(0,200));
	
		return avanceResultadoActividadBean;
	}

	@Override
	public List<AvanceResultadoActividad> findAvanceResultadoActividadByMetaActividadId(
			Integer metaPorActividadID) {
	
		String consulta = " from AvanceResultadoActividad where metaPorActividad.metaPorActividadID = ? and estadoEliminado = 1 ";
		
		Object[] params= new Object[1];
		params[0]=metaPorActividadID;
		
		return avanceResultadoActividadDAO.findAvanceResultadoActividadXConsulta(consulta, params);
	}

	@Override
	public AvanceResultadoActividad updateAvanceResultadoActividad(
			AvanceResultadoActividad avanceResultadoActividad) {

		return avanceResultadoActividadDAO.updateAvanceResultadoActividad(avanceResultadoActividad);
	}

	@Override
	public List<AvanceResultadoActividad> findAvanceResultadoActividadByMetaActividadIdXReporteAprobado(
			Integer metaPorActividadID,
			String prefijoEstadoReporteAvanceAprobado) {
		
		String consulta = " from AvanceResultadoActividad where metaPorActividad.metaPorActividadID = ? and reporteAvance.fkIdDetalleEstadoCabEstRepAvance = ? and estadoEliminado = 1 ";
		
		Object[] params= new Object[2];
		params[0]=metaPorActividadID;
		params[1]=detalleEstadoCabeceraService.findDetalleEstadoCabecerabyPrefijoEstadoCabeceraByPrefijoDetalleEstado("estrpav", prefijoEstadoReporteAvanceAprobado).getDetalleEstadoCabeceraID();
		
		return avanceResultadoActividadDAO.findAvanceResultadoActividadXConsulta(consulta, params);
		}

	@Override
	public void deleteAvanceResultadoActividad(
			Integer avanceResultadoActividadId) {

		AvanceResultadoActividad avanceResultadoActividad = avanceResultadoActividadDAO.findAvanceResultadoActividadById(avanceResultadoActividadId);
		avanceResultadoActividadDAO.deleteAvanceResultadoActividad(avanceResultadoActividad);
		
	}
}
