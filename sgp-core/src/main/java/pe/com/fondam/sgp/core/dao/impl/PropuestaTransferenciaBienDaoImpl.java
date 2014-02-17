package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.domain.PropuestaTransferenciaBien;
import pe.com.fondam.sgp.core.service.PropuestaTransferenciaBienDao;

@Repository
public class PropuestaTransferenciaBienDaoImpl extends JpaBaseDAOImpl implements
		PropuestaTransferenciaBienDao {

	//************* inyecciones ******************//
	@Autowired
	public PropuestaTransferenciaBienDaoImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//************* metodos ******************//
	@Override
	public void savePropuestaTransferenciaBien(
			PropuestaTransferenciaBien propuestaTransferenciaBien) {
		super.save(propuestaTransferenciaBien);
		
	}

	@Override
	public PropuestaTransferenciaBien updatePropuestaTransferenciaBien(
			PropuestaTransferenciaBien propuestaTransferenciaBien) {
		return super.update(propuestaTransferenciaBien);
	}

	@Override
	public void deletePropuestaTransferenciaBien(
			PropuestaTransferenciaBien propuestaTransferenciaBien) {
		super.delete(propuestaTransferenciaBien);
		
	}

	@Override
	public PropuestaTransferenciaBien findPropuestaTransferenciaBienById(
			Integer propuestaTransferenciaBienId) {
		return super.findById(PropuestaTransferenciaBien.class, propuestaTransferenciaBienId);
	}

	@Override
	public List<PropuestaTransferenciaBien> findPropuestaTransferenciaBienByConsulta(
			String consulta, Object[] params) {
		
		return super.find(consulta, params);
	}

}
