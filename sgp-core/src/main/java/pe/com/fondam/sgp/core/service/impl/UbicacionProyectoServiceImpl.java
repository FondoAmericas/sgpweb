package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.UbicacionProyectoDAO;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
import pe.com.fondam.sgp.core.service.BeneficiariosPorResultadoService;
import pe.com.fondam.sgp.core.service.DepProvDistService;
import pe.com.fondam.sgp.core.service.UbicacionProyectoService;

@Service
public class UbicacionProyectoServiceImpl implements UbicacionProyectoService {

	/************ inyecciones **************/
	@Resource
	UbicacionProyectoDAO ubicacionProyectoDAO;
	
	@Resource
	DepProvDistService depProvDistService;
	
	@Resource
	BeneficiariosPorResultadoService beneficiariosPorResultadoService;
	
	/************ metodos **************/
	@Override
	public List<UbicacionProyecto> findUbicacionProyectoXDatoProyectoId(
			Integer datoProyectoID) {
		String consulta =" from UbicacionProyecto where datoProyecto.datoProyectoID = ? ";
		
		Object[] params= new Object[1];
		params[0]= datoProyectoID;
		
		return ubicacionProyectoDAO.findUbicacionProyectoXConsulta(consulta,params);
	}


	@Override
	public UbicacionProyecto findUbicacionProyectoXDatoProyectoIdXDepProvDistId(
			Integer datoProyectoID, Integer depProvDistId) {

			String consulta =" from UbicacionProyecto where datoProyecto.datoProyectoID = ? and depProvDist.depProvDistID = ? ";
			
			Object[] params= new Object[2];
			params[0]= datoProyectoID;
			params[1]=depProvDistId;
			
			List<UbicacionProyecto> listUbicacionProyecto =ubicacionProyectoDAO.findUbicacionProyectoXConsulta(consulta,params);
			
			UbicacionProyecto ubicacionProyecto= new UbicacionProyecto();
			if(listUbicacionProyecto.size()>0){
				ubicacionProyecto=listUbicacionProyecto.get(0);
			}
			return ubicacionProyecto;
	}

	//**************** listas ********************//
	public List<UbicacionProyecto> llenaListUbicacionProyectoCompleto(List<UbicacionProyecto> listUbicacionProyecto){
		
		for (UbicacionProyecto ubicacionProyecto : listUbicacionProyecto) {
			ubicacionProyecto.setDescripcionDepartamento(depProvDistService.findListDepartamentos(ubicacionProyecto.getDepProvDist().getIdDepartamento()).getDescripcion());
			ubicacionProyecto.setDescripcionProvincia(depProvDistService.findListProvincia(ubicacionProyecto.getDepProvDist().getIdDepartamento(),ubicacionProyecto.getDepProvDist().getIdProvincia()).getDescripcion());
			ubicacionProyecto.setDescripcionDistrito(depProvDistService.findListDistrito(ubicacionProyecto.getDepProvDist().getIdDepartamento(),ubicacionProyecto.getDepProvDist().getIdProvincia(),ubicacionProyecto.getDepProvDist().getIdDistrito()).getDescripcion());
			ubicacionProyecto.setListBeneficiariosPorResultado(beneficiariosPorResultadoService.llenaListBeneficiariosPorResultado(beneficiariosPorResultadoService.findBeneficiariosPorResultadoXUbicacionProyectoId(ubicacionProyecto.getUbicacionProyectoID())));
		}
		return listUbicacionProyecto;
	}
	
}
