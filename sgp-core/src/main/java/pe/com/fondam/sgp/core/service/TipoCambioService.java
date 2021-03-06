package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TipoCambio;

public interface TipoCambioService {

	void saveTipoCambio(TipoCambio tipoCambio);
	
	TipoCambio updateTipoCambio(TipoCambio tipoCambio);
	
	void deleteTipoCambio(TipoCambio tipoCambio);
	
	TipoCambio findTipoCambioById(Integer id);
		
	List<TipoCambio> findTipoCambios();

	List<TipoCambio> findTipoCambio(String consulta, Object[] params);


	TipoCambio findTipoCambioByTipoMonedaDeAByDesembolsoID(
			Integer fkIdtablaespTipoMoneda, Integer fkIdtablaespTipoMoneda2,
			Integer desembolsoID);
	
	TipoCambio findTipoCambioByTipoMonedaDeAByDatoPlanOperativoID(Integer fkIdTipoMonedaDe, Integer fkIdTipoMonedaA,Integer datoPlanOperativoID);
	
	void createTipoCambio(TipoCambio tipoCambio);
	
	List<TipoCambio> findTipoCambioByDatoPlanOperativoID(Integer datoPlanOperativoID);
	
	List<TipoCambio> findTipoCambioByDesembolsoID(Integer desembolsoID);
	
	String getTablaEspecificaDescripcion(Integer tablaEspecificaID);

	TipoCambio findTipoCambioByDesembolsoIDByIngreso(Integer desembolsoID); 

}
