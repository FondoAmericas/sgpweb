package pe.com.fondam.sgp.web.constants;

public class SgpWebConstants {

	public final static String SESSION_PERFILES = "PERFILES";
	public final static String SESSION_USER = "USER_SESSION";
	public final static String SESSION_DATO_USER = "DATO_USER_SESSION";
	public final static String SESSION_MENU = "FUNCIONALIDAD_PERFIL";
	public static final String estadoDatoProyecto = null;	
	public final static String APROBADO_DATO_PROYECTO = "apro";
	public final static String RECHAZAR_DATO_PROYECTO = "recha";
	public final static String SESSION_CANT_OBS = "cantObservaciones";
	
	public class evaluar {
		public final static int INSTITUCION = 173;
		public final static int PERFIL =174;
		public final static int PROGRAMA =175;//proyecto
	}
	
	public class evaluarEstadoInstitucion {
		public final static int APROBADO = 2;
		public final static int RECHAZADO =3;

	}
	
	public class evaluarEstadoPerfil {
		public final static int APROBADO = 5;
		public final static int RECHAZADO =6;

	}
	
	public class evaluarEstadoProyecto {
		public final static int POR_EVALUAR = 12;
		public final static int APROBADO = 13;
		public final static int RECHAZADO =14;

	}

	public class tipoFuenteFinanciadora {
		public final static int ejecutor = 183;
		public final static int fondam = 184;
		public final static int cofinanciador = 185;
		public final static int beneficiarios =186;
		public final static int otros =188;

	}

	
}
