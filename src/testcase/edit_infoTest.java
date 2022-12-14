package testcase;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.edit_infoPage;
import common.baseSetup;

public class edit_infoTest {

    int testcase;
    String firstname;
    String lastname;
    String passold;
    String passnew;

    public edit_infoTest(int testcase, String firstname, String lastname, String passold, String passnew) {
        this.testcase = testcase;
        this.firstname = firstname;
        this.lastname = lastname;
        this.passold = passold;
        this.passnew = passnew;
    }

    public static void main(String[] args) {
        try {
            edit_infoTest[] list_data_test = {
                    new edit_infoTest(1, "", "", "", ""),
                    new edit_infoTest(2, "NGUYEN", "DAN TRUONG", "", "123456"),
                    new edit_infoTest(3, "NGUYEN", "DAN TRUONG", "1234567", "12346798"),
                    new edit_infoTest(4, "NGUYEN", "DAN TRUONG", "123456", "123456")
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            edit_infoPage edit_info = new edit_infoPage(driver);

            using.login();
            edit_info.navigation();

            if (edit_info.verify_title()) {

                for (int i = 0; i < list_data_test.length; i++) {

                    edit_info.clearTXT();

                    System.out.println("=========================");

                    System.out.println("Testcase: " + list_data_test[i].testcase);

                    edit_info.change_info(list_data_test[i].firstname, list_data_test[i].lastname,
                            list_data_test[i].passold, list_data_test[i].passnew);
                    Thread.sleep(1200);

                    String noti = using.messgaeError_tagline();
                    
                    switch (noti) {
                        case "Bạn chưa nhập họ và tên cho tài khoản!":
                            System.out.println(noti);
                            edit_info.print();
                            break;
                        case "Mật khẩu cũ không chính xác!":
                            System.out.println(noti);
                            edit_info.print();
                            break;
                        case "Đã cập nhật thông tin thành công!":
                            System.out.println(noti);
                            using.passed();
                            break;
                        default:
                            using.failed();
                            break;
                    }
                    Thread.sleep(1200);
                }

            } else {
                using.failed();
                System.out.println("Không tìm thấy title của trang");
                driver.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
