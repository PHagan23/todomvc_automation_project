package todomvc_automation_project;

import org.bouncycastle.asn1.x509.UserNotice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BackBone {
    protected WebDriver driver;
    private By todoListEntryBy = By.className("new-todo");
    private By todoListItemCount = By.className("todo-count");
    private By todoListItemToggleButton = By.className("toggle");
    private By todoItemSingle = By.className("todo-list");
    private By pageHeading = By.cssSelector("h1");
    private By todoListView = By.className("view");
    private By editItemBy = By.className("edit");
    private By todoListSingleItem = By.className("view");
    private By deleteButton = By.className("destroy");

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

    public void copyAndPasteToDoItem(){
        WebElement entryBox = driver.findElement(todoListEntryBy);
        String a = Keys.chord(Keys.COMMAND, "a");
        String c = Keys.chord(Keys.COMMAND, "c");
        String v = Keys.chord(Keys.COMMAND, "v");
        entryBox.sendKeys(a, c);
        entryBox.clear();
        entryBox.sendKeys(v);

    }

    public String getTodoItemCount() {
        return driver.findElement(todoListItemCount).getText();
    }

    public String getNewTodoValue(){
        return driver.findElement(todoListEntryBy).getText();
    }

    public String getSingleItem(){
        return driver.findElement(todoListSingleItem).getText();
    }

    public void clickToggleButton() {
        WebElement toggleButton = driver.findElement(todoListItemToggleButton);
        toggleButton.click();
    }


    public void clickNewTodoSection(){
        WebElement todoSection = driver.findElement(todoListEntryBy);
        todoSection.click();
    }

    public void waitForPageToLoad(String titleOfPage){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(pageHeading, titleOfPage));
    }

    public void waitForTodoListEntry(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(todoListView));
    }

    public void modifyItem(){
        WebElement todoList = driver.findElement(todoItemSingle);
        Actions action = new Actions(driver);
        action.doubleClick(todoList).build().perform();
    }

    public void editTodoItem(){
        WebElement editItem = driver.findElement(editItemBy);
        editItem.sendKeys(" modified");
    }
    public void pressEscape(){
        WebElement editItem = driver.findElement(editItemBy);
        editItem.sendKeys(Keys.ESCAPE );
    }

    public void pressDeleteButton(){
        //Delete button only visible on item when mouse is hovered over item
        //Move to Element moves the mouse over the single item -- Emulates user action
        WebElement deleteButt = driver.findElement(deleteButton);
        WebElement todoListViewer = driver.findElement(todoListView);
        Actions action = new Actions(driver);
        action.moveToElement(todoListViewer).perform();
        deleteButt.click();
    }
}
