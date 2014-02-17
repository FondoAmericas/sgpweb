package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.RestriccionDepProvDistDAO;
import pe.com.fondam.sgp.core.domain.RestriccionDepProvDist;
import pe.com.fondam.sgp.core.service.RestriccionDepProvDistService;

@Service
public class RestriccionDepProvDistServiceImpl implements
		RestriccionDepProvDistService {

	
	//********** inyeciones ******************//
	@Resource
	RestriccionDepProvDistDAO restriccionDepProvDistDao;
	
	//********** metodos ******************//
	@Override
	public List<RestriccionDepProvDist> findRestriccionDepProvDistByProgramaId(
			int programaId) {
		
		return restriccionDepProvDistDao.findListRestriccionDepProvDistByProgramaId(programaId);
	}
	


}
