package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.SubAreaTematicaDAO;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.service.SubAreaTematicaService;

@Service
public class SubAreaTematicaServiceImpl implements SubAreaTematicaService {

	@Resource
	SubAreaTematicaDAO subAreaTematicaDAO;

	

	/**************   metodos   *********************/
	@Override
	public SubAreaTematica findSubAreaTematicaById(Integer subAreaTematicaID) {
		return subAreaTematicaDAO.findSubAreaTematicaById(subAreaTematicaID);
	}
}
