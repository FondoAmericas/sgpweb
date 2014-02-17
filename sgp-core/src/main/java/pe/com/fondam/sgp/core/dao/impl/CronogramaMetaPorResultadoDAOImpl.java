package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CronogramaMetaPorResultadoDAO;
import pe.com.fondam.sgp.core.domain.CronogramaMetaPorResultado;

@Repository
public class CronogramaMetaPorResultadoDAOImpl extends JpaBaseDAOImpl implements
		CronogramaMetaPorResultadoDAO {

	@Autowired
	public CronogramaMetaPorResultadoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveCronogramaMetaPorResultado(
			CronogramaMetaPorResultado cronogramaMetaPorResultado) {
		super.save(cronogramaMetaPorResultado);
	}

	@Override
	public CronogramaMetaPorResultado updateCronogramaMetaPorResultado(
			CronogramaMetaPorResultado cronogramaMetaPorResultado) {
		return super.update(cronogramaMetaPorResultado);
	}

	@Override
	public void deleteCronogramaMetaPorResultado(
			CronogramaMetaPorResultado cronogramaMetaPorResultado) {
		super.delete(cronogramaMetaPorResultado);
	}

	@Override
	public CronogramaMetaPorResultado findCronogramaMetaPorResultadoById(
			Integer id) {
		return super.findById(CronogramaMetaPorResultado.class, id);
	}

	@Override
	public List<CronogramaMetaPorResultado> findCronogramaMetaPorResultadoByResultadoID(
			Integer resultadoID) {
		String queryString = "from CronogramaMetaPorResultado where resultado.resultadoID = ? ";
		Object[] params = new Object[1];
		params[0] = resultadoID;
		return super.find(queryString, params);
	}
	
	/*
	@Override
	public List<CronogramaMetaPorResultado> findCronogramaMetaPorResultadoByResultadoID(
			Integer resultadoID) {
		String queryString = "from CronogramaMetaPorResultado where resultado.resultadoID = ? and estadoEliminado = 1 ";
		Object[] params = new Object[1];
		params[0] = resultadoID;
		return super.find(queryString, params);
	}*/
	
	@Override
	public List<CronogramaMetaPorResultado> findCronogramaMetaPorResultados() {
		return super.find("from CronogramaMetaPorResultado");
	}

}
