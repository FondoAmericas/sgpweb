package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.EfectoProyectoBean;
import pe.com.fondam.sgp.core.dao.EfectoProyectoDAO;
import pe.com.fondam.sgp.core.domain.EfectoProyecto;
import pe.com.fondam.sgp.core.service.EfectoProyectoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class EfectoProyectoServiceImpl implements EfectoProyectoService {

	//************ inyecciones *****************/
	@Resource
	EfectoProyectoDAO efectoProyectoDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//************ metodos *****************/
	@Override
	public List<EfectoProyecto> findEfectoProyectoByInformeFinalId(
			Integer informeFinalId) {

		String consulta=" from EfectoProyecto where informeFinal.informeFinalID = ? ";
		Object[] params= new Object[1];
		params[0]=informeFinalId;
		
		return efectoProyectoDAO.findEfectoProyectosByConsulta(consulta, params);
	}


	@Override
	public List<EfectoProyecto> llenaCompletaListEfectoProyecto(
			List<EfectoProyecto> listEfectoProyecto) {
		for (EfectoProyecto efectoProyecto : listEfectoProyecto) {
			efectoProyecto.setDescripcionefectoProy(tablaEspecificaService.findTablaEspecificaById(efectoProyecto.getFkidtablaespefectoProy()).getDescripcionCabecera());
		}
		return listEfectoProyecto;
	}


	@Override
	public EfectoProyecto updateEfectoProyecto(EfectoProyecto efectoProyecto) {
		return efectoProyectoDAO.updateEfectoProyecto(efectoProyecto);
	}


	@Override
	public void deleteEfectoProyecto(Integer efectoProyectoId) {

		efectoProyectoDAO.deleteEfectoProyecto(efectoProyectoDAO.findEfectoProyectoById(efectoProyectoId));
	}


	@Override
	public List<EfectoProyectoBean> llenaListEfectoProyectoBean(
			List<EfectoProyecto> listEfectoProyecto) {
		
		List<EfectoProyectoBean> listEfectoProyectoBean= new ArrayList<EfectoProyectoBean>();
		
		for (EfectoProyecto efectoProyecto : listEfectoProyecto) {
		listEfectoProyectoBean.add(llenaEfectoProyectoBean(efectoProyecto));	
		}
		return listEfectoProyectoBean;
	}


	private EfectoProyectoBean llenaEfectoProyectoBean(
			EfectoProyecto efectoProyecto) {

EfectoProyectoBean efectoProyectoBean= new EfectoProyectoBean();
efectoProyectoBean.setComentario(efectoProyecto.getComentario());
efectoProyectoBean.setDescripcionefectoProy(efectoProyecto.getDescripcionefectoProy());
efectoProyectoBean.setEfectoProyectoID(efectoProyecto.getEfectoProyectoID());
efectoProyectoBean.setFkidtablaespefectoProy(efectoProyecto.getFkidtablaespefectoProy());
efectoProyectoBean.setInformeFinal(efectoProyecto.getInformeFinal());

		return efectoProyectoBean;
	}
	
	
	
}
