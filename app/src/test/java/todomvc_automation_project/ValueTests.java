package todomvc_automation_project;

import org.junit.jupiter.api.*;
import org.junitpioneer.jupiter.ExpectedToFail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ValueTests {

    private static FirefoxDriver driver;

    String[] basicPunctuationSymbols = {"!", ",", ".", "\"", "?"}; //Used in 2.7
    String[] commonSymbols = {"!", "@", "#", "%", ")", "*", "'", "\\", "~", "-", "_"}; //Used in 2.8
    String[] languageNativeSymbols = {"ẞ", "大", "本", "é", "ñ"}; //Used in 2.10
    String[] majorCurrencies = {"$", "£", "¥", "€"};
    String twoEightyCharacters = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
            "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. " +
            "Nulla consequat mas";
    String twoEightyOneCharacters = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
            "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. " +
            "Nulla consequat maso";


    @BeforeEach
    void launchBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void addEmptyItemVT() throws InterruptedException {
        // covers 2.1 on test plan
        // 'view' class only created when an item is added to the todolist
        // mutation check by adding a value to .enterTodoItem method - test will fail
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.clickNewTodoSection();
        backBone.enterTodoItem("");
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
    }

    @Test
    public void addMultiWordStringVT() throws InterruptedException {
        //covers 2.2 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Multi Word String");
        assertEquals("1 item left", backBone.getTodoItemCount());
    }

    @Test
    @ExpectedToFail("Character Limit is not implemented on ToDo List entries")
    public void characterLimitVT(){
        //covers 2.3 on test plan
        //Not an implemented feature
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem(twoEightyOneCharacters);
        backBone.waitForTodoListEntry();
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
    }

    @Test
    @ExpectedToFail("Character Limit is not implemented on ToDo List entries")
    public void oneOverCharacterPasteLimitVT(){
        //covers 2.4 on test plan
        //this test works on the basis that you are unable to enter a blank value onto the to do list
        //the value shouldn't be able to paste, therefore you can't enter a blank value
        //the test asserts that the view section ( which only appears when items are in the list) is 0
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.typeToDoItem(twoEightyOneCharacters);
        backBone.copyAndPasteToDoItem();
        backBone.enterTodoItem("");
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
    }

    @Test
    public void characterPasteLimitVT(){
        //covers 2.5 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.typeToDoItem(twoEightyCharacters);
        backBone.copyAndPasteToDoItem();
        backBone.enterTodoItem("");
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(1, elementList.size());
    }

    @Test
    public void basicPunctuationCheckVT(){
        //covers 2.7 on test plan by checking the following punctuation individually - !,."?
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        for (int i = 0; i < basicPunctuationSymbols.length; i++){
            backBone.enterTodoItem(basicPunctuationSymbols[i]);
            assertEquals(basicPunctuationSymbols[i], backBone.getSingleItem());
            backBone.pressDeleteButton();
        }
    }

    @Test
    public void symbolCheckVT(){
        //covers 2.8 on test plan by checking the following symbols individually !@#%)*'\~-_
        //potential to refactor into for loop?
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        for (int i = 0; i < commonSymbols.length; i++){
            backBone.enterTodoItem(commonSymbols[i]);
            assertEquals(commonSymbols[i], backBone.getSingleItem());
            backBone.pressDeleteButton();
        }
    }

    @Test
    @Disabled
    public void emojiSymbolCheck() throws InterruptedException {
        //covers 2.9 on test plan, only checking one as I think this is a silly test but I get paid to automate
        //can't get this to work currently - low priority, will look at if there is time
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.pressSmileyEmoji();
        Thread.sleep(10000);

    }

    @Test
    public void languageNativeSymbolCheckVT(){
        //covers 2.10 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        for (int i = 0; i < languageNativeSymbols.length; i++){
            backBone.enterTodoItem(languageNativeSymbols[i]);
            assertEquals(languageNativeSymbols[i], backBone.getSingleItem());
            backBone.pressDeleteButton();
        }
    }

    @Test
    public void majorCurrenciesCheckVT(){
        //covers 2.11 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        for (int i = 0; i < majorCurrencies.length; i++) {
            backBone.enterTodoItem(majorCurrencies[i]);
            assertEquals(majorCurrencies[i], backBone.getSingleItem());
            backBone.pressDeleteButton();
        }
    }

    @AfterEach
    void closeBrowser(){
        driver.quit();
    }
}
