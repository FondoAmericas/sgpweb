package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TipoPeriodoDAO;
import pe.com.fondam.sgp.core.domain.TipoPeriodo;
/**
*
* @author Zolanch Távara
*/
@Repository
public class TipoPeriodoDAOImpl extends JpaBaseDAOImpl implements TipoPeriodoDAO {

	@Autowired
	public TipoPeriodoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveTipoPeriodo(TipoPeriodo tipoPeriodo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoPeriodo updateTipoPeriodo(TipoPeriodo tipoPeriodo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTipoPeriodo(TipoPeriodo tipoPeriodo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TipoPeriodo findTipoPeriodoById(Integer id) {
		return super.findById(TipoPeriodo.class, id);
	}

	@Override
	public List<TipoPeriodo> findTipoPeriodos() {

		return super.find("From TipoPeriodo");
	}
	
}
