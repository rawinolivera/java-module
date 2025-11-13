import java.util.List;
import java.util.ArrayList;


public class Category {
  private int id;
  private String name;
  private String description;
  private List<Expense> expenses = new ArrayList<>();

  public Category(int id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public void addExpense(Expense expense) {
    expenses.add(expense);
  }

  public Double getTotal() {
    return expenses.stream().mapToDouble(Expense::getAmount).sum();
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public void addCategory() {

  }
}
