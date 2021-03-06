package pe.com.fondam.sgp.web.controller.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pe.com.fondam.sgp.core.domain.DatoUsuario;
import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;
import pe.com.fondam.sgp.core.domain.Observacion;
import pe.com.fondam.sgp.core.domain.PerfilUsuario;
import pe.com.fondam.sgp.core.domain.Usuario;
import pe.com.fondam.sgp.core.service.ObservacionService;
import pe.com.fondam.sgp.core.service.SeguridadService;
import pe.com.fondam.sgp.web.constants.SgpWebConstants;
import pe.com.fondam.sgp.web.session.UserSession;

@Controller
public class SecurityController {

	//******* inyecciones ******************//
	protected final Log logger = LogFactory.getLog(SecurityController.class);

	@Resource
	SeguridadService seguridadservice;

	@Resource
	ObservacionService observacionService;
	
	//********* metodos  **************//
	
	@RequestMapping(value = "/security/showLogin")
	public ModelAndView showLogin(HttpServletRequest request) {
		removeSessionAttribute(request);
		
		//para que la sesion no se pierda
		request.getSession().setMaxInactiveInterval(-1);
		List<PerfilUsuario> perfilUsuarios = seguridadservice.listAll();
		request.getSession().setAttribute(SgpWebConstants.SESSION_PERFILES, perfilUsuarios);
		return new ModelAndView("login", "perfilUsuarios", perfilUsuarios);
	}
	
	@RequestMapping(value = "/security/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		
		return new ModelAndView("cerrarSesion");
	}

	@RequestMapping(value = "/security/actionAutenticate", method = RequestMethod.POST)
	public ModelAndView actionAutenticate(
			HttpServletRequest request,
			@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "listaPerfilUsuario", required = true) int perfilUsuario) {
		/*logger.info("RequestParam login = " + login);
		logger.info("RequestParam password = " + password);
		logger.info("RequestParam perfil = " + perfilUsuario);
		*/
		Map<String, Object> model = new HashMap<String, Object>();
		Usuario usuario = seguridadservice.autenticateUsuario(login, password, perfilUsuario);
		String redirectPage = null;
		
		if (usuario != null) {
			UserSession userSession = new UserSession();
			DatoUsuario datoSession=new DatoUsuario();
			
			userSession.setUsuarioID(usuario.getUsuarioID());
			userSession.setPassword(usuario.getPassword());
			userSession.setPerfilUsuarioID(usuario.getPerfilUsuario().getPerfilUsuarioID());
			userSession.setDatoUsuarioID(usuario.getDatoUsuario().getDatoUsuarioID());
			userSession.setDatoProyectoID(usuario.getDatoUsuario().getDatoProyectoID());
			userSession.setDescripcionPerfil(usuario.getPerfilUsuario().getDescripcionPerfil());
			userSession.setPrefijoPerfil(usuario.getPerfilUsuario().getPrefijo());

			datoSession.setNombre(usuario.getDatoUsuario().getNombre());
			datoSession.setPaterno(usuario.getDatoUsuario().getPaterno());
			datoSession.setMaterno(usuario.getDatoUsuario().getMaterno());
			request.getSession().setAttribute(SgpWebConstants.SESSION_USER, userSession);
			request.getSession().setAttribute(SgpWebConstants.SESSION_DATO_USER, datoSession);
		
			List<FuncionalidadPerfil> funcionalidadPerfiles = new ArrayList<FuncionalidadPerfil>();
			funcionalidadPerfiles = seguridadservice.menusPorPerfil(userSession.getPerfilUsuarioID());
			request.getSession().setAttribute(SgpWebConstants.SESSION_MENU, funcionalidadPerfiles);//cargando menu en session
			
			if(userSession.getDatoProyectoID()!=null){
				List<Observacion> listObservacion= observacionService.findObservacionByDatoProyectoId(userSession.getDatoProyectoID());
				request.getSession().setAttribute("listObservacion",listObservacion);//lo mismo que el model
				model.put("cantObservaciones", listObservacion.size());
				request.getSession().setAttribute(SgpWebConstants.SESSION_CANT_OBS,listObservacion.size());
				
			}
			//para que la sesion no se pierda
			request.getSession().setMaxInactiveInterval(-1); 
			
			model.put("cantMuestraMensajeObs",0);
			
			redirectPage = "principalMain";
		} else {//el usuario no existe o datos incorrectos
			List<PerfilUsuario> perfilUsuarios = seguridadservice.listAll(); //(List<PerfilUsuario>) request.getSession().getAttribute(SgpWebConstants.SESSION_PERFILES);
			String messageError = "El usuario no existe";
			
			model.put("labelError", "error");
			model.put("messageError", messageError);
			model.put("perfilUsuarios", perfilUsuarios);
			redirectPage = "login";
		}
		return new ModelAndView(redirectPage,model);
	}
	
	public static ModelAndView autenticateErrorRedirect(HttpServletRequest request){
		SecurityController securityController= new SecurityController();
		List<PerfilUsuario> perfilUsuarios = securityController.listPerfiles();
		//String messageError = "El usuario no existe";
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("labelError", "error");
		model.put("messageError",  "El usuario no existe");
		model.put("perfilUsuarios", perfilUsuarios);
		return new ModelAndView("login", model);
	}
	
	public List<PerfilUsuario> listPerfiles(){
		return seguridadservice.listAll();
	}
	
	public void removeSessionAttribute(HttpServletRequest request){
		request.getSession().removeAttribute(SgpWebConstants.SESSION_PERFILES);
		request.getSession().removeAttribute(SgpWebConstants.SESSION_USER);	
		request.getSession().removeAttribute(SgpWebConstants.SESSION_MENU);
		request.getSession().removeAttribute(SgpWebConstants.SESSION_DATO_USER);
		request.getSession().removeAttribute(SgpWebConstants.SESSION_CANT_OBS);
	}

}
