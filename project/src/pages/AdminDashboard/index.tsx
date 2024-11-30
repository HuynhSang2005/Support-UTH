import { useState } from 'react';
import { User } from '@/types/user';
import { SystemMetrics } from './components/Analytics/SystemMetrics';
import { UserTable } from './components/UserManagement/UserTable';
import { UserForm } from './components/UserManagement/UserForm';
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog';
import { Button } from '@/components/ui/button';
import { Plus } from 'lucide-react';
import { useToast } from '@/components/ui/use-toast';

export default function AdminDashboard() {
  const [users] = useState<User[]>([]); // Will be replaced with actual data
  const [selectedUser, setSelectedUser] = useState<User | null>(null);
  const [isUserFormOpen, setIsUserFormOpen] = useState(false);
  const { toast } = useToast();

  const handleCreateUser = (data: any) => {
    console.log('Create user:', data);
    toast({
      title: 'Tạo người dùng thành công',
      description: `Đã tạo người dùng ${data.fullName}`,
    });
    setIsUserFormOpen(false);
  };

  const handleUpdateUser = (data: any) => {
    console.log('Update user:', data);
    toast({
      title: 'Cập nhật thành công',
      description: `Đã cập nhật thông tin người dùng ${data.fullName}`,
    });
    setIsUserFormOpen(false);
    setSelectedUser(null);
  };

  const handleDeleteUser = (userId: string) => {
    console.log('Delete user:', userId);
    toast({
      title: 'Xóa người dùng thành công',
      description: 'Đã xóa người dùng khỏi hệ thống',
    });
  };

  return (
    <div className="space-y-6">
      <div className="flex items-center justify-between">
        <h1 className="text-3xl font-bold">Quản trị hệ thống</h1>
        <Button onClick={() => setIsUserFormOpen(true)}>
          <Plus className="h-4 w-4 mr-2" />
          Thêm người dùng
        </Button>
      </div>

      <SystemMetrics />

      <div className="space-y-4">
        <h2 className="text-xl font-semibold">Quản lý người dùng</h2>
        <UserTable
          users={users}
          onEdit={(user) => {
            setSelectedUser(user);
            setIsUserFormOpen(true);
          }}
          onDelete={handleDeleteUser}
        />
      </div>

      <Dialog open={isUserFormOpen} onOpenChange={setIsUserFormOpen}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {selectedUser ? 'Cập nhật người dùng' : 'Thêm người dùng mới'}
            </DialogTitle>
          </DialogHeader>
          <UserForm
            user={selectedUser || undefined}
            onSubmit={selectedUser ? handleUpdateUser : handleCreateUser}
            onCancel={() => {
              setIsUserFormOpen(false);
              setSelectedUser(null);
            }}
          />
        </DialogContent>
      </Dialog>
    </div>
  );
}