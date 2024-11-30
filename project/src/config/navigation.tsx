import { RoleNavigation } from '@/types/navigation';
import { 
  TicketIcon, 
  UserIcon, 
  HomeIcon, 
  CogIcon, 
  ClipboardIcon,
  BarChartIcon
} from 'lucide-react';

// Cấu hình điều hướng cho từng role người dùng
export const navigationConfig: RoleNavigation = {
  student: [
    {
      title: 'Trang chủ',
      href: '/student',
      icon: HomeIcon
    },
    {
      title: 'Yêu cầu hỗ trợ',
      href: '/student/tickets',
      icon: TicketIcon
    },
    {
      title: 'Hồ sơ',
      href: '/student/profile',
      icon: UserIcon
    }
  ],
  staff: [
    {
      title: 'Trang chủ',
      href: '/staff',
      icon: HomeIcon
    },
    {
      title: 'Quản lý yêu cầu',
      href: '/staff/tickets',
      icon: ClipboardIcon
    },
    {
      title: 'Danh mục hỗ trợ',
      href: '/staff/categories',
      icon: CogIcon
    }
  ],
  admin: [
    {
      title: 'Trang chủ',
      href: '/admin',
      icon: HomeIcon
    },
    {
      title: 'Quản lý người dùng',
      href: '/admin/users',
      icon: UserIcon
    },
    {
      title: 'Quản lý yêu cầu',
      href: '/admin/tickets',
      icon: TicketIcon
    },
    {
      title: 'Thống kê',
      href: '/admin/statistics',
      icon: BarChartIcon
    }
  ]
};