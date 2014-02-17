package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DepProvDistDAO;
import pe.com.fondam.sgp.core.domain.DepProvDist;
import pe.com.fondam.sgp.core.service.DepProvDistService;

@Service
public class DepProvDistServiceImpl implements DepProvDistService {

	/**************** Inyecciones *********************/
	@Resource
	DepProvDistDAO depProvDistDAO;

	/**************** metodos *********************/
	@Override
	public DepProvDist findListDepProvDistById(Integer depProvDistID) {
		return depProvDistDAO.findDepProvDistById(depProvDistID);
	}

	@Override
	public DepProvDist findListDepartamentos(String departamentoId) {
		String consulta = "from DepProvDist where idDepartamento = ? and idProvincia = 0 and idDistrito = 0 ";

		Object[] params = new Object[1];
		params[0] = departamentoId;

		List<DepProvDist> listDepProvDist = depProvDistDAO
				.findDepProvDistXConsulta(consulta, params);

		DepProvDist depProvDist = new DepProvDist();
		if (listDepProvDist.size() > 0) {
			depProvDist = listDepProvDist.get(0);
		}
		return depProvDist;
	}

	@Override
	public DepProvDist findListProvincia(String departamentoId,
			String provinciaId) {
		String consulta = "from DepProvDist where idDepartamento = ? and idProvincia = ? and idDistrito = 0 ";

		Object[] params = new Object[2];
		params[0] = departamentoId;
		params[1] = provinciaId;

		List<DepProvDist> listDepProvDist = depProvDistDAO
				.findDepProvDistXConsulta(consulta, params);

		DepProvDist depProvDist = new DepProvDist();
		if (listDepProvDist.size() > 0) {
			depProvDist = listDepProvDist.get(0);
		}
		return depProvDist;
	}

	@Override
	public DepProvDist findListDistrito(String departamentoId,
			String provinciaId, String distritoId) {
		String consulta = "from DepProvDist where idDepartamento = ? and idProvincia = ? and idDistrito = ? ";

		Object[] params = new Object[3];
		params[0] = departamentoId;
		params[1] = provinciaId;
		params[2] = distritoId;

		List<DepProvDist> listDepProvDist = depProvDistDAO
				.findDepProvDistXConsulta(consulta, params);

		DepProvDist depProvDist = new DepProvDist();
		if (listDepProvDist.size() > 0) {
			depProvDist = listDepProvDist.get(0);
		}
		return depProvDist;
	}

	@Override
	public String findDescripcionDepProvDist(String descripcion,
			DepProvDist depProvDist) {

		String consulta = null;
		Object[] params = null;

		if (descripcion.equals("depa")) {
			consulta = "from DepProvDist where idDepartamento = ? and idProvincia = 0 and idDistrito = 0 ";

			params = new Object[1];
			params[0] = depProvDist.getIdDepartamento();
		} else if (descripcion.equals("prov")) {
			consulta = "from DepProvDist where idDepartamento = ? and idProvincia = ? and idDistrito = 0 ";

			params = new Object[2];
			params[0] = depProvDist.getIdDepartamento();
			params[1] = depProvDist.getIdProvincia();
		} else if (descripcion.equals("dist")) {
			consulta = "from DepProvDist where idDepartamento = ? and idProvincia = ? and idDistrito = ? ";

			params = new Object[3];
			params[0] = depProvDist.getIdDepartamento();
			params[1] = depProvDist.getIdProvincia();
			params[2] = depProvDist.getIdDistrito();
		}

		DepProvDist depProvDistDescrip = depProvDistDAO
				.findDepProvDistXConsulta(consulta, params).get(0);
		return depProvDistDescrip.getDescripcion();
	}

	@Override
	public List<DepProvDist> findListProvinciaByDepartamentoId(
			String departamentoId) {
		String consulta = "from DepProvDist where idDepartamento = ? and idProvincia <> 0 and idDistrito = 0 ";

		Object[] params = new Object[1];
		params[0] = departamentoId;

		return depProvDistDAO
				.findDepProvDistXConsulta(consulta, params);
	}

	@Override
	public List<DepProvDist> findListProvinciaByDepartamentoIdBuProvinciaId(
			String departamentoId, String provinciaId) {
		String consulta = "from DepProvDist where idDepartamento = ? and idProvincia = ? and idDistrito <> 0 ";

		Object[] params = new Object[2];
		params[0] = departamentoId;
		params[1] = provinciaId;

		return depProvDistDAO
				.findDepProvDistXConsulta(consulta, params);
	}

}
