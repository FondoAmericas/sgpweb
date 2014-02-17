package pe.com.fondam.sgp.core.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import pe.com.fondam.sgp.core.commons.bs.JpaBaseDAOImpl;
import pe.com.fondam.sgp.core.dao.UsuarioDAO;
import pe.com.fondam.sgp.core.domain.Usuario;

@Repository
public class UsuarioDAOImpl extends JpaBaseDAOImpl implements UsuarioDAO {

	protected final Log logger = LogFactory.getLog(UsuarioDAOImpl.class);

	@Autowired
	public UsuarioDAOImpl(@Qualifier("jpaTemplate") JpaTemplate jpaTemplate) {
		this.setJpaTemplate(jpaTemplate);
	}

	@Override
	public void saveUsuario(Usuario usuario) {
		this.save(usuario);

	}

	@Override
	public Usuario updateUsuario(Usuario usuario) {
		return super.update(usuario);
	}

	@Override
	public void deleteUsuario(Usuario usuario) {
		Usuario usuarioa= super.findById(Usuario.class, usuario.getUsuarioID());
		super.delete(usuarioa);


	}

	@Override
	public Usuario findUsuarioById(Integer id) {
		return super.findById(Usuario.class, id);
	}
	
	@Override
	public List<Usuario> findUsuarios() {
		return super.find("from Usuario where perfilUsuario.perfilUsuarioID in(1,2,3)");
	}

	public Usuario findUsuarioByLoginAndPasswordAndPerfil(String login,
			String password, int perfil) {

		Usuario usuario = null;

		String queryString = "from Usuario where login = ? and password = ? "
				+ "and perfilUsuario.perfilUsuarioID = ? ";
		Object[] params = new Object[3];
		params[0] = login;
		params[1] = password;
		params[2] = perfil;
		logger.info("en el query");
		logger.info("Valor queryString" + queryString);
		List<Usuario> usuarios = super.find(queryString, params);
		if (!usuarios.isEmpty()) {
			usuario = usuarios.get(0);
		}
		return usuario;
	}

	// public int findUsuarioByNombreAndPasswordAndPerfil(String nombre,String
	// password,int perfil){
	// String q =
	// "SELECT u.Dato_Usuario_ID FROM usuario u "+
	// " where login='"+nombre+"' " +
	// " and AND AES_DECRYPT(PASSWORD,'fondoAmericas')='"+password+"' " +
	// "and Perfil_Usuario_ID='"+perfil+"'";
	//
	// return em.createNativeQuery(q, Usuario.class).getResultList().size();
	// }

	@Override
	public List<Usuario> findUsuarioByPerfilUsuarioId(Integer id) {
		String queryString = "from Usuario where perfilUsuario.perfilUsuarioID = ?";
		Object[] params = new Object[1];
		params[0] = id;
		
		return super.find(queryString, params);
	}
	
	
	@Override
	public List<Usuario> findUsuarioByNombre(String cadena) {
		String queryString = "from Usuario where datoUsuario.nombre like '"+cadena+"%' and perfilUsuario.perfilUsuarioID in (1,2,3)";
		
		return super.find(queryString);
	}

	@Override
	public List<Usuario> findUsuarioByDatoUsuarioIDbyPerfilUsuarioMenosCT(Integer datoUsuarioID) {
		String queryString = "from Usuario where datoUsuario.datoUsuarioID=? and perfilUsuario.perfilUsuarioID !=4 ";
		Object[] params = new Object[1];
		params[0] = datoUsuarioID;
		
		return super.find(queryString, params);
		
	}
	@Override
	public List<Usuario> findUsuarioByDatoUsuarioID(Integer datoUsuarioID) {
		String queryString = "from Usuario where datoUsuario.datoUsuarioID=?";
		Object[] params = new Object[1];
		params[0] = datoUsuarioID;
		
		return super.find(queryString, params);
		
	}

	@Override
	public Usuario findUsuarioByDatoUsuarioIDbyperfilUsuarioID(Integer datoUsuarioID) {
		String queryString = "from Usuario where datoUsuario.datoUsuarioID=? and perfilUsuario.perfilUsuarioID =4 ";
		Object[] params = new Object[1];
		params[0] = datoUsuarioID;
		List<Usuario> listaUsuario=super.find(queryString, params);
		if (listaUsuario.size()!= 0) {
			return listaUsuario.get(0);
		}
		return null;
		
	}

	
}
