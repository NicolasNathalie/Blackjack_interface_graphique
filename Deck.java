package Projet3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Deck {
    private Card[] deck;
    private int currentCardIndex;
    
	//stocke les cartes dans  Card[ ] deck et ensuite il appelle la fonction shuflleDeck(). 
    public Deck() {
    	ArrayList<Card> newDeck = new ArrayList<Card>();
    	for(int i=0; i<4; i++)
    	{
    		String suite = "";
    		switch(i)
    		{
    		case 0:
    			suite = "Spades";
    			break;
    		case 1:
    			suite = "Hearts";
    			break;
    		case 2:
    			suite = "Clubs";
    			break;
    		case 3:
    			suite = "Diamonds";
    			break;    		
    		}
    		for(int j=2; j<=10; j++)
    		{
    			newDeck.add(new Card(suite, Integer.toString(j), j));
    		}
    		newDeck.add(new Card(suite, "Jack", 10));
    		newDeck.add(new Card(suite, "Queen", 10));
    		newDeck.add(new Card(suite, "King", 10));
    		newDeck.add(new Card(suite, "Ace", 11));
    	}
    	deck = new Card[newDeck.size()];
    	deck = newDeck.toArray(deck);
    	shuffleDeck();
    }
    
	//shuffleDeck, pour mélanger les cartes à l'aide de l'algorithme de mélange de Fisher-Yates:
	//https://www.geeksforgeeks.org/shuffle-a-given-array-using-fisher-yates-shuffle-algorithm/         
    private void shuffleDeck() {
        // Creating a object for Random class
        Random r = new Random();
         
        // Start from the last element and swap one by one. We don't
        // need to run for the first element that's why i > 0
        for (int i = deck.length-1; i >= 0; i--) {
             
            // Pick a random index from 0 to i
            int j = r.nextInt(i+1);
             
            // Swap arr[i] with the element at random index
            Card temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }        
    }
    
    public Card drawCard() {
        if(currentCardIndex == 51) {
            Card currCard = deck[currentCardIndex];
            shuffleDeck();
            return currCard;
        }
        else {
            return deck[currentCardIndex++];        
        }
    }
}
