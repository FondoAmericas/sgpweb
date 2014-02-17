package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.ProgramaBean2;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.service.ProgramaService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;
import pe.com.fondam.sgp.core.service.TipoPeriodoService;

@Service
public class ProgramaServiceImpl implements ProgramaService {

	//************* inyecciones *******************//
	@Resource
	ProgramaDAO programaDAO;

	@Resource
	TipoPeriodoService tipoPeriodoService;
	
	@Resource
	TablaEspecificaService tablaEspecificaService;
	
	//************* metodos *******************//
	@Override
	public List<Programa> findProgramaByModalidadFinanciamientoId(
			int modalidadFinanciamientoId) {
		/*String consulta=" from Programa where fkIdtablaespModalidadFinancia = ? ";
		Object[] params = new Object[1];
		params[0]= modalidadFinanciamientoId*/
		return programaDAO.findProgramaByModFinan(modalidadFinanciamientoId);
	}
	
	@Override
	public ProgramaBean2 llenarProgramaBean(Programa programa) {
		ProgramaBean2 programaBean = new ProgramaBean2();

		programaBean.setDuracionPrograma(programa.getDuracionPrograma());
		programaBean.setFkIdtablaespModalidadFinancia(programa
				.getFkIdtablaespModalidadFinancia());
		programaBean.setFkIdtablaespTipoCuenta(programa
				.getFkIdtablaespTipoCuenta());
		programaBean.setModalidadFinancia(tablaEspecificaService
				.findTablaEspecificaById(
						programa.getFkIdtablaespModalidadFinancia())
				.getDescripcionCabecera());
		programaBean.setNombrePrograma(programa.getNombrePrograma());
		programaBean.setNombreTipoPeriodo(tipoPeriodoService 
				.findTipoPeriodoById(
						programa.getTipoPeriodo().getTipoPeriodoID())
				.getDescripPeriodo());

		return programaBean;
	}
	
	
}
