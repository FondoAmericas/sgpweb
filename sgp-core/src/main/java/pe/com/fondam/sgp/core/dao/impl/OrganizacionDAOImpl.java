package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.OrganizacionDAO;
import pe.com.fondam.sgp.core.domain.Organizacion;


@Repository
public class OrganizacionDAOImpl extends JpaBaseDAOImpl  implements OrganizacionDAO {

	//******** inyecciones **************//
	@Autowired
	public OrganizacionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//******** metodos **************//
	@Override
	public void saveOrganizacion(Organizacion organizacion) {
		super.save(organizacion);
		
	}

	@Override
	public Organizacion updateOrganizacion(Organizacion organizacion) {
		return super.update(organizacion);
	}

	@Override
	public void deleteOrganizacion(Organizacion organizacion) {
		super.delete(organizacion);
		
	}

	@Override
	public Organizacion findOrganizacionById(Integer id) {
		return super.findById(Organizacion.class, id);
	}

	@Override
	public List<Organizacion> findOrganizacionByConsulta(String consulta,
			Object[] params) {
		return super.find(consulta, params);
	}

}
