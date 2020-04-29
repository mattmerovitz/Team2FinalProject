import java.util.*;

public class HelpWithFolds extends PokerHelp {

  public HelpWithFolds (ArrayList<Card> deck){

    Scanner fold = new Scanner(System.in);

    int counter = 0;
    String userInput = "";

    System.out.println("Are there any cards folded? (y/n)");
    String folded = fold.nextLine();

    if (folded.toLowerCase() == "y") {

      System.out.println("Please input all cards as 'suit' 'value' (Examples: Ace of spades ~ spade a, Six of hearts ~ heart 6, Queen of diamonds ~ diamond q");

      while (counter != 45) {

        counter++;

        Card foldedCard = super.validateInput(fold);

        for (Card i : deck) {
          if (deck[i] == foldedCard){
            deck.remove(i);
            break;
          }
        }

        System.out.println("Do you want to fold more cards? (y/n)");
        String endFold = fold.nextLine();
        if (endFold.toLowerCase() == 'n'){
          break;
        }
      }

    }
  }

}
