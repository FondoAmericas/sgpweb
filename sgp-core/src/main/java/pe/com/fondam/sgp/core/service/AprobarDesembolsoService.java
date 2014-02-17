package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Desembolso;
import pe.com.fondam.sgp.core.domain.Programa;

public interface AprobarDesembolsoService {

	public String obtenerDesembolsosArrayJson(String opcionBusqueda, String filtroBusqueda, int programaID);

	//public void saveAutorizacionDesembolso(Map<String, String> parametrosAutoSolDesembolso);
	
	public List<Programa> findProgramaByModFinan(int modFinan);

	public String convertirListProgramaToArrayJson(List<Programa> lstPrograma);

	public Desembolso updateAutorizacionDesembolso(Desembolso desembolso);

}
