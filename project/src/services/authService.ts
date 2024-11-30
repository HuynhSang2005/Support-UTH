import { LoginCredentials, AuthResponse, User } from '@/types/user';
import { mockUsers } from '@/data/mockUsers';

const SIMULATED_DELAY = 1000;

/// Hàm mã hóa hỗ trợ Unicode
function b64EncodeUnicode(str: string) {
  return btoa(
    encodeURIComponent(str).replace(
      /%([0-9A-F]{2})/g,
      (_, p1) => String.fromCharCode(parseInt(p1, 16))
    )
  );
}

// Hàm giải mã hỗ trợ Unicode
function b64DecodeUnicode(str: string) {
  return decodeURIComponent(
    atob(str)
      .split('')
      .map((c) => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
      .join('')
  );
}

export const loginUser = async (credentials: LoginCredentials): Promise<AuthResponse> => {
  await new Promise(resolve => setTimeout(resolve, SIMULATED_DELAY));

  const user = mockUsers.find(u => 
    u.email.toLowerCase() === credentials.email.toLowerCase() &&
    u.password === credentials.password
  );
  
  if (!user) {
    throw new Error('Email hoặc mật khẩu không chính xác');
  }

  const { password, ...userWithoutPassword } = user;
  const token = b64EncodeUnicode(JSON.stringify(userWithoutPassword));

  return {
    token,
    user: userWithoutPassword
  };
};

export const getUserFromToken = (token: string): User | null => {
  try {
    return JSON.parse(b64DecodeUnicode(token));
  } catch {
    return null;
  }
};