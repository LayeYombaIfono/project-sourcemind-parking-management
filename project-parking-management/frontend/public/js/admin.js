import { apiGet, apiPost, apiPut, apiDelete } from './api.js';

// Charger les utilisateurs
export async function loadUsers() {
    const users = await apiGet('/users');
    if (users) {
        const userList = document.getElementById('user-list');
        userList.innerHTML = '';
        users.forEach(user => {
            const row = document.createElement('tr');
            row.innerHTML = `
        <td class="border p-2">${user.id}</td>
        <td class="border p-2">${user.username}</td>
        <td class="border p-2">${user.email}</td>
        <td class="border p-2">${user.role}</td>
        <td class="border p-2">
          <button class="bg-yellow-500 text-white p-1 rounded edit-btn" data-id="${user.id}">Modifier</button>
          <button class="bg-red-500 text-white p-1 rounded delete-btn" data-id="${user.id}">Supprimer</button>
        </td>
      `;
            userList.appendChild(row);
        });

        document.querySelectorAll('.edit-btn').forEach(btn => btn.addEventListener('click', editUser));
        document.querySelectorAll('.delete-btn').forEach(btn => btn.addEventListener('click', deleteUser));
    }
}

// Modifier un utilisateur
export async function editUser(event) {
    const userId = event.target.dataset.id;
    const username = prompt('Entrez le nouveau nom d\'utilisateur :');
    const email = prompt('Entrez le nouvel email :');
    const role = prompt('Entrez le nouveau rôle (admin, manager, employee) :');

    const result = await apiPut(`/users/${userId}`, { username, email, role });
    if (result) {
        alert(result.message);
        loadUsers();
    }
}

// Supprimer un utilisateur
export async function deleteUser(event) {
    const userId = event.target.dataset.id;
    const result = await apiDelete(`/users/${userId}`);
    if (result) {
        alert(result.message);
        loadUsers();
    }
}
