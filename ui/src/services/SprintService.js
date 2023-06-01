import {URL} from "./ProjectService";

export function postSprint(success, failure, sprintToSubmit) {
    fetch(`${URL}/sprints`, {
        method: 'POST',
        body: JSON.stringify(sprintToSubmit),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then(res => {console.log(res.status); return res.text()})
        .then(res => {if (res.status === 201) {
            success(res)
        } else {
           failure(res)
        }
        })
        //.catch(err => failure(err.message))
    ;
}