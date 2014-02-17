package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;

public interface TmpFuenteFinanciadoraService {

	List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByDatoProyectoIdByCofinanciador(
			Integer datoProyectoID, int cofinanciador);

	List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByDatoProyectoIdByContrapartida(
			Integer gettMPDatoProyectoID, int cofinanciador, int fondam);

}
