package todomvc_automation_project;

import org.junit.jupiter.api.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

public class EndToEnd {

    private static FirefoxDriver driver;

    @BeforeEach
    void launchBrowser() {
        driver = new FirefoxDriver();
    }


    @Test
    public void addItemToList() throws InterruptedException {
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        Thread.sleep(3000);
        backBone.enterTodoItem("Item");
        Thread.sleep(3000);
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

    @AfterEach
    void closeBrowser() {
        driver.quit();
    }

}
