package page;

import org.openqa.selenium.By;

public class CostPage {
    public By addCostButton = By.cssSelector(".add-cost-button");
    public By itemName = By.id("itemName");
    public By amount = By.id("amount");
    public By purchaseDate = By.id("purchaseDate");
    public By monthDropdown = By.id("month");
    public By remarks = By.id("remarks");
    public By submitButton = By.cssSelector(".submit-button"); // ✅ New line
}
