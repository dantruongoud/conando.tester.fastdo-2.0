package testcase.admin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.admin.admin_addUserPage;
import page_locator.admin.deleteUserPage;
import common.baseSetup;

public class deleteUserTest {
    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            admin_addUserPage admin_createUser = new admin_addUserPage(driver);
            admin_createUser.crud_user.click();
            if (admin_createUser.verifyTitle()) {
                Thread.sleep(1200);
                deleteUserPage del = new deleteUserPage(driver);
                del.search_result("ndtruong@gmail.com");
                del.delete_btn.click();
                Thread.sleep(500);
                Alert alert = driver.switchTo().alert();
                System.out.println(alert.getText());
                alert.accept();
                using.passed();
            } else {
                System.out.println("Tiêu đề trang sai hiển thị...");
                using.failed();
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
