package testcase;

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
            SignInPage login_all = new SignInPage(driver);
            incorpPage incorppage = new incorpPage(driver);

            login_all.login();
            incorppage.clickNavigation();
            incorppage.add_incorp.click();
            Thread.sleep(1000);
            incorppage.veryfi_modal();

            for (int i = 1; i < 6; i++) {

                System.out.println("=========================");

                System.out.println("Testcase: " + excel.getCellData("TCID", i));
                incorppage.create_incorp(excel.getCellData("name", i), excel.getCellData("address", i),
                        excel.getCellData("phone", i), excel.getCellData("mail", i));
                Thread.sleep(1200);

                String noti = incorppage.messgaeError();
                switch (noti) {
                    case "Bạn chưa nhập tên tổ chức.":
                        incorppage.print();
                        break;
                    case "Bạn chưa nhập địa chỉ tổ chức.":
                        incorppage.print();
                        break;
                    case "Bạn chưa nhập Số điện thoại người đại diện.":
                        incorppage.print();
                        break;
                    case "Bạn chưa nhập email tổ chức.":
                        incorppage.print();
                        break;
                    default:
                        if (incorppage.messgaeError() != null) {
                            System.out.println("Hoàn tất tạo mới tổ chức, nhập mã xác thực ở email");
                            login_all.passed();
                        } else {
                            login_all.failed();
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
