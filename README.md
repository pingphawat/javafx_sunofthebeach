Project211 " Sun of the beach "
===============
#
สมาชิกผู้จัดทำ
---------------
```
Back-end
  *Phawat Pongthawaradet 6410401124 (Pingphawat)
  *Kanyakron Sukanuyuth 6410406509 (Kanyakron)
```
```
Front-end
  *Paramaet Kittirart 6410401108 (SchangMay)
  *Nutthakritta Kaopattanaskul 6410401043 (Nutthakritta)
```

วิธีการติดตั้งหรือรันโปรแกรม
---------------
* ไปที่ project211-sunofthebeach\KUComplaint\
* Double click CS211-Project211-sunofthebeach-KUComplaint.jar เพื่อ run โปรแกรม

#
การวางโครงสร้างไฟล์
---------------
```
project211-sunofthebeach
|   .gitignore
|   pom.xml
|   README.md
|
+---data
|   |   admin.csv
|   |   allReport.csv
|   |   claiming.csv
|   |   inappropriateContentReport.csv
|   |   inappropritateUserReport.csv
|   |   officer.csv
|   |   register.csv
|   |
|   \---images
|       \---avatar
|              ...
|
+---KUComplaint
|   |   CS211-Project211-sunofthebeach-KUComplaint.jar
|   |   KU Complaint Application.pdf
|   |   project211-sunofthebeach-uml-controller.png
|   |   project211-sunofthebeach-uml-model.png
|   |   project211-sunofthebeach-uml-service.png
|   |
|   \---data
|       |   admin.csv
|       |   allReport.csv
|       |   claiming.csv
|       |   inappropriateContentReport.csv
|       |   inappropritateUserReport.csv
|       |   officer.csv
|       |   register.csv
|       |
|       \---images
|           \---avatar
|                  ...
|
\---src
   \---main
       +---java
       |   |   module-info.java
       |   |
       |   +---com
       |   |   \---github
       |   |       \---saacsos
       |   |               FXRouter.java
       |   |               
       |   \---ku
       |       \---cs
       |           |   Main.java
       |           |   ProjectApplication.java
       |           |
       |           +---controllers
       |           |   +---admin
       |           |   |      AdminAboutProgramController.java
       |           |   |      AdminHomeController.java
       |           |   |      AdminOrganizerController.java
       |           |   |      AdminProfileController.java
       |           |   |      BannedUserListController.java
       |           |   |      ClaimingListController.java
       |           |   |      CreateOfficerController.java
       |           |   |      InappropriateContentReportListController.java
       |           |   |      InappropriateContentReportListDetailController.java
       |           |   |      InappropriateController.java
       |           |   |      InappropriateUserReportListController.java
       |           |   |      InappropriateUserReportListDetailController.java
       |           |   |      NisitDetailController.java
       |           |   |      OfficerDetailController.java
       |           |   |
       |           |   +---main
       |           |   |      AboutProgramController.java
       |           |   |      ClaimingController.java
       |           |   |      HomeController.java
       |           |   |      LoadingController.java
       |           |   |      OrganizerController.java
       |           |   |      RegisterController.java
       |           |   |
       |           |   +---nisit
       |           |   |      NisitAboutProgramController.java
       |           |   |      NisitHomeController.java
       |           |   |      NisitOrganizerController.java
       |           |   |      NisitProfileController.java
       |           |   |      NisitReportController.java
       |           |   |      NisitReportInappropriateContentController.java
       |           |   |      NisitReportInappropriateUserController.java
       |           |   |      NisitReportListController.java
       |           |   |      NisitReportListDetailController.java
       |           |   |
       |           |   \---officer
       |           |          OfficerAboutProgramController.java
       |           |          OfficerFixReportController.java
       |           |          OfficerHomeController.java
       |           |          OfficerOrganizerController.java
       |           |          OfficerProfileController.java
       |           |          OfficerReportDetailController.java
       |           |
       |           +---models
       |           |   +---account
       |           |   |      Admin.java
       |           |   |      AdminList.java
       |           |   |      Nisit.java
       |           |   |      NisitList.java
       |           |   |      Officer.java
       |           |   |      OfficerList.java
       |           |   |      User.java
       |           |   |      UserList.java
       |           |   |
       |           |   \---report
       |           |          Claiming.java
       |           |          ClaimingList.java
       |           |          InappropriateReport.java
       |           |          InappropriateReportList.java
       |           |          Report.java
       |           |          ReportList.java
       |           |
       |           \---services
       |                   AdminListDataSource.java
       |                   ClaimingListDataSource.java
       |                   DataSource.java
       |                   Filterer.java
       |                   InappropriateReportListDataSource.java
       |                   Information.java
       |                   NisitListDataSource.java
       |                   OfficerListDataSource.java
       |                   ReportListDataSource.java
       |                   ThemeMode.java
       |          
       \---resources
            \---ku
                \---cs
                    |   aboutProgram.fxml
                    |   adminAboutProgram.fxml
                    |   adminHome.fxml
                    |   adminOrganizer.fxml
                    |   adminProfile.fxml
                    |   bannedUserList.fxml
                    |   claiming.fxml
                    |   claimingList.fxml
                    |   claimingListDetail.fxml
                    |   createOfficerAccount.fxml
                    |   home.fxml
                    |   inappropriate.fxml
                    |   inappropriateContentReportList.fxml
                    |   inappropriateContentReportListDetail.fxml
                    |   inappropriateUserReportList.fxml
                    |   inappropriateUserReportListDetail.fxml
                    |   loading.fxml
                    |   nisitAboutProgram.fxml
                    |   nisitDetail.fxml
                    |   nisitHome.fxml
                    |   nisitOrganizer.fxml
                    |   nisitProfile.fxml
                    |   nisitReport.fxml
                    |   nisitReportInappropriateContent.fxml
                    |   nisitReportInappropriateUser.fxml
                    |   nisitReportList.fxml
                    |   nisitReportListDetail.fxml
                    |   officerAboutProgram.fxml
                    |   officerDetail.fxml
                    |   officerFixReport.fxml
                    |   officerHome.fxml
                    |   officerOrganizer.fxml
                    |   officerProfile.fxml
                    |   officerReportDetail.fxml
                    |   organizer.fxml
                    |   register.fxml
                    |
                    +---forButton
                    |      dark.css
                    |      light.css
                    |      pom.xml
                    |      Prompt-Regular.tff
                    |
                    \---images
                        |   back.png
                        |   BgClaimTheir_Rights.png
                        |   claiming.png
                        |   ClaimList.png
                        |   ComplaintsHandling.png
                        |   contact.png
                        |   Content_inappropriateness.png
                        |   Content_inappropriateness_detail.png
                        |   createOfficerAccount.png
                        |   danger.png
                        |   EveryLayerTop.png
                        |   FixReport.png
                        |   follower.png
                        |   homePage.gif
                        |   Howto.png
                        |   icon_Admin.png
                        |   icon_Block.png
                        |   icon_Change.png
                        |   icon_List.png
                        |   icon_Organizer.png
                        |   icon_Tutalrial.png
                        |   imagehome.jpg
                        |   InappropriateReports.png
                        |   InappropriateUserList.png
                        |   login.png
                        |   logo.gif
                        |   LOGO.png
                        |   Main.png
                        |   Manage.png
                        |   ManageReport.png
                        |   Nisit_Complaint.png
                        |   Nisit_Detail.png
                        |   Nisit_Layer.png
                        |   Nisit_Layer2.png
                        |   Nisit_logoutComplaint.png
                        |   Nisit_Profile.png
                        |   Nisit_storyComplaint.png
                        |   Nisit_writeComplaint.png
                        |   Nisit_writeComplain2.png
                        |   Nisit_writeComplaintPost.png
                        |   Officer_Detail.png
                        |   Officer_ListUser.png
                        |   Officer_report.png
                        |   Officer_report1.png
                        |   OfficerOrganization.png
                        |   OfficerReportDetail.png
                        |   owl.png
                        |   password.png
                        |   passwordNisit.png
                        |   passwordOfficerChange.png
                        |   photo-camera.png
                        |   profile.png
                        |   profileAdmin.png
                        |   profileOfficer.png
                        |   ReadTheReport.png
                        |   Report_inappropriateness.png
                        |   ReportContentList.png
                        |   ReportUserList.png
                        |   road-barrier.png
                        |   search.png
                        |   siren.png
                        |   stop.png
                        |   submit.png
                        |   TopicOrganizer.png
                        |   tutorial01.png
                        |   tutorial0201.png
                        |   tutorial0202.png
                        |   tutorial0203.png
                        |   tutorial0301.png
                        |   tutorial0302.png
                        |   tutorial0401.png
                        |   tutorial0402.png
                        |   unfriend.png
                        |   User_inappropriateness.png
                        |   User_inappropriateness_Datail.png
                        |   username.png
                        |   white-login.png
                        |   white-password.png
                        |   white-username.png
                        |   worm.png
                        |   WriteTheReport.png
                        |
                        +---organizer
                        |      Meimay.jpg
                        |      Meimay2.png
                        |      organizer Meimay.png
                        |      organizer Ping.png
                        |      organizer Prim.png
                        |      organizer ta.png
                        |      Ping.jpg
                        |      Ping2.png
                        |      Prim.jpg
                        |      Prim2.png
                        |      Ta.jpg
                        |      Ta2.png
                        |
                        \---staff
                               header.png
                               topic.png
                               topic1.png
```

