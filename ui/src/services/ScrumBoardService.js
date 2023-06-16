import {API_HEADERS as headers, API_ROUTES, API_URL} from "./api";

export function getScrumBoard(projectCode) {
    return fetch(`${API_URL}${API_ROUTES.SPRINTS}/${projectCode}/${API_ROUTES.SCRUM_BOARD}`, {
        method: 'GET',
        headers,
    })
        .then(res => res.json())
}

/*
export function updateUserStoryStatus(projectCode, requestBody) {
    return fetch(`${API_URL}${API_ROUTES.USER_STORIES}/${projectCode}_${userStory.userStoryNumber}`, {
        method: 'PATCH',
        body: JSON.stringify(requestBody),
        headers,
    })
        .then(res => res.json())
}*/
