package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import page_locator.admin.createAdminPage;

public class createAdminTest {
    int testcase;
    String email, lastname, firstname, password;

    public createAdminTest(int testcase, String email, String lastname, String firstname, String password) {
        this.testcase = testcase;
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = password;
    }

    public static void main(String[] args) {
        try {
            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            createAdminPage account_admin = new createAdminPage(driver);
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("admin - createAdmin");

            using.login_admin();

            account_admin.setting.click();
            using.waitForPageLoaded();

            account_admin.accountAdmin.click();
            using.waitForPageLoaded();

            if (account_admin.verifyTitle()) {
                account_admin.create_btn.click();

                for (int i = 1; i < 7; i++) {
                    System.out.println("=========================");
                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    account_admin.create_account(excel.getCellData("email", i), excel.getCellData("firstname", i),
                            excel.getCellData("lastname", i), excel.getCellData("password", i));
                    Thread.sleep(1200);
                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập địa chỉ email!":
                            account_admin.print();
                            break;
                        case "Địa chỉ email không đúng!":
                            account_admin.print();
                            break;
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            account_admin.print();
                            break;
                        case "Bạn chưa nhật mật khẩu cho tài khoản!":
                            account_admin.print();
                            break;
                        case "Email này đã được sử dụng, vui lòng sử dụng email khác!":
                            account_admin.print();
                            break;
                        default:
                            if (noti.equals("Đã tạo tài khoản thành công!")) {
                                using.passed();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("Sai tiêu đề trang hiển thị...");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
