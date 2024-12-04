import { User } from '../../types/user';
import { mockUsers } from '../../data/mockUsers';

// Simulated delay for API calls
const DELAY = 1000;

export interface ProfileUpdateData {
  fullName: string;
  class?: string;
}

export interface PasswordUpdateData {
  currentPassword: string;
  newPassword: string;
}

export const getStudentProfile = async (userId: string): Promise<User> => {
  await new Promise(resolve => setTimeout(resolve, DELAY));
  const user = mockUsers.find(u => u.id === userId);
  if (!user) {
    throw new Error('Không tìm thấy thông tin sinh viên');
  }
  const { password, ...userWithoutPassword } = user;
  return userWithoutPassword;
};

export const updateStudentProfile = async (
  userId: string,
  data: ProfileUpdateData
): Promise<User> => {
  await new Promise(resolve => setTimeout(resolve, DELAY));
  
  const userIndex = mockUsers.findIndex(u => u.id === userId);
  if (userIndex === -1) {
    throw new Error('Không tìm thấy thông tin sinh viên');
  }

  mockUsers[userIndex] = {
    ...mockUsers[userIndex],
    ...data
  };

  const { password, ...updatedUser } = mockUsers[userIndex];
  return updatedUser;
};

export const updateStudentPassword = async (
  userId: string,
  data: PasswordUpdateData
): Promise<void> => {
  await new Promise(resolve => setTimeout(resolve, DELAY));

  const userIndex = mockUsers.findIndex(u => u.id === userId);
  if (userIndex === -1) {
    throw new Error('Không tìm thấy thông tin sinh viên');
  }

  if (mockUsers[userIndex].password !== data.currentPassword) {
    throw new Error('Mật khẩu hiện tại không chính xác');
  }

  mockUsers[userIndex].password = data.newPassword;
};