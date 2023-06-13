import {
    CHECK_PROJECT_SPRINT,
    CREATE_SPRINT,
    SELECT_MENU,
    SELECT_PROJECT,
    GET_PROJECTS_SUCCESS,
    FETCH_FAILURE,
    POST_SPRINT_SUCCESS,
    CLOSE_BUTTON,
    GET_PROJECT_SUCCESS,
    CREATE_PROJECT,
    GET_CUSTOMERS,
    GET_CUSTOMERS_SUCCESS,
    GET_CUSTOMERS_FAILURE,
    GET_BUSINESS_SECTORS,
    GET_BUSINESS_SECTORS_SUCCESS,
    GET_BUSINESS_SECTORS_FAILURE,
    GET_TYPOLOGIES,
    GET_TYPOLOGIES_SUCCESS,
    GET_TYPOLOGIES_FAILURE,
    CREATE_PROJECT_SUCCESS,
    CREATE_PROJECT_FAILURE,
    RESET_CREATE_PROJECT,
    POST_USER_STORY,
    POST_USER_STORY_SUCCESS,
    POST_USER_STORY_FAILURE,
    RESET_POST_USER_STORY,
    FETCH_PROJECTS_STARTED,
    GET_SPRINTS_SUCCESS,
    FETCH_SPRINTS_STARTED,
    SELECT_SPRINT,
    UPDATE_SPRINT_STATUS_SUCCESS,
    UPDATE_SPRINT_STATUS_FAILURE
} from "./Actions";

/** Reducer function that updates the app state based on the dispatched actions.
 * @param state - The current state of the app.
 * @param action - The action that was dispatched by the action creator.
 * @returns The new state of the app.
 */
const reducer = (state, action) => {
    switch (action.type) {

        case CREATE_PROJECT: {
            return {
                ...state,
                loading: true
            };
        }

        case RESET_CREATE_PROJECT: {
            return {
                ...state,
                loading: false,
                messageSuccess: '',
                messageFailure: ''
            }
        }

        case CREATE_PROJECT_SUCCESS: {
            const message = action.payload.message;
            return {...state, loading: false, messageSuccess: message};
        }

        case CREATE_PROJECT_FAILURE: {
            return {...state, loading: false, messageFailure: action.payload.error};
        }

        case POST_USER_STORY: {
            return {
                ...state,
                loading: true
            }
        }

        case POST_USER_STORY_SUCCESS: {
            return {
                ...state,
                loading: false,
                messageSuccess: action.payload.data
            }
        }

        case POST_USER_STORY_FAILURE: {
            return {
                ...state,
                loading: false,
                messageFailure: action.payload.error
            }
        }

        case RESET_POST_USER_STORY: {
            return {
                ...state,
                loading: false,
                messageSuccess: '',
                messageFailure: ''
            }
        }

        case SELECT_MENU: {
            const key = action.payload.key;
            const {nav} = state;
            const menu = nav.menu.find((item) => (item.key === key));
            const newNav = {...nav, selectedMenu: menu};
            return {...state, nav: newNav,}
        }

        case CHECK_PROJECT_SPRINT: {
            const projectCode = action.payload.projectToCheck;
            return {
                ...state, selectedProject: state.projects.find((project, _) => {
                    return project.basicInfo.code === projectCode;
                })
            }
        }

        case CREATE_SPRINT: {
            const sprint = action.payload.sprintToAdd;
            const updatedProjects = [...state.projects];
            const bodyProjectIndex = state.projects.findIndex(projectSprint => {
                return projectSprint.basicInfo.code === sprint.projectCode;
            });
            updatedProjects[bodyProjectIndex].sprints.push(sprint);
            console.log(updatedProjects)
            return {...state, projects: updatedProjects}
        }

        case SELECT_PROJECT: {
            const project = action.payload.project;
            return {...state, detailedProject: project};
        }

        case GET_CUSTOMERS:

        case GET_BUSINESS_SECTORS:

        case GET_TYPOLOGIES:
            return {...state, loading: true};

        case GET_CUSTOMERS_SUCCESS: {
            const customers = action.payload.data;
            return {...state, loading: false, customers: customers};
        }

        case GET_BUSINESS_SECTORS_SUCCESS: {
            const businessSectors = action.payload.data;
            return {...state, loading: false, businessSectors: businessSectors};
        }

        case GET_TYPOLOGIES_SUCCESS: {
            const typologies = action.payload.data;
            return {...state, loading: false, typologies: typologies};
        }

        case GET_CUSTOMERS_FAILURE:

        case GET_BUSINESS_SECTORS_FAILURE:

        case GET_TYPOLOGIES_FAILURE:
            return {...state, loading: false, messageFailure: action.payload.error};

        case GET_PROJECTS_SUCCESS: {
            const projectsBE = action.payload.data;
            return {...state, projects: projectsBE, loading: false};
        }

        case FETCH_PROJECTS_STARTED: {
            return {...state, loading: true}
        }

        case FETCH_FAILURE: {
            return {...state, messageFailure: action.payload, loading: false};
        }

        case CLOSE_BUTTON: {
            return {...state, messageSuccess: '', messageFailure: ''}
        }

        case GET_PROJECT_SUCCESS: {
            const project = action.payload;
            return {...state, detailedProject: project};
        }

        case POST_SPRINT_SUCCESS: {
            return {...state, messageSuccess: action.payload};
        }

        // List all sprints from a project
        case GET_SPRINTS_SUCCESS: {
            const sprintsFromProject = action.payload.data;
            return {
                ...state,
                sprintsTableBody: sprintsFromProject,
                loading: false
            };
        }

        case FETCH_SPRINTS_STARTED: {
            return {
                ...state,
                loading: true
            }
        }

        // Select a sprint from a sprint's list
        case SELECT_SPRINT: {
            const sprint = action.payload.selected;
            return {
                ...state,
                detailedSprint: sprint
            }
        }

        case UPDATE_SPRINT_STATUS_SUCCESS:
            return {
                ...state,
                sprints: state.sprints.map((sprint, index) =>
                    index === action.payload.sprintId ? action.payload.status : sprint
                ),
            };

        case UPDATE_SPRINT_STATUS_FAILURE:
            return {
                ...state,
                messageFailure: action.payload.error,
                loading: false,
            };

        default:
            return state;
    }

};

export default reducer;