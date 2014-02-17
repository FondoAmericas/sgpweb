package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TipoCambioDAO;
import pe.com.fondam.sgp.core.domain.TipoCambio;

@Repository
public class TipoCambioDAOImpl extends JpaBaseDAOImpl implements TipoCambioDAO {
	
	@Autowired
	public TipoCambioDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveTipoCambio(TipoCambio tipoCambio) {
		super.save(tipoCambio);
	}

	@Override
	public TipoCambio updateTipoCambio(TipoCambio tipoCambio) {
		return super.update(tipoCambio);
	}

	@Override
	public void deleteTipoCambio(TipoCambio tipoCambio) {
		super.delete(tipoCambio);
	}

	@Override
	public TipoCambio findTipoCambioById(Integer id) {
		return super.findById(TipoCambio.class, id);
	}

	@Override
	public List<TipoCambio> findTipoCambioXConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	@Override
	public List<TipoCambio> findTipoCambios() {
		return super.find("from TipoCambio");
	}

}
