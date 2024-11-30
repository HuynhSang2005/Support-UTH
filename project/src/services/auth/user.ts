import { User } from '@/types/user';
import { getAuthCookie } from './cookies';
import { parseToken, isTokenExpired } from './token';

export const getCurrentUser = (): User | null => {
  const token = getAuthCookie();
  if (!token) return null;

  const tokenData = parseToken(token);
  if (!tokenData || isTokenExpired(tokenData)) {
    return null;
  }

  return tokenData.user;
};