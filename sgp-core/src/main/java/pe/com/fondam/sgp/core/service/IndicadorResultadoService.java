package pe.com.fondam.sgp.core.service;


import java.util.List;

import pe.com.fondam.sgp.core.domain.IndicadorResultado;

public interface IndicadorResultadoService {

	void deleteIndicadorResultado(IndicadorResultado indicadorResultado);

	IndicadorResultado findIndicadorResultadoById(Integer id);

	List<IndicadorResultado> findIndicadorResultadoByResultadoID(Integer resultadoID);

	IndicadorResultado updateIndicadorResultado(
			IndicadorResultado indicadorResultado);

	List<IndicadorResultado> llenaIndicadorResultadoCompleto(
			List<IndicadorResultado> listIndicadorResultado);
}
