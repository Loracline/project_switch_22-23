import {API_HEADERS as headers, API_ROUTES, API_URL} from './api';

export async function postResource(resourceToAdd) {
    try {
        const response = await fetch(`${API_URL}/${API_ROUTES.RESOURCES}`, {
            method: 'POST',
            body: JSON.stringify(resourceToAdd),
            headers,
        });

        if (response.ok) {
            return {};
        } else {
            const error = await response.json();
            throw error;
        }
    } catch (error) {
        throw error;
    }
}
