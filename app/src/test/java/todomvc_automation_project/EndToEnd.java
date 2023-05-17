package todomvc_automation_project;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EndToEnd {

    private static FirefoxDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new FirefoxDriver();
    }


    @Test
    public void test() throws InterruptedException {
        BackBone backBone = new BackBone(driver);
        backBone.navigate();
        Thread.sleep(3000);
        backBone.addToList("Test");
        Thread.sleep(3000);
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

}
