import { Link } from 'react-router-dom';
import { PasswordResetForm } from '../components/auth/PasswordResetForm';

export default function PasswordReset() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6">Đặt lại mật khẩu</h1>
        <PasswordResetForm />
        <div className="mt-6 text-center text-sm">
          <Link to="/login" className="text-primary hover:underline">
            Quay lại đăng nhập
          </Link>
        </div>
      </div>
    </div>
  );
}