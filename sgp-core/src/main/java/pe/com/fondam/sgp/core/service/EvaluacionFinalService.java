package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.EvaluacionFinalBean;
import pe.com.fondam.sgp.core.domain.EvaluacionFinal;

public interface EvaluacionFinalService {

	EvaluacionFinal updateEvaluacionFinal(EvaluacionFinal evaluacionFinal);

	List<EvaluacionFinal> findEvaluacionFinalByInformeFinalId(Integer informeFinalId);

	List<EvaluacionFinal> llenaCompletaListEvaluacionFinal(
			List<EvaluacionFinal> listEvaluacionFinal);

	void deleteEvaluacionFinal(Integer idRegistro);

	List<EvaluacionFinalBean> llenaListEvaluacionFinalBean(
			List<EvaluacionFinal> llenaCompletaListEvaluacionFinal);

}
