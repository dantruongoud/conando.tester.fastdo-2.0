package testcase.inCorp;

import org.openqa.selenium.WebDriver;

import page_locator.SignInPage;
import page_locator.incorpPage;
import common.baseSetup;
import excelHelpers.excelHelpers;

public class create_incorpTest {

    public static void main(String[] args) {
        try {

            excelHelpers excel = new excelHelpers();
            excel.setExcelSheet("Create inCorp");

            baseSetup init = new baseSetup();
            WebDriver driver = init.initChromeDriver();
            SignInPage index = new SignInPage(driver);
            incorpPage incorppage = new incorpPage(driver);

            index.login();
            incorppage.clickNavigation();
            incorppage.add_incorp.click();
            Thread.sleep(1000);
            incorppage.veryfi_modal();

            for (int i = 1; i < 6; i++) {

                System.out.println("=========================");

                System.out.println("Testcase: " + excel.getCellData("TCID", i));
                incorppage.clearDatatest();

                incorppage.create_incorp(excel.getCellData("name", i), excel.getCellData("address", i),
                        excel.getCellData("phone", i), excel.getCellData("mail", i));
                Thread.sleep(1200);

                Boolean passed = false;
                String noti = incorppage.messgaeError();
                for (int j = 0; j < incorppage.taglinetext.length; j++) {
                    if (noti.equals(incorppage.taglinetext[j])) {
                        passed = true;
                        index.passed();
                        break;
                    } else if (noti.contains("Đang gửi email xác nhận...")) {
                        passed = true;
                        index.passed();
                        break;
                    }
                }
                if (!passed)
                    index.failed();
                    
                // switch (noti) {
                // case "Bạn chưa nhập tên tổ chức.":
                // incorppage.print();
                // break;
                // case "Bạn chưa nhập địa chỉ tổ chức.":
                // incorppage.print();
                // break;
                // case "Bạn chưa nhập Số điện thoại người đại diện.":
                // incorppage.print();
                // break;
                // case "Bạn chưa nhập email tổ chức.":
                // incorppage.print();
                // break;
                // default:
                // if (noti.equals("Đang gửi email xác nhận...")) {
                // System.out.println("Hoàn tất tạo mới tổ chức, nhập mã xác thực ở email");
                // index.passed();
                // } else {
                // index.failed();
                // }
                // break;
                // }
                // Thread.sleep(1200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
