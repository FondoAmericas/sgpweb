package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.TmpFuenteFinanciadoraDAO;
import pe.com.fondam.sgp.core.domain.TmpFuenteFinanciadora;
import pe.com.fondam.sgp.core.service.TmpFuenteFinanciadoraService;

@Service
public class TmpFuenteFinanciadoraServiceImpl implements
		TmpFuenteFinanciadoraService {
	
	/***************** inyecciones ************************/
	@Resource
	TmpFuenteFinanciadoraDAO tmpFuenteFinanciadoraDAO;

	
	/***************** metodos ************************/
	@Override
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByDatoProyectoIdByCofinanciador(
			Integer datoProyectoID, int cofinanciador) {
		
		String consulta =" from TmpFuenteFinanciadora where tMPDatoProyecto.tMPDatoProyectoID = ? and fkIdtablaespTipoFuenteFinanciadora = ? ";
		Object[] params = new Object[2];
		params[0]=datoProyectoID;
		params[1]=cofinanciador;
		
		return llenatmpFuenteFinanciadoraCompleto( tmpFuenteFinanciadoraDAO.findTmpFuenteFinanciadoraByConsulta(consulta,params));
	}

	@Override
	public List<TmpFuenteFinanciadora> findTmpFuenteFinanciadoraByDatoProyectoIdByContrapartida(
			Integer datoProyectoID, int cofinanciador, int fondam) {

		String consulta =" from TmpFuenteFinanciadora where tMPDatoProyecto.tMPDatoProyectoID = ? and fkIdtablaespTipoFuenteFinanciadora not in (?,?)  ";
		Object[] params = new Object[3];
		params[0]=datoProyectoID;
		params[1]=cofinanciador;
		params[2]=fondam;
		
		return llenatmpFuenteFinanciadoraCompleto(tmpFuenteFinanciadoraDAO.findTmpFuenteFinanciadoraByConsulta(consulta,params));
	}
	
	private List<TmpFuenteFinanciadora> llenatmpFuenteFinanciadoraCompleto(
			List<TmpFuenteFinanciadora> listTmpFuenteFinanciadora) {
		
		for (TmpFuenteFinanciadora tmpFuenteFinanciadora : listTmpFuenteFinanciadora) {
			tmpFuenteFinanciadora.setNombreInstitucion(tmpFuenteFinanciadora.getTMPInstitucion().getNombreInstitucion());
		}
		
		return listTmpFuenteFinanciadora;
	}	

}
