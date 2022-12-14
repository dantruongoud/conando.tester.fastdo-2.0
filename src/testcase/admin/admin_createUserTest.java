package testcase.admin;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import excelHelpers.excelHelpers;
import page_locator.SignInPage;
import page_locator.admin.admin_addUserPage;

public class admin_createUserTest {
  

    public static void main(String[] args) {
        try {
            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("admin - createUser");

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            admin_addUserPage admin_createUser = new admin_addUserPage(driver);

            using.login_admin();
            admin_createUser.crud_user.click();
            using.waitForPageLoaded();

            if (admin_createUser.verifyTitle()) {
                admin_createUser.add.click();

                for (int i = 1; i < 10; i++) {
                    System.out.println("=========================");
                    
                    System.out.println("Testcase: " + excel.getCellData("TCID", i));
                    admin_createUser.register(excel.getCellData("username", i), excel.getCellData("firstname", i),
                            excel.getCellData("lastname", i), excel.getCellData("phone", i));
                    Thread.sleep(1200);

                    String noti = using.messgaeError_tagline();
                    switch (noti) {
                        case "Bạn chưa nhập địa chỉ email!":
                            admin_createUser.print();
                            break;
                        case "Địa chỉ email không đúng!":
                            admin_createUser.print();
                            break;
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            admin_createUser.print();
                            break;
                        case "Bạn chưa nhật mật khẩu cho tài khoản!":
                            admin_createUser.print();
                            break;
                        case "Bạn chưa nhập số điện thoại!":
                            admin_createUser.print();
                            break;
                        case "Số điện thoại không đúng định dạng!":
                            admin_createUser.print();
                            break;
                        case "Email này đã được sử dụng, vui lòng sử dụng email khác!":
                            admin_createUser.print();
                            break;
                        default:
                            if (noti.equals("Đang gửi email thông tin tài khoản...")) {
                                System.out.println("Tạo mới thành công! Nhập mã xác thực tại email...");
                                using.passed();
                            } else {
                                using.failed();
                            }
                            break;
                    }
                    Thread.sleep(1200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
