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
		boolean straight_flush = false;
		ArrayList<Card> sortedCard = new ArrayList<Card>();
		Card[] values = new Card[currentHand.size];
		for (int i = 0; i < values.length; i++) {
        int min = values[i].num;
        int minIndex = i;
        for (int j = i+1; j < values.length; j++) {
            if (values[j].num < min) {
                min = values[j];
                minIndex = j;
            }
        }
        int temporary = values[i];
        values[i] = min;
        values[minIndex] = temporary;
    }
		for (Card c : values) {
			sortedCard.add(values[i])
		}
		for (int i=1;i<sortedCard.size; i++){
			if (sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 2;
				pairCounter++;
				if (pairCounter > 1){
					bestHand = 3;
				}
			}
		}
		for (int i=2;i<sortedCard.size; i++){
			if (sortedCard.get(i-2).num == sortedCard.get(i-1).num && sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 4;
			}
		}
		for (int i=3;i<sortedCard.size; i++){
			if (sortedCard.get(i-3).num == sortedCard.get(i-2).num &&
			sortedCard.get(i-2).num == sortedCard.get(i-1).num &&
			sortedCard.get(i-1).num == sortedCard.get(i).num){
				bestHand = 7;
			}
		}
		for (int i=4;i<sortedCard.size; i++){
			if (sortedCard.get(i-4).num + 4 == sortedCard.get(i-3).num + 3 &&
					sortedCard.get(i-3).num + 3 == sortedCard.get(i-2).num + 2 &&
					sortedCard.get(i-2).num + 2 == sortedCard.get(i-1).num + 1 &&
					sortedCard.get(i-1).num + 1 == sortedCard.get(i).num) {
				bestHand = 5;
				if (sortedCard.get(i-4).num + 4 == sortedCard.get(i-3).num + 3 &&
						sortedCard.get(i-3).num + 3 == sortedCard.get(i-2).num + 2 &&
						sortedCard.get(i-2).num + 2 == sortedCard.get(i-1).num + 1 &&
						sortedCard.get(i-1).num + 1 == sortedCard.get(i).num &&
						sortedCard.get(i-4).suit == sortedCard.get(i-3).suit &&
						sortedCard.get(i-3).suit == sortedCard.get(i-2).suit &&
						sortedCard.get(i-2).suit == sortedCard.get(i-1).suit &&
						sortedCard.get(i-1).suit == sortedCard.get(i).suit){
							bestHand = 8;
							straight_flush = true;
						}
				if (straight_flush && sortedCard.get(i).num == 14){
					bestHand = 9;
				}
			}
		}

		ArrayList<Card> clubCards = new ArrayList<Card>();
		ArrayList<Card> diamondCards = new ArrayList<Card>();
		ArrayList<Card> heartCards = new ArrayList<Card>();
		ArrayList<Card> spadeCards = new ArrayList<Card>();
		for (Card c : sortedCards) {
      if (c.getSuit() == "club")
      	clubCards.add(c);
      else if (c.getSuit() == "diamond")
      	diamondCards.add(c);
      else if (c.getSuit() == "heart")
      	heartCards.add(c);
      else if (c.getSuit() == "spade")
      	spadeCards.add(c);
        }

		int numberCards = currentHand.length;

		if (numberCards > 5) {
			else if (clubCards.size() >= 5){
				bestHand = 6;
			}
			else if (diamondCards.size() >= 5){
				bestHand = 6;
			}
			else if (heartCards.size() >= 5){
				bestHand = 6;
			}
			else if (spadeCards.size() >= 5){
				bestHand = 6;
			}

		}

	}
	public String toString(){
		if (bestHand == 1){
			return("You have a high card!");
		}
		else if (bestHand == 2){
			return("You have a pair!");
		}
		if (bestHand == 3){
			return("You have a 2 pair!");
		}
		else if (bestHand == 4){
			return("You have a three of a kind!");
		}
<<<<<<< HEAD
		if (bestHand = 5){
			return("You have a straight!");
=======
		if (bestHand == 5){
			return("You have a striaght!");
>>>>>>> 521977970895204031e6aebde1b16db1d46b31b9
		}
		else if (bestHand == 6){
			return("You have a flush!");
		}
		if (bestHand == 7){
			return("You have a four of a kind!");
		}
		else if (bestHand == 8){
			return("You have a striaght flush!");
		}
		if (bestHand == 9){
			return("You have a royal flush!");
		}
	}
}
