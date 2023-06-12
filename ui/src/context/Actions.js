import {
    fetchProject, fetchProjects,
    getBusinessSectors,
    getCustomers,
    getProjectTypologies,
    postProject
} from "../services/ProjectService";
import {
    fetchSprintsFromProject,
    postSprint
} from "../services/SprintService";
import {strings} from "../strings";
import {postUserStory} from "../services/UserStoryService";

/**
 Action types.
 */
export const CREATE_SPRINT = 'CREATE_SPRINT';
export const CHECK_PROJECT = 'CHECK_PROJECT';
export const CHECK_PROJECT_SPRINT = 'CHECK_PROJECT_SPRINT';
export const SELECT_MENU = 'SELECT_MENU';
export const SELECT_PROJECT = 'SELECT_PROJECT';
export const CREATE_PROJECT = 'CREATE_PROJECT';
export const RESET_CREATE_PROJECT = 'RESET_CREATE_PROJECT';
export const CREATE_PROJECT_SUCCESS = 'CREATE_PROJECT_SUCCESS';
export const CREATE_PROJECT_FAILURE = 'CREATE_PROJECT_FAILURE';
export const GET_BUSINESS_SECTORS = 'GET_BUSINESS_SECTORS';
export const GET_BUSINESS_SECTORS_SUCCESS = 'GET_BUSINESS_SECTORS_SUCCESS';
export const GET_BUSINESS_SECTORS_FAILURE = 'GET_BUSINESS_SECTORS_FAILURE';
export const GET_TYPOLOGIES = 'GET_TYPOLOGIES';
export const GET_TYPOLOGIES_SUCCESS = 'GET_TYPOLOGIES_SUCCESS';
export const GET_TYPOLOGIES_FAILURE = 'GET_TYPOLOGIES_FAILURE';
export const GET_CUSTOMERS = 'GET_CUSTOMERS';
export const GET_CUSTOMERS_SUCCESS = 'GET_CUSTOMERS_SUCCESS';
export const GET_CUSTOMERS_FAILURE = 'GET_CUSTOMERS_FAILURE';
export const POST_USER_STORY = 'POST_USER_STORY';
export const POST_USER_STORY_SUCCESS = 'POST_USER_STORY_SUCCESS';
export const POST_USER_STORY_FAILURE = 'POST_USER_STORY_FAILURE';
export const RESET_POST_USER_STORY = 'RESET_POST_USER_STORY';
export const UPDATE_SPRINT_STATUS = 'UPDATE_SPRINT_STATUS';
export const UPDATE_SPRINT_STATUS_SUCCESS = 'UPDATE_SPRINT_STATUS_SUCCESS';
export const UPDATE_SPRINT_STATUS_FAILURE = 'UPDATE_SPRINT_STATUS_FAILURE';


/**
 * Action to fetch business sectors
 */
export function fetchBusinessSectors(dispatch) {
    const action = {
        type: GET_BUSINESS_SECTORS
    };
    dispatch(action);
    getBusinessSectors((res) => dispatch(fetchBusinessSectorsSuccess(res)), (_) =>
        dispatch(fetchBusinessSectorsFailure()));
}

function fetchBusinessSectorsSuccess(businessSectors) {
    return {
        type: GET_BUSINESS_SECTORS_SUCCESS,
        payload: {
            data: [...businessSectors]
        }

    }
}

function fetchBusinessSectorsFailure() {
    return {
        type: GET_BUSINESS_SECTORS_FAILURE,
        payload: {
            error: strings.genericServerError
        }
    }
}


/**
 * Action to fetch project Typologies
 */
export function fetchTypologies(dispatch) {
    const action = {
        type: GET_TYPOLOGIES
    };
    dispatch(action);
    getProjectTypologies((res) => dispatch(fetchTypologiesSuccess(res)), (_) =>
        dispatch(fetchTypologiesFailure()));
}

export function fetchTypologiesSuccess(typologies) {
    return {
        type: GET_TYPOLOGIES_SUCCESS,
        payload: {
            data: [...typologies]
        }
    }
}

function fetchTypologiesFailure() {
    return {
        type: GET_TYPOLOGIES_FAILURE,
        payload: {
            error: strings.genericServerError
        }
    }
}


/**
 * Action to fetch project Customers
 */
export function fetchCustomers(dispatch) {
    const action = {
        type: GET_CUSTOMERS
    };
    dispatch(action);
    getCustomers((res) => {
        dispatch(fetchCustomersSuccess(res));
    }, (_) =>
        dispatch(fetchCustomersFailure()));
}

export function fetchCustomersSuccess(customers) {
    return {
        type: GET_CUSTOMERS_SUCCESS,
        payload: {
            data: [...customers]
        }
    }
}

function fetchCustomersFailure() {
    return {
        type: GET_CUSTOMERS_FAILURE,
        payload: {
            error: strings.genericServerError
        }
    }
}


/**
 Action for creating a new project.
 */
export function createProject(dispatch, projectToSubmit) {
    postProject((res) => dispatch(postProjectSuccess(res)),
        (error) => {
            console.log(JSON.stringify(error));
            dispatch(postProjectFailure(error));
        },
        {
            projectName: projectToSubmit.name,
            projectDescription: projectToSubmit.description,
            businessSectorId: projectToSubmit.businessSector.id,
            customerId: projectToSubmit.customer.taxIdNumber,
            typologyId: projectToSubmit.typology.typologyId
        }
    );
}

