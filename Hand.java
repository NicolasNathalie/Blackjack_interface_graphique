package Projet3;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();
    private String user; //Soit le le joueur soit le courtier. Selon comment on appelle le constrcuteur et le paramertre quand utilise
    
    public Hand(String user) {
    	this.user = user;
    }
    
	//retroune le tableau hand
    public Card[] getCards() {
    	Card[] cards = new Card[hand.size()];
    	return hand.toArray(cards);
    }    
    
	// retourne la somme des cartes dans le tableau hand. Si la somme est >21, il faut recompter les cartes pour verifier si il y a un ACE. Si oui on le considere comme 1, sinon on ajoute la somme des points
    public int getPoints() {
    	int points = 0;
    	for(var card : hand){
    		points += card.getPoints();
    	}
    	if(points > 21) {
    		points = 0;
        	for(var card : hand){
        		if(card.isAce()){
        			points += 1;
        		}
        		else {
        			points += card.getPoints();
        		}
        		
        	}
    	}
    	return points;
    }
    
	// ajouter une carte au tableau
    public void addCard(Card card) {
    	hand.add(card);
    }
    
	//retourne true si la somme de deux cartes est égale à 21. False sinon 
    public boolean isBlackjack() {
    	return getPoints() == 21 && hand.size() == 2;
    }
    
	// retourne true si la somme des points a une valeur supérieur a 21. False sinon.
    public boolean isBust() {
    	return getPoints() > 21;
    }
    
    public void resetHand() {
    	hand.clear();
    }
}


