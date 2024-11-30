// AuthContext.tsx
import { createContext, useContext, PropsWithChildren, useState, useCallback, useEffect } from 'react';
import { User } from '@/types/user';
import { setAuthCookie, removeAuthCookie, getCurrentUser } from '@/lib/auth';

interface AuthContextType {
  user: User | null;
  login: (token: string, user: User) => void;
  logout: () => void;
  isAuthenticated: boolean;
}

const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: PropsWithChildren) {
  const [user, setUser] = useState<User | null>(null);

  // Initialize user from cookie
  useEffect(() => {
    const currentUser = getCurrentUser();
    if (currentUser) {
      setUser(currentUser);
    }
  }, []);

  const login = useCallback((token: string, userData: User) => {
    setAuthCookie(token);
    setUser(userData);
  }, []);

  const logout = useCallback(() => {
    removeAuthCookie();
    setUser(null);
  }, []);

  const value = {
    user,
    login,
    logout,
    isAuthenticated: !!user
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
}