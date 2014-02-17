package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TablaClase;

public interface TablaClaseService {

	TablaClase findTablaClaseById(Integer tablaClaseId);

	List<TablaClase> findTablaClase();

	TablaClase findTablaClaseByTablaNombre(
			String tablaClaseNombreDatoPlanOperativo);

}
