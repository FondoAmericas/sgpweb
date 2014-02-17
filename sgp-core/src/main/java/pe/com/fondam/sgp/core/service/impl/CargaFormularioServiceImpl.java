package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.CargaFormularioDao;
import pe.com.fondam.sgp.core.domain.CargaFormulario;
import pe.com.fondam.sgp.core.service.CargaFormularioService;

@Service
public class CargaFormularioServiceImpl implements CargaFormularioService {

	//************** inyecciones  *********************//
	@Resource
	CargaFormularioDao cargaFormularioDao;


	//************** metodos *********************//	
	@Override
	public CargaFormulario updateCargaFormulario(CargaFormulario cargaFormulario) {
		return cargaFormularioDao.updateCargaFormulario(cargaFormulario);
	}

	@Override
	public List<CargaFormulario> findFormularios() {
		return cargaFormularioDao.findFormularios();
	}

	@Override
	public CargaFormulario findCargaFormularioById(Integer formularioId) {
		return cargaFormularioDao.findById(formularioId);
	}
}
