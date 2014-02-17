package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpUbicacionProyectoDAO;
import pe.com.fondam.sgp.core.domain.TmpUbicacionProyecto;

@Repository
public class TmpUbicacionProyectoDAOImpl extends JpaBaseDAOImpl implements TmpUbicacionProyectoDAO{

	@Autowired
	public TmpUbicacionProyectoDAOImpl (
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpUbicacionProyecto(TmpUbicacionProyecto tmpUbicacionProyecto) {
		super.save(tmpUbicacionProyecto);

	}

	public TmpUbicacionProyecto updateTmpUbicacionProyecto(TmpUbicacionProyecto tmpUbicacionProyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteTmpUbicacionProyecto(TmpUbicacionProyecto tmpUbicacionProyecto) {
		super.delete(tmpUbicacionProyecto);

	}


	public TmpUbicacionProyecto findTmpUbicacionProyectoById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<TmpUbicacionProyecto> findTmpUbicacionProyecto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmpUbicacionProyecto> findTmpUbicacionProyectoBytmpDatoProyectoID(Integer tmpDatoProyectoId) {
		String queryString = "from TmpUbicacionProyecto where tMPDatoProyecto.tMPDatoProyectoID= ?";
		Object[] params = new Object[1];
		params[0] = tmpDatoProyectoId;

		return super.find(queryString,params);
	}
}
