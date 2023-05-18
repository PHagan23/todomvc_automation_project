package todomvc_automation_project;

import org.junit.jupiter.api.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEnd {

    // Test Objects that end with CF are testing Core Functionality
    // Test Objects that end with VT are Value Testing

    private static FirefoxDriver driver;

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
        Thread.sleep(3000);
        backBone.typeToDoItem("Item");
        Thread.sleep(3000);
        backBone.unDo();
        Thread.sleep(2000);
        assertEquals("", backBone.getNewTodoValue());
        backBone.enterTodoItem("Item");
        Thread.sleep(1000);
        assertEquals("1 item left", backBone.getTodoItemCount());
    }

    @Test
    public void removeItemToList() throws InterruptedException {
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        Thread.sleep(3000);
        backBone.enterTodoItem("Item");
        Thread.sleep(3000);
        backBone.clickToggleButton();
    }
    @Test
    public void modifyItemToList() throws InterruptedException {
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        Thread.sleep(3000);
        backBone.enterTodoItem("Item");
        Thread.sleep(3000);
        backBone.modifyItem();
        Thread.sleep(3000);
        backBone.editTodoItem();
        Thread.sleep(3000);
        assertEquals( "Item modified", backBone.getSingleItem());
    }

    @Test
    public void escapeToList() throws InterruptedException {
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        Thread.sleep(3000);
        backBone.enterTodoItem("Item");
        Thread.sleep(3000);
        backBone.modifyItem();
        Thread.sleep(3000);
        backBone.editTodoItemNNotEntered();
        Thread.sleep(3000);
        backBone.pressEscape();
        Thread.sleep((3000));
        assertEquals( "Item", backBone.getSingleItem());
    }


    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

}
