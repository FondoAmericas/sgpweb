package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.Desembolso;

/**
*
* @author Zolanch Távara
*/
public interface DesembolsoDAO {

	void saveDesembolso(Desembolso desembolso);
	
	Desembolso updateDesembolso(Desembolso desembolso);
	
	void deleteDesembolso(Desembolso desembolso);
	
	Desembolso findDesembolsoById(Integer id);
	
	List<Desembolso> findDesembolsos();

	List<Desembolso> findDesembolsoUltimaVersionByDatoProyectoID(Integer datoProyectoID);

	List<Desembolso> findDesembolso(String queryString, Object[] params);
	
	public List<Desembolso> findDesembolsoByDatoProyectoID(Integer datoProyectoID);
	
	public List<Desembolso> findDesembolsoByDatoProyectoIDByPeriodo(Integer datoProyectoID, String periodo);
	
	public List<Desembolso> findDesembolsoByCuentaCorrienteID(Integer cuentaCorrienteID);

	public List<Desembolso> findDesembolsoByFuenteFinanciadoraID(Integer fuenteFinanID);
}
