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