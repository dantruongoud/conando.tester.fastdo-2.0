package page_locator.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;

public class cashoutPage {

    private WebDriver driver;

    @FindBy(css = ".icon-text.has-text-link")
    public WebElement cashoutbtn;

    @FindBy(css = "a[href='/admin/transactions/cashout']")
    public WebElement naviga_cashout;

    @FindBy(xpath = "//input[@placeholder='Nhập số tiền rút']")
    private WebElement amount_input;

    @FindBy(xpath = "//input[@placeholder='Nhập ghi chú']")
    private WebElement note_input;

    public cashoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage using = new SignInPage(driver);

    public String gettitle() {
        return driver.getTitle();
    }

    public boolean verifyTitle() {
        String a = "Hệ thống rút tiền";
        return gettitle().equals(a);
    }

    public void system_cashout(String amount, String note) {
        amount_input.sendKeys(amount);
        note_input.sendKeys(note);
    }
}
