import java.util.*;
import java.io.*;

public class Card{
	int num;
	String suit;

	public Card(String suit, int num){//constructor for non-face cards
		this.suit = suit;
		this.num = num;
	}
	public Card(String suit, String num){//constructor for face cards
		this.suit = suit;
		if (num.equals("j")){
			this.num = 11;
		}
		else if (num.equals("q")){
			this.num = 12;
		}
		else if (num.equals("k")){
			this.num = 13;
		}
		else if (num.equals("a")){
			this.num = 14;
		}
		else {
			this.num = Integer.parseInt(num);//converts string into num
		}
	}

	public String getSuit(){
		return this.suit;
	}

	public int getNum(){
		return this.num;
	}
}
