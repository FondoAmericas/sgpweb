package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.OrgBienTranferidoDAO;
import pe.com.fondam.sgp.core.domain.OrgBienTranferido;

@Repository
public class OrgBienTranferidoDAOImpl extends JpaBaseDAOImpl implements OrgBienTranferidoDAO {

	//**************** inyecciones **********************//
	@Autowired
	public OrgBienTranferidoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}	
	
	
	//**************** metodos **********************//
	@Override
	public void saveOrgBienTranferido(OrgBienTranferido orgBienTranferido) {
		super.save(orgBienTranferido);
	}

	@Override
	public OrgBienTranferido updateOrgBienTranferido(
			OrgBienTranferido orgBienTranferido) {

		return super.update(orgBienTranferido);
	}

	@Override
	public void deleteOrgBienTranferido(OrgBienTranferido orgBienTranferido) {
		super.delete(orgBienTranferido);
		
	}

	@Override
	public OrgBienTranferido findOrgBienTranferidoById(Integer id) {
		return super.findById(OrgBienTranferido.class, id);
	}

	@Override
	public List<OrgBienTranferido> findOrgBienTranferidoByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
