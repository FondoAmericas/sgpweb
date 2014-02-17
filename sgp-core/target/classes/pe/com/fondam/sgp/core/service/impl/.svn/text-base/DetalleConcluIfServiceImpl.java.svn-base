package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DetalleConcluIfDAO;
import pe.com.fondam.sgp.core.domain.DetalleConcluIf;
import pe.com.fondam.sgp.core.service.DetalleConcluIfService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class DetalleConcluIfServiceImpl implements DetalleConcluIfService {

	//********** metodos ****************//
	@Resource
	DetalleConcluIfDAO detalleConcluIfDAO;
	
	@Resource 
	TablaEspecificaService tablaEspecificaService;
	
	//********** inyecciones ****************//
	@Override
	public List<DetalleConcluIf> findDetalleConcluIfByTipoConclusionFinalId(
			int tipoConclusionFinalId) {
		String consulta = " from DetalleConcluIf where flidtablaespcabeceraconcluIF = ? ";
		Object[] params= new Object[1];
		params[0]=tipoConclusionFinalId;
		
		return detalleConcluIfDAO.findDetalleConcluIfByConsulta(consulta,params);
	}

	@Override
	public DetalleConcluIf llenaCompletoDetalleConcluIf(
			DetalleConcluIf detalleConcluIf) {
		detalleConcluIf.setDescripcionCabeceraConcluIF(tablaEspecificaService.findTablaEspecificaById(detalleConcluIf.getFlidtablaespcabeceraconcluIF()).getDescripcionCabecera());
		return detalleConcluIf;
	}

	@Override
	public DetalleConcluIf findDetalleConcluIfById(
			Integer sltDetalleTipoConclusion) {
		return detalleConcluIfDAO.findDetalleConcluIfById(sltDetalleTipoConclusion);
	}

}
