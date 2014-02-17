package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.IndicadorResultadoDAO;
import pe.com.fondam.sgp.core.domain.IndicadorResultado;

@Repository
public class IndicadorResultadoDAOImpl extends JpaBaseDAOImpl implements
		IndicadorResultadoDAO {

	@Autowired
	public IndicadorResultadoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveIndicadorResultado(IndicadorResultado indicadorResultado) {
		super.save(indicadorResultado);
	}

	@Override
	public IndicadorResultado updateIndicadorResultado(
			IndicadorResultado indicadorResultado) {
		return super.update(indicadorResultado);
	}

	@Override
	public void deleteIndicadorResultado(IndicadorResultado indicadorResultado) {
		super.delete(indicadorResultado);
	}

	@Override
	public IndicadorResultado findIndicadorResultadoById(Integer id) {
		return super.findById(IndicadorResultado.class, id);
	}

	@Override
	public List<IndicadorResultado> findIndicadorResultado() {
		return super.find("from IndicadorResultado");
	}

	@Override
	public List<IndicadorResultado> findIndicadorResultadoByResultadoID(
			Integer resultadoID) {
		String queryString = "from IndicadorResultado where resultado.resultadoID = ? ";
		Object[] params = new Object[1];
		params[0] = resultadoID;
		return super.find(queryString, params);
	}

}
