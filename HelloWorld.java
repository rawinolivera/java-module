import java.util.Scanner;
import java.io.IOException;

public class HelloWorld {
  public static void main(String[] args) throws IOException {
    System.out.println("Welcome to the Register Program:");

    ExpenseControl expensecontrol = new ExpenseControl();
    expensecontrol.importData("data.csv");
    Scanner input = new Scanner(System.in);
    int menu = 0;

    while (menu != 7) {
      System.out.println("Menu: ");
      System.out.println("1- Add expense");
      System.out.println("2- Add a new category");
      System.out.println("3- See all categories");
      System.out.println("4- See all expenses");
      System.out.println("5- View expenses by category");
      System.out.println("6- Category Stats");
      System.out.println("7- Exit the program");

      System.out.print("Enter an option: ");
      menu = input.nextInt();
      input.nextLine();

      switch (menu) {
        case 1:
          System.out.println();
          expensecontrol.recordExpense();
          System.out.println();
          System.out.print("Press any key to continue...");
          System.in.read();
          System.out.println();
          break;

        case 2:
          System.out.println();
          expensecontrol.recordCategory();
          System.out.println();
          System.out.print("Press any key to continue...");
          System.in.read();
          System.out.println();
          break;

        case 3:
          System.out.println();
          expensecontrol.listCategory();
          System.out.println();
          System.out.print("Press any key to continue...");
          System.in.read();
          System.out.println();
          break;

        case 4:
          System.out.println();
          expensecontrol.listExpenses();
          System.out.println();
          System.out.print("Press any key to continue...");
          System.in.read();
          System.out.println();
          break;

        case 5:
          System.out.println();
          expensecontrol.totalByCategory();
          System.out.println();
          System.out.print("Press any key to continue...");
          System.in.read();
          System.out.println();
          break;

        case 6:
          System.out.println();
          expensecontrol.categoryTotals();
          System.out.println();
          System.out.print("Press any key to continue...");
          System.in.read();
          System.out.println();
          break;

        case 7:
          System.out.println("call functinos -> close the program");
          expensecontrol.exportData("data.csv");
          System.out.println("Session Closed!");
          break;

        default:
          System.out.println("call functinos -> Wrong option, try again!");
      }
    }
    input.close();
  }
}