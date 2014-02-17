package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.PagoLiquidacionDAO;
import pe.com.fondam.sgp.core.domain.PagoLiquidacion;


@Repository
public class PagoLiquidacionDAOImpl extends JpaBaseDAOImpl implements PagoLiquidacionDAO {

	//****************** inyecciones *************************//
	@Autowired
	public PagoLiquidacionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	
	//****************** metodos *************************//
	@Override
	public PagoLiquidacion updatePagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
		
		return super.update(pagoLiquidacion);
	}

	@Override
	public void deletePagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
		PagoLiquidacion pagoLiquidacionNew=findPagoLiquidacionById(pagoLiquidacion.getPagoLiquidacionID());
		super.delete(pagoLiquidacionNew);		
	}

	@Override
	public PagoLiquidacion findPagoLiquidacionById(Integer id) {
		return super.findById(PagoLiquidacion.class, id);
	}


	@Override
	public void savePagoLiquidacion(PagoLiquidacion pagoLiquidacion) {
		super.save(pagoLiquidacion);
	}

	@Override
	public List<PagoLiquidacion> findPagoLiquidacione(String queryString,Object[] params) {	
		return super.find(queryString, params);
	}
	
	public List<PagoLiquidacion> findPagoLiquidacionByLiquidacionGastoID(Integer liquidacionGastoID) {
		String queryString = "from PagoLiquidacion where liquidacionGasto.liquidacionGastoID = ? order by pagoLiquidacionID asc";
		Object[] params = new Object[1];
		params[0] = liquidacionGastoID;
		return super.find(queryString, params);
	}

}
