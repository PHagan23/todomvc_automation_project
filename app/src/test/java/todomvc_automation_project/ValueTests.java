package todomvc_automation_project;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ValueTests {

    private static FirefoxDriver driver;

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
        Thread.sleep(3000);
        List<WebElement> elementList = driver.findElements(By.className("view"));
        assertEquals(0, elementList.size());
    }

    @Test
    public void addMultiWordStringVT() throws InterruptedException {
        //covers 2.2 on test plan
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        Thread.sleep(1000);
        backBone.enterTodoItem("Multi Word String");
        Thread.sleep(1000);
        assertEquals("1 item left", backBone.getTodoItemCount());
    }

    @Test
    public void pass(){

    }

    @AfterEach
    void closeBrowser(){
        driver.quit();
    }
}
