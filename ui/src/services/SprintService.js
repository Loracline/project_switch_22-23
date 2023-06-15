import {URL} from "./ProjectService";

export function postSprint(success, failure, sprintToSubmit) {
    fetch(`${URL}/sprints`, {
        method: 'POST',
        body: JSON.stringify(sprintToSubmit),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => {
            if (res.status === 201)
                res.text().then(text => success(text));
            else res.text().then(errText => failure(errText));
        }).catch(err => failure(err.message))
    ;
}

/**
 * Fetches the sprints from a project.
 * @param success The success callback function that will be called when the request is successful. It receives the
 * response data as a parameter.
 * @param failure The failure callback function that will be called when an error occurs. It receives the error message
 * as a parameter.
 * @param projectCode The code or identifier of the project to fetch the sprints from.
 */
export function fetchSprintsFromProject(success, failure, projectCode) {
    fetch(
        `${URL}/sprints/${projectCode}`,
        {method: 'GET'})
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function patchSprintStatus(sprintId, status, success, failure) {
    fetch(`${URL}/sprints/${sprintId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8',
        },
        body: JSON.stringify({sprintId:sprintId, status:status}),
    })
        .then((response) => {
            if (response.ok) {
                success(status);
            } else response.json().then(errorObject => failure(errorObject.message));
        })
        .catch((error) => {
                failure(error.message);
            }
        );

}




