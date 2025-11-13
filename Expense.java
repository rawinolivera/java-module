import java.time.LocalDate;
import java.util.Scanner;

public class Expense {
  private LocalDate date;
  private String description;
  private Category category;
  private double amount;

  private static final Scanner scanner = new Scanner(System.in);

  public Expense(LocalDate date, String description, Category category, double amount) {
    this.date = date;
    this.description = description;
    this.category = category;
    this.amount = amount;
    category.addExpense(this);
  }

  public double getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }

  public Category getCategory() {
    return category;
  }

}
