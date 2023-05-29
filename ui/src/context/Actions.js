import {getBusinessSectors, getCustomers, getProjectTypologies, postProject, postSprint} from "../services/Service";
import {strings} from "../strings";

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

/**
 * Action to fetch business sectors
 */

export function fetchBusinessSectors(dispatch) {
    const action = {
        type: GET_BUSINESS_SECTORS
    };
    dispatch(action);
    setTimeout(() => {
        dispatch(fetchBusinessSectorsSuccess([]));
    }, 1000);
    // getBusinessSectors((res) => dispatch(fetchBusinessSectorsSuccess(res)), (_) =>
    //     dispatch(fetchBusinessSectorsFailure()));
}

function fetchBusinessSectorsSuccess(businessSectors) {
    return {
        type: GET_BUSINESS_SECTORS_SUCCESS,
        payload: {
            data: [{
                id: 'BS001',
                name: 'IT'
            }, {

                id: 'BS002',
                name: 'Technologies'
            }
            ]
            // data: [...businessSectors]
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
    setTimeout(() => {
        dispatch(fetchTypologiesSuccess([]))
    }, 1000);
    // getProjectTypologies((res) => dispatch(fetchTypologiesSuccess(res)), (_) =>
    //     dispatch(fetchTypologiesFailure()));
}

export function fetchTypologiesSuccess(typologies) {
    return {
        type: GET_TYPOLOGIES_SUCCESS,
        payload: {
            data: [{
                id: '1',
                name: 'Typology 1'
            }, {
                id: '2',
                name: 'Typology 2'
            }]
            // data: [...typologies]
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
    setTimeout(() => {
        dispatch(fetchCustomersSuccess([]));
    }, 1000);
    // getCustomers((res) => dispatch(fetchCustomersSuccess(res)), (_) =>
    //     dispatch(fetchCustomersFailure()));
}

export function fetchCustomersSuccess(customers) {
    return {
        type: GET_CUSTOMERS_SUCCESS,
        payload: {
            data: [{
                id: '1',
                name: 'Customer 1'
            }, {
                id: '2',
                name: 'Customer 2'
            }]
            // data: [...customers]
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
    setTimeout(() => {
        dispatch(postProjectSuccess('123SHUYW--@JM&H)_12'));
    }, 1000);
    // setTimeout(() => {
    //     dispatch(postProjectFailure('error message'));
    // }, 1000);
    // postProject((res) => dispatch(postProjectSuccess(res)),
    //     (err) => dispatch(postProjectFailure(err.message)),
    //     {
    //         projectName: projectToSubmit.name,
    //         projectDescription: projectToSubmit.description,
    //         businessSectorId: projectToSubmit.businessSector.id,
    //         customerId: projectToSubmit.customer.id,
    //         typologyId: projectToSubmit.typology.id
    //     }
    // );
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