#
ตัวอย่างข้อมูลผู้ใช้ระบบ
---------------
```
Admin
username : pingph25251
password : 6410401124
```
```
Officer
username : bell123
password : 123
```
```
Nisit
username : ta123
password : 1234
```
```
Nisit
username : ping123
password : 123
```

***
สรุปการพัฒนาและความคืบหน้าของ project 
===============
ครั้งที่ 1 (12 August 2022)
---------------
1.`Phawat Pongthawaradet`
* ทำ HomeController + home.fxml และ RegisterController + register.fxml

2.`Kanyakron Sukanuyuth`
* ทำ OrganizerController + organizer.fxml และ AboutProgramController + aboutprogram.fxml

3.`Paramaet Kittirart`
* ทำ OrganizerController + organizer.fxml ในส่วนของ imageView

4.`Nutthakritta Kaopattanaskul`
* ทำ organizer.fxml ในส่วนของข้อมูลผู้จัดทำ


#
ครั้งที่ 2 (9 September 2022)
---------------

1.`Phawat Pongthawaradet`
* ทำระบบการสมัครสมาชิก RegisterController
* ทำระบบการเข้าสู่ระบบ HomeController
   * เพิ่ม checkbox เพื่อให้สามารถเปิดดู passwordField ได้

2.`Kanyakron Sukanuyuth`
* สร้าง class User, Nisit, Officer และ Report

