package pe.com.fondam.sgp.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.EstadoCabeceraDAO;
import pe.com.fondam.sgp.core.service.EstadoCabeceraService;

@Service
public class EstadoCabeceraServiceImpl implements EstadoCabeceraService {

	/************ inyecciones *************/
	@Resource
	EstadoCabeceraDAO estadoCabeceraDAO;
	
	
	/************ metodos *************/
}
