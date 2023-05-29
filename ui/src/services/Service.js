export const URL = 'localhost:3000';

export function postSprint(success, failure, sprintToSubmit) {
    fetch(`${URL}/sprints`, {
        method: 'POST',
        body: JSON.stringify({sprintToSubmit}),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => res.text()) // Read response as text)
        .then(res => success(res))
        .catch(err => failure(err.message))
    ;
}

export function postProject(success, failure, projectToSubmit) {
    fetch(`${URL}/projects`, {
        method: 'POST',
        body: JSON.stringify(projectToSubmit),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message))
    ;
}

export function getBusinessSectors(success, failure) {
    fetch(`${URL}/business-sectors`, {
        method: 'GET',
    })
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function getProjectTypologies(success, failure) {
    fetch(`${URL}/typologies`, {
        method: 'GET',
    })
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function getCustomers(success, failure) {
    fetch(`${URL}/customers`, {
        method: 'GET',
    })
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}


export function fetchProject(success, failure, id) {
    fetch(`${URL}/projects?projectCode=${id}`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message))
}