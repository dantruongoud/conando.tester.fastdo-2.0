package testcase.admin;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.admin.createAdminPage;
import page_locator.admin.discountPage;
import common.baseSetup;

public class discountTest {
    int testcase;
    String header;
    String discount;
    String condition;

    public discountTest(int testcase, String header, String discount, String condition) {
        this.testcase = testcase;
        this.header = header;
        this.discount = discount;
        this.condition = condition;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            using.login_admin();
            createAdminPage account_admin = new createAdminPage(driver);
            account_admin.setting.click();
            using.waitForPageLoaded();

            discountPage create = new discountPage(driver);
            create.naviga.click();
            using.waitForPageLoaded();
            if (create.verifyTitle()) {
                create.navigation_create();
                discountTest[] list_data_test = {
                        new discountTest(1, "", "0", "0"),
                        new discountTest(2, "Ưu đãi", "0", "0"),
                        new discountTest(3, "Ưu đãi", "10", "0"),
                        new discountTest(4, "Ưu đãi của tôi", "10", "2")
                };
                for (int i = 0; i < list_data_test.length; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + list_data_test[i].testcase);
                    create.create_discount(list_data_test[i].header, list_data_test[i].discount,
                            list_data_test[i].condition);
                    Thread.sleep(1200);
                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Nhập tiêu đề ưu đãi":
                            System.out.println(noti);
                            create.print();
                            break;
                        case "Nhập phần trăm ưu đãi":
                            System.out.println(noti);
                            create.print();
                            break;
                        case "Nhập điều kiện áp dụng":
                            System.out.println(noti);
                            create.print();
                            break;
                        default:
                            noti = using.messgaeError_tagline();
                            if (noti.equals("Đã cập nhật ưu đãi thành công!")) {
                                System.out.println(noti);
                                System.out.println("Passed");
                                System.out.println("=========================");
                            } else {
                                System.out.println("Failed");
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("Sai hiển thị tiêu đề trang...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
