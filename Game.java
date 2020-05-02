import java.util.*;
import java.io.*;

public class Game extends PokerHelp {

  public ArrayList<Card> Game (ArrayList<Card> deck, ArrayList<Card> cards) {

    Scanner input = new Scanner(System.in);
    Random random = new Random();
    System.out.println("Would you like to play game mode (random hand) or would you like to manually input your hand? \n(type \"y\" for game mode, type anything else for manual.)");
    String gameMode = input.nextLine();

    if (!gameMode.toLowerCase().equals("y")){//if statement checks if opponent wants to play random or non random game mode

      System.out.println("Personal Card 1: ");
		  cards.add(validateInput(input,cards));
		  System.out.println("Personal Card 2: ");
		  cards.add(validateInput(input,cards));
		  System.out.println("Community Card 1 (Flop 1): ");
		  cards.add(validateInput(input,cards));
		  System.out.println("Community Card 2 (Flop 2): ");
		  cards.add(validateInput(input,cards));
		  System.out.println("Community Card 3 (Flop 3): ");
		  cards.add(validateInput(input,cards));
		  System.out.println("Community Card 4 (Turn): ");
		  cards.add(validateInput(input,cards));
		  System.out.println("Community Card 5 (River): ");
		  cards.add(validateInput(input,cards));

    }

    else {

      for (int i=0; i < 7; i++){

        int randIndex = random.nextInt(deck.size());//random index is assigned to int randIndex
        cards.add(deck.get(randIndex));//card in randIndex of deck is added to the cards ArrayList
        deck.remove(randIndex);//card in randIndex of deck is removed from the deck
      }
      for (int i=0; i < 2; i++){
        int k = i+1;
        String indexToPrint = Integer.toString(k);
        if (cards.get(i).num <= 10){

          System.out.println("Personal card " + indexToPrint + ": " + cards.get(i).suit + " " + cards.get(i).num);

        }

        else if (cards.get(i).num == 11){

    			System.out.println("Personal card " + indexToPrint + ": " + cards.get(i).suit + " j");

    		}
    		else if (cards.get(i).num == 12){

    			System.out.println("Personal card " + indexToPrint + ": " + cards.get(i).suit + " q");

    		}

    		else if (cards.get(i).num == 13){

    			System.out.println("Personal card " + indexToPrint + ": " + cards.get(i).suit + " k");

    		}

    		else if (cards.get(i).num == 14){

    			System.out.println("Personal card " + indexToPrint + ": " + cards.get(i).suit + " a");

    		}

      }

      for (int i=2; i < 7; i++){
        int j = i-1;
        String indexToPrint = Integer.toString(j);
        if (cards.get(i).num <= 10){

          System.out.println("Community card " + indexToPrint + ": " + cards.get(i).suit + " " + cards.get(i).num);

        }

        else if (cards.get(i).num == 11){

    			System.out.println("Community card " + indexToPrint + ": " + cards.get(i).suit + " j");

    		}
    		else if (cards.get(i).num == 12){

    			System.out.println("Community card " + indexToPrint + ": " + cards.get(i).suit + " q");

    		}

    		else if (cards.get(i).num == 13){

    			System.out.println("Community card " + indexToPrint + ": " + cards.get(i).suit + " k");

    		}

    		else if (cards.get(i).num == 14){

    			System.out.println("Community card " + indexToPrint + ": " + cards.get(i).suit + " a");

    		}

      }

    }

    return cards;

  }

}
