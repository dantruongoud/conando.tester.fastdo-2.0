package page_locator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class incorpPage {

    public String[] taglinetext = {
            "Bạn chưa nhập tên tổ chức.",
            "Bạn chưa nhập địa chỉ tổ chức.",
            "Bạn chưa nhập Số điện thoại người đại diện.",
            "Bạn chưa nhập email tổ chức."
    };

    WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Tổ chức')]")
    private WebElement naviga_incorp;

    @FindBy(xpath = "//span[@class='is-size-7 has-text-weight-medium']")
    public WebElement add_incorp;

    @FindBy(css = "a[class='button is-link']")
    private WebElement contineu;

    @FindBy(xpath = "//input[@placeholder='Tên tổ chức (*)']")
    private WebElement name_incorp;

    @FindBy(xpath = "//input[@placeholder='Địa chỉ (*)']")
    private WebElement address_incorp;

    @FindBy(xpath = "//input[@placeholder='Số điện thoại người đại diện (*)']")
    private WebElement phone_incorp;

    @FindBy(xpath = "//input[@placeholder='Địa chỉ email tổ chức (*)']")
    private WebElement mail_incorp;

    @FindBy(xpath = "//section[@class='modal-card-head is-success']")
    private WebElement popup;

    private By tagline = By.id("notify");

    public incorpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage index = new SignInPage(driver);

    public void clickNavigation() {
        try {
            if (naviga_incorp.isDisplayed()) {
                naviga_incorp.click();
                index.waitForPageLoaded();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void veryfi_modal() {
        try {
            if (popup.isDisplayed()) {
                contineu.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_incorp(String Name_incorp,
            String Address_incorp, String Phone_incorp, String Mail_incorp) {
        name_incorp.sendKeys(Name_incorp);
        address_incorp.sendKeys(Address_incorp);
        phone_incorp.sendKeys(Phone_incorp);
        mail_incorp.sendKeys(Mail_incorp);
        contineu.click();
    }

    public String messgaeError() {
        String validation = "";
        List<WebElement> errorMessage = driver.findElements(tagline);
        if (errorMessage.size() > 0) {
            validation = errorMessage.get(0).getText().strip();
            System.out.println("Notify System: " + validation);
        }
        return validation;
    }

    public void clearDatatest() {
        try {
            name_incorp.clear();
            address_incorp.clear();
            phone_incorp.clear();
            mail_incorp.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
