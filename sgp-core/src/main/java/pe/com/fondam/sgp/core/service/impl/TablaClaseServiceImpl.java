package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.TablaClaseDAO;
import pe.com.fondam.sgp.core.domain.TablaClase;
import pe.com.fondam.sgp.core.service.TablaClaseService;

@Service
public class TablaClaseServiceImpl implements TablaClaseService {

	//*********** inyecciones ****************//
	@Resource
	TablaClaseDAO tablaClaseDAO;

	
	//*********** metodos ****************//
	@Override
	public TablaClase findTablaClaseById(Integer tablaClaseId) {
		return tablaClaseDAO.findTablaClaseById(tablaClaseId);
	}


	@Override
	public List<TablaClase> findTablaClase() {
		return tablaClaseDAO.findTablaClase();
	}


	@Override
	public TablaClase findTablaClaseByTablaNombre(
			String tablaClaseNombreDatoPlanOperativo) {
		String consulta = " from TablaClase where tablaNombre = ? ";
		Object[] params = new Object[1];
		params[0]=tablaClaseNombreDatoPlanOperativo;
		
		List<TablaClase> listTablaClase = tablaClaseDAO.findByConsulta(consulta,params);
		TablaClase tablaClase = new TablaClase();
		if (!listTablaClase.isEmpty()) {
			tablaClase = listTablaClase.get(0);
		}
		
		return tablaClase;
	}
	

}