3.`Paramaet Kittirart`
* สร้าง NisitHomeController + nisitHome.fxml, NisitReportController + nisitReport.fxml, LoadingController + loading.fxml 
* ไฟล์ CSS

4.`Nutthakritta Kaopattanaskul`
* สร้าง OfficerHomeController + officerHome.fxml


#
ครั้งที่ 3 (30 September 2022)
---------------

1.`Phawat Pongthawaradet`
* สร้าง interface DataSource, class Admin, AdminList, AdminListDataSource, NisitListDataSource, OfficerDataSource
* ปรับปรุงระบบการเข้าสู่ระบบ
   * แยกอ่านไฟล์ admin register officer เพื่อเชื่อมไปยังหน้าต่างๆ ตามประเภทของบัญชีผู้ใช้
* ทำระบบการเปลี่ยนรหัสผ่าน ของ admin

2.`Kanyakron Sukanuyuth`
* สร้างเค้าโครงของ Controller (ยังไม่ตกแต่ง)
* สร้าง class Interface Filterer, Report, ReportList, ReportListFileDataSource
* ทำระบบการเขียนเรื่องร้องเรียนของนิสิต NisitReportController
* ทำระบบการอ่านรายการเรื่องร้องเรียนของนิสิต NisitReportListController

3.`Paramaet Kittirart`
* สร้างหน้าและตกแต่ง Main จำนวน 6 หน้า ได้แก่ Loading, Home, Organizer, AbountProgram, Register, Claiming
* สร้างหน้าและตกแต่ง Admin จำนวน 6 หน้า ได้แก่ AdminHome, AdminProfile, InappropriateList, OfficerOrganizer, CreateOfficer, UserList
* สร้างหน้าและตกแต่ง Officer จำนวน 4 หน้า ได้แก่ OfficerAboutProgram, OfficerHome, OfficerProfile, OfficerreportList
* สร้างหน้าและตกแต่ง Nisit จำนวน 6 หน้า ได้แก่ NisitAboutProgram, NisitOrganizer, NisitProfile, NisitHome, NisitReport, NisitReportList
* สร้างคลาส ClaimingController
* เขียนโค้ดใน Class ที่ต้องการ handleButton ที่เป็นรูปภาพหรือ icon ซึ่งใช้ MouseEvent 
* สร้าง Scrollpane ให้สามารถเปิดหน้าแล้วเริ่มต้นที่ Title, และลิ้ง Button ที่ใช้ ActionEvent
* เพิ่มโค้ด CSS

