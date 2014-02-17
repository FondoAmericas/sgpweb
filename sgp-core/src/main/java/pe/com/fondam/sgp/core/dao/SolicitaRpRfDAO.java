package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.SolicitaRpRf;

public interface SolicitaRpRfDAO {
	
	List<SolicitaRpRf> findSolicitaRpRfByConsulta(
			String consulta, Object[] params) ;

	SolicitaRpRf updateSolicitaRpRf(SolicitaRpRf solicitaRpRf);

	SolicitaRpRf findSolicitaRpRfById(Integer solicitaRpRfId);

}
