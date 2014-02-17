package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.InformeFinalDAO;
import pe.com.fondam.sgp.core.domain.InformeFinal;
import pe.com.fondam.sgp.core.service.InformeFinalService;

@Service
public class InformeFinalServiceImpl implements InformeFinalService {

	//*************** inyecciones **********************//
	@Resource
	InformeFinalDAO informeFinalDAO;

	
	//*************** metodos **********************//
	@Override
	public InformeFinal findInformeFinalByDatoProyectoId(Integer datoProyectoID) {
		String consulta = " from InformeFinal where datoProyecto.datoProyectoID = ? ";
		Object[] params = new Object[1];
		params[0]=datoProyectoID;
		
		List<InformeFinal> listInformeFinal=informeFinalDAO.findInformeFinalByConsulta(consulta, params);
		InformeFinal informeFinal= null;
		if ( listInformeFinal.size()>0){
			informeFinal= listInformeFinal.get(0);
		}
		return informeFinal;
	}


	@Override
	public InformeFinal updateInformeFinal(InformeFinal informeFinal) {
		return informeFinalDAO.updateInformeFinal(informeFinal);
	}


	@Override
	public InformeFinal findInformeFinalById(Integer informeFinalId) {
		return informeFinalDAO.findInformeFinalById(informeFinalId);
	}
	

	
}
