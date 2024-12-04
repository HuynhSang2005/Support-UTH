import { useCallback, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { User } from '../types/user';
import { 
  setAuthToken, 
  removeAuthToken,
  getSessionUser,
  setSessionUser,
  removeSessionUser,
  clearAuthSession
} from '../lib/auth';

export const useAuth = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState<User | null>(getSessionUser());

  const login = useCallback((token: string, userData: User) => {
    setAuthToken(token);
    setSessionUser(userData);
    setUser(userData);
    navigate(`/${userData.role}`, { replace: true });
  }, [navigate]);

  const updateUser = useCallback((updatedUser: User) => {
    setSessionUser(updatedUser);
    setUser(updatedUser);
  }, []);

  const logout = useCallback(() => {
    clearAuthSession();
    setUser(null);
    navigate('/login', { replace: true });
  }, [navigate]);

  return {
    user,
    isAuthenticated: !!user,
    role: user?.role,
    login,
    logout,
    updateUser
  };
};