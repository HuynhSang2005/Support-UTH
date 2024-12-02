import { RegisterFormData, PasswordResetFormData, AuthApiResponse } from '../types/auth';
import { LoginCredentials, AuthResponse, User, UserRole, UserWithPassword } from '../types/user';
import { mockUsers } from '../data/mockUsers';
export { getCurrentUser } from './auth/user';
export { setAuthCookie, removeAuthCookie } from './auth/cookies';

const SIMULATED_DELAY = 1000;

// Store registered users in memory during the session
let registeredUsers: UserWithPassword[] = [...mockUsers];

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