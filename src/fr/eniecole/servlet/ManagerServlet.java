package fr.eniecole.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eniecole.bean.Employe;
import fr.eniecole.bean.Employe_Manager_Enum;
import fr.eniecole.dal.EmployeDAO;
import fr.eniecole.utils.ManipEnumEmploye;

/**
 * Servlet implementation class ManagerServlet
 */
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Employe> listeEmploye = new ArrayList<>();
		
		try {
			Employe employe = new Employe();
			employe.setNom(request.getParameter("nomAremplir"));
			employe.setPrenom(request.getParameter("prenomAremplir"));
			employe.setEmail(request.getParameter("emailAremplir"));
			employe.setPassword(request.getParameter("pwdAremplir"));
			employe.setRole(ManipEnumEmploye.StringToEnum("employe"));
			String error=null;
			if (employe.getNom() != null|| employe.getPrenom()!= null || employe.getEmail()!= null || employe.getPassword()!= null ) {
				EmployeDAO.ajouter(employe);
				
			} else {error="Veuillez remplir tous les champs";}
			listeEmploye = EmployeDAO.lister();
			request.setAttribute("listeEmploye", listeEmploye);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/manager.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(listeEmploye);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(true);
		//Appel de la Service pour checker l'identification :

		Employe user = null;
		Employe personne = null;
	
			try {
				user = new EmployeDAO().authenticate(login, password);
				//personne = new EmployeDAO().rechercher(user);
				//System.out.println(personne.getId());
				if (user != null && user.getRole().equals(Employe_Manager_Enum.manager)) {
					
					session.setAttribute("user", user);			
					
					ArrayList<Employe> listeEmploye = new ArrayList<>();
					
						listeEmploye = EmployeDAO.lister();
						System.out.println(listeEmploye);
						
			
					request.setAttribute("listeEmploye", listeEmploye);
					request.getRequestDispatcher("/WEB-INF/jsp/manager/manager.jsp").forward(request, response);
					
					
				}else{
					request.setAttribute("error", "Login et/ou mot de passe incorrect(s)");
					request.getRequestDispatcher("/WEB-INF/jsp/manager/AuthManager.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				request.getRequestDispatcher("/WEB-INF/jsp/manager/AuthManager.jsp").forward(request, response);
			} 
	
		
	}

}
