import Cookies from 'js-cookie';
import { AUTH_COOKIE_NAME, COOKIE_OPTIONS } from './constants';

export const getAuthCookie = (): string | undefined => {
  return Cookies.get(AUTH_COOKIE_NAME);
};

export const setAuthCookie = (token: string): void => {
  Cookies.set(AUTH_COOKIE_NAME, token, COOKIE_OPTIONS);
};

export const removeAuthCookie = (): void => {
  Cookies.remove(AUTH_COOKIE_NAME);
};