package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.InformeFinalBienDAO;
import pe.com.fondam.sgp.core.domain.InformeFinalBien;
import pe.com.fondam.sgp.core.service.InformeFinalBienService;

@Service
public class InformeFinalBienServiceImpl implements InformeFinalBienService {

	//*************** inyeccones **********//
	@Resource
	InformeFinalBienDAO informeFinalBienDAO;
	
	//*************** metodos **********//
	@Override
	public InformeFinalBien findInformeFinalBienByInformeFinalIdByBienId(
			Integer informeFinalId, Integer bienID) {

		String consulta = " from InformeFinalBien where informeFinal.informeFinalID = ? and bien.bienID = ? ";
		Object[] params = new Object[2];
		params[0]= informeFinalId;
		params[1]=bienID;
		
		List<InformeFinalBien> listInformeFinalBien= informeFinalBienDAO.findInformeFinalBienByConsulta(consulta, params);
		
		InformeFinalBien informeFinalBien= null;
		if(listInformeFinalBien.size()>0){
			informeFinalBien= new InformeFinalBien();
			informeFinalBien=listInformeFinalBien.get(0);
		}
		return informeFinalBien;
	}

	@Override
	public List<InformeFinalBien> findInformeFinalBienByBienId(Integer bienID) {

		String consulta = " from InformeFinalBien where bien.bienID = ? ";
		Object[] params = new Object[1];
		params[0]=bienID;
		
		List<InformeFinalBien> listInformeFinalBien= informeFinalBienDAO.findInformeFinalBienByConsulta(consulta, params);
		
		return listInformeFinalBien;
	}

	@Override
	public InformeFinalBien updateInformeFinalBien(
			InformeFinalBien informeFinalBienSave) {

		return informeFinalBienDAO.updateInformeFinalBien(informeFinalBienSave);
	}

	@Override
	public void deleteInformeFinalBienByBienId(Integer bienID) {
		List<InformeFinalBien> listInformeFinalBien = findInformeFinalBienByBienId(bienID);
		for (InformeFinalBien informeFinalBien : listInformeFinalBien) {
			informeFinalBienDAO.deleteInformeFinalBien(informeFinalBien);
		}
		
	}

}
