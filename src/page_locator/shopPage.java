package page_locator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class shopPage {

    private WebDriver driver;

    @FindBy(xpath = "//span[@class='ml-2'][contains(text(),'Cửa hàng')]")
    public WebElement naviga;

    @FindBy(xpath = "//span[normalize-space()='fOKRs']")
    public WebElement list_shop;

    @FindBy(className = "px-3")
    public WebElement buy;

    @FindBy(xpath = "//div[@class='title']")
    private WebElement title;

    @FindBy(xpath = "//span[normalize-space()='Do Corp']")
    public WebElement list_incorp;

    @FindBy(xpath = "//a[@class='button is-link']")
    public WebElement confirm;

    @FindBy(xpath = "//body[1]/main[1]/section[1]/section[3]/section[1]/section[2]/ul[1]/li[3]/div[2]/div[1]/div[1]/div[1]/input[1]")
    private WebElement modal_input1;

    @FindBy(xpath = "//body[1]/main[1]/section[1]/section[3]/section[1]/section[2]/ul[1]/li[3]/div[3]/div[1]/div[1]/div[1]/input[1]")
    private WebElement modal_input2;

    @FindBy(xpath = "//div[contains(text(),'Mua hàng thành công. Hãy phân quyền sử dụng sản ph')]")
    private WebElement text_done;

    @FindBy(xpath = "//span[contains(text(),'Truy cập tổ chức')]")
    private WebElement back;

    @FindBy(xpath = "//span[@class='has-text-weight-medium']")
    public WebElement chose_incorp;

    @FindBy(className = "item_price")
    private WebElement price;

    public shopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    SignInPage get = new SignInPage(driver);

    public String get_title() {
        String titlePage = driver.getTitle();
        return titlePage;
    }

    public boolean verifyTitle() {
        String Pagetitle = "Cửa hàng Fastdo";
        return get_title().equals(Pagetitle);
    }

    public boolean verify_title_modal() {
        String title_modal = "fOKRs";
        return title.getText().strip().equals(title_modal);
    }

    public void buy_product(String user, String month) {
        modal_input1.sendKeys(user);
        modal_input2.sendKeys(month);
        confirm.click();
    }

    public String get_text() {
        return text_done.getText().strip();
    }

    public boolean verify_donetext() {
        String a = "Mua hàng thành công. Hãy phân quyền sử dụng sản phẩm cho người dùng.";
        return get_text().equals(a);
    }

    public void clearTxt() {
        try {
            modal_input1.clear();
            modal_input2.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tính công thức giảm giá để đối chiếu với cửa hàng
    public int get_price() {
        String price_text = price.getText().strip();
        String number_price = price_text.replaceAll("[^0-9]", "");
        int finally_price = Integer.parseInt(number_price);
        return finally_price;
    }

    public int discount() {
        int user = Integer.parseInt(modal_input1.getText());
        int total = user * get_price();
        return total * 25 / 100;
    }

    public int get_price_discount() {
        WebElement find_price = driver.findElement(By.xpath("//span[@class='is-size-6 has-text-weight-bold']"));
        String price_discount = find_price.getText().strip();
        String number_price_discount = price_discount.replaceAll("[^0-9]", "");
        int finally_price = Integer.parseInt(number_price_discount);
        return finally_price;
    }

    public boolean verify_price() {
        return get_price_discount() == discount();
    }
}
