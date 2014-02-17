package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.InstitucionDAO;
import pe.com.fondam.sgp.core.domain.Institucion;
/**
*
* @author Zolanch Távara
*/
@Repository
public class InstitucionDAOImpl  extends JpaBaseDAOImpl implements InstitucionDAO {
	@Autowired
	public InstitucionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public Integer saveInstitucion(Institucion institucion) {
		super.save(institucion);
		return institucion.getInstitucionID();
		
	}
	
	public void saveInstitucion1(Institucion institucion) {
		super.save(institucion);	
	}

	@Override
	public Institucion updateInstitucion(Institucion institucion) {
	
		return super.update(institucion);
	}

	@Override
	public void deleteInstitucion(Institucion institucion) {
		super.delete(institucion);
		
	}

	@Override
	public Institucion findInstitucionById(Integer id) {
		return findById(Institucion.class, id);
	}

	@Override
	public Institucion findInstitucionByRUC(String ruc) {
		//verifico si existe proyectos
		String queryString1 = "from Institucion where rucInstitucion= ? ";
		Object[] params1 = new Object[1];
		params1[0] = ruc;
		
		List<Institucion> list=super.find(queryString1,params1);
		if (list!=null && list.size()!=0) {
			return list.get(0);
		}
		return null;
	}
	
	public List<Institucion> findInstitucion() {
		String queryString = "from Institucion";
		return super.find(queryString);
	}

}
