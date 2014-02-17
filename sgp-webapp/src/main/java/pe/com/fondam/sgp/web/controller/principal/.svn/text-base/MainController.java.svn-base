package pe.com.fondam.sgp.web.controller.principal;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import pe.com.fondam.sgp.core.service.SeguridadService;

@Controller
public class MainController {

	protected final Log logger = LogFactory.getLog(MainController.class);

	@Resource
	public SeguridadService seguridadservice;

	public SeguridadService getSeguridadservice() {
		return seguridadservice;
	}

	public void setSeguridadservice(SeguridadService seguridadservice) {
		this.seguridadservice = seguridadservice;
	}
	/*
	@RequestMapping(value = "/principal/showPrincipal")
	public ModelAndView showPrincipal(HttpServletRequest request) {
		//logger.info("showPrincipal");
		String messageError = null;
		String redirectPage = null;
		//UserSession userSession = (UserSession) request.getSession().getAttribute(SgpWebConstants.SESSION_USER);
		List<FuncionalidadPerfil> funcionalidadPerfiles = new ArrayList<FuncionalidadPerfil>();
		if(userSession!=null){//si el usuario esta en sesion
			funcionalidadPerfiles = seguridadservice.menusPorPerfil(userSession.getPerfilUsuarioID());
			request.getSession().setAttribute(SgpWebConstants.SESSION_MENU, funcionalidadPerfiles);//cargando menu en session
			if(!funcionalidadPerfiles.isEmpty()){
				logger.info("mostrando funciones perfiles");
				for (FuncionalidadPerfil funcionalidadPerfil : funcionalidadPerfiles) {
					logger.info("titulo = " + funcionalidadPerfil.getFuncionalidad().getTitulo());
					logger.info("url = " + funcionalidadPerfil.getFuncionalidad().getUrl());
				}	
			}
			redirectPage = "principalMain";
		}else{//manda a la pagina de login nuevamente, cargando el combo de perfiles
			logger.info("El usuario no esta loggeado");
			redirectPage = "login";
			messageError = "El usuario no esta loggeado, por favor ingrese su usuario";
			List<PerfilUsuario> perfilusuarios = seguridadservice.listAll();
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("labelError", "error");
			model.put("messageError", messageError);
			model.put("perfilusuarios", perfilusuarios);
			return new ModelAndView(redirectPage, model);
		}
		return new ModelAndView(redirectPage);
		
	}*/
}
