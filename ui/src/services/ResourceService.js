import {API_HEADERS as headers, API_ROUTES, API_URL} from './api';

export function postResource(resourceToAdd) {
    return fetch(`${API_URL}/${API_ROUTES.RESOURCES}`, {
        method: 'POST',
        body: JSON.stringify(resourceToAdd),
        headers,
    })
        .then(res => res.json())
        .then(res => {
            if(res.status !== 201) {
                throw res;
            }
            return res;
        })
}