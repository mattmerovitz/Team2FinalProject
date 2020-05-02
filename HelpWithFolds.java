import java.util.*;

public class HelpWithFolds extends PokerHelp {//inherits the PokerHelp class

  public ArrayList<Card> HelpWithFolds (ArrayList<Card> deck, ArrayList<Card> cards){

    Scanner fold = new Scanner(System.in);//scanner for inputs
    Scanner in = new Scanner(System.in);//scanner


    int counter = 0;
    String userInput = "";

    System.out.println("Have you or any opponent folded cards on the table? (y/n)");
    String folded = fold.nextLine();//asks user if there are folded cards on the table

    if (folded.toLowerCase().equals("y")) {//if there was a folded card

      String endFold = "";

      System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spades ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q");//input message of folded card

      while (deck.size() > 0) {

        counter++;//counting folded cards

        System.out.print("Folded card " + counter + ": ");

        Card foldedCard = super.validateInput(fold, cards);//asks user for the folded card(s)

       for (int i = 0; i < deck.size(); i++) {
           if (deck.get(i).num == foldedCard.num && deck.get(i).suit.equals(foldedCard.suit)){
            deck.remove(i);//remove the card from the deck if the card has been folded
          }
        }

        System.out.println("Do you want to fold more cards? (y/n)");
        endFold = in.nextLine();//asks the user if there are more cards to fold

        if (endFold.toLowerCase().equals("n")){
          return deck;//returns the deck when done folding
        }

      }

    }
    return deck;//returns the deck
  }

}
