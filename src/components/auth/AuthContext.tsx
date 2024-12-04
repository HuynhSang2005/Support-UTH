import { createContext, useContext, PropsWithChildren, useState, useCallback, useEffect } from 'react';
import { User } from '../../types/user';
import { 
  setAuthToken as setAuthCookie,
  removeAuthToken as removeAuthCookie,
  getCurrentUser,
  setSessionUser
} from '../../lib/auth';

interface AuthContextType {
  user: User | null;
  login: (token: string, user: User) => void;
  logout: () => void;
  isAuthenticated: boolean;
}

const AuthContext = createContext<AuthContextType | null>(null);

export function AuthProvider({ children }: PropsWithChildren) {
  const [user, setUser] = useState<User | null>(getCurrentUser());

  const login = useCallback((token: string, userData: User) => {
    setAuthCookie(token);
    setSessionUser(userData);
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