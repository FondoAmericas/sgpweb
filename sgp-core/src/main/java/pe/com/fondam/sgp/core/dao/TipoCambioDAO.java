package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TipoCambio;

public interface TipoCambioDAO {

	void saveTipoCambio(TipoCambio tipoCambio);
	
	TipoCambio updateTipoCambio(TipoCambio tipoCambio);
	
	void deleteTipoCambio(TipoCambio tipoCambio);
	
	TipoCambio findTipoCambioById(Integer id);
	
	List<TipoCambio> findTipoCambioXConsulta(String consulta, Object[] params);
	
	List<TipoCambio> findTipoCambios();

}
