package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Desembolso;

public interface DesembolsoService {

	List<Desembolso> findDesembolsoByDatoProyectoID(Integer datoProyectoID);

	Desembolso updateDesembolso(Desembolso desembolso);

	List<Desembolso> findDesembolsoByDatoProyectoIDByFuenteFinanciadoraId(
			Integer datoProyectoId, Integer fuenteFinanciadoraId);

	List<Desembolso> llenaDesembolsoCompleto(
			List<Desembolso> listDesembolso);

	Desembolso findDesembolsoByID(int desembolsoId);

	Desembolso llenaDesembolsoCompleto(Desembolso desembolso);

	Integer findDesembolsoByDatoProyectoIDYAprobado(Integer datoProyectoID,
			String prefijoEstadoDesembolsoAprobado, String prefijoEstadoDesembolso);

}
