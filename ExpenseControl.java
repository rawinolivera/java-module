import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class ExpenseControl {
  private List<Category> categories = new ArrayList<>();
  private List<Expense> expenses = new ArrayList<>();

  public void addCategory(Category category) {
    categories.add(category);
  }

  public void addExpense(LocalDate date, String description, int categoryIndex, double amount) {
    Category category = categories.get(categoryIndex - 1);
    Expense expense = new Expense(date, description, category, amount);
    expenses.add(expense);
  }

  public void showCategories() {
    for (int i = 0; i < categories.size(); i++) {
      System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
    }
  }

  public List<Category> getCategories() {
    return categories;
  }

  public List<Expense> getExpenses() {
    return expenses;
  }

  // option 1
  public void recordExpense() {
    Scanner scanner = new Scanner(System.in);

    if (categories.isEmpty()) {
      System.out.println("No categories available. Please add one first.");
      return;
    }

    System.out.println("Date: (YYYY-MM-DD): ");
    String inputDate = scanner.nextLine();
    LocalDate date = LocalDate.parse(inputDate);

    System.out.println("Description:");
    String description = scanner.nextLine();

    System.out.println("Select a category:");
    for (int i = 0; i < categories.size(); i++) {
      System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
    }

    int categoryIndex;
    while (true) {
      System.out.print("Enter category number: ");
      categoryIndex = Integer.parseInt(scanner.nextLine());
      if (categoryIndex > 0 && categoryIndex <= categories.size()) {
        break;
      }
      System.out.println("Invalid category number. Try again.");
    }

    System.out.println("Amount: ");
    double amount = Double.parseDouble(scanner.nextLine());

    Category category = categories.get(categoryIndex - 1);
    Expense expense = new Expense(date, description, category, amount);
    expenses.add(expense);
    category.addExpense(expense);

    System.out.println("Expense registered successfully!");
  }

  // option 2
  public void recordCategory() {
    Scanner scanner = new Scanner(System.in);
    if (categories.isEmpty()) {
      System.out.println("No categories registered yet.");
    } else {
      System.out.println("Current Categories:");
      for (Category category : categories) {
        System.out.printf("- %s%n", category.getName());
      }
    }

    System.out.println("\nEnter a name for the new category");
    String name = scanner.nextLine().trim();

    for (Category category : categories) {
      if (category.getName().equalsIgnoreCase(name)) {
        System.out.printf("Category: %s already exists. Registration cancelled.%n", name);
        return;
      }
    }

    System.out.println("Enter a short description: ");
    String description = scanner.nextLine().trim();

    int id = categories.size() + 1;

    Category newCategory = new Category(id, name, description);
    categories.add(newCategory);

    System.out.printf("Category: %s added successfully!%n", name);
  }

  // option 4
  public void listExpenses() {
    System.out.println("List of expenses:");
    if (expenses.isEmpty()) {
      System.out.println("No expenses found!");
    } else {
      for (Expense expense : expenses) {
        System.out.printf(
            " - %s | %s | %s | $%.2f%n",
            expense.getDate(),
            expense.getCategory().getName(),
            expense.getDescription(),
            expense.getAmount());
      }

    }
  }

}