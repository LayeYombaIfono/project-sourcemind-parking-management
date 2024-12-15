import { apiPost } from './api.js';

// Gestion de l'inscription
export async function handleSignup(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const role = document.getElementById('role').value;

    const result = await apiPost('/signup', { username, email, password, role });
    if (result) {
        alert(result.message);
        if (result.success) {
            window.location.href = 'login.html';
        }
    }
}

// Gestion de la connexion
export async function handleLogin(event) {
    event.preventDefault();
    const identifier = document.getElementById('identifier').value;
    const password = document.getElementById('password').value;

    const result = await apiPost('/login', { identifier, password });
    if (result) {
        alert(result.message);
        if (result.success) {
            window.location.href = 'dashboard.html';
        }
    }
}
