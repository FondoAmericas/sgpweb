package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.SubAreaTematicaDAO;
import pe.com.fondam.sgp.core.domain.SubAreaTematica;

/**
 * 
 * @author Zolanch Távara
 */
@Repository
public class SubAreaTematicaDAOImpl extends JpaBaseDAOImpl implements SubAreaTematicaDAO {

	@Autowired
	public SubAreaTematicaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveSubAreaTematica(SubAreaTematica subAreaTematica) {
	super.save(subAreaTematica);

	}

	@Override
	public SubAreaTematica updateSubAreaTematica(SubAreaTematica subAreaTematica) {
		
		return super.update(subAreaTematica);
	}

	@Override
	public void deleteSubAreaTematica(SubAreaTematica subAreaTematica) {
		 super.delete(subAreaTematica);

	}

	@Override
	public SubAreaTematica findSubAreaTematicaById(Integer id) {
				return super.findById(SubAreaTematica.class, id);
	}

	@Override
	public List<SubAreaTematica> findSubAreaTematica() {
	
		return super.find("from SubAreaTematica");
	}

	@Override
	public List<SubAreaTematica> findSubAreaTematicaByDescripcion() {
		
		return super.find("from SubAreaTematica where codInternoSubNivel in (0)");
	}

}
