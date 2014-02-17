package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.IndicadorResultadoDAO;
import pe.com.fondam.sgp.core.domain.IndicadorResultado;
import pe.com.fondam.sgp.core.service.IndicadorResultadoService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class IndicadorResultadoServiceImpl implements IndicadorResultadoService {
	
	//****************** inyecciones ******************//
	@Resource
	IndicadorResultadoDAO indicadorResultadoDAO;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;

	
	//****************** metodos ******************//
	@Override
	public void deleteIndicadorResultado(IndicadorResultado indicadorResultado) {
		indicadorResultado = findIndicadorResultadoById(indicadorResultado.getIndicadorResultadoID());
		indicadorResultadoDAO.deleteIndicadorResultado(indicadorResultado);
	}

	@Override
	public IndicadorResultado findIndicadorResultadoById(Integer id) {
		return indicadorResultadoDAO.findIndicadorResultadoById(id);
	}

	@Override
	public List<IndicadorResultado> findIndicadorResultadoByResultadoID(Integer resultadoID) {
		return indicadorResultadoDAO.findIndicadorResultadoByResultadoID(resultadoID);
	}

	@Override
	public IndicadorResultado updateIndicadorResultado(
			IndicadorResultado indicadorResultado) {
		
		return indicadorResultadoDAO.updateIndicadorResultado(indicadorResultado);
	}

	@Override
	public List<IndicadorResultado> llenaIndicadorResultadoCompleto(
			List<IndicadorResultado> listIndicadorResultado) {
		for (IndicadorResultado indicadorResultado : listIndicadorResultado) {
			indicadorResultado.setTipoIndicadorResultadoString(tablaEspecificaService.findTablaEspecificaById(indicadorResultado.getFkIdtablaespTipoIndicadorResultado()).getDescripcionCabecera());
			indicadorResultado.setUnidadMedidaString(tablaEspecificaService.findTablaEspecificaById(indicadorResultado.getFkIdtablaespUnidadMedida()).getDescripcionCabecera());
		}
		return listIndicadorResultado;
	}

}
