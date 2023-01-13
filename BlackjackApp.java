package Projet3;


public class BlackjackApp {
	 private static BlackjackGame game;
	    
	    public static void main(String[] args) {
	        System.out.println("BLACKJACK!");
	        System.out.println("Blackjack payout is 3:2");
	        System.out.println();
	        
	        game = new BlackjackGame();
	        String playAgain = "y";
	        while(playAgain.equalsIgnoreCase("y")) {
	        	// votre scenario de simulation vient ici
	        	getBetAmount();
	        	
	        	game.deal();
	        	showPlayerHand();
	        	System.out.printf("YOUR POINTS: %d%n", game.getPlayerHand().getPoints());
	        	while(getHitOrStand().equalsIgnoreCase("h")) {
	        		game.hit();
	        		if(game.getPlayerHand().getPoints() > 21)
	        			break;
	        		showPlayerHand();
	        		System.out.printf("YOUR POINTS: %d%n", game.getPlayerHand().getPoints());
	        	}
	        	game.stand();
	        	showWinner();
	           
	        	
	        	playAgain = Blackjack.getString("Continue? (y/n):", new String[]{"y","n"});
	        	game.resetHands();
	        }
	        System.out.println("\nBye!");
	    }
	    
		// affiche le message Out of money! Would you like to add more? (y/n):. Si le joueur tappe y alors la fonction reset la balance du joueur au 100 et retourne true. False Sinon.
	    private static boolean buyMoreChips() {
	    	return Blackjack.getString("Out of money! Would you like to add more? (y/n):", new String[]{"y","n"}) == "y";
	    }
	    
		// affiche le message Bet amount, lire la valeur de la mise saisi par le joueur. Valide cette valeur. Si la valeur n'est pas valide afficher le message Bet must be between
	    private static void getBetAmount() {
	    	double bet = Blackjack.getDouble("Bet amount:", game.getMinBet(), game.getMaxBet());
	    	while(!game.isValidBet(bet))
	    	{ 
	    		System.out.println("Invalid bet!");
	    		bet = Blackjack.getDouble("Bet amount:", game.getMinBet(), game.getMaxBet());
	    	}
	    	game.setBet(bet);    	
	    }
	    
		// Affiche le message Hit or Stand? (h/s): et puis retourne ce que le joueur a tappe. 
	    private static String getHitOrStand() {
	    	return Blackjack.getString("Hit or Stand? (h/s):", new String[]{"h","s"});
	    }
	    
		// affiche les cartes dans la main du courtier et les cartes dans la main du joueur
		private static void showHands() {
			showDealerShowCard();
			showPlayerHand();
	    }
	    
		// affiche le message DEALER'S SHOW CARD et puis affiche le deuxieme carte dans la main du courtier
	    private static void showDealerShowCard() {
	    	System.out.println("DEALER'S SHOW CARD");
	    	System.out.println(game.getDealerShowCard().display());
	    }
	    
		// affiche le message DEALER'S CARDS et puis affiche les cartes dans la main du courtier
	    private static void showDealerHand() {
			System.out.println("DEALER'S CARDS");
			for(Card card : game.getDealerHand().getCards()) {
				System.out.println(card.display());
			}
	    }
	    
		// affiche le message YOUR CARDS et puis affiche les cartes dans la main du joueur
	    private static void showPlayerHand() {
			System.out.println("YOUR CARDS");
			for(Card card : game.getPlayerHand().getCards()) {
				System.out.println(card.display());
			}
	    }
	    
		// affiche Total money:  et le montant total
	    private static void showMoney() {
	    	System.out.println("Total money: " + game.getTotalMoney());
	    }
	    

	    private static void showWinner() {
	        showPlayerHand();
	        System.out.printf("YOUR POINTS: %d%n", game.getPlayerHand().getPoints());
	        
	        showDealerHand();
	        System.out.printf("DEALER'S POINTS: %d%n%n", game.getDealerHand().getPoints());
	        
	        if(game.isPush()) { 
	            System.out.println("Push!");
	        } else if(game.getPlayerHand().isBlackjack()) {
	            System.out.println("BLACKJACK! You win!");
	            game.addBlackjackToTotal();
	        } else if (game.playerWins()) {
	            System.out.println("You win!");
	            game.addBetToTotal();
	        } else {
	            System.out.println("Sorry, you lose.");
	            game.subtractBetFromTotal();
	        }
	        showMoney();
	        game.saveMoney();
	    }

}