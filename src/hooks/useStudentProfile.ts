import { useState } from 'react';
import { User } from '../types/user';
import { useAuth } from './useAuth';
import {
  getStudentProfile,
  updateStudentProfile,
  updateStudentPassword,
  ProfileUpdateData,
  PasswordUpdateData
} from '../services/student/profileService';

export const useStudentProfile = () => {
  const { user: authUser, updateUser } = useAuth();
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const updateProfile = async (data: ProfileUpdateData) => {
    if (!authUser?.id) return;
    
    setIsLoading(true);
    setError(null);
    
    try {
      const updatedUser = await updateStudentProfile(authUser.id, data);
      // Cập nhật thông tin user trong auth context
      updateUser(updatedUser);
      return updatedUser;
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Có lỗi xảy ra');
      throw err;
    } finally {
      setIsLoading(false);
    }
  };

  const updatePassword = async (data: PasswordUpdateData) => {
    if (!authUser?.id) return;
    
    setIsLoading(true);
    setError(null);
    
    try {
      await updateStudentPassword(authUser.id, data);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Có lỗi xảy ra');
      throw err;
    } finally {
      setIsLoading(false);
    }
  };

  return {
    isLoading,
    error,
    updateProfile,
    updatePassword
  };
};