import { Routes, Route } from 'react-router-dom';
import { Toaster } from './components/Toaster';
import { ProtectedRoute } from './components/auth/ProtectedRoute';
import Layout from './components/Layout';
import Login from './pages/Login';
import Register from './pages/Register';
import PasswordReset from './pages/PasswordReset';
import StudentHome from './pages/StudentDashboard/pages/Home';
import StudentTickets from './pages/StudentDashboard/pages/Tickets';
import StudentProfile from './pages/StudentDashboard/pages/Profile';
import StaffDashboard from './pages/StaffDashboard';
import AdminDashboard from './pages/AdminDashboard';
import { TicketProvider } from './contexts/TicketContext';

function App() {
  return (
    <TicketProvider>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/reset-password" element={<PasswordReset />} />
        
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <Layout />
            </ProtectedRoute>
          }
        >
          <Route path="student" element={
            <ProtectedRoute requiredRole="student">
              <StudentHome />
            </ProtectedRoute>
          } />
          <Route path="student/tickets" element={
            <ProtectedRoute requiredRole="student">
              <StudentTickets />
            </ProtectedRoute>
          } />
          <Route path="student/profile" element={
            <ProtectedRoute requiredRole="student">
              <StudentProfile />
            </ProtectedRoute>
          } />
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
    </TicketProvider>
  );
}

export default App;