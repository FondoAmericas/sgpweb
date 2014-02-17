package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.LeccionApendidaDAO;
import pe.com.fondam.sgp.core.domain.LeccionApendida;

@Repository
public class LeccionApendidaDAOImpl extends JpaBaseDAOImpl implements LeccionApendidaDAO {

	//************** inyecciones ********************//
	@Autowired
	public LeccionApendidaDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//************** metodos ********************//
	@Override
	public void saveLeccionApendida(LeccionApendida leccionApendida) {
		super.save(leccionApendida);
		
	}

	@Override
	public LeccionApendida updateLeccionApendida(LeccionApendida leccionApendida) {
		return super.update(leccionApendida);
	}

	@Override
	public void deleteLeccionApendida(LeccionApendida leccionApendida) {
		super.delete(leccionApendida);
		
	}

	@Override
	public LeccionApendida findLeccionApendidaById(Integer id) {

		return super.findById(LeccionApendida.class, id);
	}

	@Override
	public List<LeccionApendida> findLeccionApendidaByConsulta(String consulta,Object[] params) {

		return super.find(consulta, params);
	}

}
