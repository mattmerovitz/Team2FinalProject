import java.util.*;
import java.io.*;

public class PokerHelp{

	public static void main(String[] args) throws FileNotFoundException{
    	File deckFile = new File("Deck.txt");
		Scanner filereader = new Scanner(deckFile);//new scanner created to read the deck file
		ArrayList<Card> deck = new ArrayList<Card>();//initialises an ArrayList of type Card to act as a deck
   		if (deckFile.exists()){
     		for (int i=0; i<52; i++){
  				deck.add(new Card(filereader.next(),filereader.next()));//adds a card object to the deck object
  			}
  		}
   		else{
    		System.out.print("This file does not exist");
    	}
		ArrayList<Card> cards = new ArrayList<Card>();//initianlises an ArrayList of type Card to store the user's personal and community cards
		System.out.println("\nWelcome to our poker win percentage calculator!\n");
		System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spades ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q\n");
		Scanner input = new Scanner(System.in);//new scanner created to ask for input
		Game game = new Game();//Game object initialised
		cards = game.Game(deck, cards);//Game object returns user's personal and community cards
		HelpWithFolds help = new HelpWithFolds();//HelpWithFolds object initialised
		deck = help.HelpWithFolds(deck);//deck is changed based on folded cards
  		Hand hand = new Hand(cards);//Hand object initialised for user's cards
		System.out.println(hand.toString());
		for (int j = 0; j < hand.all.size(); j++){//for loop iterates through user's cards
			for (int i = 0; i < deck.size(); i++) {//for loop iterates through the deck
				if (deck.get(i).num == hand.all.get(j).num && deck.get(i).suit.equals(hand.all.get(j).suit)){//if statement looks for identical cards (same suit, same value)
			 		deck.remove(i);//identical cards are removed from the deck
		 		}
	 		}
		}
    	double handsBeaten = 0;
		double totalHands = 0;
		ArrayList<Card> communityCards = new ArrayList<Card>();
		for (int d = 2; d < 7; d++){
			communityCards.add(hand.all.get(d));
		}
		for (int i = 0; i < deck.size(); i++){//nested for loops allow for analysis of every single other possible hand
			for (int j = i + 1; j < deck.size(); j++){
				ArrayList<Card> temp = new ArrayList<Card>();//temp ArrayList initialised, it will hold the opponent's personal and community cards
				for (int k = 0; k < communityCards.size(); k++){
					temp.add(communityCards.get(k));//community cards are added to opponents cards
				}
				temp.add(deck.get(i));//card from deck added to opponents cards
				temp.add(deck.get(j));//card from deck added to opponents cards
				Hand opponentsHand = new Hand(temp);//Hand object created for opponent's cards
				totalHands++;
				if (hand.bestHand > opponentsHand.bestHand){//if statement looks for cases where user's bestHand is better than opponents bestHand
					handsBeaten++;
				}
				else if (hand.bestHand == opponentsHand.bestHand && hand.secondaryIndex > opponentsHand.secondaryIndex){
					handsBeaten++;
				}
			}
	  	}
   		double winPercentage = handsBeaten/totalHands;
   		System.out.println("How many opponents are there? ");
   		int numPlayers = input.nextInt();
   		winPercentage = Math.pow(winPercentage,numPlayers);//accounts for multiple opponents drawing cards
   		winPercentage *= 100;
		System.out.println("Your hand beats " + handsBeaten + " out of " + totalHands + " total hands.");
		System.out.println("That's a win-percentage of " + winPercentage + "%.");
	}

	public static Card validateInput(Scanner input, ArrayList<Card> cards){
		String suit = "";
		while (true){
			try{
				suit = input.next().toLowerCase();//tries to accept suit input as a string
			}
			catch (InputMismatchException e){
  				System.out.println("Invalid input, please try again: ");
			}
			try{
				String num = input.next();//tries to accept value input as a string
				try{
					int intNum = Integer.parseInt(num.trim());//tries to convert value input from a string into an int (for number cards)
					if (intNum > 1 && intNum < 11){//condition for value input
						if (suit.equals("spade") || suit.equals("diamond") || suit.equals("heart") || suit.equals("club")){//condition for suit input
							Card card = new Card(suit,intNum);
							int temp = 0;
							for (int i = 0; i < cards.size(); i++){//traverses through cards to see if card has already been inputted
								if (cards.get(i).num == card.num && cards.get(i).suit.equals(card.suit)){//checks to see if card is identical to cards already being considered
									temp++;
								}
							}
							if (temp == 0){
								return card;//if conditions are met, card is acceptable and is returned
							}
							else{
								System.out.println("Card already entered, please try again: ");
							}
						}
						else{
							System.out.println("Invalid input, please try again: ");
						}
					}
					else{
						System.out.println("Invalid input, please try again: ");
					}
				}
				catch (NumberFormatException nfe){//catch statement in case of attempted conversion of a face card into an int
					if (suit.equals("spade") || suit.equals("diamond") || suit.equals("heart") || suit.equals("club")){
							Card card = new Card(suit,num);
							int temp = 0;
							for (int i = 0; i < cards.size(); i++){//traverses through cards to see if card has already been inputted
								if (cards.get(i).num == card.num && cards.get(i).suit.equals(card.suit)){//checks to see if card is identical to cards already being considered
									temp++;
								}
							}
							if (temp == 0){
								return card;//if conditions are met, card is acceptable and is returned
							}
							else{
								System.out.println("Card already entered, please try again: ");
							}
					}
					else{
							System.out.println("Invalid input, please try again: ");
					}
				}
			}
			catch (InputMismatchException e){
  				System.out.println("Invalid input, please try again: ");
			}
		}
	}
}
