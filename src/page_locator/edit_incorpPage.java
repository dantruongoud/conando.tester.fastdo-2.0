package page_locator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class edit_incorpPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Tổ chức')]")
    private WebElement naviga_incorp;

    @FindBy(linkText = "Do Corp")
    public WebElement find;

    @FindBy(xpath = "/html/body/main/section/section[2]/div/div/div/div[4]/a/i")
    public WebElement edit;

    @FindBy(xpath = "//input[@placeholder='Tên tổ chức (*)']")
    private WebElement name_incorp;

    @FindBy(xpath = "//input[@placeholder='Địa chỉ (*)']")
    private WebElement address_incorp;

    @FindBy(xpath = "//input[@placeholder='Số điện thoại người đại diện (*)']")
    private WebElement phone_incorp;

    @FindBy(xpath = "//input[@placeholder='Địa chỉ email tổ chức (*)']")
    private WebElement mail_incorp;

    @FindBy(xpath = "//span[contains(text(),'Cập nhật')]")
    private WebElement save;

    @FindBy(xpath = "//section[@class='modal is-active']")
    private WebElement popup;

    public edit_incorpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage get = new SignInPage(driver);

    public void clickNavigation() {
        try {
            if (naviga_incorp.isDisplayed()) {
                naviga_incorp.click();
                get.waitForPageLoaded();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean verify_popup() {
        return popup.isDisplayed();
    }

    public void create_incorp(String Name_incorp,
            String Address_incorp, String Phone_incorp, String Mail_incorp) {
        name_incorp.sendKeys(Name_incorp);
        address_incorp.sendKeys(Address_incorp);
        phone_incorp.sendKeys(Phone_incorp);
        mail_incorp.sendKeys(Mail_incorp);
        save.click();
    }

    public void clearTXT() {
        try {
            name_incorp.clear();
            address_incorp.clear();
            phone_incorp.clear();
            mail_incorp.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("Status: Passed");
        System.out.println("=========================");
        clearTXT();
    }
}
