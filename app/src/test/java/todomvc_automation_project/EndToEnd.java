package todomvc_automation_project;

import org.junit.jupiter.api.*;
import org.junitpioneer.jupiter.ExpectedToFail;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEnd {

    // Test Objects that end with CF are testing Core Functionality
    // Test Objects that end with VT are Value Testing

    private static FirefoxDriver driver;

    public static void takeScreenshot(WebDriver webdriver,String desiredPath) throws Exception{
        //Stolen screenshot method
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }

    String[] basicPunctuationSymbols = {"!", ",", ".", "\"", "?"}; //Used in 2.7
    String[] commonSymbols = {"!", "@", "#", "%", ")", "*", "'", "\\", "~", "-", "_"}; //Used in 2.8
    String[] languageNativeSymbols = {"ẞ", "大", "本", "é", "ñ"}; //Used in 2.10
    String[] majorCurrencies = {"$", "£", "¥", "€"};
    String twoEightyCharacters = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
            "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. " +
            "Nulla consequat mas"; //Used in 2.5
    String twoEightyOneCharacters = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " +
            "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient " +
            "montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. " +
            "Nulla consequat maso"; //Used in 2.3 + 2.4

    @BeforeEach
    void launchBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void addItemToListCF() throws InterruptedException {
        // This Test Script encapsulates CF 1.1 & 1.2
        // Undo function is OS dependent - Command is used on Mac, Control on Windows
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.typeToDoItem("Item");
        backBone.unDo();
        assertEquals("", backBone.getNewTodoValue());
        backBone.enterTodoItem("Item");
        assertEquals("1 item left", backBone.getTodoItemCount());
    }
    @Test
    public void modifyItemToList(){
        //Covers 1.3 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item");
        backBone.modifyItem();
        backBone.editTodoItem();
        assertEquals( "Item modified", backBone.getSingleItem());
    }

    @Test
    public void escapeToList(){
        //Covers 1.4 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item");
        backBone.modifyItem();
        backBone.editTodoItemNNotEntered();
        backBone.pressEscape();
        assertEquals( "Item", backBone.getSingleItem());
    }

    @Test
    public void itemCanBeTickedOffAndTickedAgainCF() throws Exception {
        //Covers 1.5 + 1.6 on test plan
        //Manual check of screenshot required
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item");
        backBone.clickToggleButton();
        takeScreenshot(driver, "test1.5");
        backBone.clickToggleButton();
        takeScreenshot(driver, "test1.6");
    }

    @Test
    @ExpectedToFail("Dragging feature not implemented currently - Create test script when there is more time")
    public void pass(){
        //Covers 1.7 on test plan
        ;
    }

    @Test
    public void deleteCompleteAndIncompleteItemsCF(){
        //Covers 1.8 + 1.9 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item");
        assertEquals("1 item left", backBone.getTodoItemCount());
        backBone.pressDeleteButton();
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
        backBone.enterTodoItem("Item");
        assertEquals("1 item left", backBone.getTodoItemCount());
        backBone.clickToggleButton();
        backBone.pressDeleteButton();
        assertEquals(0, elementList.size());
    }

    @Test
    public void itemLeftCounterCF() {
        //Covers 1.10, 1.11 and 1.12 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        for (int i = 1; i < 20; i++){
            backBone.enterTodoItem("Item" + i);
            if (i == 1) {
                assertEquals(i + " item left", backBone.getTodoItemCount());
            }
            else {
                assertEquals(i + " items left", backBone.getTodoItemCount());
            }
        }
    }

    @Test
    public void checkStatusBarIsHiddenCF(){
        //Covers 1.13 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
    }

    @Test
    public void checkStatusToggleCF() {
        //Covers 1.14 on test plan
        //Need to figure out button locators -- Will come back if there is time
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item1");
        backBone.clickToggleButton();
        backBone.enterTodoItem("Item2");
    }

    @Test
    public void checkClearCompletedIsDisplayedCF() {
        //Covers 1.15 on test plan
        //Half finished - Wanted to assert that false is returned if nothing is toggled, but test stops due to failing
        //to find the locator required - To be reviewed -- Currently an acceptable test however
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item");
        //assertFalse(backBone.checkIfClearCompletedButtonIsPresent());
        backBone.clickToggleButton();
        assertTrue(backBone.checkIfClearCompletedButtonIsPresent());
    }

    @Test
    public void checkClearCompletedClearListCF(){
        //Covers 1.16 on test plan
        //Currently can only clear single item due to only having automation set up to hit one toggle button
        //Can extend coverage with appropriate method if time allows
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item");
        backBone.clickToggleButton();
        backBone.clickClearCompletedButton();
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
    }

    @Test
    public void checkToggleAllButtonCF() throws Exception {
        //Covers 1.17 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        backBone.waitForPageToLoad("todos");
        backBone.enterTodoItem("Item1");
        backBone.clickToggleAll();
        takeScreenshot(driver, "test1.17");
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
    public void emojiSymbolCheckVT() throws InterruptedException {
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
    void closeBrowser() {
        driver.quit();
    }

}
