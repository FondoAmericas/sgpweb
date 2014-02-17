package pe.com.fondam.sgp.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.ObservacionDAO;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Observacion;
import pe.com.fondam.sgp.core.domain.TablaClase;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.TablaClaseService;
import pe.com.fondam.sgp.core.service.TablaProfundidadesService;
import pe.com.fondam.sgp.core.util.CommonUtilities;

@Service
public class ObservacionServiceImpl implements ObservacionService {

	//************ inyecciones *****************//
	@Resource
	ObservacionDAO observacionDAO;

	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	@Resource
	DatoProyectoDAO datoProyectoDAO;
	
	@Resource
	TablaProfundidadesService tablaProfundidadesService;

	@Resource
	TablaClaseService tablaClaseService;
	
	//************ metodos *****************//
	@Override
	public void saveObservacion(Observacion observacion) {
		observacionDAO.saveObservacion(observacion);
		
	}

	@Override
	public void updateObservacion(Observacion observacion) {
		observacionDAO.updateObservacion(observacion);
		
	}

	@Override
	public List<Observacion> findObservacionesByDatoProyectoId(
			Integer datoProyectoID) {

		String consulta=" from Observacion where datoProyecto.datoProyectoID = ? ";
		Object[] params= new Object[1];
		params[0]=datoProyectoID;
		
		return observacionDAO.findObservacionByConsulta(consulta,params);
	}

	@Override
	public List<Observacion> llenaListObservacionesCompleto(
			List<Observacion> listObservaciones) {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		for (Observacion observacion : listObservaciones) {
			observacion.setDescripcionEstado(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(observacion.getFkIdtablaespEstado()).getDescripEstado());
			observacion.setFechaObservacionString(formato.format(observacion.getFechaObservacion()));
			if(observacion.getFechaAtencionObservacion()!=null){
				observacion.setFechaAtencionObservacionString(formato.format(observacion.getFechaAtencionObservacion()));
			}
			if(observacion.getFechaLevantamientoObservacion()!=null){
				observacion.setFechaLevantamientoObservacionString(formato.format(observacion.getFechaLevantamientoObservacion()));
			}else{
				observacion.setFechaLevantamientoObservacionString("0");
			}
			observacion.setDescripcionTablaProfundidades(tablaProfundidadesService.findProfundidadText(tablaProfundidadesService.findTablaProfundidadesServiceById(observacion.getTablaProfundidades().getTablaProfundidadesID())));
		}
		return listObservaciones;
	}

	@Override
	public List<Observacion> findObservacionByDatoProyectoId(
			Integer datoProyectoID) {

		String consulta = " from Observacion where datoProyecto.datoProyectoID = ? and flagEstado <> 1";
		Object[] params=new Object[1];
		params[0]= datoProyectoID;
		
		return observacionDAO.findObservacionByConsulta(consulta, params);
	}

	@Override
	public List<DatoProyectoBean> getLstProyectosPorProgramaConObservaciones(
			String cbxModalidadFinan, String programaID,
			String codProyecto, String nomProyecto) {
		List<DatoProyectoBean> lstDatoProyBean = new ArrayList<DatoProyectoBean>();
		DatoProyectoBean datoProyectoBean;
		List<DatoProyecto> lstDatoProy = new ArrayList<DatoProyecto>();
		
		if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaID(CommonUtilities.toInt(programaID));
		}else if(CommonUtilities.isNotNullOrBlank(codProyecto) && CommonUtilities.isNullOrBlank(nomProyecto)){//buscar por ProgramaID y codProyecto 
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyCodProyecto(CommonUtilities.toInt(programaID), codProyecto);
		}else if(CommonUtilities.isNullOrBlank(codProyecto) && CommonUtilities.isNotNullOrBlank(nomProyecto)){//buscar por ProgramaID y nomProyecto
			lstDatoProy = datoProyectoDAO.findDatoProyectoByProgramaIDbyNomProy(CommonUtilities.toInt(programaID), nomProyecto);
		}
		
		for (DatoProyecto datoProyecto : lstDatoProy) {
			datoProyectoBean = new DatoProyectoBean();
			datoProyectoBean.setDatoProyectoID(datoProyecto.getDatoProyectoID());
			datoProyectoBean.setNombreProyecto(datoProyecto.getNombreProyecto());
			datoProyectoBean.setCodigoProyecto(datoProyecto.getCodigoProyecto());
			datoProyectoBean.setDuracionProyecto(datoProyecto.getDuracionProyecto());
			datoProyectoBean.setCantidadPeriodo(datoProyecto.getCantidadPeriodo());
			datoProyectoBean.setCantObservaciones(findObservacionByDatoProyectoId(datoProyecto.getDatoProyectoID()).size());
			
			lstDatoProyBean.add(datoProyectoBean);
			
		}
		return lstDatoProyBean;
	}

	@Override
	public List<Observacion> findObservacionesByDatoProyectoIdByTablaClaseId(
			Integer datoProyectoID, Integer tablaClaseId) {
		
		String consulta=" from Observacion where datoProyecto.datoProyectoID = ? and tablaClase.tableclaseID = ? ";
		Object[] params= new Object[2];
		params[0]=datoProyectoID;
		params[1]=tablaClaseId;
		
		return observacionDAO.findObservacionByConsulta(consulta,params);
	}

	@Override
	public Observacion findObservacionById(Integer observacionId) {
		return observacionDAO.findObservacionById(observacionId);
	}

	@Override
	public Integer findObservacionesRelevatesAlDocumento(
			Integer datoProyectoID, Integer claseID,
			String tablaClaseNombreDatoPlanOperativo) {

		TablaClase tablaClase =tablaClaseService.findTablaClaseByTablaNombre(tablaClaseNombreDatoPlanOperativo);
		
		String consulta = " from Observacion where datoProyecto.datoProyectoID = ? and tablaClase.tableclaseID = ? and claseID = ? and flagEstado < 1 and relevanteProyecto = 1 ";
		Object[] params = new Object[3];
		params[0]=datoProyectoID;
		params[1]=tablaClase.getTableclaseID();
		params[2]=claseID;
			
		List<Observacion> listObservacion = observacionDAO.findObservacionByConsulta(consulta, params);
		
		return listObservacion.size();
	}
}
