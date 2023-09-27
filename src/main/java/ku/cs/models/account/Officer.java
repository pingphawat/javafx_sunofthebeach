package ku.cs.models.account;

import ku.cs.services.OfficerListDataSource;
import ku.cs.models.report.Report;

import java.awt.image.BufferedImage;
import java.io.File;

public class Officer extends User{
    private String department;

    public Officer(String name, String surname, String username, String password, String department) {
        super(name, surname, username, password);
        this.department = department;
    }

    public Officer(String name, String surname, String username, String password, boolean loginStatus, String lastLoginDateTime, String department) {
        super(name, surname, username, password, loginStatus, lastLoginDateTime);
        this.department = department;
    }

    @Override
    public void setImage(BufferedImage bi) {
        String directoryName = "data" + File.separator + "images" + File.separator + "avatar";
        String filepath = directoryName + File.separator + getUsername() + ".png";

        OfficerListDataSource ulData = new OfficerListDataSource(false);
        ulData.setImageFile(bi, directoryName, getUsername() + ".png");

        this.picture = ulData.getImage(filepath);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean checkManagement(String reportType){
        if(department.equals("สำนักการกีฬา") && reportType.equals("กิจกรรมและสันทนาการ")) {
            return true;
        } else if(department.equals("สำนักงานทรัพย์สิน") && reportType.equals("อาคารและสถานที่")){
            return true;
        } else if(department.equals("สำนักงานกฏหมาย") && reportType.equals("เอกสารและงานธุรการ")){
            return true;
        } else if(department.equals("สำนักงานพัฒนาคุณภาพและบริหารความเสี่ยง") && reportType.equals("คุณภาพชีวิตในมหาวิทยาลัย")){
            return true;
        } else if(department.equals("สำนักงานบริการวิชาการ") && reportType.equals("การเรียน")){
            return true;
        }
        return false;
    }

    public boolean checkManageReport(Report report){
        if(report.getStatus().equals("ยังไม่จัดการ") || (report.getStatus().equals("กำลังดำเนินการ") && username.equals(report.getOfficerUsername()))){
            return true;
        }
        return false;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + department;
    }
}
