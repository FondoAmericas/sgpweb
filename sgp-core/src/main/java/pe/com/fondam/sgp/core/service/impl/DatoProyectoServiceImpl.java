package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoProyectoDAO;
import pe.com.fondam.sgp.core.domain.DatoProyecto;
import pe.com.fondam.sgp.core.service.DatoProyectoService;
import pe.com.fondam.sgp.core.service.DesembolsoService;

@Service
public class DatoProyectoServiceImpl implements DatoProyectoService {

	@Resource
	DatoProyectoDAO datoProyectoDAO; 
	
	@Resource
	DesembolsoService desembolsoService;
	
	//****************  metodos  ***********************//
	@Override
	public DatoProyecto findDatoProyectoById(Integer datoProyectoID) {
		return datoProyectoDAO.findDatoProyectoById(datoProyectoID);
	}

	@Override
	public void updateDatoProyecto(DatoProyecto datoProyecto) {
		datoProyectoDAO.updateDatoProyecto(datoProyecto);
		
	}

	@Override
	public List<DatoProyecto> findDatoProyectoByProgramaByNombre(
			int programaId, String filtroNombre) {
		return datoProyectoDAO.findDatoProyectoByNomProyByProgramaID(filtroNombre, programaId);
	}

	@Override
	public List<DatoProyecto> findDatoProyectoByProgramaByCodigo(
			int programaId, String filtroCodigo) {
		return datoProyectoDAO.findDatoProyectoByCodProyByProgramaID(filtroCodigo, programaId);
	}

	@Override
	public List<DatoProyecto> llenaDatoProyectoConDesembolsos(
			List<DatoProyecto> listDatoProyecto) {
		
		for (DatoProyecto datoProyecto : listDatoProyecto) {
			datoProyecto.setListDesembolso(desembolsoService.llenaDesembolsoCompleto(desembolsoService.findDesembolsoByDatoProyectoID(datoProyecto.getDatoProyectoID())));
		}

		return listDatoProyecto;
	}

	@Override
	public List<DatoProyecto> findDatoProyectoByPrograma(int programaId) {
		return datoProyectoDAO.findDatoProyectoByProgramaID(programaId);
	}

}
