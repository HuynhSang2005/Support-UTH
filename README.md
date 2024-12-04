# Support UTH

## **Tổng quan dự án:**

### **1.1 Mục tiêu**

Website này nhằm hỗ trợ sinh viên Đại học Giao thông Vận tải TP.HCM (UTH) trong việc giải quyết các vấn đề hoặc tình huống liên quan đến học tập, hành chính và các yêu cầu cá nhân khác. Mục tiêu cụ thể bao gồm:

- **Hỗ trợ xử lý các yêu cầu**: Sinh viên có thể yêu cầu hỗ trợ trong nhiều trường hợp như tư vấn học vụ, xin giấy xác nhận sinh viên, điều chỉnh môn học, hoặc thắc mắc liên quan đến học phí.
- **Giao tiếp hiệu quả**: Tạo kênh kết nối giữa sinh viên, giảng viên và bộ phận hỗ trợ hành chính.
- **Quản lý thông tin dễ dàng**: Hệ thống hỗ trợ việc lưu trữ và quản lý các yêu cầu, thông tin và tình trạng giải quyết yêu cầu một cách hiệu quả.

### **1.2. Phạm vi dự án**

- Hệ thống chỉ phục vụ nội bộ cho sinh viên, giảng viên và nhân viên hành chính của trường UTH.
- Hỗ trợ các yêu cầu học vụ, hành chính, và giải quyết các vấn đề phát sinh trong quá trình học tập của sinh viên.

## **Yêu cầu chức năng**

