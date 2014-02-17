package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.SolicitaRpRf;

public interface SolicitaRpRfService {

	List<SolicitaRpRf> findSolicitaRpRfByDatoProyectoIdByVersionPo(
			Integer datoProyectoID, String version);

	SolicitaRpRf updateSolicitaRpRf(SolicitaRpRf solicitaRpRf);

	List<SolicitaRpRf> findSolicitaRpRfByDatoProyectoId(Integer datoProyectoID);

	List<SolicitaRpRf> llenaListSolicitaRpRfCompleto(
			List<SolicitaRpRf> listSolicitaRpRf);

	SolicitaRpRf findSolicitaRpRfById(Integer solicitaRpRfId);

	SolicitaRpRf llenaSolicitaRpRfCompleto(SolicitaRpRf solicitaRpRf);
}
