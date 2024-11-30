import { User } from '@/types/user';
import { TOKEN_EXPIRY_DAYS } from './constants';

interface TokenData {
  user: User;
  exp: number;
}

export const createToken = (user: User): string => {
  const tokenData: TokenData = {
    user,
    exp: Date.now() + TOKEN_EXPIRY_DAYS * 24 * 60 * 60 * 1000
  };
  return btoa(JSON.stringify(tokenData));
};

export const parseToken = (token: string): TokenData | null => {
  try {
    const decoded = atob(token);
    const data = JSON.parse(decoded) as TokenData;
    
    if (!data.user || !data.exp) {
      return null;
    }
    
    return data;
  } catch {
    return null;
  }
};

export const isTokenExpired = (tokenData: TokenData): boolean => {
  return Date.now() > tokenData.exp;
};