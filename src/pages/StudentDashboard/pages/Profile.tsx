import { Card, CardContent, CardHeader, CardTitle } from '../../../components/ui/card';
import { ProfileForm } from '../components/Profile/ProfileForm';
import { PasswordForm } from '../components/Profile/PasswordForm';
import { useAuth } from '../../../hooks/useAuth';
import { ProfileUpdateData } from '../../../services/student/profileService';

export default function StudentProfile() {
  const { user, updateUser } = useAuth(); // Thêm updateUser từ useAuth

  if (!user) return null;

  return (
    <div className="space-y-6">
      <h1 className="text-3xl font-bold">Hồ sơ sinh viên</h1>

      <div className="grid gap-6">
        <Card>
          <CardHeader>
            <CardTitle>Thông tin cá nhân</CardTitle>
          </CardHeader>
          <CardContent>
            <ProfileForm
              user={user}
              onSubmit={async (data: ProfileUpdateData) => {
                updateUser({
                  ...user,
                  ...data
                });
                console.log('Update profile:', data);
              }}
            />
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Đổi mật khẩu</CardTitle>
          </CardHeader>
          <CardContent>
            <PasswordForm
              onSubmit={async (data) => {
                console.log('Change password:', data);
              }}
            />
          </CardContent>
        </Card>
      </div>
    </div>
  );
}