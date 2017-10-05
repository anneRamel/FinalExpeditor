package fr.eniecole.bean;

public enum Statut {

	A_TRAITER,
	EN_COURS_DE_TRAITEMENT,
	TRAITEE;
	
	
	@Override
	public String toString() {
		String retour = "";
		switch (this) {
		case A_TRAITER:
			retour = "A traiter";
			break;
		case EN_COURS_DE_TRAITEMENT:
			retour = "En cours de traitement";
			break;
		case TRAITEE:
			retour = "Traitée";
			break;
		default:
			throw new IllegalArgumentException();
		}
		return retour;
	}

	
}
