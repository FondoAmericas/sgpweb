package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.EvaluacionDAO;
import pe.com.fondam.sgp.core.domain.Evaluacion;
import pe.com.fondam.sgp.core.service.EvaluacionService;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {

	//************* inyecciones ***************//
	@Resource
	EvaluacionDAO evaluacionDAO;

	
	//************* metodos ***************//
	@Override
	public Evaluacion findEvaluacionByID(Integer evaluacionId) {
		return evaluacionDAO.findEvaluacionById(evaluacionId);
	}
	

}