4.`Nutthakritta Kaopattanaskul`
* เชื่อมโยงลิ้งก์แต่ละหน้าด้วย Button จาก Button และ ImageView ใน AdminHomeController
* แก้บัคยิบย่อยที่พบ เช่น เปิดหน้าใดหน้าหนึ่งไม่ได้เนื่องจากไม่มี method ที่เชื่อมโยงกับ Button ในหน้านั้นๆ

#
ครั้งที่ 4 (21 September 2022)
---------------

1.`Phawat Pongthawaradet`
* ปรับปรุงระบบการสมัครสมาชิกและสร้างบัญชีของเจ้าหน้าที่ RegisterController, CreateOfficerController
* ทำระบบการเปลี่ยนรหัสผ่านและเปลี่ยนรูปโปรไฟล์ AdminProfile, NisitProfile, OfficerProfile
* ทำปุ่มโหวดและเขียนโค้ดของปุ่มโหวต ในหน้า NisitReportListDetailController
* เขียนโค้ดในส่วนของการแสดงผลรูปภาพของผู้ใช้ ในหน้า NisitDetail, OfficerDetail, OfficerHome, NisitHome
* ทำ .jar file

2.`Kanyakron Sukanuyuth`
* สร้าง class Information, Claiming, ClaimingList, ClaimingListDataSource, InappropriateReport, InappropriateReportList, InappropriateReportListDataSource
* ทำระบบขอคืนสิทธิ์ ClaimingController
* ทำระบบการจัดการเรื่องร้องเรียนของเจ้าหน้าที่ OfficerHome(ส่วนตาราง), OfficerReportDetailController, OfficerFixReportController
* ทำระบบการอ่านและรายงานความไม่เหมาะสมของเรื่องร้องเรียนและผู้ใช้ของนิสิต NisitReportListDetailController, NisitReportInappropriateContentController, NisitReportInappropriateUserController
* ทำระบบการดูรายชื่อผู้ใช้งานของผู้ดูแลระบบ AdminHome(ส่วนตาราง), NisitDetailController, OfficerDetailController
* ทำระบบการจัดการความไม่เหมาะสม InappropriateController
   * รายชื่อผู้ใช้ที่ถูกแบน BannedUserListController
   * รายการการขอคืนสิทธิ์, การคืนสิทธิ์ ClaimingListContoller, ClaimingListDetailController
   * รายการความไม่เหมาะสมของผู้ใช้, การระงับสิทธิ์ผู้ใช้ InappropriateUserReportListController, InappropriateUserReportListDetailController
   * รายการความไม่เหมาะสมของเนื้อหา, การลบเรื่องรายงาน InappropriateContentReportListController, InappropriateContentReportListDetailController
* ทำ UML diagram


3.`Paramaet Kittirart`
* สร้างหน้าและตกแต่งการรายงานเนื้อหากับการรายงานผู้ใช้
* ปรับเปลี่ยนแก้ไขหน้า Loading พร้อมทั้งแก้ไขปุ่ม Button ต่างๆ
* ทำ Transition ทุกหน้า
* ทำ Dark-Light Mode
* อัพเดท CSS


4.`Nutthakritta Kaopattanaskul`
* ทำ aboutProgram
* ทำ dark mode
* ทำข้อมูลทั้งหมดในระบบ
* ทำไฟล์ PDF การใช้โปรแกรม
# myproject
# myproject
