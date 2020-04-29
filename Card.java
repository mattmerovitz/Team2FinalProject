import java.util.*;
import java.io.*;

public class Card{
	int num;
	String suit;

	public Card(String suit, int num){
		this.suit = suit;
		this.num = num;
	}
	public Card(String suit, String num){
		this.suit = suit;
		if (num.equals("j")){
			this.num = 11;
		}
		else if (num.equals("q")){
			this.num = 12;
		}
		if (num.equals("k")){
			this.num = 13;
		}
		else if (num.equals("a")){
			this.num = 14;
		}
	}

	public String getSuit(){
		return this.suit;
	}
}
