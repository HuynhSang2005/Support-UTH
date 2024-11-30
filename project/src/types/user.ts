export enum UserRole {
  STUDENT = 'student',
  STAFF = 'staff',
  ADMIN = 'admin',
}

export interface User {
  id: string;
  email: string;
  fullName: string;
  role: UserRole;
  studentId?: string;
  class?: string;
}

export interface UserWithPassword extends User {
  password: string;
}

export interface LoginCredentials {
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: User;
}