package fr.eniecole.dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.xml.internal.ws.server.sei.ValueGetter;

import fr.eniecole.bean.Employe;
import fr.eniecole.bean.Employe_Manager_Enum;
import fr.eniecole.utils.AccesBase;
import fr.eniecole.utils.ManipEnumEmploye;


public class EmployeDAO {
	
	public static void ajouter(Employe employe) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;

		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("insert into Employes(nom, prenom, role, mail,password) values (?,?,?,?,?)");
			rqt.setString(1, employe.getNom());
			rqt.setString(2, employe.getPrenom());
			rqt.setString(3, employe.getRole().toString());
			rqt.setString(4, employe.getEmail());
			rqt.setString(5, employe.getPassword());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	/**
	 * Méthode qui permet de supprimer une formation dans la BD.
	 * @param formation Bean formation à supprimer.
	 * @throws SQLException Exception de type SQL.
	 */
	public static void supprimer(Employe employe) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("delete from Employes where idEmploye = ?");
			rqt.setInt(1, employe.getId());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	/**
	 * Méthode qui permet de modifier une formation dans la BD.
	 * @param formation Bean formation à modifer.
	 * @throws SQLException Exception de type SQL.
	 */
	public static void modifier(Employe employe) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("update Employes set nom=?, prenom=?, role=?, mail=?,password=?");
			rqt.setString(1, employe.getNom());
			rqt.setString(2, employe.getPrenom());
			rqt.setString(3, employe.getRole().toString());
			rqt.setString(4, employe.getEmail());
			rqt.setString(5, employe.getPassword());

			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	/**
	 * Méthode qui permet de rechercher une formation dans la BD.
	 * @param formation Bean formation à rechercher.
	 * @throws SQLException Exception de type SQL.
	 */
	public static Employe rechercher(Employe employe) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("select * from Employes where idEmploye = ?");
			rqt.setInt(1, employe.getId());
			rs=rqt.executeQuery();
			while (rs.next()){
				employe.setNom(rs.getString("nom"));
				employe.setPrenom(rs.getString("prenom"));
				employe.setRole(ManipEnumEmploye.StringToEnum(rs.getString("role")));
				employe.setEmail(rs.getString("mail"));
				employe.setPassword(rs.getString("password"));
				
			}
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return employe;
	}
	
	/**
	 * Retourne la liste des formations présente dans la BD.
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws SQLException Exception de type SQL.
	 */
	public static ArrayList<Employe> lister() throws SQLException{
		Connection cnx=null;
		Statement rqt=null;
		ResultSet rs=null;
		ArrayList<Employe> listeEmployes = new ArrayList<Employe>();
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.createStatement();			
			rs=rqt.executeQuery("select * from Employes where Employes.role='employe'");
			Employe employe;
			while (rs.next()){
				employe = new Employe(
									rs.getInt("idEmploye"),
									rs.getString("nom"),
									rs.getString("prenom"),
									ManipEnumEmploye.StringToEnum(rs.getString("role")),
									rs.getString("mail"),
									rs.getString("password")
						);
				listeEmployes.add(employe);				
			}
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return listeEmployes;
	}
	
	public Employe authenticate(String login, String password) {
		Employe result = null ;
		Connection cnx;
		try {
			cnx = AccesBase.getConnection();			
			PreparedStatement rqt = cnx.prepareStatement("select * from Employes where Employes.mail=? and Employes.password=? ");
			rqt.setString(1, login);
			rqt.setString(2, password);
			ResultSet rs= rqt.executeQuery();
			if (rs.next()) {
				result = itemBuilder(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
					
		return result;
	}
	
	public Employe itemBuilder(ResultSet rs)  {
		Employe result = new Employe();		
		
			try {
				result.setId(rs.getInt("idEmploye"));
				result.setNom(rs.getString("nom"));
				result.setPrenom(rs.getString("prenom"));
				result.setEmail(rs.getString("mail"));
				result.setPassword(rs.getString("password"));
				result.setRole(ManipEnumEmploye.StringToEnum(rs.getString("role")));
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		
		return result;
	}

}
