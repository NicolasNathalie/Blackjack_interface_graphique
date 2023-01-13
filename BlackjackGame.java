package Projet3;

public class BlackjackGame {
	 private final Hand playerHand;
	    private final Hand dealerHand;
	    private final Deck deck;
	    private double betAmount;
	    private final double minBet;
	    private final double maxBet;
	    private double totalMoney;
	    
		//Initialiser deck, playerHand, dealerHand, minBet et maxBet
		//le minimum et le maximum de la mise sont de 5 et 1000 respectivement.
	    public BlackjackGame() {
	    	deck = new Deck();
	    	playerHand = new Hand("Player");
	    	dealerHand = new Hand("Dealer");
	    	minBet = 5;
	    	maxBet = 1000;
	    	resetMoney();
	    } 
	    
	    public void loadMoney() {
	        totalMoney = 100;
	    } 
	    
		//retourne true le total d’argent dont un joueur dispose est inférieur au minimum de mise. False sinon.
	    public boolean isOutOfMoney() {
	    	return totalMoney <= minBet;
	    }

		// pour initialiser totalMoney a 100
	    public void resetMoney() {
	    	totalMoney = 100;
	    }
	    
		//retourne false si double localBetAmt est inférieur au minBet ou supérieur au maxBet ou supérieur au totalMoney. True sinon.
	    public boolean isValidBet(double localBetAmt) {
	    	return totalMoney >= localBetAmt && localBetAmt >= minBet && localBetAmt <= maxBet;
	    }
	    
		//retourner minBet
	    public double getMinBet() {  
	    	return minBet;
	    }
	    
		//retourner le montant total que le joeur peut l'utiliser pour la mise.
	    public double getMaxBet() {
	    	return maxBet;
	    }
	    
		// pour retrouner le montant total
	    public double getTotalMoney() {  
	    	return totalMoney;
	    }
	    
		//pour intialiser le montant de la mise qu'on va le faire
	    public void setBet(double amt) {   
	    	betAmount = amt;
	    }
	    
		// distribue deux cartes pour le joueur (playerHand) et deux cartes pour le courtier (dealerHand).
	    public void deal() {
	    	Card card1 = deck.drawCard();
	    	Card card2 = deck.drawCard();
	    	playerHand.addCard(card1);
	    	playerHand.addCard(card2);
	    	card1 = deck.drawCard();
	    	card2 = deck.drawCard();
	    	dealerHand.addCard(card1);
	    	dealerHand.addCard(card2);
	    }
	    
		//pour distribuer une carte en plus pour le joueur dans le cas où il fait hit.
	    public void hit() {
	    	Card card = deck.drawCard();
	    	playerHand.addCard(card);
	    }
	    
		//qui ajoute des cartes au main du courtier tant que la somme des points dont il dispose est moins que 17.
	    public void stand() {
	    	while(dealerHand.getPoints() < 17)
	    	{
	        	Card card = deck.drawCard();
	        	dealerHand.addCard(card);
	    	}
	    }
	    
		//retourne le deuxième carte dans la main du courtier.
	    public Card getDealerShowCard() {
	    	return dealerHand.getCards()[1];
	    }
	    
		//retourne dealerHand
	    public Hand getDealerHand() {
	    	return dealerHand;
	    }

		//retourne playerHand
	    public Hand getPlayerHand() {
	    	return playerHand;

	    }
	    
		// ice cream
	    public boolean isBlackjackOrBust() {
	        if(playerHand.isBlackjack() || playerHand.isBust() 
	                || dealerHand.isBlackjack() || dealerHand.isBust()) {
	            return true;
	        } else{
	            return false;
	        }
	    }
	    
		//retourne true si les points dans la main de joueur est inférieur ou égale 21 et ces points sont égales aux points avec le courtier. False sinon.
	    public boolean isPush() {
	    	return playerHand.getPoints() <= 21 && playerHand.getPoints() == dealerHand.getPoints();
	    }
	    
		
		//retourne true si le player gagne. False sinon.
	    public boolean playerWins() {
	    	return playerHand.getPoints() <= 21 && (dealerHand.getPoints() > 21 || playerHand.getPoints() > dealerHand.getPoints());
	    }
	    
		// ajoute le montant du mise gagner au montant total
	    public void addBetToTotal() {
	    	totalMoney += betAmount;
	    }
	    
		// ajoute le montant de mise gagner selon 3:2 au montant total dans le cas de blackjack
	    public void addBlackjackToTotal() {
	    	totalMoney += 3 * betAmount / 2;
	    }
	    
		// soustraire le montant du bet perdu du montant total
	    public void subtractBetFromTotal() {
	    	totalMoney -= betAmount;
	    }
	    
	    public void resetHands() {
	    	playerHand.resetHand();
	    	dealerHand.resetHand();
	    }

		public void saveMoney() {
			// TODO Auto-generated method stub
			
		}
}
