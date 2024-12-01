// AuthWrapper.tsx
import { PropsWithChildren } from 'react';
import { AuthProvider } from './AuthContext';

export function AuthWrapper({ children }: PropsWithChildren) {
  return <AuthProvider>{children}</AuthProvider>;
}