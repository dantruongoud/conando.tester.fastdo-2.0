package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;
import page_locator.rechargePage;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.KeyEvent;

public class admin_requestAmountPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='ml-2'][contains(text(),'Quản lý giao dịch')]")
    public WebElement crud_request;

    @FindBy(xpath = "//input[@placeholder='Email, họ và tên KH, mã giao dịch...']")
    private WebElement search;

    @FindBy(xpath = "//span[contains(text(),'Duyệt')]")
    public WebElement accecpt;

    SignInPage get = new SignInPage(driver);

    public admin_requestAmountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        String titlePage = driver.getTitle();
        return titlePage;
    }

    public boolean verify_title() {
        String PageTitle = "Yêu cầu nạp tiền";
        return getTitle().equals(PageTitle);
    }

    public void find_id() {
        try {
            rechargePage get = new rechargePage(driver);
            String id = get.get_IDrecharge();
            if (search.isDisplayed()) {
                search.sendKeys(id);
                // Khởi tạo Robot class
                Robot rb = null;
                try {
                    rb = new Robot();
                } catch (AWTException e) {
                    e.printStackTrace();
                }
                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
