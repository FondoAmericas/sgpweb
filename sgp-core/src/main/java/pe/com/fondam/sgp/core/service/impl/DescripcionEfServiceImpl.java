package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DescripcionEfDAO;
import pe.com.fondam.sgp.core.domain.DescripcionEf;
import pe.com.fondam.sgp.core.service.DescripcionEfService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class DescripcionEfServiceImpl implements DescripcionEfService {

	//***************** inyecciones **********************//
	@Resource
	DescripcionEfDAO descripcionEfDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService ;
	
	//***************** metodos **********************//
	@Override
	public List<DescripcionEf> findDescripcionEfByEvaluacionFinalId(
			int tipoEvaluacionFinalId) {
		String consulta = " from DescripcionEf where fkIdtablaespEvalFinalCabecera = ? ";
		Object[] params= new Object[1];
		params[0]=tipoEvaluacionFinalId;
		
		return descripcionEfDAO.findDescripcionEfByConsulta(consulta,params);
	}

	@Override
	public DescripcionEf findDescripcionEfById(
			Integer sltDescripcionTipoEvaluacionFinal) {
		return descripcionEfDAO.findDescripcionEfById(sltDescripcionTipoEvaluacionFinal);
	}

	@Override
	public DescripcionEf llenaCompletoDescripcionEf(DescripcionEf descripcionEf) {
		descripcionEf.setDescripcionEvalFinal(tablaEspecificaService.findTablaEspecificaById(descripcionEf.getFkIdtablaespEvalFinalCabecera()).getDescripcionCabecera());
		return descripcionEf;
	}
}
