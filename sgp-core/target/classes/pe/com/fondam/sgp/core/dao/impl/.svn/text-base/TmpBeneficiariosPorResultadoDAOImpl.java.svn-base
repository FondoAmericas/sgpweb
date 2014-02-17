package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpBeneficiariosPorResultadoDAO;
import pe.com.fondam.sgp.core.domain.TmpBeneficiariosPorResultado;
@Repository
public class TmpBeneficiariosPorResultadoDAOImpl extends JpaBaseDAOImpl implements TmpBeneficiariosPorResultadoDAO{

	@Autowired
	public TmpBeneficiariosPorResultadoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpBeneficiariosPorResultado(TmpBeneficiariosPorResultado tmpBeneficiariosPorResultado) {
		super.save(tmpBeneficiariosPorResultado);

	}

	public TmpBeneficiariosPorResultado updateTmpBeneficiariosPorResultado(TmpBeneficiariosPorResultado tmpBeneficiariosPorResultado) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void deleteTmpBeneficiariosPorResultado(TmpBeneficiariosPorResultado tmpBeneficiariosPorResultado) {
		super.delete(tmpBeneficiariosPorResultado);

	}


	public TmpBeneficiariosPorResultado findTmpBeneficiariosPorResultado(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<TmpBeneficiariosPorResultado> findTmpBeneficiariosPorResultado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmpBeneficiariosPorResultado> findTmpBeneficiariosPorResultadoByTmpubicacionproyectoID(Integer tmpUbicacionProyectoID) {
		String queryString1 = "from TmpBeneficiariosPorResultado where tmpubicacionproyecto.tMPUbicacionProyectoID= ? ";
		Object[] params1 = new Object[1];
		params1[0] =tmpUbicacionProyectoID;

		return super.find(queryString1, params1);
	}
}
