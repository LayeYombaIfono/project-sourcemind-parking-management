const API_BASE_URL = 'http://localhost:8080';

/**
 * Effectue une requête GET vers une URL spécifique.
 * @param {string} endpoint - Le chemin de l'API (ex: `/users`).
 * @returns {Promise<any>} - Les données récupérées.
 */
export async function apiGet(endpoint) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`);
        if (!response.ok) {
            throw new Error(`Erreur GET ${endpoint} : ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return null;
    }
}

/**
 * Effectue une requête POST vers une URL spécifique.
 * @param {string} endpoint - Le chemin de l'API (ex: `/users`).
 * @param {Object} body - Les données à envoyer dans le corps de la requête.
 * @returns {Promise<any>} - La réponse de l'API.
 */
export async function apiPost(endpoint, body) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        });
        if (!response.ok) {
            throw new Error(`Erreur POST ${endpoint} : ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return null;
    }
}

/**
 * Effectue une requête PUT vers une URL spécifique.
 * @param {string} endpoint - Le chemin de l'API (ex: `/users/:id`).
 * @param {Object} body - Les données à mettre à jour.
 * @returns {Promise<any>} - La réponse de l'API.
 */
export async function apiPut(endpoint, body) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body),
        });
        if (!response.ok) {
            throw new Error(`Erreur PUT ${endpoint} : ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return null;
    }
}

/**
 * Effectue une requête DELETE vers une URL spécifique.
 * @param {string} endpoint - Le chemin de l'API (ex: `/users/:id`).
 * @returns {Promise<any>} - La réponse de l'API.
 */
export async function apiDelete(endpoint) {
    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, {
            method: 'DELETE',
        });
        if (!response.ok) {
            throw new Error(`Erreur DELETE ${endpoint} : ${response.statusText}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return null;
    }
}
