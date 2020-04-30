import java.util.*;
import java.io.*;

public class PokerHelp{

	public static void main(String[] args) throws FileNotFoundException{
    File deckFile = new File("Deck.txt");
		Scanner filereader = new Scanner(deckFile);
		ArrayList<Card> deck = new ArrayList<Card>();
    if (deckFile.exists()){
      for (int i=0; i<52; i++){
  			deck.add(new Card(filereader.next(),filereader.next()));
  		}
    }
    else{
      System.out.print("This file does not exist");
    }

    ArrayList<Card> cards = new ArrayList<Card>();
		System.out.println("\nWelcome to our poker win percentage calculator!\n");
		System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spades ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q\n");
		Scanner input = new Scanner(System.in);
		Game game = new Game();
		cards = game.Game(deck, cards);
		HelpWithFolds help = new HelpWithFolds();
		deck = help.HelpWithFolds(deck);
  	Hand hand = new Hand(cards);
		System.out.println(hand.toString());
		for (int j = 0; j < hand.all.size(); j++){
			for (int i = 0; i < deck.size(); i++) {
				if (deck.get(i).num == hand.all.get(j).num && deck.get(i).suit.equals(hand.all.get(j).suit)){
			 		deck.remove(i);
		 		}
	 		}
		}
    double handsBeaten = 0;
		double totalHands = 0;
		ArrayList<Card> communityCards = new ArrayList<Card>();
		for (int d = 2; d < 7; d++){
			communityCards.add(hand.all.get(d));
		}
		for (int i = 0; i < deck.size(); i++){
			for (int j = i + 1; j < deck.size(); j++){
				ArrayList<Card> temp = new ArrayList<Card>();
				for (int k = 0; k < communityCards.size(); k++){
					temp.add(communityCards.get(k));
				}
				temp.add(deck.get(i));
				temp.add(deck.get(j));
				Hand opponentsHand = new Hand(temp);
				totalHands++;
				if (hand.bestHand > opponentsHand.bestHand){
					handsBeaten++;
				}
			}
	  	}
   		double winPercentage = handsBeaten/totalHands;
   		System.out.println("How many opponents are there? ");
   		int numPlayers = input.nextInt();
   		winPercentage = Math.pow(winPercentage,numPlayers);
   		winPercentage *= 100;
		System.out.println("Your hand beats " + handsBeaten + " out of " + totalHands + " total hands.");
		System.out.println("That's a win-percentage of " + winPercentage + "%.");
   }
	public static Card validateInput(Scanner input){
		String suit = "";
		while (true){
			try{
				suit = input.next().toLowerCase();
			}
			catch (InputMismatchException e){
  				System.out.println("Invalid input, please try again: ");
			}
			try{
				String num = input.next();
				try{
					int intNum = Integer.parseInt(num.trim());
					if (intNum > 1 && intNum < 11){
						if (suit.equals("spade") || suit.equals("diamond") || suit.equals("heart") || suit.equals("club")){
							Card card = new Card(suit,intNum);
							return card;
						}
						else{
							System.out.println("Invalid input, please try again: ");
						}
					}
					else{
						System.out.println("Invalid input, please try again: ");
					}
				}
				catch (NumberFormatException nfe){
					if (suit.equals("spade") || suit.equals("diamond") || suit.equals("heart") || suit.equals("club")){
							Card card = new Card(suit,num);
							return card;
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
