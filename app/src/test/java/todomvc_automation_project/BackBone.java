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
    private By clearCompletedButton = By.className("clear-completed");
    private By toggleAllButton = By.xpath("/html/body/section/section/label");

    public BackBone(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/backbone");
    }
    public void waitForPageToLoad(String titleOfPage){
        //Used to wait for the page to load by checking if the 'todos' heading is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(pageHeading, titleOfPage));
    }

    public String getTodoItemCount() {
        //Returns the text from the bottom left of the To do list if there is any item within the To do list
        //Example '1 item left'
        return driver.findElement(todoListItemCount).getText();
    }

    public String getNewTodoValue(){
        //Returns the current entry in the New To do List box
        //Used in conjunction with undo method
        return driver.findElement(todoListEntryBy).getText();
    }

    public String getSingleItem(){
        //Returns the value of the item within the To do list providing it's the only one
        //Haven't tested a method for multiple values within the to do list as of now
        return driver.findElement(todoListSingleItem).getText();
    }

    public boolean checkIfClearCompletedButtonIsPresent(){
        WebElement clearCompletedButt = driver.findElement(clearCompletedButton);
        return clearCompletedButt.isDisplayed();
    }

    public void clickNewTodoSection() {
        //Clicks on the New To do Item box
        WebElement todoSection = driver.findElement(todoListEntryBy);
        todoSection.click();
    }

        public void typeToDoItem(String singleValue){
        //Types string into the New To do Item box
        WebElement entryBox = driver.findElement(todoListEntryBy);
        entryBox.sendKeys(singleValue);
    }

    public void enterTodoItem(String singleValue){
        //Types string and enters it into the New To do Item box
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
        //Copies whatever is written in the New To do entry box, clears it and re-pastes it
        //Not too reusable - made for a couple of specific tests for character limits on pasting
        WebElement entryBox = driver.findElement(todoListEntryBy);
        String a = Keys.chord(Keys.COMMAND, "a");
        String c = Keys.chord(Keys.COMMAND, "c");
        String v = Keys.chord(Keys.COMMAND, "v");
        entryBox.sendKeys(a, c);
        entryBox.clear();
        entryBox.sendKeys(v);

    }
    public void clickToggleButton() {
        //Clicks the toggle button on the left hand side of a task that shows it's been completed
        WebElement toggleButton = driver.findElement(todoListItemToggleButton);
        toggleButton.click();
    }
    public void clickToggleAll(){
        WebElement toggleAllButt = driver.findElement(toggleAllButton);
        toggleAllButt.click();
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
    public void waitForTodoListEntry(){
        //Waits for an entry into the to do list I think -- Im not sure this actually does anything...
        //To be reviewed
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(todoListView));
    }

    public void modifyItem(){
        //Double-clicks a single item on the list -- unsure how this works with multiple items in the list currently
        WebElement todoList = driver.findElement(todoItemSingle);
        Actions action = new Actions(driver);
        action.doubleClick(todoList).build().perform();
    }

    public void editTodoItem(){
        //Adds ' modified' to the end of a single item and then enters it to confirm the change
        WebElement editItem = driver.findElement(editItemBy);
        editItem.sendKeys(" modified", Keys.ENTER);
    }
    public void editTodoItemNNotEntered(){
        //Adds ' modified' to the end of a single item but doesn't enter it
        //Used for a specific test on cancelling an edit
        WebElement editItem = driver.findElement(editItemBy);
        editItem.sendKeys(" modified");
    }
    public void clickClearCompletedButton(){
        WebElement clearCompletedButt = driver.findElement(clearCompletedButton);
        clearCompletedButt.click();
    }
    public void pressEscape(){
        //Presses escape...
        WebElement editItem = driver.findElement(editItemBy);
        editItem.sendKeys(Keys.ESCAPE );
    }
    public void pressSmileyEmoji(){
        //Unused currently -- needs worked on
        WebElement entryBox = driver.findElement(todoListEntryBy);
        String emojiBox = Keys.chord(Keys.COMMAND, Keys.SPACE);
        entryBox.sendKeys(emojiBox);
    }
}
