import java.util.*;
import java.io.*;
/*TODO
calculator method
information retriever invalid input catches
PokerHelpwithFolds class that extends PokerHelp, accounts for cards folded
Deck file with every card
*/
public class PokerHelp{
	ArrayList<Card> deck;
	ArrayList<Card> leftoverDeck;
	Hand hand;

	public static void main(String[] args){
		Scanner filereader = new Scanner(new File "Deck");
		while (filereader.hasNextLine()){
			deck.add(Card(filereader.next(),filereader.next()));
		}
		Hand hand = new Hand(informationRetriever());
		hand.toString();
		calculator(hand);
	}

	public static ArrayList<Card> informationRetriever(){
		ArrayList<Card> cards = new ArrayList<Card>();
		Scanner input = new Scanner(System.in);
		System.out.println("Personal Card 1: ");
		cards.add(card(input.next(), input.next()));
		System.out.println("Personal Card 2: ");
		cards.add(card(input.next(), input.next()));
		System.out.println("Community Card 1 (Flop 1): ");
		cards.add(card(input.next(), input.next()));
		System.out.println("Community Card 2 (Flop 2): ");
		cards.add(card(input.next(), input.next()));
		System.out.println("Community Card 3 (Flop 3): ");
		cards.add(card(input.next(), input.next()));
		System.out.println("Community Card 4 (Turn): ");
		cards.add(card(input.next(), input.next()));
		System.out.println("Community Card 5 (River): ");
		cards.add(card(input.next(), input.next()));
		return cards;
	}

	public static void calculator(ArrayList<Card> hand){

	}
}
