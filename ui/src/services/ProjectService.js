export const URL = 'http://localhost:8080';

export function postProject(success, failure, projectToSubmit) {
    fetch(`${URL}/projects`, {
        method: 'POST',
        body: JSON.stringify(projectToSubmit),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => {
            if (res.ok) {
                return res.text();
            } else {
                return res.text().then(text => {
                    throw text
                })
            }
        })
        .then(res => success(res))
        .catch(error => failure(error));
}

export function getBusinessSectors(success, failure) {
    fetch(`${URL}/business_sectors`, {
        method: 'GET',
    })
        .then(res => {
            if (res.ok) {
                return res.json();
            } else {
                return res.text().then(text => {
                    throw text
                })
            }
        })
        .then(res => success(res))
        .catch(err => failure(err));
}

export function getProjectTypologies(success, failure) {
    fetch(`${URL}/typologies`, {
        method: 'GET',
    })
        .then(res => {
            if (res.ok) {
                return res.json();
            } else {
                return res.text().then(text => {
                    throw text
                })
            }
        })
        .then(res => success(res))
        .catch(err => failure(err));
}

export function getCustomers(success, failure) {
    fetch(`${URL}/customers`, {
        method: 'GET',
    })
        .then(res => {
            if (res.ok) {
                return res.json();
            } else {
                return res.text().then(text => {
                    throw text
                })
            }
        })
        .then(res => success(res))
        .catch(err => failure(err));
}

export function fetchProjects(success, failure) {
    fetch(`${URL}/projects`, {
        method: 'GET',
    })
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

export function fetchProject(success, failure, id) {
    fetch(`${URL}/projects/${id}`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}
