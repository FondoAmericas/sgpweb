package pe.com.fondam.sgp.core.helper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActividadDAO;
import pe.com.fondam.sgp.core.dao.CostoActividadDAO;
import pe.com.fondam.sgp.core.dao.CronogramaCostoActividadDAO;
import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.FuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.CostoActividad;
import pe.com.fondam.sgp.core.domain.CronogramaCostoActividad;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.FuenteFinanciadora;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.helper.PlanOperativoHelper;

@Service
public class PlanOperativoHelperImpl implements PlanOperativoHelper {

	protected final Log logger = LogFactory
			.getLog(PlanOperativoHelperImpl.class);

	@Resource
	private DatoPlanOperativoDAO datoPlanOperativoDAO;
	
	@Resource
	private DatoProyectoDAO datoProyectoDAO;

	@Resource
	private FuenteFinanciadoraDAO fuenteFinanciadoraDAO;

	@Resource
	private ResultadoDAO resultadoDAO;

	@Resource
	private ActividadDAO actividadDAO;

	@Resource
	private CostoActividadDAO costoActividadDAO;
	
	@Resource
	private CronogramaCostoActividadDAO cronogramacostoActividadDAO;

	public DatoPlanOperativoDAO getDatoPlanOperativoDAO() {
		return datoPlanOperativoDAO;
	}

	public void setDatoPlanOperativoDAO(
			DatoPlanOperativoDAO datoPlanOperativoDAO) {
		this.datoPlanOperativoDAO = datoPlanOperativoDAO;
	}

	public DatoProyectoDAO getDatoProyectoDAO() {
		return datoProyectoDAO;
	}

	public void setDatoProyectoDAO(DatoProyectoDAO datoProyectoDAO) {
		this.datoProyectoDAO = datoProyectoDAO;
	}

	public FuenteFinanciadoraDAO getFuenteFinanciadoraDAO() {
		return fuenteFinanciadoraDAO;
	}

	public void setFuenteFinanciadoraDAO(
			FuenteFinanciadoraDAO fuenteFinanciadoraDAO) {
		this.fuenteFinanciadoraDAO = fuenteFinanciadoraDAO;
	}

	public ResultadoDAO getResultadoDAO() {
		return resultadoDAO;
	}

	public void setResultadoDAO(ResultadoDAO resultadoDAO) {
		this.resultadoDAO = resultadoDAO;
	}

	public ActividadDAO getActividadDAO() {
		return actividadDAO;
	}

	public void setActividadDAO(ActividadDAO actividadDAO) {
		this.actividadDAO = actividadDAO;
	}

	public CostoActividadDAO getCostoActividadDAO() {
		return costoActividadDAO;
	}

	public void setCostoActividadDAO(CostoActividadDAO costoActividadDAO) {
		this.costoActividadDAO = costoActividadDAO;
	}

	public CronogramaCostoActividadDAO getCronogramacostoActividadDAO() {
		return cronogramacostoActividadDAO;
	}

	public void setCronogramacostoActividadDAO(
			CronogramaCostoActividadDAO cronogramacostoActividadDAO) {
		this.cronogramacostoActividadDAO = cronogramacostoActividadDAO;
	}

	@Override
	public Integer getDatoPlanOperativoIDByDatoProyectoID(Integer datoProyectoID) {
		Integer datoPlanOperativoID = null;
		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO().findDatoPlanOperativoByDatoProyectoID(datoProyectoID);
		if (datoPlanOperativo != null) {
			datoPlanOperativoID = datoPlanOperativo.getDatoPlanOperativoID(); 
		}
		return datoPlanOperativoID;
	}

	@Override
	public Integer getDetalleEstadoCabeceraID(Integer datoPlanOperativoID) {
		Integer detalleEstadoCabeceraID = null;
		DatoPlanOperativo datoPlanOperativo = getDatoPlanOperativoDAO().findDatoPlanOperativoById(datoPlanOperativoID);
		if (datoPlanOperativo != null) {
			detalleEstadoCabeceraID = datoPlanOperativo.getFkIdDetalleEstadoCabEstadoPlanOper(); 
		}
		return detalleEstadoCabeceraID;
	}
	
