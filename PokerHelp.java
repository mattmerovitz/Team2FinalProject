import java.util.*;
import java.io.*;
/*TODO
PokerHelpwithFolds class that extends PokerHelp, accounts for cards folded
*/
public class PokerHelp{
	ArrayList<Card> deck;
	Hand hand;

	public PokerHelp() throws FileNotFoundException{
		Scanner filereader = new Scanner(new File ("Deck.txt"));
		while (filereader.hasNextLine()){
			deck.add(new Card(filereader.next(),filereader.next()));
		}
		Hand hand = new Hand(informationRetriever());
		hand.toString();
		for (int i = 0; i < hand.all.size(); i++){
			deck.remove(hand.all.get(i));
		}
		calculator(hand);
	}

	public ArrayList<Card> informationRetriever(){
		ArrayList<Card> cards = new ArrayList<Card>();
		System.out.println("Welcome to our poker win percentage calculator!");
		System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spades ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q");
		Scanner input = new Scanner(System.in);
		System.out.println("Personal Card 1: ");
		cards.add(validateInput(input));
		System.out.println("Personal Card 2: ");
		cards.add(validateInput(input));
		System.out.println("Community Card 1 (Flop 1): ");
		cards.add(validateInput(input));
		System.out.println("Community Card 2 (Flop 2): ");
		cards.add(validateInput(input));
		System.out.println("Community Card 3 (Flop 3): ");
		cards.add(validateInput(input));
		System.out.println("Community Card 4 (Turn): ");
		cards.add(validateInput(input));
		System.out.println("Community Card 5 (River): ");
		cards.add(validateInput(input));
		return cards;
	}

	public Card validateInput(Scanner input){
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
					Card card = new Card(suit,num);
					return card;
				}
			}
			catch (InputMismatchException e){
  				System.out.println("Invalid input, please try again: ");
			}
		}
	}

	public void calculator(Hand hand){
		int handsBeaten = 0;
		int totalHands = 0;
		ArrayList<Card> communityCards = new ArrayList<Card>();
		for (int i = 2; i < 7; i++){
			communityCards.add(hand.all.get(i));
		}
		for (int i = 0; i < deck.size(); i++){
			for (int j = i + 1; j < deck.size(); j++){
				ArrayList<Card> temp = communityCards;
				temp.add(deck.get(i));
				temp.add(deck.get(j));
				Hand opponentsHand = new Hand(temp);
				totalHands++;
				if (hand.bestHand > opponentsHand.bestHand){
					handsBeaten++;
				}
			}
		}
		double winPercentage = handsBeaten*100/totalHands;
		System.out.println("Your hand beats " + handsBeaten + " out of " + totalHands + " total hands.");
		System.out.println("Thats a win-percentage of " + winPercentage + "%.");
	}
}
