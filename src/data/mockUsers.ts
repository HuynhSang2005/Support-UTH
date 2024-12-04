import { UserWithPassword, UserRole } from '../types/user';

export const mockUsers: UserWithPassword[] = [
  {
    id: '1',
    email: 'student1@uth.edu.vn',
    password: 'Pass123',
    fullName: 'Nguyễn Huỳnh Sang',
    role: UserRole.STUDENT,
    studentId: '087205009789',
    class: 'CN2303E'
  },
  {
    id: '2',
    email: 'student2@uth.edu.vn',
    password: 'Pass123',
    fullName: 'Nguyễn Thanh Tân',
    role: UserRole.STUDENT,
    studentId: '087205001234',
    class: 'CN2303E'
  },
  {
    id: '3',
    email: 'staff@uth.edu.vn',
    password: 'Pass123',
    fullName: 'Trần Thị B',
    role: UserRole.STAFF
  },
  {
    id: '4',
    email: 'admin@uth.edu.vn',
    password: 'Pass123',
    fullName: 'Lê Văn C',
    role: UserRole.ADMIN
  }
];