import { RegisterFormData, PasswordResetFormData, AuthApiResponse } from '../types/auth';
import { User, UserRole, LoginCredentials } from '../types/user';
import { mockUsers } from '../data/mockUsers';


const SIMULATED_DELAY = 1000;

export async function loginUser(credentials: LoginCredentials): Promise<AuthApiResponse> {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  const user = mockUsers.find(u => u.email === credentials.email && u.password === credentials.password);

  if (!user) {
    throw new Error('Email hoặc mật khẩu không đúng');
  }

  // Giả lập token
  const token = `${user.id}-${Date.now()}`;

  return {
    success: true,
    message: 'Đăng nhập thành công',
    token,
    user,
  };
}

export async function registerUser(data: RegisterFormData): Promise<AuthApiResponse> {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  // Kiểm tra email đã tồn tại
  const existingUser = mockUsers.find(u => u.email === data.email);
  if (existingUser) {
    throw new Error('Email đã được sử dụng');
  }

  // Kiểm tra mã số sinh viên đã tồn tại
  const existingStudentId = mockUsers.find(u => u.studentId === data.studentId);
  if (existingStudentId) {
    throw new Error('Mã số sinh viên đã được sử dụng');
  }

  // Tạo người dùng mới
  const newUser: User = {
    id: (mockUsers.length + 1).toString(),
    email: data.email,
    fullName: data.fullName,
    role: UserRole.STUDENT, // Sử dụng UserRole.STUDENT
    studentId: data.studentId,
  };

  // Giả lập token
  const token = `${newUser.id}-${Date.now()}`;

  // Lưu người dùng mới (bao gồm token)
  mockUsers.push({ ...newUser, password: data.password, token });

  return {
    success: true,
    message: 'Đăng ký thành công',
    token,
    user: newUser,
  };
}

export async function resetPassword(data: PasswordResetFormData): Promise<AuthApiResponse> {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  // Tìm người dùng
  const user = mockUsers.find(u => 
    u.email === data.email && 
    u.studentId === data.studentId
  );

  if (!user) {
    throw new Error('Không tìm thấy thông tin người dùng');
  }

  // Đặt lại mật khẩu
  user.password = data.password; // Sử dụng data.password

  return {
    success: true,
    message: 'Đặt lại mật khẩu thành công',
  };
}

// Hàm giải mã token
export function getUserFromToken(token: string): User | null {
  const user = mockUsers.find(u => u.token === token);
  return user || null;
}