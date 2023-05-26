import {postSprint} from "../services/Service";

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
export const CREATE_PROJECT ='CREATE_PROJECT';

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

/*
export const CREATE_SPRINT_2 = 'CREATE_SPRINT_2';
export const POST_SPRINT_SUCCESS = 'POST_SPRINT_SUCCESS';
export const POST_SPRINT_FAILURE = "POST_SPRINT_FAILURE";

export function createSprint2(dispatch, sprintToSubmit) {
    postSprint((res) => dispatch(postSprintSuccess(res.text())),
        (err) =>dispatch(postSprintFailure(err.message)),
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

function postSprintFailure(message) {
    return {
        type: POST_SPRINT_FAILURE,
        payload: {
            error: message
        }
    }
}*/