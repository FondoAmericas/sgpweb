package pe.com.fondam.sgp.core.service;

import java.util.List;

import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;

public interface CronogramaMetaPorResultadoService {

	void saveCronogramaMetaPorResultado(CronogramaMetaPorResultado cronogramaMetaPorResultado);

	CronogramaMetaPorResultado updateCronogramaMetaPorResultado(CronogramaMetaPorResultado cronogramaMetaPorResultado);

	void deleteCronogramaMetaPorResultado(CronogramaMetaPorResultado cronogramaMetaPorResultado);

	CronogramaMetaPorResultado findCronogramaMetaPorResultadoById(Integer id);

	List<CronogramaMetaPorResultado> findCronogramaMetaPorResultadoByResultadoID(Integer resultadoID);

	List<CronogramaMetaPorResultado> findCronogramaMetaPorResultados();

}
