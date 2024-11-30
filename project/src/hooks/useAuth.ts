import { useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { User } from '@/types/user';
import { getCurrentUser, setAuthCookie, removeAuthCookie } from '@/lib/auth';

export const useAuth = () => {
  const navigate = useNavigate();
  const user = getCurrentUser();

  const login = useCallback((token: string, userData: User) => {
    setAuthCookie(token);
    navigate(`/${userData.role}`, { replace: true });
  }, [navigate]);

  const logout = useCallback(() => {
    removeAuthCookie();
    navigate('/login', { replace: true });
  }, [navigate]);

  return {
    user,
    isAuthenticated: !!user,
    role: user?.role,
    isStudent: user?.role === 'student',
    isStaff: user?.role === 'staff',
    isAdmin: user?.role === 'admin',
    login,
    logout
  };
};