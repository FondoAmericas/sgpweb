package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.UbicacionProyecto;

public interface UbicacionProyectoService {

	List<UbicacionProyecto> findUbicacionProyectoXDatoProyectoId(
			Integer datoProyectoID);

	UbicacionProyecto findUbicacionProyectoXDatoProyectoIdXDepProvDistId(
			Integer datoProyectoID, Integer depProvDistId);
	
	List<UbicacionProyecto> llenaListUbicacionProyectoCompleto(List<UbicacionProyecto> listUbicacionProyecto);

}
