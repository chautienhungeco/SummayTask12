/** Employee Managerment System
1. cấu truc
-> model: chứa class định nghĩa dữ liệu
    + ''Employee.kt' - abstract class cho nhân viên
    + 'FullTimeEmployee.kt' - Class cho nhân viên chính thức
    + 'Intern.kt' - Class cho tts
-> util: chứa utility class, fun main()
    + Main.kt - entry point
    + 'ReportGenarator.kt' - tạo các báo cáo danh sách
    + 'EmployeeDataProvider.kt' - dữ liệu ví dụ mẫu
-> **extension**: chưa các extension function
    + 'EmployeeExtension.kt' - hàm mở rộng cho Employee operator
2. Tính năng
   -  Hiển thị danh sách nhân viên
   -  Tìm nhân viên có lương cao nhất
   -  Thống kê số lượng thực tập sinh từ 'Đại học Bách Khoa'
   -  Thống kê số lượng nhân viên chức vụ 'Chuyên viên'
   -  Tính lương tự động theo công thức
3. cách chạy
   - Mở dự án trên android studio
   - Truy cập theo đường dẫn: SummaryTask12/app/src/main/java/com.example.summaytask12/util/Main
   - Tại 'Main.kt' Run Main.kt hoặc tổ hợp phím 'Ctrl + Shift + F10'
   */