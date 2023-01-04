package testcase.userInfo;

import org.openqa.selenium.WebDriver;

import common.baseSetup;
import page_locator.SignInPage;
import page_locator.forgotPassPage;

public class forgotPassTest {
    int testcase;
    String email;

    public forgotPassTest(int testcase, String email) {
        this.testcase = testcase;
        this.email = email;
    }

    public static void main(String[] args) {
        try {
            forgotPassTest[] list_data_test = {
                    new forgotPassTest(1, ""),
                    new forgotPassTest(2, "email"),
                    new forgotPassTest(3, "ndtruong.conand132o@gmail.com"),
                    new forgotPassTest(4, "ndtruong.conando@gmail.com")
            };

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage using = new SignInPage(driver);
            forgotPassPage forgotPass = new forgotPassPage(driver);

            forgotPass.forgot_btn.click();
            Thread.sleep(1000);

            for (int i = 0; i < list_data_test.length; i++) {
                System.out.println("=========================");

                System.out.println("Testcase: " + list_data_test[i].testcase);
                forgotPass.forgot(list_data_test[i].email);
                Thread.sleep(1200);

                String validation = using.messgaeError_fogot();

                switch (validation) {
                    case "Bạn chưa nhập địa chỉ email":
                        System.out.println(validation);
                        forgotPass.print();
                        break;
                    case "Địa chỉ email không đúng định dạng":
                        System.out.println(validation);
                        forgotPass.print();
                        forgotPass.cleartxt();
                        break;
                    case "Không tìm thấy tài khoản của email này":
                        System.out.println(validation);
                        forgotPass.print();
                        forgotPass.cleartxt();
                        break;
                    default:
                        validation = using.messgaeError_tagline();
                        if (validation.equals("Đã gửi email thành công!")) {
                            using.passed();
                        } else {
                            using.failed();
                        }
                        break;
                }
                Thread.sleep(1200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
