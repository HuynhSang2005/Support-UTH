import { Link } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';
import { Button } from '../../components/ui/button';
import { Avatar, AvatarFallback } from '@radix-ui/react-avatar';
import { LogOut } from 'lucide-react';

export function Navbar() {
  const { user, logout, role } = useAuth();
  
  const homeUrl = role ? `/${role}` : '/';

  const getInitials = (name: string) => {
    return name
      .split(' ')
      .map(word => word[0])
      .join('')
      .toUpperCase();
  };

  return (
    <nav className="border-b bg-background">
      <div className="flex h-16 items-center px-4">
        <div className="flex items-center space-x-4">
          <Link to={homeUrl} className="font-bold text-xl">
            UTH Support
          </Link>
        </div>

        <div className="ml-auto flex items-center space-x-4">
          {user && (
            <div className="flex items-center space-x-4">
              <span className="text-sm font-medium">{user.fullName}</span>
              <Avatar className="h-8 w-8">
                <AvatarFallback className="bg-primary/10">
                  {getInitials(user.fullName)}
                </AvatarFallback>
              </Avatar>
              <Button variant="ghost" size="icon" onClick={logout}>
                <LogOut className="h-4 w-4" />
              </Button>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
}