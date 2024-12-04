import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as z from 'zod';
import { Button } from '../../../../components/ui/button';
import { Input } from '../../../../components/ui/input';
import { Label } from '../../../../components/ui/label';
import { useToast } from '../../../../components/ui/use-toast';
import { User } from '../../../../types/user';
import { useStudentProfile } from '../../../../hooks/useStudentProfile';
import { ProfileUpdateData } from '../../../../services/student/profileService';

const profileSchema = z.object({
  fullName: z.string().min(1, 'Vui lòng nhập họ tên'),
  email: z.string().email('Email không hợp lệ'),
  studentId: z.string().min(1, 'Vui lòng nhập mã số sinh viên'),
  class: z.string().min(1, 'Vui lòng nhập lớp'),
});

type ProfileFormData = z.infer<typeof profileSchema>;

interface ProfileFormProps {
  user: User;
  onSubmit: (data: ProfileUpdateData) => Promise<void>;
}

export function ProfileForm({ user, onSubmit }: ProfileFormProps) {
  const { toast } = useToast();
  const { updateProfile , isLoading } = useStudentProfile();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<ProfileFormData>({
    resolver: zodResolver(profileSchema),
    defaultValues: user,
  });

  const handleFormSubmit = async (data: ProfileFormData) => {
    try {
      const updatedUser = await updateProfile({
        fullName: data.fullName,
        class: data.class
      });
      
      if (updatedUser) {
        // Đảm bảo gọi onSubmit với dữ liệu mới
        await onSubmit({
          fullName: updatedUser.fullName,
          class: updatedUser.class || ''
        });

        toast({
          title: 'Cập nhật thành công',
          description: 'Thông tin cá nhân đã được cập nhật',
        });
      }
    } catch (error) {
      toast({
        variant: 'destructive',
        title: 'Lỗi',
        description: error instanceof Error ? error.message : 'Có lỗi xảy ra khi cập nhật thông tin cá nhân',
      });
    }
  };

  return (
    <form onSubmit={handleSubmit(handleFormSubmit)} className="space-y-4">
      <div className="space-y-2">
        <Label htmlFor="fullName">Họ và tên</Label>
        <Input id="fullName" {...register('fullName')} />
        {errors.fullName && (
          <p className="text-sm text-red-500">{errors.fullName.message}</p>
        )}
      </div>
      <div className="space-y-2">
        <Label htmlFor="email">Email</Label>
        <Input id="email" type="email" {...register('email')} disabled />
        {errors.email && (
          <p className="text-sm text-red-500">{errors.email.message}</p>
        )}
      </div>
      <div className="space-y-2">
        <Label htmlFor="studentId">Mã số sinh viên</Label>
        <Input id="studentId" {...register('studentId')} disabled />
        {errors.studentId && (
          <p className="text-sm text-red-500">{errors.studentId.message}</p>
        )}
      </div>
      <div className="space-y-2">
        <Label htmlFor="class">Lớp</Label>
        <Input id="class" {...register('class')} />
        {errors.class && (
          <p className="text-sm text-red-500">{errors.class.message}</p>
        )}
      </div>
      <Button type="submit" disabled={isLoading}>
        {isLoading ? 'Đang cập nhật...' : 'Cập nhật'}
      </Button>
    </form>
  );
}