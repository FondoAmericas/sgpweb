package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.InstitucionDAO;
import pe.com.fondam.sgp.core.domain.Institucion;
import pe.com.fondam.sgp.core.service.InstitucionService;

@Service
public class InstitucionServiceImpl implements InstitucionService {

	@Resource
	InstitucionDAO institucionDAO;

	@Override
	public Institucion findInstitucionById(Integer institucionID) {
		return institucionDAO.findInstitucionById(institucionID);
	}
	
}
