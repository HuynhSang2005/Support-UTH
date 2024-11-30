import Cookies from 'js-cookie';
import { User } from '@/types/user';
import { getUserFromToken } from '@/services/authService';

const AUTH_COOKIE_NAME = '_auth';

export const getAuthCookie = () => {
  return Cookies.get(AUTH_COOKIE_NAME);
};

export const setAuthCookie = (token: string) => {
  Cookies.set(AUTH_COOKIE_NAME, token, {
    expires: 1, // 1 day
    secure: window.location.protocol === 'https:',
    sameSite: 'Lax'
  });
};

export const removeAuthCookie = () => {
  Cookies.remove(AUTH_COOKIE_NAME);
};

export const getCurrentUser = (): User | null => {
  const token = getAuthCookie();
  if (!token) return null;
  return getUserFromToken(token);
};