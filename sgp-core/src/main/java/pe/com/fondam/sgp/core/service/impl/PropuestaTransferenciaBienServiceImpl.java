package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienDao;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienService;

@Service
public class PropuestaTransferenciaBienServiceImpl implements
		PropuestaTransferenciaBienService {

	//************** inyecciones *******************//
	@Resource
	PropuestaTransferenciaBienDao propuestaTransferenciaBienDao;

	
	//************** metodos *******************//
	@Override
	public PropuestaTransferenciaBien findPropuestaTransferenciaBienByPropuestaTransferenciaIdByBienId(
			Integer propuestaTransferenciaId, Integer bienId) {

		String consulta = " from PropuestaTransferenciaBien where propuestaTransferencia.propuestaTransferenciaID = ? and bien.bienID = ? ";
		Object[] params = new Object[2];
		params[0]= propuestaTransferenciaId;
		params[1]=bienId;
		
		List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien= propuestaTransferenciaBienDao.findPropuestaTransferenciaBienByConsulta(consulta, params);
		
		PropuestaTransferenciaBien propuestaTransferenciaBien= null;
		if(listPropuestaTransferenciaBien.size()>0){
			propuestaTransferenciaBien= new PropuestaTransferenciaBien();
			propuestaTransferenciaBien=listPropuestaTransferenciaBien.get(0);
		}
		return propuestaTransferenciaBien;
	}


	@Override
	public PropuestaTransferenciaBien updatePropuestaTransferenciaBien(
			PropuestaTransferenciaBien propuestaTransferenciaBien) {
		return propuestaTransferenciaBienDao.updatePropuestaTransferenciaBien(propuestaTransferenciaBien);
	}


	@Override
	public List<PropuestaTransferenciaBien> findPropuestaTransferenciaBienByPropuestaTransferenciaId(
			int propuestaTransferenciaId) {

		String consulta = " from PropuestaTransferenciaBien where propuestaTransferencia.propuestaTransferenciaID = ? ";
		Object[] params = new Object[1];
		params[0]= propuestaTransferenciaId;
		return propuestaTransferenciaBienDao.findPropuestaTransferenciaBienByConsulta(consulta, params);
	}


	@Override
	public PropuestaTransferenciaBien findPropuestaTransferenciaBienById(
			Integer bienBienTransferido) {
		return propuestaTransferenciaBienDao.findPropuestaTransferenciaBienById(bienBienTransferido);
	}


	@Override
	public List<PropuestaTransferenciaBien> findPropuestaTransferenciaBienByBienId(
			Integer bienId) {
		String consulta = " from PropuestaTransferenciaBien where bien.bienID = ? ";
		Object[] params = new Object[1];
		params[0]= bienId;
		return propuestaTransferenciaBienDao.findPropuestaTransferenciaBienByConsulta(consulta, params);
	}


	@Override
	public void deletePropuestaTransferenciaBienByBienId(Integer bienID) {
		List<PropuestaTransferenciaBien> listPropuestaTransferenciaBien = findPropuestaTransferenciaBienByBienId(bienID);
		for (PropuestaTransferenciaBien propuestaTransferenciaBien : listPropuestaTransferenciaBien) {
			propuestaTransferenciaBienDao.deletePropuestaTransferenciaBien(propuestaTransferenciaBien);
		}
		
	}
	
	

	
	
}