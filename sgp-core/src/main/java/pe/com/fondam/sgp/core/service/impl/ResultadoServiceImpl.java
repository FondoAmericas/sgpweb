package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.dao.ResultadoDAO;
import pe.com.fondam.sgp.core.domain.Actividad;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.domain.Resultado;
import pe.com.fondam.sgp.core.service.ActividadService;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorResultadoService;
import pe.com.fondam.sgp.core.service.ResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.util.UtilList;

@Service
public class ResultadoServiceImpl implements ResultadoService {

	/********** inyecciones  *******************/
	@Resource
	DatoProyectoDAO datoProyectoDAO;
	
	@Resource
	DatoPlanOperativoDAO datoPlanOperativoDAO;
	
	@Resource
	ResultadoDAO resultadoDAO;
	
	@Resource
	CronogramaMetaPorResultadoService cronogramaMetaPorResultadoService;
	
	@Resource
	ActividadService actividadService;
	
	@Resource
	BeneficiariosPorResultadoService beneficiariosPorResultadoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	/********** metodos *******************/
	@Override
	public List<Resultado> findResultadoXDatoProyectoID(Integer datoProyectoID) {
		DatoProyecto datoProyecto= datoProyectoDAO.findDatoProyectoById(datoProyectoID);
		
		//traigo la ultima version de plan operativo y uso el datoPlanOperativoId
		DatoPlanOperativo datoPlanOperativo =  datoPlanOperativoDAO.findDatoPlanOperativoByDatoProyectoID(datoProyecto.getDatoProyectoID());
		
		String consulta = " from Resultado where datoPlanOperativo.datoPlanOperativoID = ? ";
		
		Object[] params= new Object[1];
		params[0]= datoPlanOperativo.getDatoPlanOperativoID();
		
		return resultadoDAO.findListResultadoXConsulta(consulta,params);
	}
	
	@Override
	public Resultado findResultadoByID(Integer resultadoID) {
		
		return resultadoDAO.findResultadoById(resultadoID);
	}

	@Override
	public List<Resultado> findResultadoXDatoPlanOperativoID(
			Integer datoPlanOperativoID) {
		//traigo la ultima version de plan operativo y uso el datoPlanOperativoId
		DatoPlanOperativo datoPlanOperativo =  datoPlanOperativoDAO.findDatoPlanOperativoById(datoPlanOperativoID);
		
		String consulta = " from Resultado where datoPlanOperativo.datoPlanOperativoID = ? ";
		
		Object[] params= new Object[1];
		params[0]= datoPlanOperativo.getDatoPlanOperativoID();
		
		return resultadoDAO.findListResultadoXConsulta(consulta,params);
	}

	@Override
	public Resultado updateResultado(Resultado resultado) {
		return resultadoDAO.updateResultado(resultado);
	}

	@Override
	public Resultado llenaResultadoCompleto(Resultado resultado) {
		
		List<CronogramaMetaPorResultado> listCronogramaMetaPorResultado=cronogramaMetaPorResultadoService.findCronogramaMetaPorResultadoByResultadoID(resultado.getResultadoID());
		resultado.setListCronogramaMetaPorResultado(listCronogramaMetaPorResultado);
		
		Integer sumaTotalCronogramaMetaPorResultado=0;
		for (CronogramaMetaPorResultado cronogramaMetaPorResultado : listCronogramaMetaPorResultado) {
			sumaTotalCronogramaMetaPorResultado+=cronogramaMetaPorResultado.getAvanceMeta();
		}
		resultado.setSumaTotalCronogramaMetaPorResultado(sumaTotalCronogramaMetaPorResultado);
		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resultado> llenaResultadoActividadesMeta(
			List<Resultado> listResultado) {
		
		for (Resultado resultado : listResultado) {
			List<Actividad> listActividad=actividadService.llenaActividadCompletaMeta ((List<Actividad>) UtilList.orderAscList(actividadService.findActividadXResultadoId(resultado.getResultadoID()),"codigoActividad"));
			resultado.setListActividad(listActividad);
		}
		
		return listResultado;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resultado> llenaResultadoActividadesCosto(
			List<Resultado> listResultado) {
		
		for (Resultado resultado : listResultado) {
			List<Actividad> listActividad=actividadService.llenaActividadCompletaCosto((List<Actividad>) UtilList.orderAscList(actividadService.findActividadXResultadoId(resultado.getResultadoID()),"codigoActividad"));
			resultado.setListActividad(listActividad);
		}
		
		return listResultado;
	}

	@Override
	public List<Resultado> llenaResultadoBeneficiarios(
			List<Resultado> listResultado) {
		
		for (Resultado resultado : listResultado) {
			resultado.setListBeneficiariosPorResultado(beneficiariosPorResultadoService.findBeneficiariosPorResultadoByResultadoID(resultado.getResultadoID()));
		}
		
		return listResultado;
	}

	@Override
	public List<Resultado> llenaResultadoCronogramaMetas(
			List<Resultado> listResultado) {

		for (Resultado resultado : listResultado) {
			if(resultado.getFkIdtablaespUnidadMedida()!=null){
				resultado.setDescripcionUnidadMedida(tablaEspecificaService.findTablaEspecificaById(resultado.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
			}
			resultado.setListCronogramaMetaPorResultado(cronogramaMetaPorResultadoService.findCronogramaMetaPorResultadoByResultadoID(resultado.getResultadoID()));
		}
		
		return listResultado;
	}	

}
