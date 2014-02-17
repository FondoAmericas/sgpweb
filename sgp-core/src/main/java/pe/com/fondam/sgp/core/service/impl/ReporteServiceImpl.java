package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorActividad;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.report.ActividadReporteBean;
import pe.com.fondam.sgp.core.report.RptResultados;
import pe.com.fondam.sgp.core.service.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService {

	@Override
	public List<RptResultados> llenaRptResultadosReporteCronogramaActividades(
			List<Resultado> listResultado) {
		
		List<RptResultados> listRptResultados= new ArrayList<RptResultados>();
		
		for (Resultado resultado : listResultado) {
			RptResultados rptResultados = new RptResultados();
			
			rptResultados.setCodigoResultado(resultado.getCodigoResultado());
			rptResultados.setDefinicionResultado(resultado.getDefinicionResultado());
			
			//lleno el ActividadReporteBean
			List<ActividadReporteBean> listActividadReporteBean = new ArrayList<ActividadReporteBean>();
			for (Actividad actividad : resultado.getListActividad()) {
				ActividadReporteBean actividadReporteBean= new ActividadReporteBean();
				if(actividad.getListMetaPorActividad().size()==0){
					actividadReporteBean.setNombreActividad(actividad.getNombreActividad());
					actividadReporteBean.setCodigoActividadString(resultado.getCodigoResultado()+"."+actividad.getCodigoActividad());
					actividadReporteBean.setDescripcionTipoActividad(actividad.getDescripcionTipoActividad());
					actividadReporteBean.setDuracionMeses(actividad.getDuracionMeses());
				}
				Integer flag=0;
				for (MetaPorActividad metaPorActividad : actividad.getListMetaPorActividad()) {
					if(flag==0){
						actividadReporteBean.setNombreActividad(actividad.getNombreActividad());
						actividadReporteBean.setCodigoActividadString(resultado.getCodigoResultado()+"."+actividad.getCodigoActividad());
						actividadReporteBean.setDescripcionTipoActividad(actividad.getDescripcionTipoActividad());
						actividadReporteBean.setDuracionMeses(actividad.getDuracionMeses());
					}else{
						actividadReporteBean= new ActividadReporteBean();
					}
					
					actividadReporteBean.setCantidadMetaActividad(metaPorActividad.getCantidadMetaActividad());
					actividadReporteBean.setDescripcionUnidadMedida(metaPorActividad.getDescripcionUnidadMedida());
					
					for (CronogramaMetaPorActividad cronogramaMetaPorActividad : metaPorActividad.getListCronogramaMetaPorActividad()) {
						switch (Integer.valueOf(cronogramaMetaPorActividad.getPeriodo())) {
						case 1:
							actividadReporteBean.setCantPer01(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 2:
							actividadReporteBean.setCantPer02(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 3:
							actividadReporteBean.setCantPer03(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 4:
							actividadReporteBean.setCantPer04(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 5:
							actividadReporteBean.setCantPer05(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 6:
							actividadReporteBean.setCantPer06(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 7:
							actividadReporteBean.setCantPer07(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 8:
							actividadReporteBean.setCantPer08(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 9:
							actividadReporteBean.setCantPer09(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 10:
							actividadReporteBean.setCantPer10(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 11:
							actividadReporteBean.setCantPer11(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
						case 12:
							actividadReporteBean.setCantPer12(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()>0?String.valueOf(cronogramaMetaPorActividad.getCantidadMetaActividadProgPorPeriodo()):"--");
							break;
							}
					}
					flag+=1;
					listActividadReporteBean.add(actividadReporteBean);
				}
				
				if(!listActividadReporteBean.contains(actividadReporteBean)){
					listActividadReporteBean.add(actividadReporteBean);
				}
			}
			
			rptResultados.setListActividadReporteBean(listActividadReporteBean);
			
			listRptResultados.add(rptResultados);
		}
		
		return listRptResultados;
	}

	@Override
	public String pathCronogramaActividadPorCantidadPeriodos(
			DatoProyecto datoProyecto, String reportPath) {
	
		String sourceFileSubReportName = "";
		
		switch (datoProyecto.getCantidadPeriodo()) {
		case 1:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport1.jrxml";
			break;
		case 2:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport2.jrxml";
			break;
		case 3:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport3.jrxml";
			break;
		case 4:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport4.jrxml";
			break;
		case 5:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport5.jrxml";
			break;
		case 6:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport6.jrxml";
			break;
		case 7:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport7.jrxml";
			break;
		case 8:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport8.jrxml";
			break;
		case 9:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport9.jrxml";
			break;
		case 10:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport10.jrxml";
			break;
		case 11:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport11.jrxml";
			break;
		case 12:
			sourceFileSubReportName = reportPath + "CronogramaActividades_subreport12.jrxml";
			break;	
		}
		
		return sourceFileSubReportName;
	}

}
