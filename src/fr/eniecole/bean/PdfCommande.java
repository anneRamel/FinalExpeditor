package fr.eniecole.bean;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCommande {

	static Document document ;
	

    public static String createDeliveryNote(Commande commande, String chemin) throws IOException, DocumentException {
        
        String nomFichier = "commande"+commande.getId()+".pdf" ;
        String path = chemin+nomFichier ;
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();
        PdfContentByte canvas = writer.getDirectContent();
        
   
        
        setSenderBlock(canvas);
        setReceiverBlock(commande.getSociete(), canvas);
        setCommandeInfo(commande, canvas);
        setCommandeDetail(commande, canvas);

        document.close();
        return nomFichier ;
    }
    
	/**
     * Méthode fournissant l'affichage de l'expéditeur
     * @param canvas
     */
    public static void setSenderBlock(PdfContentByte canvas){
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("EXPEDITEUR"), 50, 800, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("GAAC"), 50, 780, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("12 rue Léo Lagrange"), 50, 760, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("35000 RENNES"), 50, 740, 0);
    }
    
    /**
     * Permet d'ajouter le bloc destinataire au PDF, crée une nouvelle ligne pour chaque String de la liste en paramètres/
     * @param data
     * @param canvas
     */
    public static void setReceiverBlock(Societe societe, PdfContentByte canvas){
    	String [] adresseSplit = societe.getAdresse().split("-");
    	String rue = adresseSplit[0];
    	String ville = adresseSplit[1];
    	int i = 800 ;
    	int x = 420 ;
    	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("DESTINATAIRE"), x, i, 0);
		i = i-20;
	
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(societe.getNom()), x, i, 0);
		i = i-20;
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(rue), x, i, 0);
		i = i-20;
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(ville), x, i, 0);
		i = i-20;
		
    }
    
    /**
     * Ajoute la ligne n° de commande
     * @param numFacture
     * @param canvas
     */
    private static void setCommandeInfo(Commande commande, PdfContentByte canvas) {
    	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Commande n° "+commande.getId()), 50, 670, 0);	
	}
    


	/**
     * Permet d'afficher la liste des articles dans le tableau
     * @param data
     * @param canvas
     */
    public static void setCommandeDetail(Commande commande, PdfContentByte canvas){
    	int i = 630 ;
    	float poidsTotal = 0;
    	//En tete
    	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("LIBELLE"), 50, i, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("QUANTITE"), 150, i, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("POIDS(grammes)"), 350, i, 0);
        
        i=i-20;
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("__________________________________________________________________________"), 50, i, 0);
        //Génération a partir du tableau : 
        i=i-30;
        for (Map.Entry<Article, Integer> entry : commande.getArticlesCommandes().entrySet()) {
        	float poidsArticle = entry.getKey().getPoids()*entry.getValue();
        	poidsTotal += poidsArticle;
        	ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(entry.getKey().getLibelle()), 50, i, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(entry.getValue().toString()), 150, i, 0);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(String.valueOf(poidsArticle)), 350, i, 0);
            i = i-20 ;
		}
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("__________________________________________________________________________"), 50, i, 0);
        i = i-40;
        float weightKg = poidsTotal/1000 ;
        //float weightG = weightKg%1000;
        String display = String.valueOf(weightKg) + " Kg";
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("POIDS COLIS : "+ display), 420, 670, 0);
        
        
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Signature du client "), 420, i, 0);
        ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase("Signature du transporteur "), 50, i, 0);
        i=i-30;
    }
        
    
    
    /**
     * Méthode en charger de transformer l'id de la commande 
     * en cnuméro de commande suffisamment long pour le code barre
     * 
     * @param commande
     * @return
     */
    private static String sixDigitsOrderNumber(Commande commande) {
		String result = "1" ;
    	int zeroToAdd = 0 ;
		int id = commande.getId();
		
		if (id < 10) 
			zeroToAdd = 4 ;
		else if (id < 100) 
			zeroToAdd = 3 ;
		else if (id < 1000) 
			zeroToAdd = 2 ;
		else if (id < 10000) 
			zeroToAdd = 1 ;
		else if (id < 10000) 
			zeroToAdd = 0 ;
		else
			result = String.valueOf(id);
	
		if(zeroToAdd > 0){
			for (int i = 0 ;  i < zeroToAdd; i++){
				result += "0";
			}
		}
		
		return result;
		

	}

}
