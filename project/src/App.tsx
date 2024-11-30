import { Routes, Route } from 'react-router-dom';
import { Toaster } from '@/components/Toaster';
import { ProtectedRoute } from '@/components/auth/ProtectedRoute';
import Layout from '@/components/Layout';
import Login from '@/pages/Login';
import StudentDashboard from '@/pages/StudentDashboard';
import StaffDashboard from '@/pages/StaffDashboard';
import AdminDashboard from '@/pages/AdminDashboard';

function App() {
  return (
    <>
      <Routes>
        <Route path="/login" element={<Login />} />
        
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <Layout />
            </ProtectedRoute>
          }
        >
          <Route
            path="student"
            element={
              <ProtectedRoute requiredRole="student">
                <StudentDashboard />
              </ProtectedRoute>
            }
          />
          <Route
            path="staff"
            element={
              <ProtectedRoute requiredRole="staff">
                <StaffDashboard />
              </ProtectedRoute>
            }
          />
          <Route
            path="admin"
            element={
              <ProtectedRoute requiredRole="admin">
                <AdminDashboard />
              </ProtectedRoute>
            }
          />
        </Route>
      </Routes>
      <Toaster />
    </>
  );
}

export default App;