package todomvc_automation_project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BackBone {
    protected WebDriver driver;
    private By todoListEntryBy = By.className("new-todo");
    private By todoListItemCount = By.className("todo-count");
    private By todoListItemToggleButton = By.className("toggle");

    public BackBone(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/backbone");
    }

    public void clickToDoList(){
        WebElement entryBox = driver.findElement(todoListEntryBy);
        entryBox.click();
    }

    public void enterTodoItem(String singleValue){
        WebElement entryBox = driver.findElement(todoListEntryBy);
        entryBox.sendKeys(singleValue, Keys.ENTER);
    }

    public String getTodoItemCount() {
        return driver.findElement(todoListItemCount).getText();
    }

    public void clickToggleButton() {
        WebElement toggleButton = driver.findElement(todoListItemToggleButton);
        toggleButton.click();
    }
}
