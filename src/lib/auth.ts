import Cookies from 'js-cookie';
import { User } from '../types/user';

const AUTH_TOKEN_KEY = '_auth_token';
const USER_STORAGE_KEY = 'user_session';

// Token management functions
export function setAuthToken(token: string): void {
  Cookies.set(AUTH_TOKEN_KEY, token, {
    expires: 1, // 1 day
    secure: window.location.protocol === 'https:',
    sameSite: 'Lax'
  });
}

export function getAuthToken(): string | undefined {
  return Cookies.get(AUTH_TOKEN_KEY);
}

export function removeAuthToken(): void {
  Cookies.remove(AUTH_TOKEN_KEY);
}

// Session management functions
export function setSessionUser(user: User): void {
  localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user));
}

export function getSessionUser(): User | null {
  const userStr = localStorage.getItem(USER_STORAGE_KEY);
  if (!userStr) return null;
  try {
    return JSON.parse(userStr);
  } catch {
    return null;
  }
}

export function getCurrentUser(): User | null {
  const token = getAuthToken();
  if (!token) return getSessionUser();
  
  return getSessionUser();
}

export function removeSessionUser(): void {
  localStorage.removeItem(USER_STORAGE_KEY);
}

// Combined session cleanup
export function clearAuthSession(): void {
  removeAuthToken();
  removeSessionUser();
}