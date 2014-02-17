package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.CronogramaMetaPorResultadoDAO;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;
import pe.com.fondam.sgp.core.service.CronogramaMetaPorResultadoService;

@Service
public class CronogramaMetaPorResultadoServiceImpl implements CronogramaMetaPorResultadoService {
	
	@Autowired
	CronogramaMetaPorResultadoDAO cronogramaMetaPorResultadoDAO;

	@Override
	public void saveCronogramaMetaPorResultado(CronogramaMetaPorResultado cronogramaMetaPorResultado) {
		cronogramaMetaPorResultadoDAO.saveCronogramaMetaPorResultado(cronogramaMetaPorResultado);
	}

	@Override
	public CronogramaMetaPorResultado updateCronogramaMetaPorResultado(CronogramaMetaPorResultado cronogramaMetaPorResultado) {
		return cronogramaMetaPorResultadoDAO.updateCronogramaMetaPorResultado(cronogramaMetaPorResultado);
	}

	@Override
	public void deleteCronogramaMetaPorResultado(CronogramaMetaPorResultado cronogramaMetaPorResultado) {
		cronogramaMetaPorResultado = cronogramaMetaPorResultadoDAO.findCronogramaMetaPorResultadoById(cronogramaMetaPorResultado.getCronogramaMetaPorResultadoID());
		cronogramaMetaPorResultadoDAO.deleteCronogramaMetaPorResultado(cronogramaMetaPorResultado);
	}

	@Override
	public CronogramaMetaPorResultado findCronogramaMetaPorResultadoById(Integer id) {
		return cronogramaMetaPorResultadoDAO.findCronogramaMetaPorResultadoById(id);
	}

	@Override
	public List<CronogramaMetaPorResultado> findCronogramaMetaPorResultadoByResultadoID(Integer resultadoID) {
		return cronogramaMetaPorResultadoDAO.findCronogramaMetaPorResultadoByResultadoID(resultadoID);
	}

	@Override
	public List<CronogramaMetaPorResultado> findCronogramaMetaPorResultados() {
		return cronogramaMetaPorResultadoDAO.findCronogramaMetaPorResultados();
	}
	
	
	//*********************** listas ********************************

}
