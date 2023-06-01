export const URL = 'http://localhost:8080';

export function postUserStory(success, failure, userStoryToSubmit) {
    const request_options = {
        method: 'POST',
        body: JSON.stringify(userStoryToSubmit),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        }
    }

    fetch(`${URL}/userStories/`, request_options)
        .then(async response => {
            if (response.ok) { // When it's a 2xx status code
                return response.json();
            } else { // When it's a 4xx or 5xx status code
                throw await response.json();
            }
        })
        .then(data => success(data.userStoryId))
        .catch(error => failure(error));
}