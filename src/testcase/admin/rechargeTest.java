package testcase.admin;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.admin.admin_requestAmountPage;
import page_locator.admin.rechargePage;

public class rechargeTest {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            admin_requestAmountPage use = new admin_requestAmountPage(driver);
            rechargePage add_money = new rechargePage(driver);

            using.login_admin();

            use.crud_request.click();
            using.waitForPageLoaded();

            if (use.verify_title()) {

                add_money.recharge_naviga.click();
                using.waitForPageLoaded();

                if (add_money.verify_title()) {

                    add_money.recharge_btn.click();
                    Thread.sleep(1000);

                    add_money.confirm_btn.click();
                    System.out.println("=========================");
                    System.out.println("Testcase: 1");

                    String validation_input = add_money.validation();
                    if (validation_input != null) {
                        System.out.println(validation_input);
                        using.passed();
                        add_money.tr.click();
                        add_money.confirm_btn.click();
                        Thread.sleep(500);
                        System.out.println("=========================");
                        System.out.println("Testcase: 2");
                        validation_input = add_money.validation();
                        if (validation_input != null) {
                            System.out.println(validation_input);
                            using.passed();
                            add_money.system_recharge("123", "Cho người nghèo");
                            add_money.confirm_btn.click();
                            Thread.sleep(500);
                            Alert alert = driver.switchTo().alert();
                            alert.accept();
                            Thread.sleep(500);
                            System.out.println("=========================");
                            System.out.println("Testcase: 3");
                            String noti = using.messgaeError_tagline();
                            if (noti != null) {
                                using.passed();
                            } else {
                                using.failed();
                            }
                        } else {
                            using.failed();
                        }
                    } else {
                        using.failed();
                        driver.close();
                    }
                } else {
                    System.out.println("Sai tiêu đề trang hiển thị...");
                    using.failed();
                    driver.close();
                }
            } else {
                System.out.println("Bạn chưa vào được danh sách quản lý giao dịch");
                using.failed();
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
