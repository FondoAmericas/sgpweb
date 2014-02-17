package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.UbicacionProyectoDAO;
import pe.com.fondam.sgp.core.domain.UbicacionProyecto;
/**
*
*/
@Repository
public class UbicacionProyectoDAOImpl extends JpaBaseDAOImpl  implements UbicacionProyectoDAO {

	@Autowired
	public UbicacionProyectoDAOImpl (@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	@Override
	public void saveUbicacionProyecto(UbicacionProyecto ubicacionProyecto) {
		super.save(ubicacionProyecto);
		
	}

	@Override
	public UbicacionProyecto updateUbicacionProyecto(
			UbicacionProyecto ubicacionProyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUbicacionProyectoo(UbicacionProyecto ubicacionProyecto) {
		super.delete(ubicacionProyecto);
		
	}

	@Override
	public UbicacionProyecto findUbicacionProyectoById(Integer id) {
		return super.findById(UbicacionProyecto.class, id);
	}

	@Override
	public List<UbicacionProyecto> findUbicacionProyecto() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<UbicacionProyecto> findUbicacionProyectoByIdProyecto(Integer datoProyectoId) {
		
		String queryString = "from UbicacionProyecto where datoProyecto.datoProyectoID=? ";
		Object[] params = new Object[1];
		params[0] = datoProyectoId;

		return super.find(queryString, params);

	}
	@Override
	public List<UbicacionProyecto> findUbicacionProyectoXConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
