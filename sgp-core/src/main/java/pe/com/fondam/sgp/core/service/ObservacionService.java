package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.DatoProyectoBean;
import pe.com.fondam.sgp.core.domain.Observacion;

public interface ObservacionService {
	//List<DatoProyecto> findDatoProyectoByProgramaID(Integer actividadID);
	void saveObservacion(Observacion observacion);
	void updateObservacion(Observacion observacion);
	List<Observacion> findObservacionesByDatoProyectoId(Integer datoProyectoID);
	List<Observacion> llenaListObservacionesCompleto(
			List<Observacion> listObservaciones);
	List<Observacion> findObservacionByDatoProyectoId(Integer datoProyectoID);
	List<DatoProyectoBean> getLstProyectosPorProgramaConObservaciones(
			String cbxModalidadFinan, String cbxPrograma,
			String txtCodProyecto, String txtNomProyecto);
	List<Observacion> findObservacionesByDatoProyectoIdByTablaClaseId(
			Integer datoProyectoID, Integer tablaClaseId);
	Observacion findObservacionById(Integer observacionId);
	Integer findObservacionesRelevatesAlDocumento(Integer datoProyectoID,
			Integer claseID,
			String tablaClaseNombreDatoPlanOperativo);

}
