package page_locator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class addUser_incorpPage {

    private WebDriver driver;

    SignInPage get = new SignInPage(driver);

    @FindBy(xpath = "//span[contains(text(),'Người dùng')]")
    private WebElement userIncorp;

    @FindBy(css = ".icon-text.has-text-link")
    public WebElement addBtn;

    @FindBy(css = ".title.has-text-white")
    private WebElement popup;

    @FindBy(xpath = "//a[@class='button is-link']")
    public WebElement button;

    @FindBy(xpath = "//input[@placeholder='Nhập địa chỉ email của người dùng...']")
    public WebElement email;

    @FindBy(xpath = "//input[@placeholder='Nhập họ']")
    private WebElement lastname_input;

    @FindBy(xpath = "//input[@placeholder='Nhập tên']")
    private WebElement firstname_input;

    public addUser_incorpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void naviga_user() {
        try {
            if (userIncorp.isDisplayed()) {
                userIncorp.click();
                get.waitForPageLoaded();
                addBtn.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String gettitle() {
        return popup.getText().strip();
    }

    public boolean verifyTitle() {
        String a = "Thêm người dùng";
        return gettitle().equals(a);
    }

    public void cleartxt() {
        try {
            email.clear();
            lastname_input.clear();
            firstname_input.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUser(String username, String lastname, String firstname) {
        email.sendKeys(username);
        lastname_input.sendKeys(lastname);
        firstname_input.sendKeys(firstname);
        button.click();
    }

    public void print() {
        try {
            System.out.println("Status: PASSED");
            System.out.println("=========================");
            cleartxt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
