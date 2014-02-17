package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CompromisoPagoDAO;
import pe.com.fondam.sgp.core.domain.CompromisoPago;
/**
*
* @author Zolanch Távara
*/

@Repository
public class CompromisoPagoDAOImpl extends JpaBaseDAOImpl implements CompromisoPagoDAO {

	@Autowired
	public CompromisoPagoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveCompromisoPago(CompromisoPago compromisoPago) {
		super.save(compromisoPago);
		
	}

	@Override
	public CompromisoPago updateCompromisoPago(CompromisoPago compromisoPago) {
		return super.update(compromisoPago);
	}

	@Override
	public void deleteCompromisoPago(CompromisoPago compromisoPago) {
		CompromisoPago compromisoPagoNew=findCompromisoPagoById(compromisoPago.getCompromisoPagoID());
		super.delete(compromisoPagoNew);
	}

	@Override
	public CompromisoPago findCompromisoPagoById(Integer id) {
		return super.findById(CompromisoPago.class, id);
	}

	@Override
	public List<CompromisoPago> findCompromisoPagos(String queryString, Object[] params ) {
		return super.find(queryString, params);
	}


}
