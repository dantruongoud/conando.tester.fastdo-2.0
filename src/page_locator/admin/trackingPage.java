package page_locator.admin;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class trackingPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Giao dịch')]")
    public WebElement naviga;

    @FindBy(xpath = "//body[1]/main[1]/section[1]/section[2]/table[1]/tbody[1]/tr[1]")
    private WebElement table;

    @FindBy(xpath = "//span[contains(text(),'Lệnh mua sản phẩm')]")
    public WebElement naviga_command;

    @FindBy(xpath = "//input[@placeholder='Email, họ và tên KH, tên tổ chức...']")
    public WebElement search_input;

    @FindBy(xpath = "//span[contains(text(),'Đăng xuất')]")
    private WebElement logout;

    public trackingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle().strip();
    }

    public boolean verify_title() {
        String a = "Lịch sử giao dịch";
        return getTitle().equals(a);
    }

    public String get_tracking1() {
        List<WebElement> tdlist = table.findElements(By.tagName("td"));
        String id_tracking = tdlist.get(0).getText().strip();
        return id_tracking;
    }

    public void click_logout() {
        try {
            if (logout.isDisplayed()) {
                logout.click();
                Thread.sleep(1000);
                Alert alert = driver.switchTo().alert();
                alert.accept();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void get_datatracking() {
        try {
            List<WebElement> tdlist = table.findElements(By.tagName("td"));
            System.out.println("Thời gian: " + tdlist.get(1).getText());
            WebElement buyer = tdlist.get(2).findElement(By.xpath("//td/div/div/p[1]"));
            System.out.println("Khách hàng: " + buyer.getText().strip());
            System.out.println("Số tiền: " + tdlist.get(3).getText().strip());
            System.out.println("Nội dung: " + tdlist.get(4).getText().strip());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
