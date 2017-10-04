package fr.eniecole.dal;
import fr.eniecole.bean.Societe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SocieteDAO {

	Logger logger = Logger.getLogger(this.getClass().getName());
	private final static String COL_ADRESSE = "adresse";
	private final static String COL_NOM_SOCIETE = "nomSociete";
	private final static String COL_IDSOCIETE = "idSociete";
	
	
	/**
	 * 
	 */
	public Societe itemBuilder(ResultSet rs) throws SQLException {
		Societe societe = new Societe();
		societe.setId(rs.getInt(COL_IDSOCIETE));
		societe.setAdresse(rs.getString(COL_ADRESSE));
		societe.setNom(rs.getString(COL_NOM_SOCIETE));
		return societe;
	}
}
