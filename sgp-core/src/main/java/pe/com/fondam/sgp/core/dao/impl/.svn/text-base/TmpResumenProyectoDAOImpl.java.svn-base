package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpResumenProyectoDAO;
import pe.com.fondam.sgp.core.domain.TmpResumenProyecto;

@Repository
public class TmpResumenProyectoDAOImpl extends JpaBaseDAOImpl implements TmpResumenProyectoDAO{

	@Autowired
	public TmpResumenProyectoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpResumenProyecto(TmpResumenProyecto tmpResumenProyecto) {
		super.save(tmpResumenProyecto);

	}

	public TmpResumenProyecto updateTmpResumenProyecto(TmpResumenProyecto tmpResumenProyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteTmpResumenProyecto(TmpResumenProyecto tmpResumenProyecto) {
		super.delete(tmpResumenProyecto);

	}


	public TmpResumenProyecto findTmpResumenProyectoById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<TmpResumenProyecto> findTmpResumenProyecto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmpResumenProyecto> findTmpResumenProyectoByTmpDatoProyectoID(Integer tmpDatoProyectoId) {
		String queryString1 = "from TmpResumenProyecto where tmpDatoProyecto.tMPDatoProyectoID= ? ";
		Object[] params1 = new Object[1];
		params1[0] = tmpDatoProyectoId;

		return super.find(queryString1, params1);
	}
}
