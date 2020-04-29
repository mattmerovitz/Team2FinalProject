/*TODO
bestHandFinder method
*/
import java.util.*;
import java.io.*;


public class Hand{
	ArrayList<Card> all;
	int bestHand;

	public Hand(ArrayList<Card> hand){
		all = hand;
		bestHand = bestHandfinder();
	}
	public int bestHandfinder(){
		
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
		if (bestHand == 5){
			return("You have a striaght!");
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