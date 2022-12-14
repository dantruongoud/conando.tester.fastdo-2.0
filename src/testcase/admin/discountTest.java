package testcase.admin;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.admin.createAdminPage;
import page_locator.admin.discountPage;
import common.baseSetup;
import excelHelpers.excelHelpers;

public class discountTest {

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            createAdminPage account_admin = new createAdminPage(driver);
            discountPage create = new discountPage(driver);
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("admin - discount");

            using.login_admin();
            account_admin.setting.click();
            using.waitForPageLoaded();

            create.naviga.click();
            using.waitForPageLoaded();
            if (create.verifyTitle()) {
                create.navigation_create();

                for (int i = 1; i < 5; i++) {
                    System.out.println("=========================");

                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    create.create_discount(excel.getCellData("header", i), excel.getCellData("discount", i),
                            excel.getCellData("condition", i));
                    Thread.sleep(1200);

                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Nhập tiêu đề ưu đãi":
                            create.print();
                            break;
                        case "Nhập phần trăm ưu đãi":
                            create.print();
                            break;
                        case "Nhập điều kiện áp dụng":
                            create.print();
                            break;
                        default:
                            if (noti.equals("Đã cập nhật ưu đãi thành công!")) {
                                using.passed();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                using.failed();
                System.out.println("Sai hiển thị tiêu đề trang...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
