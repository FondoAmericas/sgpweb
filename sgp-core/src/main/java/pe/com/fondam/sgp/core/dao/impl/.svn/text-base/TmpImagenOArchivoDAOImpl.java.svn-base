package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpImagenOArchivoDAO;
import pe.com.fondam.sgp.core.domain.TmpImagenOArchivo;

@Repository
public class TmpImagenOArchivoDAOImpl extends JpaBaseDAOImpl implements TmpImagenOArchivoDAO{

	@Autowired
	public TmpImagenOArchivoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	public void saveTmpImagenOArchivo(TmpImagenOArchivo tmpImagenOArchivo) {
		super.save(tmpImagenOArchivo);

	}

	public TmpImagenOArchivo updateTmpImagenOArchivo(TmpImagenOArchivo tmpImagenOArchivo) {
		// TODO Auto-generated method stub
		return null;
	}


	public TmpImagenOArchivo findTmpImagenOArchivoById(Integer id) {
		return super.findById(TmpImagenOArchivo.class, id);
	}

	
	public List<TmpImagenOArchivo> findTmpImagenOArchivo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TmpImagenOArchivo> findTmpImagenOArchivoByPerfilByID(Integer tmpPerfilID) {
		String queryString = "from TmpImagenOArchivo where tmpPerfil.tMPPerfilID= ?";
		Object[] params = new Object[1];
		params[0] = tmpPerfilID;

		return super.find(queryString,params);

	}

	@Override
	public void deleteTmpImagenOArchivo(TmpImagenOArchivo tmpImagenOArchivo) {
		super.delete(tmpImagenOArchivo);
		
	}
}
