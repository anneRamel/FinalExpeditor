package fr.eniecole.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eniecole.bean.Article;
import fr.eniecole.bean.Employe;
import fr.eniecole.dal.ArticleDAO;
import fr.eniecole.dal.EmployeDAO;

/**
 * Servlet implementation class ArticleServlet
 */
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Employe emp = new Employe();
		emp.setId(5);
		EmployeDAO edao = new EmployeDAO();
		try {
			emp= edao.rechercher(emp);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("employe", emp);
		
		List<Article> listedArticles = new ArrayList<Article>();
		ArticleDAO adao = new ArticleDAO();
		listedArticles = adao.getListeArticles();
		request.setAttribute("listeArticles", listedArticles);
		
		
		request.getRequestDispatcher("/WEB-INF/jsp/manager/GestionArticle.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("supprimer")!=null)
		{
			supprimerArticle(request,response);
		}
		else if(request.getParameter("modifier")!=null)
		{
			modifierArticle(request,response);
		}
		else if(request.getParameter("ajouter")!=null)
		{
			ajouterArticle(request,response);
		}
	}

	private void ajouterArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			String lib = request.getParameter("libelle");
			float pds = Float.parseFloat(request.getParameter("poids"));
			int qte = Integer.parseInt(request.getParameter("quantite"));
			
			Article art = new Article(lib, pds, qte);
			
			ArticleDAO adao = new ArticleDAO();
			try{
			adao.addArticle(art);
			request.setAttribute("messageAjout", "L'article est bien enregistrer");
			}
			catch (Exception e){
				e.printStackTrace();
				request.setAttribute("messageAjout", "Erreur lors de l'ajout");		
			}
		}
		catch(Exception e){
			e.printStackTrace();
			request.setAttribute("messageAjout", "Merci de remplir les champs");	
		}
		doGet(request, response);
	}

	private void modifierArticle(HttpServletRequest request, HttpServletResponse response) {

		
	}

	private void supprimerArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Article art = new Article(id);
			
			ArticleDAO adao = new ArticleDAO();
			try{
			adao.deleteArticle(art);
			request.setAttribute("messageAjout", "La suppression a été effectuée");
			}
			catch (Exception e){
				e.printStackTrace();
				request.setAttribute("messageAjout", "Erreur lors de la suppression");		
			}
		}
		catch (Exception e){
			
			e.printStackTrace();
			request.setAttribute("messageAjout", "L'id n'a pas été identifié");	
			
		}
		
		doGet(request, response);
	}

}