function postProjectSuccess(projectId) {
    return {
        type: CREATE_PROJECT_SUCCESS,
        payload: {
            message: projectId,
        }
    }
}

export function resetCreateProject() {
    return {
        type: RESET_CREATE_PROJECT
    }
}

function postProjectFailure(message) {
    return {
        type: CREATE_PROJECT_FAILURE,
        payload: {
            error: message
        }
    }
}


/**
 Action for creating a new user story.
 */
export function createUserStory(dispatch, userStoryToSubmit) {
    postUserStory((response) => dispatch(postUserStorySuccess(response)),
        (error) => dispatch(postUserStoryFailure(error.message)), userStoryToSubmit
    );
}


/**
 Function for dealing with a POST User Story with success.
 */
function postUserStorySuccess(userStoryId) {
    return {
        type: POST_USER_STORY_SUCCESS,
        payload: {
            data: userStoryId
        }
    }
}


/**
 Function for dealing with a POST User Story with failure.
 */
function postUserStoryFailure(message) {
    return {
        type: POST_USER_STORY_FAILURE,
        payload: {
            error: message
        }
    }
}


/**
 Function for resetting the data from state after a POST User Story action.
 */
export function resetPostUserStory() {
    return {
        type: RESET_POST_USER_STORY
    }
}


/**
 * Action to set the current selected menu.
 * @param name of the menu item
 * @returns {{payload: {key}, type: string}}
 */
export function selectMenu(name) {
    return {
        type: SELECT_MENU,
        payload: {
            key: name
        }
    }
}


/**
 * Action to set the current selected project.
 * @param project to be used.
 * @returns {{payload: {project}, type: string}}
 */
export function setCurrentProject(project) {
    return {
        type: SELECT_PROJECT,
        payload: {
            project
        }
    }
}


/**
 * Action to close messages
 */
export const CLOSE_BUTTON = 'CLOSE_BUTTON';

export function closeButton() {
    return {
        type: CLOSE_BUTTON
    }
}


/**
 * Generic action for fetch failure.
 */
export const FETCH_FAILURE = 'FETCH_FAILURE';

export function fetchFailure(message) {
    return {
        type: FETCH_FAILURE,
        payload: message
    }
}


/**
 * Actions to get a specific project.
 */
export const GET_PROJECT_SUCCESS = 'GET_PROJECT_SUCCESS';

export function getProject(dispatch, projectCode) {
    fetchProject((res) => dispatch(getProjectSuccess(res)), (err) => dispatch(fetchFailure(err.message)), projectCode);
}

export function getProjectSuccess(project) {
    return {
        type: GET_PROJECT_SUCCESS,
        payload: project?.[0],
    }
}


/**
 * Actions to create a sprint.
 */
export const POST_SPRINT_SUCCESS = 'POST_SPRINT_SUCCESS';

export function createSprint(dispatch, sprintToSubmit) {
    postSprint((res) => dispatch(postSprintSuccess(res)),
        (err) => dispatch(fetchFailure(err)),
        sprintToSubmit);
}

function postSprintSuccess(message) {
    return {
        type: POST_SPRINT_SUCCESS,
        payload: message
    }
}


/**
 * Actions to get all projects.
 */
export const GET_PROJECTS_SUCCESS = 'GET_PROJECTS_SUCCESS';
export const FETCH_PROJECTS_STARTED = 'FETCH_PROJECTS_STARTED';

export function getProjects(dispatch) {
    const action = {
        type: FETCH_PROJECTS_STARTED
    }
    dispatch(action);
    fetchProjects((res) => dispatch(getProjectsSuccess(res)), (err) => fetchFailure(err.message));
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


/**
 * Action to update sprint status
 */
export function updateSprintStatus(sprintId, status) {
    return (dispatch) => {
        // Dispatch action to indicate update started
        dispatch({
            type: UPDATE_SPRINT_STATUS,
        });


// Function to handle successful update
        function updateSprintStatusSuccess(message) {
            return {
                type: UPDATE_SPRINT_STATUS_SUCCESS,
                payload: message,
            };
        }

// Function to handle update failure
        function updateSprintStatusFailure(error) {
            return {
                type: UPDATE_SPRINT_STATUS_FAILURE,
                payload: error,
            };
        }
    }
}


/*
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
}*/


/**
 * Actions to list all sprints of a specific project.
 */
export const GET_SPRINTS_SUCCESS = 'GET_SPRINTS_SUCCESS'
export function getSprintsFromProjectSuccessfully(sprintsFromProject) {
    return {
        type: GET_SPRINTS_SUCCESS,
        payload: {
            data: [...sprintsFromProject]
        }
    }
}

export const FETCH_SPRINTS_STARTED = 'FETCH_SPRINTS_STARTED';
export function getSprintsFromProject(dispatch) {
    const action = {
        type: FETCH_SPRINTS_STARTED
    }
    dispatch(action);
    fetchSprintsFromProject(
        (res) => dispatch(getSprintsFromProjectSuccessfully(res)),
        (err) => fetchFailure(err.message));
}


/**
 * Action to select a sprint from a list of sprints of a project.
 */
export const SELECT_SPRINT = 'SELECT_SPRINT';
export function setCurrentSprint(sprint) {
    return {
        type: SELECT_SPRINT,
        payload: {
            selected: sprint
        }
    }
}
