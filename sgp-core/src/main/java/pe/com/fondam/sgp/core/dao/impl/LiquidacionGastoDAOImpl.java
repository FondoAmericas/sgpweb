package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.LiquidacionGastoDAO;
import pe.com.fondam.sgp.core.domain.LiquidacionGasto;
/**
*
*/
@Repository
public class LiquidacionGastoDAOImpl extends JpaBaseDAOImpl implements LiquidacionGastoDAO {

	@Autowired
	public LiquidacionGastoDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	@Override
	public void saveLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		super.save(liquidacionGasto);
		
	}

	@Override
	public LiquidacionGasto updateLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		return super.update(liquidacionGasto);
	}

	@Override
	public void deleteLiquidacionGasto(LiquidacionGasto liquidacionGasto) {
		LiquidacionGasto liquidacionGastoNew=findLiquidacionGastoById(liquidacionGasto.getLiquidacionGastoID());
		super.delete(liquidacionGastoNew);		
	}

	@Override
	public LiquidacionGasto findLiquidacionGastoById(Integer id) {
		return super.findById(LiquidacionGasto.class, id);
	}

	@Override
	public List<LiquidacionGasto> findLiquidacionGastosByFuenteFinanciadora(Integer datoProyectoID,Integer fuenteFinanciadoraID) {
		String queryString = "from LiquidacionGasto where datoProyecto.datoProyectoID= ? and fuenteFinanciadora.fuenteFinanciadoraID=? ";
		Object[] params = new Object[2];
		params[0] = datoProyectoID;
		params[1] = fuenteFinanciadoraID;
		List<LiquidacionGasto> listLiquidacionGasto = super.find(queryString, params);
		
		return listLiquidacionGasto;
	}

	@Override
	public List<LiquidacionGasto> findLiquidacionesGasto(String consulta,Object[] params) {
		return super.find(consulta, params);
	}
	
	
	public int getCantidadLiquidacionGastosByProyectoIdByEstadoLiqGenerada(Integer datoProyectoID) {
		String queryString = "from LiquidacionGasto where datoProyecto.datoProyectoID = ? ";//and fkIdDetalleEstadoCabEstLiqGasto=52 ";//52 estado liq generada
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<LiquidacionGasto> listLiquidacionGasto = super.find(queryString, params);
		
		return listLiquidacionGasto.size();
	}
	
	public List<LiquidacionGasto> findLiquidacionGastosByProyectoIdByEstadoLiqGenerada(Integer datoProyectoID) {
		String queryString = "from LiquidacionGasto where datoProyecto.datoProyectoID = ?  order by liquidacionGastoID asc ";//and fkIdDetalleEstadoCabEstLiqGasto=52 order by liquidacionGastoID asc";//52 estado liq generada
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<LiquidacionGasto> listLiquidacionGasto = super.find(queryString, params);
		
		return listLiquidacionGasto;
	}

}
