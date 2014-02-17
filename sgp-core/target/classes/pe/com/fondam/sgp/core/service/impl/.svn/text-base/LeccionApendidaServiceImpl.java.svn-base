package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.LeccionApendidaBean;
import pe.com.fondam.sgp.core.dao.LeccionApendidaDAO;
import pe.com.fondam.sgp.core.domain.LeccionApendida;
import pe.com.fondam.sgp.core.service.LeccionApendidaService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class LeccionApendidaServiceImpl implements LeccionApendidaService {

	//*********** inyecciones ***************//
	@Resource
	LeccionApendidaDAO leccionApendidaDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//*********** metodos ***************//
	@Override
	public LeccionApendida updateLeccionApendida(LeccionApendida leccionApendida) {
		return leccionApendidaDAO.updateLeccionApendida(leccionApendida);
	}

	@Override
	public List<LeccionApendida> findLeccionApendidaByInformeFinalId(
			Integer informeFinalId) {
		String consulta=" from LeccionApendida where informeFinal.informeFinalID = ? ";
		Object[] params= new Object[1];
		params[0]=informeFinalId;
		
		return leccionApendidaDAO.findLeccionApendidaByConsulta(consulta, params);
	}

	@Override
	public List<LeccionApendida> llenaCompletaListLeccionApendida(
			List<LeccionApendida> listLeccionApendida) {
		for (LeccionApendida leccionApendida : listLeccionApendida) {
			leccionApendida.setDescripcionTipoLeccion(tablaEspecificaService.findTablaEspecificaById(leccionApendida.getFkIdtablaespTipoLeccion()).getDescripcionCabecera());
		}
		return listLeccionApendida;
	}

	@Override
	public void deleteLeccionApendida(Integer idRegistro) {
		leccionApendidaDAO.deleteLeccionApendida(leccionApendidaDAO.findLeccionApendidaById(idRegistro));
		
	}

	@Override
	public List<LeccionApendidaBean> llenaListLeccionApendidaBean(
			List<LeccionApendida> listLeccionApendida) {
		
		List<LeccionApendidaBean> listLeccionApendidaBean= new ArrayList<LeccionApendidaBean>();
		for (LeccionApendida leccionApendida : listLeccionApendida) {
			listLeccionApendidaBean.add(llenaLeccionApendidaBean(leccionApendida));
		}
		return listLeccionApendidaBean;
	}

	private LeccionApendidaBean llenaLeccionApendidaBean(
			LeccionApendida leccionApendida) {
		LeccionApendidaBean leccionApendidaBean= new LeccionApendidaBean();
		
		leccionApendidaBean.setComentario(leccionApendida.getComentario());
		leccionApendidaBean.setDescripcionTipoLeccion(leccionApendida.getDescripcionTipoLeccion());
		leccionApendidaBean.setFkIdtablaespTipoLeccion(leccionApendida.getFkIdtablaespTipoLeccion());
		leccionApendidaBean.setInformeFinal(leccionApendida.getInformeFinal());
		leccionApendidaBean.setLeccionApendidaID(leccionApendida.getLeccionApendidaID());
		
		return leccionApendidaBean;
	}
	
}