	@Override
	public Integer getCantidadPeriodo(Integer datoProyectoID) {
		Integer cantidadPeriodo = null;
		DatoProyecto datoProyecto = getDatoProyectoDAO().findDatoProyectoById(datoProyectoID);
		if (datoProyecto != null) {
			cantidadPeriodo = datoProyecto.getCantidadPeriodo();	
		}
		return cantidadPeriodo;
	}

	
	@Override
	public List<FuenteFinanciadora> getListFuenteFinanciadoraByDatoPlanOperativoID(
			Integer datoPlanOperativoID) {
		List<FuenteFinanciadora> listFuenteFinanciadora = new ArrayList<FuenteFinanciadora>();
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoDAO.findDatoPlanOperativoById(datoPlanOperativoID);
		if (datoPlanOperativo != null) {
			Integer datoProyectoID = datoPlanOperativo.getDatoProyecto().getDatoProyectoID();
			listFuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraByDatoProyectoID(datoProyectoID);
		}
		return listFuenteFinanciadora;
	}

	
	@Override
	public List<Institucion> getListInstitucionByDatoPlanOperativoID(Integer datoPlanOperativoID) {
		List<Institucion> listInstitucion = new ArrayList<Institucion>();
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoDAO.findDatoPlanOperativoById(datoPlanOperativoID);
		if (datoPlanOperativo != null) {
			Integer datoProyectoID = datoPlanOperativo.getDatoProyecto().getDatoProyectoID();
			List<FuenteFinanciadora> listFuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraByDatoProyectoID(datoProyectoID);
			for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
				listInstitucion.add(fuenteFinanciadora.getInstitucion());
			}
		}
		return listInstitucion;
	}

	@Override
	public List<Actividad> getListActividadByDatoPlanOperativoID(
			Integer datoPlanOperativoID) {
		List<Actividad> listActividad = new ArrayList<Actividad>();
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		for (Resultado resultado : listResultado) {
			Integer resultadoID = resultado.getResultadoID(); 
			listActividad.addAll(getActividadDAO().findActividadByResultadoID(resultadoID));
		}
		return listActividad;
	}
	
	@Override
	public Double sumMontoActividadByFuenteFinanciadoraID(Integer actividadID,
			Integer fuenteFinanciadoraID) {
		Double sumMontoActividad = (double) 0;
		List<CronogramaCostoActividad> listCronogramaCostoActividad = listCronogramaCostoActividadByActividadIDAndFuenteFinanciadoraID(actividadID, fuenteFinanciadoraID);
		if(!listCronogramaCostoActividad.isEmpty()){
			for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
				Double precioUnitario = cronogramaCostoActividad.getCostoActividad().getPrecioUnitario();
				Integer cantidad = cronogramaCostoActividad.getCantidad();
				Double montoCronogramaCostoActividad = precioUnitario * cantidad;
				sumMontoActividad = sumMontoActividad + montoCronogramaCostoActividad; 
			}	
		}
		return sumMontoActividad;
	}

	
	private List<CronogramaCostoActividad> listCronogramaCostoActividadByActividadIDAndFuenteFinanciadoraID(Integer actividadID, Integer fuenteFinanciadoraID){
		List<CronogramaCostoActividad> listCronogramaCostoActividad = new ArrayList<CronogramaCostoActividad>();
		List<CostoActividad> listCostoActividad = getCostoActividadDAO().findCostoActividadByActividadID(actividadID);
		for (CostoActividad costoActividad : listCostoActividad) {
			Integer costoActividadID = costoActividad.getCostoActividadID();
			listCronogramaCostoActividad.addAll(getCronogramacostoActividadDAO().findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(costoActividadID, fuenteFinanciadoraID));	
		}
		return listCronogramaCostoActividad;
	}
	
	
	@Override
	public Double sumMontoFinanciadoByFuenteFinanciadoras(
			Integer datoPlanOperativoID) {
		Double sumMontoFinanciado = (double) 0;
		DatoPlanOperativo datoPlanOperativo = datoPlanOperativoDAO.findDatoPlanOperativoById(datoPlanOperativoID);
		if (datoPlanOperativo != null) {
			Integer datoProyectoID = datoPlanOperativo.getDatoProyecto().getDatoProyectoID();
			List<FuenteFinanciadora> listFuenteFinanciadora = fuenteFinanciadoraDAO.findFuenteFinanciadoraByDatoProyectoID(datoProyectoID);
			for (FuenteFinanciadora fuenteFinanciadora : listFuenteFinanciadora) {
				sumMontoFinanciado = sumMontoFinanciado + fuenteFinanciadora.getMontoFinanciado();
			}
		}
		return sumMontoFinanciado;
	}

	@Override
	public Double sumCostoActividadByDatoPlanOperativoID(Integer datoPlanOperativoID) {
		Double sumCostoActividad = (double) 0;
		List<CostoActividad> listCostoActividadAll = new ArrayList<CostoActividad>();
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		if (!listResultado.isEmpty()) {
			listCostoActividadAll = listCostoActividadByListResultado(listResultado);
			for (CostoActividad costoActividad : listCostoActividadAll) {
				Double montoCostoActividad = (double) 0;
				montoCostoActividad = costoActividad.getPrecioUnitario() * costoActividad.getCantidadTotal();
				sumCostoActividad = sumCostoActividad + montoCostoActividad;
			}
		}
		return sumCostoActividad;
	}

	@Override
	public Double montoFinanciadoByFuenteFinanciadoraIDAndDatoProyectoID(
			Integer fuenteFinanciadoraID) {
		Double montoFinanciado = (double) 0;
		FuenteFinanciadora fuenteFinanciadora = getFuenteFinanciadoraDAO().findFuenteFinanciadoraById(fuenteFinanciadoraID);
		if (fuenteFinanciadora != null) {
			montoFinanciado = fuenteFinanciadora.getMontoFinanciado();
		}
		return montoFinanciado;
	}

	
	@Override
	public Double sumCronogramaCostoActividadByFuenteFinanciadoraIDAndDatoProyectoID(
			Integer fuenteFinanciadoraID, Integer datoPlanOperativoID) {
		Double sumCronogramaCostoActividad = (double) 0;
		List<CronogramaCostoActividad> listCronogramaCostoActividad = listCronogramaCostoActividadByFuenteFinanciadoraIDAndDatoPlanOperativoID(fuenteFinanciadoraID, datoPlanOperativoID);
		if(!listCronogramaCostoActividad.isEmpty()){
			for (CronogramaCostoActividad cronogramaCostoActividad : listCronogramaCostoActividad) {
				Double precioUnitario = cronogramaCostoActividad.getCostoActividad().getPrecioUnitario();
				Integer cantidad = cronogramaCostoActividad.getCantidad();
				Double montoCronogramaCostoActividad = precioUnitario * cantidad;
				sumCronogramaCostoActividad = sumCronogramaCostoActividad + montoCronogramaCostoActividad; 
			}	
		}
		return sumCronogramaCostoActividad;
	}

	private List<CostoActividad> listCostoActividadByListResultado(List<Resultado> listResultado){
		List<CostoActividad> listCostoActividadByListResultado = new ArrayList<CostoActividad>();
		for (Resultado resultado : listResultado) {
			Integer resultadoID = resultado.getResultadoID();
			listCostoActividadByListResultado.addAll(listCostoActividadByResultadoID(resultadoID));
		}
		return listCostoActividadByListResultado;
	}
	
	private List<CostoActividad> listCostoActividadByResultadoID(Integer resultadoID){
		List<CostoActividad> listCostoActividad = new ArrayList<CostoActividad>();
		List<Actividad> listActividad = getActividadDAO().findActividadByResultadoID(resultadoID);
		for (Actividad actividad : listActividad) {
			Integer actividadID = actividad.getActividadID();
			listCostoActividad.addAll(getCostoActividadDAO().findCostoActividadByActividadID(actividadID));
		}
		return listCostoActividad;
	}
	
	private List<CronogramaCostoActividad> listCronogramaCostoActividadByFuenteFinanciadoraIDAndDatoPlanOperativoID(Integer fuenteFinanciadoraID, Integer datoPlanOperativoID){
		List<CronogramaCostoActividad> listCronogramaCostoActividad = new ArrayList<CronogramaCostoActividad>();
		List<Resultado> listResultado = getResultadoDAO().findListResultadoByDatoPlanOperativoID(datoPlanOperativoID);
		List<CostoActividad> listCostoActividad = listCostoActividadByListResultado(listResultado);
		for (CostoActividad costoActividad : listCostoActividad) {
			Integer costoActividadID = costoActividad.getCostoActividadID();
			listCronogramaCostoActividad.addAll(getCronogramacostoActividadDAO().findCronogramaCostoActividadByCostoActividadIdAndFuenteFinanciadoraID(costoActividadID, fuenteFinanciadoraID));	
		}		
		return listCronogramaCostoActividad;
	}

}
