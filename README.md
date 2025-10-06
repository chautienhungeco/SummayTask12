/** Employee Managerment System
Đây là một chương trình quản lý nhân viên đơn giản được phát triển bằng Kotlin,
tập trung vào việc áp dụng các nguyên tắc Lập trình Hướng đối tượng (OOP)
----------------------------------------------------------------------
1. Tổng quan chương trình 
- Chương trình cung cấp giao diện Console để thực hiện các nghiệp vụ quản lý cơ bản đối với
hai loại nhân viên chính: Nhân viên Chính thức (FullTimeEmployee) và Thực tập sinh (Intern).
- Mục tiêu chính của dự án:
 + Áp dụng đa hình thông qua lớp cơ sở Employee và hàm calculateSalary().
 + Sử dụng các cấu trúc dữ liệu của Kotlin như data class, sealed class, và enum class.
 + Phân tách trách nhiệm  rõ ràng giữa các tầng:
   Dữ liệu, Nghiệp vụ (Domain), và Trình bày (Presentation).
----------------------------------------------------------------------
2. Cấu trúc code (các package)
   2.1 app - Entry Point
   - Chứa hàm main() và khởi chạy ứng dụng.
   2.2 data - Quản lý dữ liệu
   - Chứa EmployeeRepository (xử lý CRUD), và EmployeeDataSeeder (khởi tạo dữ liệu mẫu).
   2.3 domain.model - Định nghĩa cấu trúc dữ liệu
   - 'Employee.kt' - abstract class cho nhân viên
   - 'FullTimeEmployee.kt' - Class cho nhân viên chính thức
   - 'Intern.kt' - Class cho tts
   - 'ContactInformation' - lưu thông tin liên lạc
   - 'Position' - enum class lưu vị trí cấp bậc nhân viên
     - 'EmployeeStatus' - sealed class lưu thông tin trạng thái làm việc
   2.4 Extension - Giao diện người dùng trên console
   - Được tách nhỏ theo chức năng (*FormatExt, *SearchExt, *StatExt) 
   để tăng cường khả năng đọc và sử dụng lại code.
   2. 5Presentation: chưa các extension function
   -  Chứa EmployeeProgram (điều phối chính) và các Screen để xử lý
   I/O cho chương trình
----------------------------------------------------------------------
3. Tính năng
   3.1 Quản lý
   - Thêm mới nhân viên (Phân biệt Chính thức/Thực tập sinh).
   - Xóa nhân viên theo Mã ID (có xác nhận).
   - Cập nhật trạng thái làm việc (Active, Onleave, Retired) cho nhân viên
   3.2 Báo cáo & Thống kê
   - Hiển thị danh sách chi tiết của tất cả nhân viên.
   - Tìm kiếm nhân viên có mức lương cao nhất.
   - Thống kê số lượng Thực tập sinh từ "Đại học Bách Khoa" và số lượng "Chuyên viên" (EXPERT)
   3.3 Tìm kiếm & Lọc
   - Tìm kiếm nhanh nhân viên theo tên (hoặc một phần tên).
   - Lọc danh sách nhân viên theo năm sinh tối đa, hoặc theo khoảng lương.
   - Đếm số lượng nhân viên theo từng chức vụ (Position).
4. cách chạy
   - Mở dự án trên android studio 
   - Tìm đến file Main.kt trong package app.
   - Tại 'Main.kt' Run Main.kt hoặc tổ hợp phím 'Ctrl + Shift + F10' để chạy
   - Tương tác với chương trình thông qua các menu hiển thị trong cửa sổ Terminal/Console.
   */