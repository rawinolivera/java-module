import java.util.Scanner;

public class HelloWorld {
  public static void main(String[] args) {
    System.out.println("Welcome to the Register Program:");

    ExpenseControl expensecontrol = new ExpenseControl();
    Scanner input = new Scanner(System.in);
    int menu = 0;

    while (menu != 7) {
      System.out.println("Menu: ");
      System.out.println("1- Add expense");
      System.out.println("2- Add a new category");
      System.out.println("3- Reload Data");
      System.out.println("4- See all expenses");
      System.out.println("5- View expenses by category or period");
      System.out.println("6- Save Data");
      System.out.println("7- Exit the program");

      System.out.println("Enter an option: ");
      menu = input.nextInt();

      switch (menu) {
        case 1:
          System.out.println("call functinos -> Add expense");
          expensecontrol.recordExpense();
          break;

        case 2:
          System.out.println("call functinos -> Add new category");
          expensecontrol.recordCategory();
          break;

        case 3:
          System.out.println("call functinos -> reload data csv file");
          break;

        case 4:
          System.out.println("call functinos -> see all expenses");
          expensecontrol.listExpenses();
          break;

        case 5:
          System.out.println("call functinos -> view expenses from category or period");
          break;

        case 6:
          System.out.println("call functinos -> safe file");
          break;

        case 7:
          System.out.println("call functinos -> close the program");

        default:
          System.out.println("call functinos -> Wrong option, try again!");
      }
    }
    input.close();
  }
}