package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.EvaluacionFinalBean;
import pe.com.fondam.sgp.core.dao.DescripcionEfDAO;
import pe.com.fondam.sgp.core.dao.EvaluacionFinalDAO;
import pe.com.fondam.sgp.core.domain.EvaluacionFinal;
import pe.com.fondam.sgp.core.service.DescripcionEfService;
import pe.com.fondam.sgp.core.service.EvaluacionFinalService;

@Service
public class EvaluacionFinalServiceImpl implements EvaluacionFinalService {

	//****************** inyecciones ********************//
	@Resource
	EvaluacionFinalDAO evaluacionFinalDAO;
	
	@Resource
	DescripcionEfDAO descripcionEfDAO;

	@Resource
	DescripcionEfService descripcionEfService;
	
	//****************** metodos ********************//
	@Override
	public EvaluacionFinal updateEvaluacionFinal(EvaluacionFinal evaluacionFinal) {
		return evaluacionFinalDAO.updateEvaluacionFinal(evaluacionFinal);
	}


	@Override
	public List<EvaluacionFinal> findEvaluacionFinalByInformeFinalId(
			Integer informeFinalId) {
		String consulta= " from EvaluacionFinal where informeFinal.informeFinalID = ? ";
		Object[] params = new Object[1];
		params[0]=informeFinalId;
		
		return evaluacionFinalDAO.findEvaluacionFinalesByConsulta(consulta, params);
	}


	@Override
	public List<EvaluacionFinal> llenaCompletaListEvaluacionFinal(
			List<EvaluacionFinal> listEvaluacionFinal) {
		for (EvaluacionFinal evaluacionFinal : listEvaluacionFinal) {
			evaluacionFinal.setDescripcionEf(descripcionEfService.llenaCompletoDescripcionEf(descripcionEfDAO.findDescripcionEfById(evaluacionFinal.getDescripcionEf().getDescripcionEFID())));
		}
		return listEvaluacionFinal;
	}


	@Override
	public void deleteEvaluacionFinal(Integer idRegistro) {
		evaluacionFinalDAO.deleteEvaluacionFinal(evaluacionFinalDAO.findEvaluacionFinalById(idRegistro));
	}


	@Override
	public List<EvaluacionFinalBean> llenaListEvaluacionFinalBean(
			List<EvaluacionFinal> listEvaluacionFinal) {
		
		List<EvaluacionFinalBean> listEvaluacionFinalBean= new ArrayList<EvaluacionFinalBean>(); 
		for (EvaluacionFinal evaluacionFinal : listEvaluacionFinal) {
			listEvaluacionFinalBean.add(llenaEvaluacionFinalBean(evaluacionFinal));
		}
		return listEvaluacionFinalBean;
	}


	private EvaluacionFinalBean llenaEvaluacionFinalBean(
			EvaluacionFinal evaluacionFinal) {

		EvaluacionFinalBean evaluacionFinalBean= new EvaluacionFinalBean();
		
		evaluacionFinalBean.setDescripcionEf(evaluacionFinal.getDescripcionEf());
		evaluacionFinalBean.setEvaluacionFinalID(evaluacionFinal.getEvaluacionFinalID());
		evaluacionFinalBean.setInformeFinal(evaluacionFinal.getInformeFinal());
		evaluacionFinalBean.setComentario(evaluacionFinal.getComentario());
		
		return evaluacionFinalBean;
	}	
	
}
