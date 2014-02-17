package pe.com.fondam.sgp.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.bean.DirectivaBeneficiarioBean;
import pe.com.fondam.sgp.core.dao.DirectivaBeneficiarioDAO;
import pe.com.fondam.sgp.core.domain.DirectivaBeneficiario;
import pe.com.fondam.sgp.core.service.DirectivaBeneficiarioService;
import pe.com.fondam.sgp.core.service.TablaEspecificaService;

@Service
public class DirectivaBeneficiarioServiceImpl implements
		DirectivaBeneficiarioService {

	//*********** inyecciones **************//
	@Resource
	private DirectivaBeneficiarioDAO directivaBeneficiarioDAO;

	@Resource
	TablaEspecificaService tablaEspecificaService;
	//*********** metodos **************//
	@Override
	public List<DirectivaBeneficiario> findDirectivaBeneficiarioByPropuestaTransferenciaId(Integer propuestaTransferenciaId) {
		
		String consulta=" from DirectivaBeneficiario where propuestaTransferencia.propuestaTransferenciaID = ? ";
		Object[] params = new Object[1];
		params[0]=propuestaTransferenciaId;
		return directivaBeneficiarioDAO.findDirectivaBeneficiarioByConsulta(consulta,params);
	}


	@Override
	public DirectivaBeneficiario updateDirectivaBeneficiario(
			DirectivaBeneficiario directivaBeneficiario) {
		return directivaBeneficiarioDAO.updateDirectivaBeneficiario(directivaBeneficiario);
	}


	@Override
	public DirectivaBeneficiario findDirectivaBeneficiarioById(
			Integer idRegistro) {
		return directivaBeneficiarioDAO.findDirectivaBeneficiarioById(idRegistro);
	}


	@Override
	public void deleteDirectivaBeneficiario(
			Integer directivaBeneficiarioId) {
		//DirectivaBeneficiario directivaBeneficiario= findDirectivaBeneficiarioById(directivaBeneficiarioId);
		directivaBeneficiarioDAO.deleteDirectivaBeneficiario(findDirectivaBeneficiarioById(directivaBeneficiarioId));//directivaBeneficiario);
		
	}

	//******************* listas  ******************//
	@Override
	public List<DirectivaBeneficiario> llenaListDirectivaBeneficiario(
			List<DirectivaBeneficiario> listDirectivaBeneficiario) {

		for (DirectivaBeneficiario directivaBeneficiario : listDirectivaBeneficiario) {
			directivaBeneficiario.setDescripcionCargo(tablaEspecificaService
					.findTablaEspecificaById(
							directivaBeneficiario.getFkIdtablaespCargo())
					.getDescripcionCabecera());
			directivaBeneficiario
					.setDescripcionTipoDocumento(tablaEspecificaService
							.findTablaEspecificaById(
									directivaBeneficiario
											.getFkIdtablaespTipoDocumento())
							.getDescripcionCabecera());
		}
		return listDirectivaBeneficiario;
	}


	@Override
	public List<DirectivaBeneficiarioBean> llenaListDirectivaBeneficiarioBean(
			List<DirectivaBeneficiario> listDirectivaBeneficiario) {

		List<DirectivaBeneficiarioBean> listDirectivaBeneficiarioBean= new ArrayList<DirectivaBeneficiarioBean>();
		for (DirectivaBeneficiario directivaBeneficiario : listDirectivaBeneficiario) {
			listDirectivaBeneficiarioBean.add(llenaDirectivaBeneficiarioBean(directivaBeneficiario));
		}
		return listDirectivaBeneficiarioBean;
	}


	private DirectivaBeneficiarioBean llenaDirectivaBeneficiarioBean(
			DirectivaBeneficiario directivaBeneficiario) {
		DirectivaBeneficiarioBean directivaBeneficiarioBean= new DirectivaBeneficiarioBean();
		
		directivaBeneficiarioBean.setAcreditacion(directivaBeneficiario.getAcreditacion());
		directivaBeneficiarioBean.setDescripcionCargo(directivaBeneficiario.getDescripcionCargo());
		directivaBeneficiarioBean.setDescripcionTipoDocumento(directivaBeneficiario.getDescripcionTipoDocumento());
		directivaBeneficiarioBean.setDirectivaBeneficiarioID(directivaBeneficiario.getDirectivaBeneficiarioID());
		directivaBeneficiarioBean.setFkIdtablaespCargo(directivaBeneficiario.getFkIdtablaespCargo());
		directivaBeneficiarioBean.setFkIdtablaespTipoDocumento(directivaBeneficiario.getFkIdtablaespTipoDocumento());
		directivaBeneficiarioBean.setNombreCompleto(directivaBeneficiario.getNombreCompleto());
		directivaBeneficiarioBean.setNumeroDocumento(directivaBeneficiario.getNumeroDocumento());
		directivaBeneficiarioBean.setPropuestaTransferencia(directivaBeneficiario.getPropuestaTransferencia());
		directivaBeneficiarioBean.setVigenciaPoder(directivaBeneficiario.getVigenciaPoder());
		return directivaBeneficiarioBean;
	}
}
