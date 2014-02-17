package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TipoPeriodo;

/**
*
* @author Zolanch Távara
*/
public interface TipoPeriodoDAO {

	void saveTipoPeriodo(TipoPeriodo tipoPeriodo);
	
	TipoPeriodo updateTipoPeriodo(TipoPeriodo tipoPeriodo);
	
		void deleteTipoPeriodo(TipoPeriodo tipoPeriodo);
	
		TipoPeriodo findTipoPeriodoById(Integer id);
	
		List<TipoPeriodo> findTipoPeriodos();

}
