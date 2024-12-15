import { handleSignup, handleLogin } from './auth.js';
import { loadUsers } from './admin.js';
import { loadDashboard } from './dashboard.js';

if (window.location.pathname.includes('signup.html')) {
    document.getElementById('signup-form')?.addEventListener('submit', handleSignup);
}

if (window.location.pathname.includes('login.html')) {
    document.getElementById('login-form')?.addEventListener('submit', handleLogin);
}

if (window.location.pathname.includes('admin.html')) {
    loadUsers();
}

if (window.location.pathname.includes('dashboard.html')) {
    loadDashboard();
}
