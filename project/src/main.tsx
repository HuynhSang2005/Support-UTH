import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { AuthWrapper } from '@/components/auth/AuthWrapper';
import App from './App';
import './styles/globals.css';

const rootElement = document.getElementById('root');
if (!rootElement) {
  throw new Error('Root element not found');
}

ReactDOM.createRoot(rootElement).render(
  <React.StrictMode>
    <AuthWrapper>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </AuthWrapper>
  </React.StrictMode>
);