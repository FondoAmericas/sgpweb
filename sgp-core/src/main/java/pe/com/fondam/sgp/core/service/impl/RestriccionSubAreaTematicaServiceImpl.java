package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.RestriccionSubAreaTematicaDAO;
import pe.com.fondam.sgp.core.domain.RestriccionSubAreaTematica;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;
import pe.com.fondam.sgp.core.service.RestriccionSubAreaTematicaService;
import pe.com.fondam.sgp.core.service.SubAreaTematicaService;

@Service
public class RestriccionSubAreaTematicaServiceImpl implements
		RestriccionSubAreaTematicaService {

	//************** inyecciones **********************//
	@Resource
	RestriccionSubAreaTematicaDAO restriccionSubAreaTematicaDAO;
	
	@Resource
	SubAreaTematicaService subAreaTematicaService;
	
	//************** metodos **********************//
	@Override
	public List<RestriccionSubAreaTematica> findRestriccionSubAreaTematicaByProgramaId(
			int programaId) {
		/*String consulta=" from RestriccionSubAreaTematica where programa.programaID = ? ";
		Object[] params = new Object[1];
		params[0]= programaId;*/
		return restriccionSubAreaTematicaDAO.findListRestriccionSubAreaTematicayProgramaId(programaId);
	}

	@Override
	public List<SubAreaTematica> findRestriccionSubAreaTematicaByProgramaIdByAreaTematicaId(
			int programaId, int areaTematicaId) {

		List<RestriccionSubAreaTematica> listRestriccionSubAreaTematica= restriccionSubAreaTematicaDAO.findListRestriccionSubAreaTematicayProgramaId(programaId);
		List<SubAreaTematica> listSubAreaTematica = new ArrayList<SubAreaTematica>();
		for (RestriccionSubAreaTematica restriccionSubAreaTematica : listRestriccionSubAreaTematica) {
			if(restriccionSubAreaTematica.getSubAreaTematica().getFkIdtablaespAreaTematica()==areaTematicaId){
				listSubAreaTematica.add(subAreaTematicaService.findSubAreaTematicaById(restriccionSubAreaTematica.getSubAreaTematica().getSubAreaTematicaID()));
			}
			
		}
		return listSubAreaTematica;
	}

	
	
}
