package pe.com.fondam.sgp.core.dao;

import java.util.List;

import pe.com.fondam.sgp.core.domain.TablaClase;

public interface TablaClaseDAO {

	TablaClase findByClaseNombre(String claseNombre);

	TablaClase findTablaClaseById(Integer tablaClaseId);

	List<TablaClase> findTablaClase();

	List<TablaClase> findByConsulta(String consulta, Object[] params);
}
