package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.CategoriaActividadDAO;
import pe.com.fondam.sgp.core.domain.CategoriaActividad;

@Repository
public class CategoriaActividadDAOImpl extends JpaBaseDAOImpl implements
		CategoriaActividadDAO {

	@Autowired
	public CategoriaActividadDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveCategoriaActividad(CategoriaActividad categoriaActividad) {
		super.save(categoriaActividad);
	}

	@Override
	public CategoriaActividad updateCategoriaActividad(
			CategoriaActividad categoriaActividad) {
		return super.update(categoriaActividad);
	}

	@Override
	public void deleteCategoriaActividad(CategoriaActividad categoriaActividad) {
		super.delete(categoriaActividad);
	}

	@Override
	public CategoriaActividad findCategoriaActividadById(Integer id) {
		return super.findById(CategoriaActividad.class, id);
	}

	@Override
	public List<CategoriaActividad> findCategoriaActividadByTipoActividadId(
			Integer tipoActividadId) {

		String queryString = "from CategoriaActividad where fkIdtablaespTipoActividad = ? ";
		Object[] params = new Object[1];
		params[0] = tipoActividadId;

		return super.find(queryString, params);
	}

	@Override
	public List<CategoriaActividad> findCategoriaActividades() {
		return super.find("from CategoriaActividad");
	}

}
