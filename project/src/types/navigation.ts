// Định nghĩa kiểu dữ liệu cho các mục điều hướng
export interface NavigationItem {
  title: string;
  href: string;
  icon?: React.ComponentType<{ className?: string }>;
}

// Định nghĩa kiểu dữ liệu cho cấu hình điều hướng theo vai trò
export interface RoleNavigation {
  student: NavigationItem[];
  staff: NavigationItem[];
  admin: NavigationItem[];
}