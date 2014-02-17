package pe.com.fondam.sgp.core.dao;
import java.util.List;

import pe.com.fondam.sgp.core.domain.Usuario;


public interface UsuarioDAO {
	void saveUsuario(Usuario usuario);
	
	Usuario updateUsuario(Usuario usuario);
    
	void deleteUsuario(Usuario usuario);
    
    Usuario findUsuarioById(Integer id);
    
    Usuario findUsuarioByLoginAndPasswordAndPerfil(String login, String password, int perfil);
	
    List<Usuario> findUsuarios();
	
	List<Usuario> findUsuarioByPerfilUsuarioId(Integer id);
	
	List<Usuario> findUsuarioByNombre(String cadena);

	Usuario findUsuarioByDatoUsuarioIDbyperfilUsuarioID(Integer datoUsuarioID);

	List<Usuario> findUsuarioByDatoUsuarioID(Integer datoUsuarioID);

	List<Usuario> findUsuarioByDatoUsuarioIDbyPerfilUsuarioMenosCT(
			Integer datoUsuarioID);
}
