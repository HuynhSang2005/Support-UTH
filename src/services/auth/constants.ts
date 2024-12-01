export const AUTH_COOKIE_NAME = '_auth';
export const TOKEN_EXPIRY_DAYS = 1;

export const COOKIE_OPTIONS = {
  expires: TOKEN_EXPIRY_DAYS,
  secure: window.location.protocol === 'https:',
  sameSite: 'Lax' as const
} as const;