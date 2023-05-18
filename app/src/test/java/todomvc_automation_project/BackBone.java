package todomvc_automation_project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BackBone {
    protected WebDriver driver;
    private By todoListEntryBy = By.className("new-todo");
    private By todoListItemCount = By.className("todo-count");
    private By todoListItemToggleButton = By.className("toggle");
    private By todoItemSingle = By.className("todo-list");

    public BackBone(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/backbone");
    }

    public void typeToDoItem(String singleValue){
        WebElement entryBox = driver.findElement(todoListEntryBy);
        entryBox.sendKeys(singleValue);
    }

    public void enterTodoItem(String singleValue){
        WebElement entryBox = driver.findElement(todoListEntryBy);
        entryBox.sendKeys(singleValue, Keys.ENTER);
    }

    public void unDo(){
        // Undo function is OS dependent - Command is used on Mac, Control on Windows
        // Could be expanded to check users OS and then use conditional flow to choose key press
        WebElement entryBox = driver.findElement(todoListEntryBy);
        String s = Keys.chord(Keys.COMMAND, "z");
        entryBox.sendKeys(s);
    }

    public String getTodoItemCount() {
        return driver.findElement(todoListItemCount).getText();
    }

    public String getNewTodoValue(){
        return driver.findElement(todoListEntryBy).getText();
    }

    public void clickToggleButton() {
        WebElement toggleButton = driver.findElement(todoListItemToggleButton);
        toggleButton.click();
    }
    public void modifyItem(){
        WebElement todoList = driver.findElement(todoItemSingle);
        Actions action = new Actions(driver);
        action.doubleClick(todoList).build().perform();
    }

    public void clickNewTodoSection(){
        WebElement todoSection = driver.findElement(todoListEntryBy);
        todoSection.click();
    }

}
