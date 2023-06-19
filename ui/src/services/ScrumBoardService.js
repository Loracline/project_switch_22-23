import {API_HEADERS as headers, API_ROUTES, API_URL} from "./api";

export function getScrumBoard(projectCode) {
    return fetch(`${API_URL}${API_ROUTES.SPRINTS}/${projectCode}/${API_ROUTES.SCRUM_BOARD}`, {
        method: 'GET',
        headers,
    })
        .then(res => res.json())
}

export function updateUserStoryStatus(requestBody) {
    return fetch(`${API_URL}/${API_ROUTES.USER_STORIES}/${requestBody.usId}`, {
        method: 'PATCH',
        body: JSON.stringify(requestBody),
        headers,
    })
        .then(async response => {
            if(!response.ok) {
                throw await response.json()
            }
            return {}
        })
        .catch(error => {console.error('Error:', error)})
}
