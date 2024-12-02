import * as z from 'zod';

// Schema xác thực cho form đăng ký
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
    .regex(/[0-9]/, 'Mật khẩu phải chứa ít nhất 1 số')
});

// Schema xác thực cho form đặt lại mật khẩu
export const passwordResetSchema = z.object({
  lastName: z.string().min(1, 'Vui lòng nhập họ'),
  firstName: z.string().min(1, 'Vui lòng nhập tên'),
  email: z.string()
    .min(1, 'Vui lòng nhập email')
    .email('Email không hợp lệ')
    .endsWith('@uth.edu.vn', 'Email phải là địa chỉ @uth.edu.vn'),
  studentId: z.string()
    .min(1, 'Vui lòng nhập mã số sinh viên')
    .regex(/^\d{9,}$/, 'Mã số sinh viên phải có ít nhất 9 chữ số'),
  password: z.string()
    .min(6, 'Mật khẩu phải có ít nhất 8 ký tự')
    .regex(/[A-Z]/, 'Mật khẩu phải chứa ít nhất 1 chữ hoa')
    .regex(/[0-9]/, 'Mật khẩu phải chứa ít nhất 1 số'),
  confirmPassword: z.string()
}).refine((data) => data.password === data.confirmPassword, {
  message: "Mật khẩu xác nhận không khớp",
  path: ["confirmPassword"],
});