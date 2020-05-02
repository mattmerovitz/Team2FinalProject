import java.util.*;
import java.io.*;


public class Hand{
	ArrayList<Card> all;//player's personal and community cards
	int bestHand;//ranking of player's best hand (1 = high card, 2 = pair...)
	int secondaryIndex;//secondary ranking for cases of two Hands with the same bestHands

	public Hand(ArrayList<Card> hand){
		all = hand;
		bestHand = bestHandfinder(all);
	}
	public int bestHandfinder(ArrayList<Card> currentHand){

		//lines 17-26 initialize some variables
		bestHand = 1;
		int pairCounter = 0;
		int royalChecker = 0;
		ArrayList<Card> sortedCard = new ArrayList<Card>();
		ArrayList<Card> straight = new ArrayList<Card>();
		ArrayList<Card> heart = new ArrayList<Card>();
		ArrayList<Card> club = new ArrayList<Card>();
		ArrayList<Card> diamond = new ArrayList<Card>();
		ArrayList<Card> spade = new ArrayList<Card>();
		Card[] values = new Card[currentHand.size()];

		//sorts the cards by number
		for (int i=0; i<values.length; i++){
			values[i] = currentHand.get(i);//sends the hand to a normal array
		}
		sortedCard = sort(values);//sorts the cards by number and returns them as an ArrayList
		for (int i=0; i<sortedCard.size(); i++){
			straight.add(sortedCard.get(i));//makes the ArrayList for the straight
		}
		secondaryIndex = sortedCard.get(sortedCard.size()-1).num;

		//three of a kind
		for (int i=2;i<sortedCard.size(); i++){
			if (sortedCard.get(i-2).num == sortedCard.get(i-1).num && sortedCard.get(i-1).num == sortedCard.get(i).num){//if there are three equal cards in a row
				bestHand = 4;//sets best hand to three of a kind
				secondaryIndex = sortedCard.get(i).num;//sets the secondary index to the number of the three of a kind
			}
		}

		//full house
		if (bestHand == 4){//if three of a kind
			for (int i=1;i<sortedCard.size(); i++){
				if (sortedCard.get(i-1).num == sortedCard.get(i).num){//if also a pair
					pairCounter++;
					if (pairCounter > 2){//sets full house
						bestHand = 7;
					}
				}
			}
		}

		//two pair
		pairCounter = 0;//resets the pair counter to ensure no overlap
		ArrayList<Integer> highestPair = new ArrayList<Integer>();//makes an ArrayList of the numbers in the pairs
		int highPair = 0;//sets the value of the highet pair
		for (int i=1;i<sortedCard.size(); i++){
			if (sortedCard.get(i-1).num == sortedCard.get(i).num && bestHand == 1){//if pair
				pairCounter++;//counts numbers of pairs
				highestPair.add(sortedCard.get(i).num);//adds to the highestPair ArrayList
				secondaryIndex = sortedCard.get(i).num;//sets the secondaryIndex
				if (pairCounter > 1){
					bestHand = 3;//sets two pair
				}
			}
		}

		//checks highest of the two pairs
		if (bestHand == 3){
			for (int i=0; i<highestPair.size(); i++){
				if (highestPair.get(i) > highPair){//if it is the highest pair
					secondaryIndex = highestPair.get(i);//change the value of the highest pair
					highPair = secondaryIndex;
				}
			}
		}

		//pair
		if (pairCounter == 1){
			bestHand = 2;//sets pair if cards is 1
		}

		//four of a kind
		for (int i=3;i<sortedCard.size(); i++){
			//if four cards of the same value in a row
			if (sortedCard.get(i-3).num == sortedCard.get(i-2).num &&
			sortedCard.get(i-2).num == sortedCard.get(i-1).num &&
			sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 8;//sets four of a kind
				secondaryIndex = sortedCard.get(i).num;//sets secondaryIndex
			}
		}

		//makes straight ArrayList
		for (int i=1;i<straight.size(); i++){
			if (straight.get(i-1).num == straight.get(i).num){//removes value if two of the same card numbers are in the hand
				straight.remove(i);
			}
		}

		//categorize cards by suit
		for (int i=0;i<sortedCard.size(); i++){
			//four ArrayLists; each card is categorized by suit
			if (sortedCard.get(i).suit.equals("heart")){
				heart.add(sortedCard.get(i));
			}
			else if (sortedCard.get(i).suit.equals("club")){
				club.add(sortedCard.get(i));
			}
			else if (sortedCard.get(i).suit.equals("diamond")){
				diamond.add(sortedCard.get(i));
			}
			else if (sortedCard.get(i).suit.equals("spade")){
				spade.add(sortedCard.get(i));
			}
		}

		//straight
		for (int i=4;i<straight.size(); i++){
			//if there are 5 cards in a row in the hand
			if (straight.get(i-4).num + 4 == straight.get(i-3).num + 3 &&
					straight.get(i-3).num + 3 == straight.get(i-2).num + 2 &&
					straight.get(i-2).num + 2 == straight.get(i-1).num + 1 &&
					straight.get(i-1).num + 1 == straight.get(i).num) {
				bestHand = 5;//sets best hand to straight
				secondaryIndex = sortedCard.get(i).num;//sets secondaryIndex to the highest card of the straight
			}
		}

		int highest = 0;

		//flush
		//lines 138-173 check to see if there are 5+ cards in a single suit
		if (heart.size() > 4) {
			bestHand = 6;
			for (int i=0; i<heart.size(); i++){
				if (i > pairCounter){
					secondaryIndex = heart.get(i).num;
					pairCounter = i;
				}
			}
		}
		else if (club.size() > 4) {
			bestHand = 6;
			for (int i=0; i<club.size(); i++){
				if (i > pairCounter){
					secondaryIndex = club.get(i).num;
					pairCounter = i;
				}
			}
		}
		else if (diamond.size() > 4) {
			bestHand = 6;
			for (int i=0; i<diamond.size(); i++){
				if (i > pairCounter){
					secondaryIndex = diamond.get(i).num;
					pairCounter = i;
				}
			}
		}
		else if (spade.size() > 4) {
			bestHand = 6;
			for (int i=0; i<spade.size(); i++){
				if (i > pairCounter){
					secondaryIndex = spade.get(i).num;
					pairCounter = i;
				}
			}
		}

		//straight flush
		//lines 177-224 check to see if the flushed cards are also a straight and sets the secondaryIndex to the highest card of the straight
		if (heart.size() > 4) {
			for (int i=4;i<heart.size(); i++){
				if (heart.get(i-4).num + 4 == heart.get(i-3).num + 3 &&
					heart.get(i-3).num + 3 == heart.get(i-2).num + 2 &&
					heart.get(i-2).num + 2 == heart.get(i-1).num + 1 &&
					heart.get(i-1).num + 1 == heart.get(i).num){
						bestHand = 9;
						secondaryIndex = sortedCard.get(i).num;
						royalChecker = heart.get(i).num;
					}
				}
			}
		else if (club.size() > 4) {
			for (int i=4;i<club.size(); i++){
				if (club.get(i-4).num + 4 == club.get(i-3).num + 3 &&
					club.get(i-3).num + 3 == club.get(i-2).num + 2 &&
					club.get(i-2).num + 2 == club.get(i-1).num + 1 &&
					club.get(i-1).num + 1 == club.get(i).num){
						bestHand = 9;
						secondaryIndex = sortedCard.get(i).num;
						royalChecker = club.get(i).num;
					}
				}
			}
		else if (diamond.size() > 4) {
			for (int i=4;i<diamond.size(); i++){
				if (diamond.get(i-4).num + 4 == diamond.get(i-3).num + 3 &&
					diamond.get(i-3).num + 3 == diamond.get(i-2).num + 2 &&
					diamond.get(i-2).num + 2 == diamond.get(i-1).num + 1 &&
					diamond.get(i-1).num + 1 == diamond.get(i).num){
						bestHand = 9;
						secondaryIndex = sortedCard.get(i).num;
						royalChecker = diamond.get(i).num;
					}
				}
			}
		else if (spade.size() > 4){
			for (int i=4;i<spade.size(); i++){
				if (spade.get(i-4).num + 4 == spade.get(i-3).num + 3 &&
					spade.get(i-3).num + 3 == spade.get(i-2).num + 2 &&
					spade.get(i-2).num + 2 == spade.get(i-1).num + 1 &&
					spade.get(i-1).num + 1 == spade.get(i).num){
						bestHand = 9;
						secondaryIndex = sortedCard.get(i).num;
						royalChecker = spade.get(i).num;
					}
				}
			}

		//royal flush
		if (royalChecker == 14){
			bestHand = 10;//sets best hand to royal flush if there is a straight flush and the highest card of the straight flush is an ace
		}

		return bestHand;

	}
	public String toString(){
		/*
		*
		***lines 239-270 return the string message regarding the best hand the user has.
		*/
		if (bestHand == 1){
			return("You have a high card!");
		}
		else if (bestHand == 2){
			return("You have a pair!");
		}
		else if (bestHand == 3){
			return("You have a 2 pair!");
		}
		else if (bestHand == 4){
			return("You have a three of a kind!");
		}
		else if (bestHand == 5){
			return("You have a straight!");
		}
		else if (bestHand == 6){
			return("You have a flush!");
		}
		else if (bestHand == 7){
			return("You have a full house!");
		}
		else if (bestHand == 8){
			return("You have a four of a kind!");
		}
		else if (bestHand == 9){
			return("You have a straight flush!");
		}
		else if (bestHand == 10){
			return("You have a royal flush!");
		}
		return "";
	}

	public ArrayList<Card> sort(Card[] values){
		ArrayList<Card> sortedCard = new ArrayList<Card>();//ArrayList of the sorted cards
		Card temporary = values[0];//sets the temp card to the first card of the array
		for (int i = 1; i < 7; i++) {
      for (int j = i; j > 0; j--) {
        if (values[j].num < values[j-1].num) {//checks to see if the current value is higher than the next value
					temporary = values[j];
          values[j] = values[j-1];
					values[j-1] = temporary;
        }
      }
    }
		for (int i=0; i<7; i++) {
			sortedCard.add(values[i]);//makes the sorted cards to an ArrayList
		}
		return sortedCard;//returns the ArrayList
	}
}
