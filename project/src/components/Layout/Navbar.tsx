import { Link } from 'react-router-dom';
import { useAuth } from '@/hooks/useAuth';
import { Button } from '@/components/ui/button';
import { Avatar, AvatarFallback } from '@radix-ui/react-avatar';
import { LogOut } from 'lucide-react';

export function Navbar() {
  const { user, logout } = useAuth();

  return (
    <nav className="border-b bg-background">
      <div className="flex h-16 items-center px-4">
        <div className="flex items-center space-x-4">
          <Link to="/" className="font-bold text-xl">
            UTH Support
          </Link>
        </div>

        <div className="ml-auto flex items-center space-x-4">
          <div className="flex items-center space-x-4">
            <Avatar className="h-8 w-8">
              <AvatarFallback className="bg-primary/10">
                {user?.fullName.charAt(0)}
              </AvatarFallback>
            </Avatar>
            <div>
              <p className="text-sm font-medium">{user?.fullName}</p>
              <p className="text-xs text-muted-foreground">{user?.email}</p>
            </div>
          </div>
          <Button variant="ghost" size="icon" onClick={logout}>
            <LogOut className="h-5 w-5" />
          </Button>
        </div>
      </div>
    </nav>
  );
}