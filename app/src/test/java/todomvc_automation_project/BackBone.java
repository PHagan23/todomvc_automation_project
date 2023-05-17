package todomvc_automation_project;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BackBone {
    protected WebDriver driver;
    private By toDoListEntryBy = By.className("new-todo");

    public BackBone(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://todomvc.com/examples/backbone");
    }

    public void clickToDoList(){
        WebElement entryBox = driver.findElement(toDoListEntryBy);
        entryBox.click();
    }

    public void addToList(String singleValue){
        WebElement entryBox = driver.findElement(toDoListEntryBy);
        entryBox.sendKeys(singleValue, Keys.ENTER);
    }
}
