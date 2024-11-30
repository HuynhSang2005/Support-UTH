import { Link, useLocation } from 'react-router-dom';
import { useAuth } from '@/hooks/useAuth';
import { navigationConfig } from '@/config/navigation';
import { cn } from '@/lib/utils';

export function Sidebar() {
  const { role } = useAuth();
  const location = useLocation();
  
  if (!role) return null;
  
  const navigation = navigationConfig[role];

  return (
    <aside className="hidden md:flex h-screen w-64 flex-col fixed left-0 top-16 border-r bg-background">
      <div className="flex flex-col gap-2 p-4">
        {navigation.map((item) => {
          const Icon = item.icon;
          return (
            <Link
              key={item.href}
              to={item.href}
              className={cn(
                "flex items-center gap-3 rounded-lg px-3 py-2 text-sm transition-colors",
                location.pathname === item.href
                  ? "bg-primary text-primary-foreground"
                  : "hover:bg-accent"
              )}
            >
              {Icon && <Icon className="h-4 w-4" />}
              {item.title}
            </Link>
          );
        })}
      </div>
    </aside>
  );
}