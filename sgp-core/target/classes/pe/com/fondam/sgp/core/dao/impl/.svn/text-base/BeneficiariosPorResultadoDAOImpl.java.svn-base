package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.BeneficiariosPorResultadoDAO;
import pe.com.fondam.sgp.core.domain.BeneficiariosPorResultado;

@Repository
public class BeneficiariosPorResultadoDAOImpl extends JpaBaseDAOImpl implements
		BeneficiariosPorResultadoDAO {

	//*****************  inyecciones  **************//
	@Autowired
	public BeneficiariosPorResultadoDAOImpl(
			@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	//*****************  metodos **************//
	@Override
	public void saveBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado) {
		super.save(beneficiariosPorResultado);
	}

	@Override
	public BeneficiariosPorResultado updateBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado) {
		return super.update(beneficiariosPorResultado);
	}

	@Override
	public void deleteBeneficiariosPorResultado(
			BeneficiariosPorResultado beneficiariosPorResultado) {
		super.delete(beneficiariosPorResultado);
	}

	@Override
	public BeneficiariosPorResultado findBeneficiariosPorResultadoById(
			Integer id) {
		return super.findById(BeneficiariosPorResultado.class, id);
	}
	
	@Override
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByResultadoID(
			Integer id) {

		String queryString = "from BeneficiariosPorResultado where resultado.resultadoID = ?";
		Object[] params = new Object[1];
		params[0] = id;
		return super.find(queryString, params);
	}

	@Override
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultados() {
		return super.find("from BeneficiariosPorResultado");
	}
	
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdPerfil(Integer idPerfil) {

		String queryString = "from BeneficiariosPorResultado where perfil.perfilID = ?";
		Object[] params = new Object[1];
		params[0] = idPerfil;
		return super.find(queryString, params);
	}
	
	
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoByIdUbicacionProyecto(Integer idProyecto){
		String queryString = "from BeneficiariosPorResultado where ubicacionProyecto.ubicacionProyectoID = ?";
		Object[] params = new Object[1];
		params[0] = idProyecto;
		//return super.find(queryString, params);
		
		List<BeneficiariosPorResultado> lst = super.find(queryString, params);
		if(lst.size()>0 && lst!=null){
			return lst;
		}else{
			return null;
		}
		
	}

	@Override
	public BeneficiariosPorResultado findBeneficiariosPorResultadoByResultadoIDAndTipoBeneficiarioIDAndEstratoID(
			Integer resultadoID, Integer tipoBeneficiarioID, Integer estratoID) {

		String queryString = "from BeneficiariosPorResultado where resultado.resultadoID = ? and fkIdtablaespTipoBeneficiario = ? and fkidtablaespEstrato = ? ";
		
		Object[] params = new Object[3];
		params[0] = resultadoID;
		params[1] = tipoBeneficiarioID;
		params[2] = estratoID;
		
		BeneficiariosPorResultado beneficiariosPorResultado = null;
		
		List<BeneficiariosPorResultado> listBeneficiariosPorResultado = super.find(queryString, params);
		if(!listBeneficiariosPorResultado.isEmpty()){
			beneficiariosPorResultado = listBeneficiariosPorResultado.get(0); 
		}
		
		return beneficiariosPorResultado;
	}

	@Override
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoXConsulta(
			String consulta, Object[] params) {
		return super.find(consulta, params);
	}

	@Override
	public List<BeneficiariosPorResultado> findBeneficiariosPorResultadoXConsultaSinParam(
			String consulta) {
		return super.find(consulta);
	}

}
