import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";

export function postUserStoryInSprint(sprintId, userStory, code, success) {

    fetch(`${API_URL}${API_ROUTES.SPRINTS}${API_ROUTES.SPRINTBACKLOG}`, {
        method: 'POST',
        body: JSON.stringify({
            userStoryId: code + "_" + userStory.userStoryNumber,
            sprintId: sprintId
        }),
        headers,
    }).then(async response => {
        if (response.ok) {
            success(userStory);
        } else {
            throw await response.json()
        }
    })
}
