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
		Card[] values = new Card[currentHand.size()];
		for (int i=0; i<values.length; i++){
			values[i] = currentHand.get(i);
		}
		sortedCard = sort(values);
		for (int i=2;i<sortedCard.size(); i++){
			if (sortedCard.get(i-2).num == sortedCard.get(i-1).num && sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 4;
			}
		}
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

		for (int i=3;i<sortedCard.size(); i++){
			if (sortedCard.get(i-3).num == sortedCard.get(i-2).num &&
			sortedCard.get(i-2).num == sortedCard.get(i-1).num &&
			sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 8;
			}
		}

		for (int i=4;i<sortedCard.size(); i++){
			if (sortedCard.get(i-4).num + 4 == sortedCard.get(i-3).num + 3 &&
					sortedCard.get(i-3).num + 3 == sortedCard.get(i-2).num + 2 &&
					sortedCard.get(i-2).num + 2 == sortedCard.get(i-1).num + 1 &&
					sortedCard.get(i-1).num + 1 == sortedCard.get(i).num) {
				bestHand = 5;
			}
		}

		for (int i=4;i<sortedCard.size(); i++){
			if (sortedCard.get(i-4).suit.equals(sortedCard.get(i-3).suit) &&
					sortedCard.get(i-3).suit.equals(sortedCard.get(i-2).suit) &&
					sortedCard.get(i-2).suit.equals(sortedCard.get(i-1).suit) &&
					sortedCard.get(i-1).suit.equals(sortedCard.get(i).suit)) {
				bestHand = 6;
			}
		}

		for (int i=4;i<sortedCard.size(); i++){
			if (sortedCard.get(i-4).num + 4 == sortedCard.get(i-3).num + 3 &&
					sortedCard.get(i-3).num + 3 == sortedCard.get(i-2).num + 2 &&
					sortedCard.get(i-2).num + 2 == sortedCard.get(i-1).num + 1 &&
					sortedCard.get(i-1).num + 1 == sortedCard.get(i).num &&
					sortedCard.get(i-4).suit.equals(sortedCard.get(i-3).suit) &&
					sortedCard.get(i-3).suit.equals(sortedCard.get(i-2).suit) &&
					sortedCard.get(i-2).suit.equals(sortedCard.get(i-1).suit) &&
					sortedCard.get(i-1).suit.equals(sortedCard.get(i).suit)) {
				bestHand = 9;
				royalChecker = sortedCard.get(i).num;
			}
		}

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
