import { User } from './user';
// Định nghĩa kiểu dữ liệu cho form đăng ký
export interface RegisterFormData {
  fullName: string;
  email: string;
  studentId: string;
  password: string;
}

// Định nghĩa kiểu dữ liệu cho form đặt lại mật khẩu
export interface PasswordResetFormData {
  lastName: string;
  firstName: string;
  email: string;
  studentId: string;
  password: string;
  confirmPassword: string;
}

// Định nghĩa kiểu dữ liệu cho phản hồi API
export interface AuthApiResponse {
  success: boolean;
  message: string;
  token?: string;
  user?: User;
  errors?: Record<string, string>;
}