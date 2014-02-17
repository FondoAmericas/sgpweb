package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ActivoDAO;
import pe.com.fondam.sgp.core.domain.Activo;
import pe.com.fondam.sgp.core.service.ActivoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class ActivoServiceImpl implements ActivoService {

	//**************  inyecciones  *****************//
	@Resource
	ActivoDAO activoDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//**************  inyecciones  *****************//
	@Override
	public Activo findActivoById(int activoId) {
		return llenaActivoCompleto(activoDAO.findActivoById(activoId));
	}

	@Override
	public List<Activo> findActivoByCategoriaActivoId(int categoriaActivoId) {
		String consulta =" from Activo where fkIdtablaespCategoriaActivo = ? ";
		Object[] params=new Object[1];
		params[0]=categoriaActivoId;
		return activoDAO.findActivoByConsulta(consulta,params);
	}
	
	//*************** listas ********************//
	private Activo llenaActivoCompleto(Activo activo) {
			activo.setDescripcionCategoriaActivo(tablaEspecificaService.findTablaEspecificaById(activo.getFkIdtablaespCategoriaActivo()).getDescripcionCabecera());
		return activo;
	}

}
