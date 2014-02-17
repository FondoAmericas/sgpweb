package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.FuncionalidadPerfilDAO;
import pe.com.fondam.sgp.core.domain.FuncionalidadPerfil;

@Repository
public class FuncionalidadPerfilDAOImpl extends JpaBaseDAOImpl implements FuncionalidadPerfilDAO{
	

		protected final Log logger = LogFactory.getLog(FuncionalidadPerfilDAOImpl.class);

		@Autowired
		public FuncionalidadPerfilDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
			this.setJpaTemplate(jpaTemplate);
		}

		@Override
		public List<FuncionalidadPerfil> findFuncionalidadPerfilByPerfilUsuarioId(
				Integer perfilUsuarioId) {
			String queryString = "from FuncionalidadPerfil where perfilUsuario.perfilUsuarioID = ? ";
            Object[] params = new Object[1];
            params[0] = perfilUsuarioId;
            return super.find(queryString, params);
		}

		
}
