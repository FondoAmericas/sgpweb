package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.RaLgDAO;
import pe.com.fondam.sgp.core.domain.RaLg;
/**
*
* @author Zolanch Távara
*/
@Repository
public class RaLgDAOImpl extends JpaBaseDAOImpl implements RaLgDAO {

	@Autowired
	public RaLgDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}
	
	@Override
	public void saveRaLg(RaLg raLg) {
		super.save(raLg);
		
	}

	@Override
	public RaLg updateRaLg(RaLg raLg) {
		// TODO Auto-generated method stub
		return super.update(raLg);
	}

	@Override
	public void deleteRaLg(RaLg raLg) {
		RaLg raLgNew=findRaLgById(raLg.getRaLgId());
		super.delete(raLgNew);		
	}

	@Override
	public RaLg findRaLgById(Integer id) {
		return super.findById(RaLg.class, id);
	}

	@Override
	public List<RaLg> findRaLgByDatoProyectoID(Integer datoProyectoID) {
		String queryString = "from RaLg  where  liquidacionGasto.datoProyecto.datoProyectoID= ? ";
		Object[] params = new Object[1];
		params[0] = datoProyectoID;
		List<RaLg> listRaLg = super.find(queryString, params);

		return listRaLg;
	}

	@Override
	public RaLg findRaLgByLiquidcionGastoID(int liquidacionGastoID) {
		String queryString = "from RaLg  where  liquidacionGasto.liquidacionGastoID= ? ";
		Object[] params = new Object[1];
		params[0] = liquidacionGastoID;
		List<RaLg> listRaLg = super.find(queryString, params);

		return listRaLg.get(0);
	}

}
