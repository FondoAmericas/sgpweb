package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.PropuestaTransferenciaDAO;
import pe.com.fondam.sgp.core.domain.PropuestaTransferencia;
import pe.com.fondam.sgp.core.service.DetalleEstadoCabeceraService;
import pe.com.fondam.sgp.core.service.DirectivaBeneficiarioService;
import pe.com.fondam.sgp.core.service.OrganizacionService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBeneficiarioService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienService;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaService;

@Service
public class PropuestaTransferenciaServiceImpl implements
		PropuestaTransferenciaService {
	
	//********** inyeccones ***********//
	@Resource
	PropuestaTransferenciaDAO propuestaTransferenciaDAO;

	@Resource
	DirectivaBeneficiarioService directivaBeneficiarioService;

	@Resource
	OrganizacionService organizacionService;
	
	@Resource
	PropuestaTransferenciaBeneficiarioService propuestaTransferenciaBeneficiarioService;
	
	@Resource
	PropuestaTransferenciaBienService propuestaTransferenciaBienService;
	
	@Resource
	DetalleEstadoCabeceraService detalleEstadoCabeceraService;
	
	
	//********** metodos **************//
	@Override
	public PropuestaTransferencia findPropuestaTransferenciaByDatoProyectoId(
			Integer datoProyectoID) {

		String consulta= " from PropuestaTransferencia where datoProyecto.datoProyectoID = ? ";
		Object[] params= new Object[1];
		params[0]=datoProyectoID;
		
		List<PropuestaTransferencia> listPropuestaTransferencia = propuestaTransferenciaDAO.findPropuestaTransferenciaByConsulta(consulta,params);
		PropuestaTransferencia propuestaTransferencia= null;
		if ( listPropuestaTransferencia.size()>0){
			propuestaTransferencia= llenaPropuestaTransferenciaCompleta(listPropuestaTransferencia.get(0));
		}
		return propuestaTransferencia;
	}


	@Override
	public PropuestaTransferencia updatePropuestaTransferencia(
			PropuestaTransferencia propuestaTransferencia) {

		return propuestaTransferenciaDAO.updatePropuestaTransferencia(propuestaTransferencia);
	}


	@Override
	public PropuestaTransferencia findPropuestaTransferenciaById(
			Integer propuestaTransferenciaId) {
		return propuestaTransferenciaDAO.findPropuestaTransferenciaById(propuestaTransferenciaId);
	}


	@Override
	public PropuestaTransferencia llenaPropuestaTransferenciaCompleto(
			PropuestaTransferencia propuestaTransferencia) {

		propuestaTransferencia.setListDirectivaBeneficiario(directivaBeneficiarioService.findDirectivaBeneficiarioByPropuestaTransferenciaId(propuestaTransferencia.getPropuestaTransferenciaID()));
		propuestaTransferencia.setListOrganizacion(organizacionService.findOrganizacionByPropuestaTransferenciaId(propuestaTransferencia.getPropuestaTransferenciaID()));
		propuestaTransferencia.setListPropuestaTransferenciaBeneficiario(propuestaTransferenciaBeneficiarioService.findPropuestaTransferenciaBeneficiarioByPropuestaTransferenciaId(propuestaTransferencia.getPropuestaTransferenciaID()));
		propuestaTransferencia.setListPropuestaTransferenciaBien(propuestaTransferenciaBienService.findPropuestaTransferenciaBienByPropuestaTransferenciaId(propuestaTransferencia.getPropuestaTransferenciaID()));
		
		
		return propuestaTransferencia;
	}
	
	private PropuestaTransferencia llenaPropuestaTransferenciaCompleta(
			PropuestaTransferencia propuestaTransferencia) {

		propuestaTransferencia.setDescripcionEstadoPropuesta(detalleEstadoCabeceraService.findDetalleEstadoCabeceraById(propuestaTransferencia.getFkIdDetalleEstadoCabEstInfPropTransfer()).getDescripEstado());
		
		return propuestaTransferencia;
	}
}
