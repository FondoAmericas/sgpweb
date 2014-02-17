package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.MetaPorActividadBean;
import pe.com.fondam.sgp.core.dao.MetaPorActividadDAO;
import pe.com.fondam.sgp.core.domain.MetaPorActividad;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorActividadService;
import pe.com.fondam.sgp.core.service.MetaPorActividadService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class MetaPorActividadServiceImpl implements MetaPorActividadService {

	/******************* inyecciones *********************/
	@Resource
	MetaPorActividadDAO metaPorActividadDAO;
	
	@Resource
	ActividadService actividadService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	@Resource
	CronogramaMetaPorActividadService cronogramaMetaPorActividadService;
	
	/******************* metodos *********************/
	@Override
	public List<MetaPorActividad> findMetaPorActividadXActividadId(Integer actividadId) {
		
		/*String consulta = " from MetaPorActividad where actividad.actividadID = ? ";
		Object[] params= new Object[1];
		params[0] = actividadId; 
		*/	
		return metaPorActividadDAO.findMetasPorActividadByActividadId(actividadId);
	}

	@Override
	public MetaPorActividad findMetaPorActividadById(Integer metasXActividadId) {
		return metaPorActividadDAO.findMetaPorActividadById(metasXActividadId);
	}

	@Override
	public List<MetaPorActividad> findMetasPorActividadByActividadId(Integer actividadID) {
		return metaPorActividadDAO.findMetasPorActividadByActividadId(actividadID);
	}

	@Override
	public void deleteMetaPorActividad(MetaPorActividad metaPorActividad) {
		metaPorActividad = findMetaPorActividadById(metaPorActividad.getMetaPorActividadID());
		metaPorActividadDAO.deleteMetaPorActividad(metaPorActividad);		
	}

	@Override
	public MetaPorActividad updateMetaPorActividad(
			MetaPorActividad metaPorActividad) {
		
		return metaPorActividadDAO.updateMetaPorActividad(metaPorActividad);
	}

	
	//**************** listas *************************//
	@Override
	public List<MetaPorActividadBean> llenaListMetaPorActividadBean(
			List<MetaPorActividad> listMetaPorActividad) {

		List<MetaPorActividadBean> listMetaPorActividadBean = new ArrayList<MetaPorActividadBean>();

		for (MetaPorActividad metaPorActividad : listMetaPorActividad) {
			MetaPorActividadBean metaPorActividadBean = llenaMetaPorActividadBean(metaPorActividad);
			listMetaPorActividadBean.add(metaPorActividadBean);
		}

		return listMetaPorActividadBean;
	}
	
	@Override
	public MetaPorActividadBean llenaMetaPorActividadBean(
			MetaPorActividad metaPorActividad) {

		MetaPorActividadBean metaPorActividadBean = new MetaPorActividadBean();

			metaPorActividadBean.setActividad(actividadService
					.findActividadById(metaPorActividad.getActividad()
							.getActividadID()));
			metaPorActividadBean.setCantidadMetaActividad(metaPorActividad
					.getCantidadMetaActividad());
			metaPorActividadBean
					.setCantidadMetaActividadEjecutada(metaPorActividad
							.getCantidadMetaActividadEjecutada());
			metaPorActividadBean.setContribucionProposito(metaPorActividad
					.getContribucionProposito());
			if (metaPorActividad.getFkIdtablaespTipoIndicadorActividad() != null) {
				metaPorActividadBean
						.setFkIdtablaespTipoIndicadorActividad(metaPorActividad
								.getFkIdtablaespTipoIndicadorActividad());
				metaPorActividadBean
						.setDescripcionTipoIndicadorActividad(tablaEspecificaService
								.findTablaEspecificaById(
										metaPorActividad
												.getFkIdtablaespTipoIndicadorActividad())
								.getDescripcionCabecera());
			}
			metaPorActividadBean.setFkIdtablaespUnidadMedida(metaPorActividad
					.getFkIdtablaespUnidadMedida());
			metaPorActividadBean
					.setDescripcionUnidadMedida(tablaEspecificaService
							.findTablaEspecificaById(
									metaPorActividad
											.getFkIdtablaespUnidadMedida())
							.getDescripcionCabecera());
			metaPorActividadBean.setLogroAlcanzado(metaPorActividad
					.getLogroAlcanzado());
			metaPorActividadBean.setMetaPorActividadID(metaPorActividad
					.getMetaPorActividadID());


		return metaPorActividadBean;
	}

	@Override
	public List<MetaPorActividad> llenaMetaPorActividadCompletaCronograma(
			List<MetaPorActividad> listMetaPorActividad) {
		
		for (MetaPorActividad metaPorActividad : listMetaPorActividad) {
			metaPorActividad.setDescripcionTipoIndicadorActividad(tablaEspecificaService.findTablaEspecificaById(metaPorActividad.getFkIdtablaespTipoIndicadorActividad()).getDescripcionCabecera());
			metaPorActividad.setDescripcionUnidadMedida(tablaEspecificaService.findTablaEspecificaById(metaPorActividad.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
			metaPorActividad.setListCronogramaMetaPorActividad(cronogramaMetaPorActividadService.findCronogramaMetaPorActividadXMetaPorActividadId(metaPorActividad.getMetaPorActividadID()));
		}
		return listMetaPorActividad;
	}
}
