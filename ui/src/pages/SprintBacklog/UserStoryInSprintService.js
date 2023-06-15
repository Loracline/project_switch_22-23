import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";

export function userStoryInSprintService(sprintId, userStoryNumber, code) {

        fetch(`${API_URL}${API_ROUTES.SPRINTS}/${sprintId}${API_ROUTES.SPRINTBACKLOG}`, {
            method: 'POST',
            body: JSON.stringify({
                userStoryId: code + "_" + userStoryNumber,
                sprintId: sprintId
            }),
            headers,
        }).then(async response => {
            if (!response.ok) {
                throw await response.json()
            }
            return {}
        })
    }
