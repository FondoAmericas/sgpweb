package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.ProblemaSolucionDAO;
import pe.com.fondam.sgp.core.domain.ProblemaSolucion;
import pe.com.fondam.sgp.core.service.ProblemaSolucionService;

@Service
public class ProblemaSolucionServiceImpl implements ProblemaSolucionService {

	/*********  inyecciones ****************/
	@Resource
	ProblemaSolucionDAO problemaSolucionDao;
	
	/*********  metodos  ****************/
	@Override
	public List<ProblemaSolucion> findProblemaSolucionXReporteAvanceId(
			Integer reporteAvanceId) {
		
		String consulta= " from ProblemaSolucion where reporteAvance.reporteAvanceID = ?";
		Object[] params= new Object[1];
		params[0]=reporteAvanceId;
		
		return problemaSolucionDao.findProblemaSolucionXConsulta(consulta, params);
	}

	@Override
	public void saveProblemaSolucion(ProblemaSolucion problemaSolucion) {
		problemaSolucionDao.updateProblemaSolucion(problemaSolucion);
	}

	@Override
	public ProblemaSolucion findProblemaSolucionById(Integer problemaSolucionId) {
		return problemaSolucionDao.findProblemaSolucionById(problemaSolucionId);
	}

	@Override
	public List<ProblemaSolucion> findProblemaSolucionByDatoProyectoId(
			Integer datoProyectoId) {
		String consulta= " from ProblemaSolucion where datoProyecto.datoProyectoID = ?";
		Object[] params= new Object[1];
		params[0]=datoProyectoId;
		
		return problemaSolucionDao.findProblemaSolucionXConsulta(consulta, params);
	}

	@Override
	public ProblemaSolucion updateProblemaSolucion(
			ProblemaSolucion problemaSolucion) {
		return problemaSolucionDao.updateProblemaSolucion(problemaSolucion);
	}

	@Override
	public void deleteProblemaSolucion(Integer problemaSolucionId) {
		ProblemaSolucion problemaSolucion= problemaSolucionDao.findProblemaSolucionById(problemaSolucionId);
		problemaSolucionDao.deleteProblemaSolucion(problemaSolucion);
		
	}

}
