package Projet3;

import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Blackjack extends Application {
	
	private Label money = new Label("Money:");
	private TextField moneyField = new TextField();
	private Label betAmount = new Label("Bet:");
	private TextField betAmountField = new TextField();
	private Label listViewLabel = new Label("Cards:");
	private ListView<String> cardsDealerListView  = new ListView<>();
	private Label dealerPointsLabel = new Label("Points:");
	private TextField dealerPointsField = new TextField();
	private Label listViewLabel1 = new Label("Cards:");
	private ListView<String> cardsPlayerListView  = new ListView<>();
	private Label playerPointsLabel = new Label("Points:");
	private TextField playerPointsField = new TextField();
	private Label result = new Label("RESULT:");
	private TextField resultField = new TextField();
	
    private Button hitButton = new Button("Hit");
    private Button standButton = new Button("Stand");
    private Button playButton = new Button("Play");
    private Button exitButton = new Button("Exit");

    private BlackjackGame game = new BlackjackGame();
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		moneyField.setEditable(false);
		resultField.setEditable(false);
		dealerPointsField.setEditable(false);
		playerPointsField.setEditable(false);
		primaryStage.setTitle("Blackjack");
		moneyField.setText(String.valueOf(game.getTotalMoney()));
		hitButton.setDisable(true);
		standButton.setDisable(true);
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(10);
		grid.setVgap(10);
		
		Scene scene = new Scene(grid);
		
		grid.add(money, 0, 0);
		grid.add(moneyField, 1, 0);
		
		grid.add(betAmount, 0, 1);
		grid.add(betAmountField, 1, 1);
		
		grid.add(new Label("DEALER"), 0, 2);
		
		cardsDealerListView.setPrefHeight(10*10);
		cardsDealerListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		grid.add(listViewLabel,0, 4);
		grid.add(cardsDealerListView, 1, 4);
		
		grid.add(dealerPointsLabel, 0, 5);
		grid.add(dealerPointsField, 1, 5);
		
		grid.add(new Label("YOU"), 0, 6);
		
		cardsPlayerListView.setPrefHeight(10*10);
		cardsPlayerListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		grid.add(listViewLabel1,0, 7);
		grid.add(cardsPlayerListView, 1, 7);
		
		grid.add(playerPointsLabel, 0, 8);
		grid.add(playerPointsField, 1, 8);
		
		//Ajouter un evement sur les boutons
		hitButton.setOnAction(event -> hitButtonFunction());
		standButton.setOnAction(event -> standButtonFunction());
		
		//Mettre les boutons dans une box
		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().add(hitButton);
		buttonBox.getChildren().add(standButton);
		
		//Ajouter la box de boutons au grid
		grid.add(buttonBox, 0, 9);
		
		grid.add(result, 0, 10);
		grid.add(resultField, 1, 10);
		    
		//Ajouter un evement sur les boutons
		playButton.setOnAction(event -> playButtonFunction());
		exitButton.setOnAction(event -> exitButtonFunction());
		    
		//Mettre les boutons dans une box 
		HBox buttonBox1 = new HBox(10);
		buttonBox1.getChildren().add(playButton);
		buttonBox1.getChildren().add(exitButton);
		    
		//Ajouter la box de boutons au grid
		grid.add(buttonBox1, 0, 11);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void exitButtonFunction() {
		System.exit(0);
	}

	private void playButtonFunction() {
		try
		{
			if(game.isValidBet(Double.parseDouble(betAmountField.getText()))) {
				game.setBet(Double.parseDouble(betAmountField.getText()));
				playButton.setDisable(true);
				exitButton.setDisable(true);
				hitButton.setDisable(false);
				betAmountField.setDisable(true);
				standButton.setDisable(false);
				resultField.setText("");
				game.resetHands();	
				game.deal();			
				ShowPlayerHand();
				ShowDealerShowCard();
			}	
			else {
				resultField.setText("Invalid bet!");			
			}
		}
		catch(Exception ex)
		{
			resultField.setText("Invalid bet!");	
		}
	}

	private void standButtonFunction() {
		game.stand();
		ShowPlayerHand();
		ShowDealerHand();
		ShowWinner();
	}
	

	private void hitButtonFunction() {
		game.hit();
		if(game.getPlayerHand().getPoints() > 21)
		{
			standButtonFunction();
			return;
		}
		ShowPlayerHand();
		ShowDealerShowCard();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		launch(args);

	}
	
	private void ShowPlayerHand() {
		cardsPlayerListView.getItems().clear();
		for(Card card: game.getPlayerHand().getCards())
		{
			cardsPlayerListView.getItems().add(card.display());
		}
		playerPointsField.setText(String.valueOf(game.getPlayerHand().getPoints()));
	}
	
	private void ShowDealerHand() {
		cardsDealerListView.getItems().clear();
		for(Card card: game.getDealerHand().getCards())
		{
			cardsDealerListView.getItems().add(card.display());
		}	
		dealerPointsField.setText(String.valueOf(game.getDealerHand().getPoints()));
	}
	
	private void ShowDealerShowCard() {
		cardsDealerListView.getItems().clear();
		cardsDealerListView.getItems().add(game.getDealerShowCard().display());	
		dealerPointsField.setText("");
	}

    private void ShowWinner() {        
        if(game.isPush()) { 
        	resultField.setText("Push!");
        } else if(game.getPlayerHand().isBlackjack()) {
        	resultField.setText("BLACKJACK! You win!");
            game.addBlackjackToTotal();
        } else if (game.playerWins()) {
        	resultField.setText("You win!");
            game.addBetToTotal();
        } else {
        	resultField.setText("Sorry, you lose.");
            game.subtractBetFromTotal();
        }
        if(game.getTotalMoney() < game.getMinBet())
        	game.resetMoney();
        moneyField.setText(String.valueOf(game.getTotalMoney()));
		playButton.setDisable(false);
		exitButton.setDisable(false);
		hitButton.setDisable(true);
		standButton.setDisable(true);	
		betAmountField.setDisable(false);
    }
	
	public static String getString(String string, String[] strings) {
		// TODO Auto-generated method stub
		  while (true) {
	            char[] value = null;
				// get value from user
	            System.out.print(value);
	           
	            
	            // make sure value is in array of allowed values
	            for (String s: strings) {
	                if (s.equalsIgnoreCase(string)) {
	                    return string;  // end loop
	                }
	            }
	            
	            // if we are here, value isn't in array - notify user
	            System.out.println("Error! Value must be in this list: " 
	                    + Arrays.toString(strings) + ".");
	        }
	}

	public static double getDouble(String string, double minBet, double maxBet) {
		// TODO Auto-generated method stub
		return 0;
	}

}
