package testcase.admin;

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
            using.login_admin();

            admin_requestAmountPage use = new admin_requestAmountPage(driver);
            use.crud_request.click();
            using.waitForPageLoaded();
            if (use.verify_title()) {
                rechargePage add_money = new rechargePage(driver);
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
                        System.out.println("Passed");
                        add_money.tr.click();
                        add_money.confirm_btn.click();
                        validation_input = add_money.validation();
                        if (validation_input != null) {
                            System.out.println("=========================");
                            System.out.println("Testcase: 2");
                            System.out.println(validation_input);
                            System.out.println("Passed");
                            add_money.system_recharge("123", "Cho người nghèo");
                            add_money.confirm_btn.click();
                            String noti = using.messgaeError_tagline();
                            if (noti != null) {
                                System.out.println("=========================");
                                System.out.println("Testcase: 3");
                                System.out.println(noti);
                                System.out.println("Passed");
                            } else {
                                System.out.println("Failed");
                                driver.close();
                            }
                        } else {
                            System.out.println("Failed");
                            driver.close();
                        }
                    } else {
                        System.out.println("Failed");
                        driver.close();
                    }
                } else {
                    System.out.println("Sai tiêu đề trang hiển thị...");
                    driver.close();
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
