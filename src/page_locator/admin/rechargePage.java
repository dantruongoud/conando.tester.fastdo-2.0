package page_locator.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class rechargePage {

    private WebDriver driver;

    @FindBy(css = ".icon-text.has-text-link")
    public WebElement recharge_btn;

    @FindBy(css = "a[class='button is-link']")
    public WebElement confirm_btn;

    @FindBy(xpath = "//input[@placeholder='Email, họ và tên khách hàng']")
    private WebElement search_input;

    @FindBy(tagName = "lable")
    private WebElement label_validation;

    @FindBy(xpath = "//input[@placeholder='Nhập số tiền nạp']")
    private WebElement amount_input;

    @FindBy(xpath = "//input[@placeholder='Nhập ghi chú']")
    private WebElement note_input;

    @FindBy(xpath = "//span[contains(text(),'Nạp tiền')]")
    public WebElement recharge_naviga;

    @FindBy(xpath = "//tbody/tr[1]/td[1]/a[1]/i[1]")
    public WebElement tr;

    public rechargePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage using = new SignInPage(driver);

    public String gettitle() {
        return driver.getTitle();
    }

    public boolean verify_title() {
        String a = "Hệ thống nạp tiền";
        return gettitle().equals(a);
    }

    public void research(String search) {
        try {
            if (search_input.isDisplayed()) {
                search_input.sendKeys(search);
                Thread.sleep(1200);
                Robot rb = null;

                rb = new Robot();

                rb.keyPress(KeyEvent.VK_ENTER);
                rb.keyRelease(KeyEvent.VK_ENTER);
                Thread.sleep(2000);
                driver.findElement(By.xpath("//section[@class='modal is-active']//th[2]")).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String validation() {
        String text = "";
        if (label_validation.isDisplayed()) {
            text = label_validation.getText().strip();
            System.out.println("Notify: " + text);
        }
        return text;
    }

    public void system_recharge(String amount, String note) {
        amount_input.sendKeys(amount);
        note_input.sendKeys(note);
        confirm_btn.click();
    }
}