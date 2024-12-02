# React + TypeScript + Vite

# Hệ thống Hỗ trợ UTH - Frontend

Tài liệu này cung cấp một cái nhìn tổng quan về frontend của Hệ thống Hỗ trợ UTH, được xây dựng bằng React, TypeScript và Vite.

## Công nghệ sử dụng

- **React**: Thư viện JavaScript để xây dựng giao diện người dùng.
- **TypeScript**: Superset của JavaScript, thêm các kiểu dữ liệu.
- **Vite**: Công cụ build nhanh cho phát triển.
- **React Hook Form**: Thư viện quản lý trạng thái form.
- **React Auth Kit**: Thư viện quản lý xác thực người dùng cho React.
- **Zod**: Thư viện xác thực schema đầu tiên cho TypeScript.
- **Tailwind CSS**: Framework CSS theo hướng utility-first.
- **Shadcn Ui**: Bộ component UI được xây dựng trên Radix UI với các style sẵn có.

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

```typecript
// Hàm hỗ trợ mã hóa Unicode sang Base64
function b64EncodeUnicode(str: string): string {
  return btoa(
    encodeURIComponent(str).replace(/%([0-9A-F]{2})/g, function (match, p1) {
      return String.fromCharCode(parseInt(p1, 16));
    })
  );
}

// Hàm hỗ trợ giải mã Base64 sang Unicode
function b64DecodeUnicode(str: string): string {
  return decodeURIComponent(
    Array.prototype.map
      .call(atob(str), (c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
      .join('')
  );
}

// Hàm tạo token
function createToken(user: User): string {
  const userData = JSON.stringify({ user });
  const token = b64EncodeUnicode(userData);
  return token;
}

// Hàm đăng nhập người dùng
export async function loginUser(credentials: LoginCredentials): Promise<AuthResponse> {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  // So sánh email không phân biệt chữ hoa chữ thường
  const user = registeredUsers.find(u =>
    u.email.toLowerCase() === credentials.email.toLowerCase() &&
    u.password === credentials.password
  );

  if (!user) {
    throw new Error('Email hoặc mật khẩu không chính xác');
  }

  const { password, ...userWithoutPassword } = user;
  const token = createToken(userWithoutPassword);

  return {
    token,
    user: userWithoutPassword
  };
}

// Hàm đăng ký người dùng mới
export async function registerUser(data: RegisterFormData): Promise<AuthApiResponse> {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  // Kiểm tra email đã tồn tại
  const existingUser = registeredUsers.find(u => u.email === data.email);
  if (existingUser) {
    throw new Error('Email đã được sử dụng');
  }

  // Kiểm tra mã số sinh viên đã tồn tại
  const existingStudentId = registeredUsers.find(u => u.studentId === data.studentId);
  if (existingStudentId) {
    throw new Error('Mã số sinh viên đã được sử dụng');
  }

  // Tạo người dùng mới
  const newUser: UserWithPassword = {
    id: (registeredUsers.length + 1).toString(),
    email: data.email,
    password: data.password,
    fullName: data.fullName,
    role: UserRole.STUDENT,
    studentId: data.studentId,
    class: 'NEW'
  };

  // Thêm người dùng mới vào mảng registeredUsers
  registeredUsers.push(newUser);

  return {
    success: true,
    message: 'Đăng ký thành công'
  };
}

// Hàm đặt lại mật khẩu
export async function resetPassword(data: PasswordResetFormData): Promise<AuthApiResponse> {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  const userIndex = registeredUsers.findIndex(u => 
    u.email === data.email && 
    u.studentId === data.studentId
  );

  if (userIndex === -1) {
    throw new Error('Không tìm thấy thông tin người dùng');
  }

  // Cập nhật mật khẩu người dùng
  registeredUsers[userIndex] = {
    ...registeredUsers[userIndex],
    password: data.password
  };

  return {
    success: true,
    message: 'Đặt lại mật khẩu thành công'
  };
}

// Hàm giải mã token và trả về thông tin người dùng
export function getUserFromToken(token: string): User | null {
  try {
    const decodedData = b64DecodeUnicode(token);
    const data = JSON.parse(decodedData);
    return data.user;
  } catch (error) {
    return null;
  }
}
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
