package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DetalleDesembolsoDAO;
import pe.com.fondam.sgp.core.domain.DetalleDesembolso;


@Repository
public class DetalleDesembolsoDAOImpl extends JpaBaseDAOImpl implements DetalleDesembolsoDAO{
	
	//***********  inyecciones  **************//
	@Autowired
	public DetalleDesembolsoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	//***********  metodos **************//
	@Override
	public List<DetalleDesembolso> findDetalleDesembolsoByDesembolsoID(Integer desembolsoID) {
		String queryString = "from DetalleDesembolso where desembolso.desembolsoID= ? ";
		Object[] params = new Object[1];
		params[0] = desembolsoID;
		return super.find(queryString, params);
	}
	
	public void saveDetalleDesembolso(DetalleDesembolso detalleDesembolso) {
		super.save(detalleDesembolso);
		
		//return detalleDesembolso.getDetalleDesembolsoID();

	}


	@Override
	public DetalleDesembolso updateDetalleDesembolso(DetalleDesembolso detalleDesembolso) {
		return super.update(detalleDesembolso);
		
	}

}
