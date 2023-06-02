import {API_HEADERS as headers, API_ROUTES, API_URL} from './api';

export function postResource(resourceToAdd) {
    return fetch(`${API_URL}/${API_ROUTES.RESOURCES}`, {
        method: 'POST',
        body: JSON.stringify(resourceToAdd),
        headers,
    }).then(async response => {
        if(!response.ok) {
            throw await response.json()
        }
        return {}
    })
}