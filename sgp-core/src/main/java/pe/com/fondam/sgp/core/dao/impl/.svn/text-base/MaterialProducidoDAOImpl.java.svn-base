package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.MaterialProducidoDAO;
import pe.com.fondam.sgp.core.domain.MaterialProducido;

@Repository
public class MaterialProducidoDAOImpl extends JpaBaseDAOImpl implements MaterialProducidoDAO {

	//*********** inyecciones ************//
	@Autowired
	public MaterialProducidoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//*********** metodos ************//
	@Override
	public void saveMaterialProducido(MaterialProducido materialProducido) {
		super.save(materialProducido);
		
	}

	@Override
	public MaterialProducido updateMaterialProducido(
			MaterialProducido materialProducido) {
		return super.update(materialProducido);
	}

	@Override
	public void deleteMaterialProducido(MaterialProducido materialProducido) {
		super.delete(materialProducido);
		
	}

	@Override
	public MaterialProducido findMaterialProducidoById(Integer id) {
		return super.findById(MaterialProducido.class, id);
	}

	@Override
	public List<MaterialProducido> findMaterialProducidoByConsulta(String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
