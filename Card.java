package Projet3;

public class Card {
    private final String suite; // Spades", "Hearts", "Clubs" ou "Diamonds"
    private final String rank;//2, 3, 4, ..., 10,  
    private final int points; 
    
    public Card(String suite, String rank, int points) {
    	this.suite = suite;
    	this.rank = rank;
    	this.points = points;
    }
    
    public Card(Card c) { 
    	this.suite = c.getSuite();
    	this.rank = c.getRank();
    	this.points = c.getPoints();
    }
    
    public String getSuite() {
    	return suite;
    }
    
    public String getRank() {
    	return rank;
    }
    
    public int getPoints() {
    	return points;
    }
    
	//qui retourne true si la carte est un Ace
    public boolean isAce() {
    	return rank == "Ace";
    }
    
	//retourne une chaine de caractère : Valeur_rank_variable + " of " + valeur_suite_variable. Par exemple : 3 of Hearts 
    public String display() {
    	return rank + " of " + suite;
    }

	public static Object getSelectionModel() {
		// TODO Auto-generated method stub
		return null;
	}
}

