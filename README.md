# React + TypeScript + Vite

# Hệ thống Hỗ trợ UTH - Frontend

Tài liệu này cung cấp một cái nhìn tổng quan về frontend của Hệ thống Hỗ trợ UTH, được xây dựng bằng React, TypeScript và Vite.

## Công nghệ sử dụng

- **React**: Thư viện JavaScript để xây dựng giao diện người dùng.
- **TypeScript**: Superset của JavaScript, thêm các kiểu dữ liệu.
- **Vite**: Công cụ build nhanh cho phát triển.
- **React Hook Form**: Thư viện quản lý trạng thái form.
- **Zod**: Thư viện xác thực schema đầu tiên cho TypeScript.
- **Tailwind CSS**: Framework CSS theo hướng utility-first.
- **Radix UI**: Các component không có style nhưng có tính truy cập cao.

## Cấu trúc dự án

```plaintext
src/
├── components/
│   ├── ui/
│   │   ├── button.tsx
│   │   ├── input.tsx
│   │   ├── label.tsx
│   │   └── ...
├── data/
│   └── mockUsers.ts
├── hooks/
│   └── useAuth.ts
├── pages/
│   ├── Login.tsx
│   ├── RegisterForm.tsx
│   └── ...
├── services/
│   └── authService.ts
├── types/
│   ├── auth.ts
│   └── user.ts
├── lib/
│   └── validation.ts
└── styles/
    └── globals.css
```


## Các thành phần chính

### Xác thực

- **Login.tsx**: Xử lý đăng nhập người dùng với xác thực form sử dụng Zod và React Hook Form.
- **RegisterForm.tsx**: Quản lý đăng ký người dùng.
- **authService.ts**: Chứa các hàm như `loginUser`, `registerUser`, và `resetPassword` để giả lập các quy trình xác thực.
- **useAuth.ts**: Hook tùy chỉnh để quản lý trạng thái xác thực.
- **Xử lý token**: Hàm getUserFromToken trong authService.ts rất quan trọng để lấy thông tin người dùng từ token.
- **Xác thực Form**: Sử dụng nhất quán các schema Zod để đảm bảo xác thực đầu vào trên các form.
```typescript
export const registerSchema = z.object({
  fullName: z.string().min(1, 'Vui lòng nhập họ tên'),
  email: z.string()
    .min(1, 'Vui lòng nhập email')
    .email('Email không hợp lệ')
    .endsWith('@uth.edu.vn', 'Email phải là địa chỉ @uth.edu.vn'),
  studentId: z.string()
    .min(1, 'Vui lòng nhập mã số sinh viên')
    .regex(/^\d{9,}$/, 'Mã số sinh viên phải có ít nhất 9 chữ số'),
  password: z.string()
    .min(6, 'Mật khẩu phải có ít nhất 6 ký tự')
    .regex(/[A-Z]/, 'Mật khẩu phải chứa ít nhất 1 chữ hoa')
    .regex(/[0-9]/, 'Mật khẩu phải chứa ít nhất 1 số'),
  confirmPassword: z.string()
}).refine((data) => data.password === data.confirmPassword, {
  message: "Mật khẩu xác nhận không khớp",
  path: ["confirmPassword"],
});
```

### Mô hình dữ liệu

- **user.ts**: Định nghĩa các kiểu dữ liệu người dùng và vai trò.
  ```typescript
  export enum UserRole {
    STUDENT = 'student',
    STAFF = 'staff',
    ADMIN = 'admin',
  }
  ```
### Styling

 ```typescript
  @tailwind base;
  @tailwind components;
  @tailwind utilities;

  @layer base {
    :root {
      --background: 0 0% 100%;
      --foreground: 222.2 84% 4.9%;
      --card: 0 0% 100%;
      --card-foreground: 222.2 84% 4.9%;
      --popover: 0 0% 100%;
      --popover-foreground: 222.2 84% 4.9%;
      --primary: 222.2 47.4% 11.2%;
      --primary-foreground: 210 40% 98%;
      --secondary: 210 40% 96.1%;
      --secondary-foreground: 222.2 47.4% 11.2%;
      --muted: 210 40% 96.1%;
      --muted-foreground: 215.4 16.3% 46.9%;
      --accent: 210 40% 96.1%;
      --accent-foreground: 222.2 47.4% 11.2%;
    }
  }
 ```

## Cài đặt và sử dụng

1. **Cài đặt các phụ thuộc**
   ```sh
   npm install
   ```
2. **Cài đặt các phụ thuộc**
  ```sh
  npm run dev
  ```
3. **Build cho production**
  ```sh
  npm run dev
  ```
2. **Chạy các bài kiểm tra**
  ```sh
  npm run test
  ```
