package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.TablaProfundidadesDAO;
import pe.com.fondam.sgp.core.domain.TablaProfundidades;
import pe.com.fondam.sgp.core.service.TablaProfundidadesService;

@Service
public class TablaProfundidadesServiceImpl implements TablaProfundidadesService {

	//*************** inyecciones **************//
	@Resource
	TablaProfundidadesDAO tablaProfundidadesDAO;

	
	//*************** metodos **************//
	@Override
	public TablaProfundidades findTablaProfundidadesServiceById(
			Integer tablaProfundidadId) {
		return tablaProfundidadesDAO.findTablaProfundidadesById(tablaProfundidadId);
	}


	@Override
	public String findProfundidadText(TablaProfundidades tablaProfundidades) {
		String profundidad="";
		
		if(!tablaProfundidades.getProfundidad01().equals("")){
			profundidad=tablaProfundidades.getProfundidad01();
		}
		if(!tablaProfundidades.getProfundidad02().equals("")){
			profundidad=tablaProfundidades.getProfundidad02();
		}
		if(!tablaProfundidades.getProfundidad03().equals("")){
			profundidad=tablaProfundidades.getProfundidad03();
		}
		if(!tablaProfundidades.getProfundidad04().equals("")){
			profundidad=tablaProfundidades.getProfundidad04();
		}
		if(!tablaProfundidades.getProfundidad05().equals("")){
			profundidad=tablaProfundidades.getProfundidad05();
		}
		
		return profundidad;
	}
	
}
