import {fetchProject, fetchProjects, postSprint} from "../services/Service";

/**
 Action types.
 */
//the following is just a dummy action, needs to be updated with proper implementation
export const CREATE_USER_STORY = 'CREATE_USER_STORY';
export const CREATE_SPRINT = 'CREATE_SPRINT';
export const CHECK_PROJECT = 'CHECK_PROJECT';
export const CHECK_PROJECT_SPRINT = 'CHECK_PROJECT_SPRINT';
export const SELECT_MENU = 'SELECT_MENU';
export const SELECT_PROJECT = 'SELECT_PROJECT';
export const CREATE_PROJECT = 'CREATE_PROJECT';

/**
 Action for creating a new project.
 */
export const createProject = (project) => {
    return {
        type: CREATE_PROJECT,
        payload: {projectToAdd: project}
    };
}

/**
 Action for creating a new user story.
 */
export const createUserStory = (userStory) => {
    return {
        type: CREATE_USER_STORY,
        payload: {
            userStory
        }
    }
}

/**
 Action for creating a new sprint.
 */
export const createSprint = (sprint) => {
    return {
        type: CREATE_SPRINT,
        payload: {
            sprintToAdd: sprint,
        },
    };
};

export const checkProject = (code) => {
    return {
        type: CHECK_PROJECT,
        payload: {
            projectToCheck: code,
        }
    }
}

export const checkProjectSprint = (projectCode) => {
    return {
        type: CHECK_PROJECT_SPRINT,
        payload: {
            projectToCheck: projectCode,
        }
    }
}

export function selectMenu(name) {
    return {
        type: SELECT_MENU,
        payload: {
            key: name
        }
    }
}

export function setCurrentProject(project) {
    return {
        type: SELECT_PROJECT,
        payload: {
            project
        }
    }
}

export const CLOSE_BUTTON = 'CLOSE_BUTTON';

export function closeButton() {
    return {
        type: CLOSE_BUTTON
    }
}

export const FETCH_FAILURE = 'FETCH_FAILURE';

export function fetchFailure(message) {
    return {
        type: FETCH_FAILURE,
        payload: message
    }
}

export const GET_PROJECT_SUCCESS = 'GET_PROJECT_SUCCESS';

export function getProject(dispatch, projectCode) {
    fetchProject((res) => dispatch(getProjectSuccess(res), (err) => fetchFailure(err.message)), projectCode)
}

export function getProjectSuccess(project) {
    return {
        type: GET_PROJECT_SUCCESS,
        payload: project
    }
}

export const POST_SPRINT_SUCCESS = 'POST_SPRINT_SUCCESS';

export function createSprint2(dispatch, sprintToSubmit) {
    postSprint((res) => dispatch(postSprintSuccess(res.text())),
        (err) =>dispatch(fetchFailure(err.message)),
        sprintToSubmit
    );
}

function postSprintSuccess(message) {
    return {
        type: POST_SPRINT_SUCCESS,
        payload: {
            message
        }
    }
}

export const GET_PROJECTS_SUCCESS = 'GET_PROJECTS_SUCCESS';

export function getProjects(dispatch) {
    fetchProjects((res) => dispatch(getProjectsSuccess(res), (err) => fetchFailure(err.message)))
}

function getProjectsSuccess(projects) {
    return {
        type: GET_PROJECTS_SUCCESS,
        payload: {
            data:
                [...projects]
        }
    }
}