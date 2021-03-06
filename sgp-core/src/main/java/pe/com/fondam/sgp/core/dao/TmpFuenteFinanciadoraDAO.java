package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.ActividadPerfil;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;

public interface TmpFuenteFinanciadoraDAO {

	public void saveTmpFuenteFinanciadora(TmpFuenteFinanciadora tmpFuenteFinanciadora);
	public ActividadPerfil updateTmpFuenteFinanciadora(TmpFuenteFinanciadora tmpFuenteFinanciadora);
	public void deleteTmpFuenteFinanciadora(TmpFuenteFinanciadora tmpFuenteFinanciadora);
	TmpFuenteFinanciadora findTmpFuenteFinanciadoraByTmpIdDatoProyecto(Integer id);
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByProgramaId(Integer idProyecto);
	public TmpFuenteFinanciadora findTmpFuenteFinanciadoraById(Integer idDatoProyecto);
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByTmpIdDatoProyectoAndNoejecutor(Integer tmpDatoProyectoId);	
	 List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByTmpIdDatoProyectoList(Integer id);
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByConsulta(
			String consulta, Object[] params);
}
