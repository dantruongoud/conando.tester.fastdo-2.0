package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.AWTException;

public class deleteUserPage {
    public WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='Email, họ và tên khách hàng']")
    private WebElement searchInput;

    @FindBy(css = "a[title='Xóa']")
    public WebElement delete_btn;

    public deleteUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void reseach(String search) {
        try {
            if (searchInput.isDisplayed()) {
                searchInput.sendKeys(search);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void search_result(String search) {
        try {
            reseach(search);
            Thread.sleep(1000);
            Robot rb = null;
            try {
                rb = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
