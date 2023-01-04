package page_locator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class edit_infoPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='ml-2 has-text-white']")
    private WebElement naviga;

    @FindBy(xpath = "//input[@placeholder='Nhập họ (*)']")
    private WebElement firstname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập tên (*)']")
    private WebElement lastname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập số điện thoại (*)']")
    private WebElement phone_input;

    @FindBy(xpath = "//input[@placeholder='Nhập mật khẩu cũ']")
    private WebElement pass_old_input;

    @FindBy(xpath = "//input[@placeholder='Nhập mật khẩu mới']")
    private WebElement pass_new_input;

    @FindBy(xpath = "//span[contains(text(),'Cập nhật')]")
    private WebElement save;

    public edit_infoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage get = new SignInPage(driver);

    public void navigation() {
        try {
            if (naviga.isDisplayed()) {
                naviga.click();
                get.waitForPageLoaded();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean verify_title() {
        String a = "Thông tin tài khoản";
        return getTitle().equals(a);
    }

    public void change_info(String firstname, String lastname, String phone, String passold, String passnew) {
        firstname_input.sendKeys(firstname);
        lastname_input.sendKeys(lastname);
        phone_input.sendKeys(phone);
        pass_old_input.sendKeys(passold);
        pass_new_input.sendKeys(passnew);
        save.click();
    }

    public void clearTXT() {
        try {
            firstname_input.clear();
            lastname_input.clear();
            phone_input.clear();
            pass_old_input.clear();
            pass_new_input.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void print() {
        System.out.println("Status: PASSED");
        System.out.println("=========================");
        clearTXT();
    }

}
