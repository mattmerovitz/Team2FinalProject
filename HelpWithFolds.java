import java.util.*;

public class HelpWithFolds extends PokerHelp {

  public ArrayList<Card> HelpWithFolds (ArrayList<Card> deck){

    Scanner fold = new Scanner(System.in);
    Scanner in = new Scanner(System.in);

    int counter = 0;
    String userInput = "";

    System.out.println("Are there any cards folded on the table? (y/n)");
    String folded = fold.nextLine();

    if (folded.toLowerCase().equals("y")) {

      String endFold = "";

      System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spades ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q");

      while (deck.size() > 0) {

        counter++;

        System.out.print("Folded card " + counter + ": ");

        Card foldedCard = super.validateInput(fold);

        for (int i = 0; i < deck.size(); i++) {
          if (deck.get(i).num == foldedCard.num && deck.get(i).suit.equals(foldedCard.suit)){
            deck.remove(i);
          }
        }

        System.out.println("Do you want to fold more cards? (y/n)");
        endFold = in.nextLine();

        if (endFold.toLowerCase().equals("n")){
          return deck;
        }

      }

    }
    return deck;
  }

}
