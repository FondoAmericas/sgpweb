package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.RecursoUtilizadoDAO;
import pe.com.fondam.sgp.core.domain.RecursoUtilizado;
import pe.com.fondam.sgp.core.domain.RecursoUtilizadoBean;
import pe.com.fondam.sgp.core.service.RecursoUtilizadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class RecursoUtilizadoServiceImpl implements RecursoUtilizadoService {

	//*************** inyecciones *********************//
	@Resource
	RecursoUtilizadoDAO recursoUtilizadoDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	
	//*************** metodos *********************//
	@Override
	public RecursoUtilizado updateRecursoUtilizado(
			RecursoUtilizado recursoUtilizado) {
		return recursoUtilizadoDAO.updateRecursoUtilizado(recursoUtilizado);
	}

	@Override
	public List<RecursoUtilizadoBean> findRecursoUtilizadoBeanByBienId(Integer bienID) {

		String consulta = " from RecursoUtilizado where bien.bienID = ? ";
		Object[] params= new Object[1];
		params[0]=bienID;
		
		List<RecursoUtilizado> listRecursoUtilizado=recursoUtilizadoDAO.findRecursoUtilizadoByConsulta(consulta, params);
		for (RecursoUtilizado recursoUtilizado : listRecursoUtilizado) {
			recursoUtilizado.setDescripcionTipoMoneda(tablaEspecificaService.findTablaEspecificaById(recursoUtilizado.getFkIdtablaespTipoMoneda()).getDescripcionCabecera());
		}
		
		List<RecursoUtilizadoBean> listRecursoUtilizadoBean=llenaListRecursoUtilizadoBean(listRecursoUtilizado);
		return listRecursoUtilizadoBean;
	}

	@Override
	public List<RecursoUtilizado> findRecursoUtilizadoByBienId(Integer bienID) {

		String consulta = " from RecursoUtilizado where bien.bienID = ? ";
		Object[] params= new Object[1];
		params[0]=bienID;
		
		List<RecursoUtilizado> listRecursoUtilizado=recursoUtilizadoDAO.findRecursoUtilizadoByConsulta(consulta, params);
		
		
		return listRecursoUtilizado;
	}
	
	@Override
	public void deleteRecursoUtilizado(Integer recursoUtilizadoId) {
		
			recursoUtilizadoDAO.deleteRecursoUtilizado(recursoUtilizadoDAO.findRecursoUtilizadoById(recursoUtilizadoId));
		
	}
	
	//*************** listas *********************//
	private List<RecursoUtilizadoBean> llenaListRecursoUtilizadoBean(
			List<RecursoUtilizado> listRecursoUtilizado) {
		
		List<RecursoUtilizadoBean>  listRecursoUtilizadoBean =  new ArrayList<RecursoUtilizadoBean>();
		
		for (RecursoUtilizado recursoUtilizado : listRecursoUtilizado) {
			RecursoUtilizadoBean recursoUtilizadoBean = new RecursoUtilizadoBean();
			
			recursoUtilizadoBean.setBien( recursoUtilizado.getBien());
			recursoUtilizadoBean.setDescripcionTipoMoneda(recursoUtilizado.getDescripcionTipoMoneda());
			recursoUtilizadoBean.setFkIdtablaespTipoMoneda(recursoUtilizado.getFkIdtablaespTipoMoneda());
			recursoUtilizadoBean.setFuenteFinanciadora(recursoUtilizado.getFuenteFinanciadora());
			recursoUtilizadoBean.setMonto(recursoUtilizado.getMonto());
			recursoUtilizadoBean.setRecursoUtilizadoID(recursoUtilizado.getRecursoUtilizadoID());
			
			listRecursoUtilizadoBean.add(recursoUtilizadoBean);
		}
		return listRecursoUtilizadoBean;
	}
}
