package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.bean.ProgramaBean2;
import pe.com.fondam.sgp.core.domain.Programa;

public interface ProgramaService {

	List<Programa> findProgramaByModalidadFinanciamientoId(
			int modalidadFinanciamientoId);

	ProgramaBean2 llenarProgramaBean(Programa programa); 
}
