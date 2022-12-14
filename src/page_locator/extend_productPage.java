package page_locator;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class extend_productPage {

    private WebDriver driver;

    @FindBys(@FindBy(tagName = "tr"))
    private List<WebElement> extend;

    private By price = By.xpath("//div[@class='control has-text-link']");

    @FindBy(xpath = "//span[contains(text(),'Tiếp tục')]")
    public WebElement continuebtn;

    @FindBy(tagName = "input")
    public WebElement usercorrect;

    @FindBy(xpath = "//a[@class='button is-link']")
    public WebElement confirm;

    @FindBy(xpath = "//li[@class='column is-full']")
    private WebElement donetext;

    public extend_productPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void click_extend() {
        try {
            for (int i = 0; i < extend.size(); i++) {
                List<WebElement> tdList = extend.get(i).findElements(By.tagName("td"));
                if (tdList.size() == 7) {
                    WebElement product = tdList.get(0);
                    String name_product = product.getText().strip();
                    if (name_product.equals("fOKRs")) {
                        WebElement extendBtn = tdList.get(5).findElement(By.tagName("a"));
                        extendBtn.click();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy giá tiền gia hạn để verify với số tiền thanh toán khi gia hạn
    public int get_price() {
        int price = 0;
        for (int i = 0; i < extend.size(); i++) {
            List<WebElement> tdList = extend.get(i).findElements(By.tagName("td"));
            if (tdList.size() == 7) {
                WebElement product = tdList.get(4);
                String price_product = product.getText().strip();
                String replace_price = price_product.replaceAll("[^0-9]", "");
                price = Integer.parseInt(replace_price);
                break;
            }
        }
        return price;
    }

    public String price_amount() {
        WebElement priceAmount = driver.findElement(price);
        String getprice = priceAmount.getText().strip();
        return getprice;
    }

    public String get_textdone() {
        return donetext.getText();
    }
}
