import java.util.*;
import java.io.*;


public class Hand{
	ArrayList<Card> all;
	int bestHand;

	public Hand(ArrayList<Card> hand){
		all = hand;
		bestHand = bestHandfinder(all);
	}
	public int bestHandfinder(ArrayList<Card> currentHand){
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

		//sorts cards
		for (int i=0; i<values.length; i++){
			values[i] = currentHand.get(i);
		}
		sortedCard = sort(values);
		for (int i=0; i<sortedCard.size(); i++){
			straight.add(sortedCard.get(i));
		}

		//three of a kind
		for (int i=2;i<sortedCard.size(); i++){
			if (sortedCard.get(i-2).num == sortedCard.get(i-1).num && sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 4;
			}
		}

		//full house
		if (bestHand == 4){
			for (int i=1;i<sortedCard.size(); i++){
				if (sortedCard.get(i-1).num == sortedCard.get(i).num){
					pairCounter++;
					if (pairCounter > 2){
						bestHand = 7;
					}
				}
			}
		}

		//pair
		pairCounter = 0;
		for (int i=1;i<sortedCard.size(); i++){
			if (sortedCard.get(i-1).num == sortedCard.get(i).num && bestHand == 1){
				pairCounter++;
				if (pairCounter > 1){
					bestHand = 3;
				}
			}
		}
		if (pairCounter == 1){
			bestHand = 2;
		}

		//four of a kind
		for (int i=3;i<sortedCard.size(); i++){
			if (sortedCard.get(i-3).num == sortedCard.get(i-2).num &&
			sortedCard.get(i-2).num == sortedCard.get(i-1).num &&
			sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 8;
			}
		}

		//makes straight ArrayList
		for (int i=1;i<straight.size(); i++){
			if (straight.get(i-1).num == straight.get(i).num){
				straight.remove(i);
			}
		}

		//categorize cards by suit
		for (int i=0;i<sortedCard.size(); i++){
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
			if (straight.get(i-4).num + 4 == straight.get(i-3).num + 3 &&
					straight.get(i-3).num + 3 == straight.get(i-2).num + 2 &&
					straight.get(i-2).num + 2 == straight.get(i-1).num + 1 &&
					straight.get(i-1).num + 1 == straight.get(i).num) {
				bestHand = 5;
			}
		}

		//flush
		if (heart.size() > 4 || club.size() > 4 || diamond.size() > 4 || spade.size() > 4){
			bestHand = 6;
		}

		//straight flush
		if (heart.size() > 4) {
			for (int i=4;i<heart.size(); i++){
				if (heart.get(i-4).num + 4 == heart.get(i-3).num + 3 &&
					heart.get(i-3).num + 3 == heart.get(i-2).num + 2 &&
					heart.get(i-2).num + 2 == heart.get(i-1).num + 1 &&
					heart.get(i-1).num + 1 == heart.get(i).num){
						bestHand = 9;
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
						royalChecker = spade.get(i).num;
					}
				}
			}

		//royal flush
		if (royalChecker == 14){
			bestHand = 10;
		}

		return bestHand;

	}
	public String toString(){
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
		ArrayList<Card> sortedCard = new ArrayList<Card>();
		Card temporary = values[0];
		for (int i = 1; i < 7; i++) {
      for (int j = i; j > 0; j--) {
        if (values[j].num < values[j-1].num) {
					temporary = values[j];
          values[j] = values[j-1];
					values[j-1] = temporary;
        }
      }
    }
		for (int i=0; i<7; i++) {
			sortedCard.add(values[i]);
		}
		return sortedCard;
	}
}
