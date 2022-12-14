package page_locator.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import page_locator.SignInPage;

public class admin_activeIncorpPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='ml-2'][contains(text(),'Quản lý khách hàng')]")
    public WebElement crud_user;

    @FindBy(how = How.XPATH, using = "//table/tbody/tr")
    private List<WebElement> table;

    @FindBy(linkText = "Do Corp")
    public WebElement find;

    @FindBy(xpath = "/html/body/main/section/section[2]/div/div/div/div[4]/div/div[1]/span/i")
    private WebElement menu_edit;

    @FindBy(xpath = "//a[contains(text(),'Chỉnh sửa')]")
    public WebElement chose_edit;

    @FindBy(xpath = "//span[contains(text(),'Kích hoạt')]")
    public WebElement active;

    @FindBy(xpath = "//span[contains(text(),'Cập nhật')]")
    public WebElement save;

    public admin_activeIncorpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage get = new SignInPage(driver);

    public String getTitle() {
        String titlePage = driver.getTitle();
        return titlePage;
    }

    public boolean verifyTitle() {
        String pageTitle = "Danh sách khách hàng";
        return getTitle().equals(pageTitle);
    }

    public void chose_user() {
        try {
            for (int i = 0; i < table.size(); i++) {
                List<WebElement> tdList = table.get(i).findElements(By.xpath("//td/a/div/p[1]"));
                String td_username = tdList.get(i).getText().strip();
                if (td_username.equals("ndtruong.conando@gmail.com")) {
                    tdList.get(i).click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hover_menu() {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(menu_edit).perform();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
