package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.TmpFuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.domain.ActividadPerfil;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;

@Repository
public class TmpFuenteFinanciadoraDAOImpl extends JpaBaseDAOImpl implements
		TmpFuenteFinanciadoraDAO {

	//*************** inyecciones ****************//
	@Autowired
	public TmpFuenteFinanciadoraDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//*************** metodos ****************//
	public void saveTmpFuenteFinanciadora(
			TmpFuenteFinanciadora tmpFuenteFinanciadora) {
		super.save(tmpFuenteFinanciadora);

	}

	public ActividadPerfil updateTmpFuenteFinanciadora(
			TmpFuenteFinanciadora tmpFuenteFinanciadora) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteTmpFuenteFinanciadora(TmpFuenteFinanciadora tmpFuenteFinanciadora) {
		super.delete(tmpFuenteFinanciadora);

	}

	public TmpFuenteFinanciadora findTmpFuenteFinanciadoraByTmpIdDatoProyecto(Integer id) {
		String queryString = "from TmpFuenteFinanciadora where tMPDatoProyecto.tMPDatoProyectoID= ? and define_si_es_ejecutor=1 ";
		Object[] params = new Object[1];
		params[0] = id;
		List<TmpFuenteFinanciadora> listFuneteFinanciadora = super.find(
				queryString, params);
		if (listFuneteFinanciadora != null
				&& listFuneteFinanciadora.size() != 0) {
			return listFuneteFinanciadora.get(0);
		}
		return null;

	}
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByProgramaId(
			Integer idPrograma) {
		String queryString = "from TmpFuenteFinanciadora where tMPDatoProyecto.programa.programaID= ? and define_si_es_ejecutor=1 and tMPDatoProyecto.flagPasoTablaNormal=0";
		Object[] params = new Object[1];
		params[0] = idPrograma;
		return super.find(queryString, params);
	}

	@Override
	public TmpFuenteFinanciadora findTmpFuenteFinanciadoraById(Integer idDatoProyecto) {

		return super.findById(TmpFuenteFinanciadora.class, idDatoProyecto);
	}

	@Override
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByTmpIdDatoProyectoAndNoejecutor(
			Integer tmpDatoProyectoId) {
		String queryString = "from TmpFuenteFinanciadora where tMPDatoProyecto.tMPDatoProyectoID= ? and define_si_es_ejecutor=0";
		Object[] params = new Object[1];
		params[0] = tmpDatoProyectoId;
		return super.find(queryString, params);
	}
	@Override
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByTmpIdDatoProyectoList(
			Integer tmpDatoProyectoId) {
		String queryString = "from TmpFuenteFinanciadora where tMPDatoProyecto.tMPDatoProyectoID= ? ";
		Object[] params = new Object[1];
		params[0] = tmpDatoProyectoId;
		return super.find(queryString, params);
	}

	@Override
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

}
