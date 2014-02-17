package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoPlanOperativoDAO;
import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.domain.DatoPlanOperativo;
import pe.com.fondam.sgp.core.service.DatoPlanOperativoService;
import pe.com.fondam.sgp.core.service.DatoProyectoService;

@Service
public class DatoPlanOperativoServiceImpl implements DatoPlanOperativoService {

	//*********************  inyecciones  ******************//
	@Resource
	DatoPlanOperativoDAO datoPlanOperativoDAO;

	@Resource
	DatoProyectoService datoProyectoService;
	
	@Resource
	DatoProyectoDAO datoProyectoDAO;
	
	//*********************  metodos ******************//
	@Override
	public List<DatoPlanOperativo> findDatoPlanOperativoByDatoProyectoID(
			Integer datoProyectoID) {

		String consulta= " from DatoPlanOperativo where datoProyecto.datoProyectoID = ?";
		Object[] params= new Object[1];
		params[0]=datoProyectoID;
		
		return datoPlanOperativoDAO.findDatoPlanOperativoByConsulta(consulta, params);
	}


	@Override
	public DatoPlanOperativo updateDatoPlanOperativo(
			DatoPlanOperativo datoPlanOperativo) {
		return datoPlanOperativoDAO.updateDatoPlanOperativo(datoPlanOperativo);
	}


	@Override
	public DatoPlanOperativo findDatoPlanOperativoByID(
			Integer datoPlanOperativoID) {
		return datoPlanOperativoDAO.findDatoPlanOperativoById(datoPlanOperativoID);
	}

//*************** metodos internos ***************//
	/*
	private DatoPlanOperativo llenaDatoPlanOperativo(
			DatoPlanOperativo datoPlanOperativo) {
		
		datoPlanOperativo.setDatoProyecto(datoProyectoDAO.findDatoProyectoById(datoPlanOperativo.getDatoProyecto().getDatoProyectoID()));
		return datoPlanOperativo;
	}*/
	
}
