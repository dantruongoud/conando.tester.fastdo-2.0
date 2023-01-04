package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.incorpPage;
import page_locator.admin.admin_requestAmountPage;

public class admin_requestAmountTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage login_all = new SignInPage(driver);
            login_all.login_admin();

            admin_requestAmountPage use = new admin_requestAmountPage(driver);
            use.crud_request.click();
            login_all.waitForPageLoaded();
            if (use.verify_title()) {
                use.find_id();
                incorpPage get = new incorpPage(driver);
                use.accecpt.click();
                String noti = get.messgaeError();
                if (noti != null) {
                    System.out.println("Phê duyệt thành công: " + noti);
                }
            } else {
                System.out.println("Bạn chưa vào được danh sách quản lý giao dịch");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
