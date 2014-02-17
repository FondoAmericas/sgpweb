package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.DetallePagoLiquidacionDAO;
import pe.com.fondam.sgp.core.domain.DetallePagoLiquidacion;
/**
*
* 
*/
@Repository
public class DetallePagoLiquidacionDAOImpl extends JpaBaseDAOImpl implements DetallePagoLiquidacionDAO {

	@Autowired
	public DetallePagoLiquidacionDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveDetallePagoLiquidacion(
			DetallePagoLiquidacion detallePagoLiquidacion) {
		super.save(detallePagoLiquidacion);
		
	}

	@Override
	public DetallePagoLiquidacion updateDetallePagoLiquidacion(
			DetallePagoLiquidacion detallePagoLiquidacion) {
		
		return super.update(detallePagoLiquidacion);
	}

	@Override
	public void deleteDetallePagoLiquidacion(
			DetallePagoLiquidacion detallePagoLiquidacion) {
		DetallePagoLiquidacion	detallePagoLiquidacionNew=findDetallePagoLiquidacionById(detallePagoLiquidacion.getDetallePagoLiquidacionID());
		super.delete(detallePagoLiquidacionNew);
	}

	@Override
	public DetallePagoLiquidacion findDetallePagoLiquidacionById(Integer id) {
		
		 return super.findById(DetallePagoLiquidacion.class, id);
	}

	@Override
	public List<DetallePagoLiquidacion> findDetallePagoLiquidacionES() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DetallePagoLiquidacion> findDetallePagoLiquidacion(
			String queryString, Object[] params) {
		return super.find(queryString, params);
	}
	
	public List<DetallePagoLiquidacion> findDetallePagoLiquidacionByPagoLiquidacionID(Integer pagoLiquidacionID) {
		String queryString = "from DetallePagoLiquidacion where pagoLiquidacion.pagoLiquidacionID = ? order by detallePagoLiquidacionID asc";
		Object[] params = new Object[1];
		params[0] = pagoLiquidacionID;
		return super.find(queryString, params);
	}

}
