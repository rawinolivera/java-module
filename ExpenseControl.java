import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    category.addExpense(expense);
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

  // option 3
  public void listCategory() {
    for (int i = 0; i < categories.size(); i++) {
      System.out.printf("%d. %s%n", i + 1, categories.get(i).getName());
    }
  }

  // option 1
  public void recordExpense() {
    Scanner scanner = new Scanner(System.in);

    if (categories.isEmpty()) {
      System.out.println("No categories available. Please add one first.");
      return;
    }

    System.out.print("Date: (YYYY-MM-DD): ");
    String inputDate = scanner.nextLine();
    LocalDate date = LocalDate.parse(inputDate);

    System.out.print("Description: ");
    String description = scanner.nextLine();

    System.out.println("Select a category:");
    listCategory();

    int categoryIndex;
    while (true) {
      System.out.print("Enter category number: ");
      categoryIndex = Integer.parseInt(scanner.nextLine());
      if (categoryIndex > 0 && categoryIndex <= categories.size()) {
        break;
      }
      System.out.println("Invalid category number. Try again.");
    }

    System.out.print("Amount: ");
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
      System.out.printf("%-10s | %-14s | %-30s | %10s\n",
          "Date", "Category", "Description", "Amount");
      System.out.println("---------------------------------------------------------------------------");
      for (Expense expense : expenses) {
        System.out.printf(
            "%-10s | %-14s | %-30s | $%10.2f\n",
            expense.getDate(),
            expense.getCategory().getName(),
            expense.getDescription(),
            expense.getAmount());
      }
    }
  }

  // support
  public Category getCategoryById(int id) {
    for (Category category : categories) {
      if (category.getId() == id)
        return category;
    }
    return null;
  }

  // option 7
  public void exportData(String filePath) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      writer.write("[CATEGORIES]");
      writer.newLine();
      writer.write("Id,Name,Description");
      writer.newLine();

      for (Category category : categories) {
        writer.write(category.getId() + "," + category.getName() + "," + category.getDescription());
        writer.newLine();
      }

      writer.newLine();

      writer.write("[EXPENSES]");
      writer.newLine();
      writer.write("Date,Description,Category,Amount");
      writer.newLine();

      for (Expense expense : expenses) {
        writer.write(expense.getDate() + "," + expense.getDescription() + "," + expense.getCategory().getId() + ","
            + expense.getAmount());
        writer.newLine();
      }
      System.out.println("Data Exported");
    }
  }

  // init
  public void importData(String filePath) throws IOException {
    try (Scanner scanner = new Scanner(new File(filePath))) {
      String section = "";
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();

        if (line.equals("[CATEGORIES]")) {
          section = "categories";
          scanner.nextLine();
          continue;
        }

        if (line.equals("[EXPENSES]")) {
          section = "expenses";
          scanner.nextLine();
          continue;
        }

        if (section.isEmpty())
          continue;
        if (line.trim().isEmpty())
          continue;

        String[] data = line.split(",");

        if (section.equals("categories")) {
          int id = Integer.parseInt(data[0]);
          String name = data[1];
          String description = data[2];
          categories.add(new Category(id, name, description));
        }

        if (section.equals("expenses")) {
          LocalDate date = LocalDate.parse(data[0]);
          String description = data[1];
          int categoryId = Integer.parseInt(data[2]);
          Category category = getCategoryById(categoryId);
          double amount = Double.parseDouble(data[3]);
          expenses.add(new Expense(date, description, category, amount));
        }

      }
    }
  }

  // option 5
  public void totalByCategory() {
    Scanner scanner = new Scanner(System.in);
    if (categories.isEmpty()) {
      System.out.println("No categories available. Please add one first.");
      return;
    }

    listCategory();
    int categoryIndex;
    while (true) {
      System.out.print("Enter category number: ");
      try {
        categoryIndex = Integer.parseInt(scanner.nextLine());
        if (categoryIndex > 0 && categoryIndex <= categories.size()) {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid category number. Try again.");
      }
    }

    Category selectedCategory = categories.get(categoryIndex - 1);

    double total = 0;
    boolean found = false;
    System.out.println();
    System.out.printf("Expenses for category: " + selectedCategory.getName());
    System.out.println();

    for (Expense expense : expenses) {
      if (expense.getCategory().equals(selectedCategory)) {
        found = true;
        System.out.printf(" - %s | %s | $%.2f%n",
            expense.getDate(),
            expense.getDescription(),
            expense.getAmount());
        total += expense.getAmount();
      }
    }
    if (found) {
      System.out.printf("Total: %s", total);
    } else {
      System.out.printf("No expenses found for category: %s", selectedCategory.getName());
    }
  }

  // option 6
  public void categoryTotals() {
    if (categories.isEmpty()) {
      System.out.println("No categories available.");
      return;
    }
    double grandTotal = categories.stream().mapToDouble(Category::getTotal).sum();

    if (grandTotal == 0) {
      System.out.println("No expenses recorded.");
      return;
    }

    System.out.println("============== CATEGORY TOTALS =============");
    System.out.println("ID | Category       |      Total | % of Total");
    System.out.println("--------------------------------------------");

    for (Category category : categories) {
      double total = category.getTotal();
      double percentage = (total * 100) / grandTotal;

      System.out.printf("%2d | %-14s | $%8.2f | %6.2f %% %n",
          category.getId(),
          category.getName(),
          total,
          percentage);
    }

    System.out.println("---------------------------------------------");
    System.out.printf("Gerenal Total: $%.2f%n", grandTotal);
    System.out.println("=============================================");

  }

}