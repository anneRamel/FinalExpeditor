package fr.eniecole.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import fr.eniecole.bean.Commande;
import fr.eniecole.bean.PdfCommande;
import fr.eniecole.bean.Statut;
import fr.eniecole.dal.CommandeDAO;


/**
 * Servlet implementation class pdfServlet
 */
public class pdfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pdfServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 
		   Commande commandeEnCours = (Commande) request.getSession().getAttribute("commandeEnCours");
	       Document document = new Document();
	       RequestDispatcher rd = null;
	       
	       
	        try { 
	        	
	        	String realPath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/PDF/";
	        	String nomFichier = null;
	        	nomFichier = PdfCommande.createDeliveryNote(commandeEnCours,realPath);    
	        	
	        	String chemin = "/WEB-INF/PDF/" + nomFichier;
	        	request.setAttribute("test", request.getContextPath()+"/WEB-INF/PDF/"+nomFichier);
	        	 
	        	rd = request.getRequestDispatcher(chemin);
	        	
	        
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        rd.forward(request, response);
	        document.close();
	    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
