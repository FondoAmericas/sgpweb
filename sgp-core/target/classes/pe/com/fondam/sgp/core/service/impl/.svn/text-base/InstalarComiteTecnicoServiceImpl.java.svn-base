package pe.com.fondam.sgp.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import pe.com.fondam.sgp.core.dao.DatoUsuarioDAO;
import pe.com.fondam.sgp.core.dao.EvaluadorDAO;
import pe.com.fondam.sgp.core.dao.PerfilUsuarioDAO;
import pe.com.fondam.sgp.core.dao.ProgramaDAO;
import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.Evaluador;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Programa;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.InstalarComiteTecnicoService;
@Service
public class InstalarComiteTecnicoServiceImpl implements InstalarComiteTecnicoService{
	@Resource
	private ProgramaDAO  programaDAO;
	
	@Resource
	private DatoUsuarioDAO  datoUsuarioDAO;
	
	@Resource
	private UsuarioDAO  usuarioDAO;
	
	@Resource
	private EvaluadorDAO  evaluadorDAO;
	
	@Resource
	private PerfilUsuarioDAO  perfilUsuarioDAO;
	


	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public EvaluadorDAO getEvaluadorDAO() {
		return evaluadorDAO;
	}

	public void setEvaluadorDAO(EvaluadorDAO evaluadorDAO) {
		this.evaluadorDAO = evaluadorDAO;
	}

	public ProgramaDAO getProgramaDAO() {
		return programaDAO;
	}

	public void setProgramaDAO(ProgramaDAO programaDAO) {
		this.programaDAO = programaDAO;
	}

	public DatoUsuarioDAO getDatoUsuarioDAO() {
		return datoUsuarioDAO;
	}

	public void setDatoUsuarioDAO(DatoUsuarioDAO datoUsuarioDAO) {
		this.datoUsuarioDAO = datoUsuarioDAO;
	}
	
	public List<DatoUsuario> findDatosUsuario(){
		return datoUsuarioDAO.findDatoUsuarios();
		
	}

	@Override
	public List<Usuario> findUsuarios() {
		
		return usuarioDAO.findUsuarios();
	}

	@Override
	public List<Evaluador> findEvaluador() {
	
		return evaluadorDAO.findEvaluador();
	}

	@Override
	public List<Evaluador> findEvaluadorByProgramaId(Integer id) {
	
		return evaluadorDAO.findEvaluadorByProgramaId(id);
	}
	@Override
	public List<Usuario> findUsuarioByPerfilUsuarioId(Integer id) {
	
		return usuarioDAO.findUsuarioByPerfilUsuarioId(id);
	}
	public List<Usuario> findUsuarioByNombre(String cadena) {
		return usuarioDAO.findUsuarioByNombre(cadena);
	}

	@Override
	public List<Programa> findProgramaByFiltro(Programa programa,
			Integer idFiltro) {
		
		return programaDAO.findProgramaByFiltro(programa, idFiltro);
	}

	@Override
	public Integer saveEvaluador(Evaluador evaluador) {
		Integer valor=0;
		Evaluador evaluador1=evaluadorDAO.findEvaluadorByDatoUsuarioIDAndTipoEvaluacionAndProyectoID(evaluador);

		if (evaluador1==null) {
			evaluadorDAO.saveEvaluador(evaluador);
			//generar el nuevo usuario con el peril de ct 
			Usuario usuario=usuarioDAO.findUsuarioByDatoUsuarioIDbyperfilUsuarioID(evaluador.getDatoUsuario().getDatoUsuarioID());
			if (usuario==null) {
				List<Usuario> listUsuarios= usuarioDAO.findUsuarioByDatoUsuarioIDbyPerfilUsuarioMenosCT(evaluador.getDatoUsuario().getDatoUsuarioID());
				if (listUsuarios!=null) {
					Usuario newUsuario= new Usuario();
					newUsuario.setLogin(listUsuarios.get(0).getLogin());
					newUsuario.setPassword(listUsuarios.get(0).getPassword());
					newUsuario.setDatoUsuario(listUsuarios.get(0).getDatoUsuario());
					PerfilUsuario pUsuario=perfilUsuarioDAO.findPerfilUsuarioById(4);
					newUsuario.setPerfilUsuario(pUsuario);
					usuarioDAO.saveUsuario(newUsuario);
				}
			}
			
			valor=1;
		}
		
		return valor;
	}

	@Override
	public List<Evaluador> findComiteTecnicoByFiltro(Evaluador evaluador,
			Integer idFiltro) {
		
		return evaluadorDAO.findComiteTecnicoByFiltro(evaluador, idFiltro);
	}

	@Override
	public List<Evaluador> findComiteTecnicoByFiltro(Usuario usuario,
			Integer evaluacion, int idfiltro, String dato) {

		return evaluadorDAO.findComiteTecnicoByFiltro(usuario.getDatoUsuario().getDatoUsuarioID(),evaluacion, idfiltro, dato);

	}

	@Override
	public void deleteEvaluador(Integer id) {
		evaluadorDAO.deleteEvaluador(id);
	}

	@Override
	public void DeleteUsuarioDelEvaluadorAsignado(Integer datoUsuarioID,Integer programaID) {
		System.out.println("datoUsuarioID"+datoUsuarioID);
		System.out.println("programaID"+programaID);
	List<Evaluador> listaEvaludor=evaluadorDAO.findEvaluadorByDatoUsuarioIDAndProgramaID(datoUsuarioID, programaID);
	
	if (listaEvaludor.size()==0) {
		Usuario usuario=usuarioDAO.findUsuarioByDatoUsuarioIDbyperfilUsuarioID(datoUsuarioID);
		System.out.println(usuario.getUsuarioID());
		System.out.println(usuario.getDatoUsuario().getDatoUsuarioID());
		System.out.println(usuario.getPerfilUsuario().getPerfilUsuarioID());
		usuarioDAO.deleteUsuario(usuario);
	}

	}
	
	
}
