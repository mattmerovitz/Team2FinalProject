import java.util.*;
import java.io.*;
/*TODO
PokerHelpwithFolds class that extends PokerHelp, accounts for cards folded

*/
public class PokerHelp{
	ArrayList<Card> deck;
	Hand hand;

	public static void main(String[] args) throws fileNotFoundException{
		Scanner filereader = new Scanner(new File "Deck");
		while (filereader.hasNextLine()){
			deck.add(Card(filereader.next(),filereader.next()));
		}
		Hand hand = new Hand(informationRetriever());
		hand.toString();
		for (int i = 0; i < hand.all.length; i++){
			deck.remove(hand.all.get(i));
		}
		calculator(hand);
	}

	public static ArrayList<Card> informationRetriever(){
		ArrayList<Card> cards = new ArrayList<Card>();
		System.out.println("Welcome to our poker win percentage calculator!")
		System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spads ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q");
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

	public static Card validateInput(Scanner input){
		while (true){
			String suit = input.next().toLowerCase();
			if (suit.equals("spade") || suit.equals("diamond") || suit.equals("heart") || suit.equals("club")){
				break;
			}
			System.out.println("Invalid suit input, please try again");
		}
		while (true){
			try{
				Int num = input.nextInt();
				if (num > 1 && num < 11){
					break;
				}
			}
			try{
				String num = input.next();
				if (num.equals("j") || num.equals("q") || num.equals("k") || num.equals("a")){
					break;
				}
			}
			System.out.println("Invalid value input, please try again")
		}
		Card card = new Card(suit,num);
		return card;
	} 

	public static void calculator(Hand hand){
		int handsBeaten = 0;
		int totalHands = 0;
		ArrayList<Card> communityCards = new ArrayList<Card>;
		for (int i = 2; i < 7; i++){
			communityCards.add(hand.all.get(i));
		}
		for (int i = 0; i < deck.length; i++){
			for (int j = i + 1; j < deck.length; j++){
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
