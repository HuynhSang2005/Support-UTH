import { UserWithPassword, UserRole } from '@/types/user';

export const mockUsers: UserWithPassword[] = [
  {
    id: '1',
    email: 'student@uth.edu.vn',
    password: 'pass123',
    fullName: 'Nguyễn Văn A',
    role: UserRole.STUDENT,
    studentId: '2001234',
    class: 'CS2001'
  },
  {
    id: '2',
    email: 'staff@uth.edu.vn',
    password: '123456789',
    fullName: 'Trần Thị B',
    role: UserRole.STAFF
  },
  {
    id: '3',
    email: 'admin@uth.edu.vn',
    password: '123456789',
    fullName: 'Lê Văn C',
    role: UserRole.ADMIN
  }
];