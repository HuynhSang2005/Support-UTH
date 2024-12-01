import { Link } from 'react-router-dom';
import { RegisterForm } from '../components/auth/RegisterForm';

export default function Register() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 className="text-2xl font-bold text-center mb-6">Đăng ký tài khoản</h1>
        <RegisterForm />
        <div className="mt-6 text-center text-sm">
          <span className="text-muted-foreground">Đã có tài khoản? </span>
          <Link to="/login" className="text-primary hover:underline">
            Đăng nhập
          </Link>
        </div>
      </div>
    </div>
  );
}