### **2.1. Tác nhân**
![Screenshot_2024-11-27_140257](https://github.com/user-attachments/assets/30b86aba-7b18-49a2-ae45-15ca629722c6)
- **Sinh viên**: Là người yêu cầu hỗ trợ, có thể tạo yêu cầu mới, theo dõi trạng thái, và nhận phản hồi từ giảng viên hoặc admin.
- **Giảng viên (Staff)**: Xử lý các yêu cầu của sinh viên liên quan đến học vụ, tư vấn, hướng dẫn về các môn học.
- **Admin**: Quản trị hệ thống, xử lý các yêu cầu liên quan đến hành chính (giấy tờ, học phí...), phân quyền và quản lý các tác vụ của giảng viên.

### **2.2. Các chức năng chính**
![image](https://github.com/user-attachments/assets/7e0e607a-e05c-4c14-839f-ec177acdbd5e)

- **Đăng nhập và đăng ký**: Hệ thống cung cấp tính năng đăng nhập cho sinh viên, giảng viên và admin. Tùy thuộc vào vai trò mà giao diện và chức năng sẽ được điều chỉnh cho phù hợp.
- **Tạo yêu cầu hỗ trợ**: Sinh viên có thể tạo yêu cầu hỗ trợ liên quan đến nhiều vấn đề khác nhau (học vụ, hành chính, tài liệu...).
- **Quản lý yêu cầu**: Sinh viên có thể xem danh sách yêu cầu đã tạo, theo dõi trạng thái giải quyết và nhận phản hồi từ giảng viên hoặc admin.
- **Xử lý yêu cầu**: Giảng viên hoặc admin có thể cập nhật trạng thái yêu cầu (đang xử lý, đã hoàn thành) và phản hồi cho sinh viên.
- **Quản lý tài khoản**: Admin có thể quản lý thông tin tài khoản của giảng viên, sinh viên và quản lý phân quyền cho từng nhóm người dùng.

### **2.3. Use Case**
1. **Đăng nhập và đăng ký**
    - **Sinh viên, giảng viên, admin**: Đăng nhập vào hệ thống bằng tài khoản trường (hoặc qua hệ thống xác thực **JWT**).
2. **Tạo yêu cầu hỗ trợ**
    - **Sinh viên**: Tạo yêu cầu hỗ trợ, chọn danh mục yêu cầu (học vụ, hành chính, tài liệu, học phí…), kiểm tra tình trạng yêu cầu, nhập nội dung và gửi đi.
3. **Quản lý và xử lý yêu cầu**
    - **Giảng viên/Admin**: Xem các yêu cầu từ sinh viên, phản hồi hoặc cập nhật trạng thái yêu cầu (đang xử lý, đã xử lý).
4. **Quản lý tài khoản**
    - **Admin**: Thêm mới, chỉnh sửa, và phân quyền cho tài khoản giảng viên, sinh viên.
5. **Quản lý danh mục:**
    - **Thêm mới danh mục**: Admin có thể thêm các danh mục mới để tổ chức các yêu cầu hỗ trợ.
    - **Chỉnh sửa danh mục**: Admin có thể cập nhật thông tin của các danh mục, chẳng hạn như tên hoặc mô tả.
    - **Xóa danh mục**: Admin có thể xóa danh mục nếu không còn cần thiết, nhưng cần đảm bảo rằng các yêu cầu cũ đã được chuyển sang danh mục khác trước khi thực hiện thao tác này.
    - **Liệt kê danh mục**: Danh sách các danh mục sẽ được hiển thị khi sinh viên tạo yêu cầu mới, giúp họ chọn đúng danh mục liên quan đến vấn đề cần hỗ trợ.
    
    ### **Biểu đồ Use Case chi tiết**
    
    **Chức năng của Guest:**
    
   ![image 1](https://github.com/user-attachments/assets/be342319-e371-4e63-bff3-2c499c066196)

    
    **Chức năng của Student(User):**
    
![image 2](https://github.com/user-attachments/assets/9226b1d6-9dbb-498c-bfd3-c8018eba3be3)


**Chức năng của Giảng viên(Staff):**

![image 3](https://github.com/user-attachments/assets/7af2bb09-355b-4d03-893f-58617bcec219)


**Chức năng của Admin:**

![image 4](https://github.com/user-attachments/assets/954bd7b3-79da-4beb-8c5e-4d3f1d378454)


### **2.4. Quy trình hoạt động**

- **Sinh viên** đăng nhập và tạo yêu cầu hỗ trợ với nội dung chi tiết.
- **Hệ thống** sẽ tự động chuyển yêu cầu đó đến giảng viên hoặc admin phụ trách lĩnh vực liên quan.
- **Giảng viên hoặc Admin** sẽ nhận yêu cầu, tiến hành xử lý và phản hồi lại sinh viên.
- **Sinh viên** nhận phản hồi hoặc cập nhật trạng thái yêu cầu từ giảng viên/admin và có thể tương tác lại nếu cần.
- **Admin** có thể giám sát toàn bộ quá trình xử lý, đảm bảo các yêu cầu của sinh viên được giải quyết đúng hạn.
1. **Quy trình gửi hỗ trợ của sinh viên**

![image 5](https://github.com/user-attachments/assets/87e9b385-9116-40dc-bd19-74ef8319b115)

1. **Quy trình tiếp nhận và xử lý yêu cầu của giảng viên**

![image 6](https://github.com/user-attachments/assets/fec2b1fe-08d9-4525-897c-53b94e75910e)

1. Quy trình quản lý danh mục của Admin

![image 7](https://github.com/user-attachments/assets/8bc1b54c-2286-434b-b192-eba9c0ed1bd5)

### **2.5 Luồng xử lý**

1. **Luồng Đăng Ký Người Dùng**

![image 8](https://github.com/user-attachments/assets/aa2b7543-535f-4bd4-9579-5693f540bcdb)

1. **Luồng Tạo và Xử Lý Ticket**

![image 9](https://github.com/user-attachments/assets/8335f2fd-e667-4954-baeb-4ef6d5ffd47f)

1. Luồng Quản Lý Tài Khoản và Phân Quyền:

![image 10](https://github.com/user-attachments/assets/d3e19677-bca9-4f8a-90f1-a3a839b82b71)

### 2.6 Luồng dữ liệu

![image 11](https://github.com/user-attachments/assets/c5b046bd-2f97-44d8-8ba6-c7e0d0825186)

### **2.7 Luồng dữ liệu**

![image 12](https://github.com/user-attachments/assets/6c9c5e90-85ac-466a-90e2-d8ceb586c1a1)

### **2.8 Các trạng thái thực thể trong hệ thống**

Trạng thái gửi ticket hỗ trợ

![image 13](https://github.com/user-attachments/assets/303d7479-0e24-4da7-b38b-53c92df3e66c)


## **Yêu cầu phi chức năng**

- **Hiệu năng**: Hệ thống phải có khả năng xử lý nhiều yêu cầu đồng thời từ sinh viên.
- **Bảo mật**: Dữ liệu cá nhân và thông tin yêu cầu của sinh viên phải được bảo mật thông qua mã hóa và quyền truy cập rõ ràng.
- **Tính khả dụng**: Hệ thống cần có thời gian hoạt động (uptime) cao, hạn chế tối đa thời gian ngưng hoạt động.
- **Tính dễ dùng**: Giao diện người dùng phải thân thiện và dễ hiểu cho cả sinh viên và giảng viên.

## **Cơ sở dữ liệu (PostgreSQL)**

![image 14](https://github.com/user-attachments/assets/456993e7-c5d4-42df-a212-6ea192c8c308)


1. **Bảng Users (Thông tin người dùng)**
    
    Lưu thông tin người dùng bao gồm sinh viên, giảng viên và admin.
    
    - **user_id**: int (PK)
    - **username**: varchar(255)
    - **password**: varchar(255)
    - **email**: varchar(255)
    - **role**: enum('student', 'staff', 'admin') - (Vai trò của user)
    - **full_name**: varchar(255)
    - **created_at**: datetime (ngày tạo account)
    - **updated_at**: datetime (ngày update thông tin account)
2. **Bảng Tickets (Yêu cầu hỗ trợ)**
    
    Lưu thông tin về các yêu cầu phiếu hỗ trợ (tickets) được tạo bởi sinh viên.
    
    - **ticket_id**: int (PK)
    - **user_id**: int (Foreign Key từ bảng Users, người tạo yêu cầu - là sinh viên)
    - **category_id**: int (Foreign Key từ bảng Categories)
    - **subject**: varchar(255) (Chủ đề của yêu cầu)
    - **description**: text (Nội dung chi tiết của yêu cầu)
    - **status**: enum('pending', 'in_progress', 'resolved', 'closed') (Trạng thái yêu cầu)
    - **created_at**: datetime
    - **updated_at**: datetime
3. **Bảng Categories (Danh mục yêu cầu)**
    
    Lưu thông tin về các loại yêu cầu hỗ trợ, phục vụ việc phân loại các ticket.
    
    - **category_id**: int (PK)
    - **name**: varchar(255) (Tên danh mục)
    - **description**: text (Mô tả danh mục)
    - **created_at**: datetime (Ngày tạo danh mục)
    - **updated_at**: datetime (Ngày update danh mục)
4. **Bảng Comments (Phản hồi)**
    
    Lưu các phản hồi (comments) từ nhiều đối tượng người dùng (sinh viên, staff (giảng viên), admin) trên từng yêu cầu hỗ trợ (ticket).
    
    - **comment_id**: int (Primary Key)
    - **ticket_id**: int (Foreign Key từ bảng Tickets)
    - **user_id**: int (Foreign Key từ bảng Users, người phản hồi)
    - **content**: text (Nội dung phản hồi)
    - **created_at**: datetime
- **Mối quan hệ giữa các bảng**
    - **Users** và **Tickets**: Quan hệ **1-n**. Một sinh viên có thể tạo nhiều yêu cầu hỗ trợ (tickets), nhưng mỗi yêu cầu chỉ có một người tạo duy nhất.
    - **Tickets** và **Categories**: Quan hệ **n-1**. Mỗi phiếu yêu cầu thuộc về một danh mục, nhưng một danh mục có thể bao gồm nhiều phiếu yêu cầu khác nhau.
    - **Users** và **Categories**: Quan hệ **n-n**. Thông qua bảng trung gian **Category_Asignees**, một giáo viên có thể quản lý nhiều danh mục, và mỗi danh mục có thể do nhiều giáo viên quản lý.
    - **Tickets** và **Comments**: Quan hệ **1-n**. Mỗi phiếu yêu cầu có thể có nhiều phản hồi từ các người dùng khác nhau.
    - **Users** và **Comments**: Quan hệ **1-n**. Một người dùng có thể gửi nhiều phản hồi cho các phiếu yêu cầu khác nhau.
