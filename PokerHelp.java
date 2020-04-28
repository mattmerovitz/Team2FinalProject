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
	Hand hand;

	public static void main(String[] args){
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
