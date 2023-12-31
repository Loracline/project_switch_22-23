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
    UPDATE_SPRINT_STATUS_FAILURE,
    GET_SPRINT_BACKLOG_SUCCESS,
    FETCH_SPRINT_BACKLOG,
    POST_USER_STORY_TO_SPRINT_SUCCESS,
    SET_CURRENT_PROJECT_CODE_FROM_URL,
    SET_CURRENT_SPRINT_NUMBER_FROM_URL
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
            const code = action.payload.code;
            const selfLink = action.payload.selfLink;
            return {...state, loading: false, messageSuccess: code, selfLink: selfLink};
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
            return {...state, loading: true};

        case GET_BUSINESS_SECTORS:
            return {...state, loading: true};

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
            return {...state, loading: false, messageFailure: action.payload.error};

        case GET_BUSINESS_SECTORS_FAILURE:
            return {...state, loading: false, messageFailure: action.payload.error};

        case GET_TYPOLOGIES_FAILURE:
            return {...state, loading: false, messageFailure: action.payload.error};

        case GET_PROJECTS_SUCCESS: {
            let detailedProject;
            const projectsBE = action.payload.data;
            if(state.routerProjectCode) {
                detailedProject = projectsBE.find(project => project.code === state.routerProjectCode);
            }

            return {...state, projects: projectsBE, loading: false, detailedProject };
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
            let detailedSprint;
            const sprintsFromProject = action.payload.data;
            if(state.routerSprintNumber) {
                detailedSprint = sprintsFromProject.find(sprint => sprint.number === state.routerSprintNumber);
            }
            return {
                ...state,
                sprintsTableBody: sprintsFromProject,
                loading: false,
                detailedSprint
            }
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

        case UPDATE_SPRINT_STATUS_SUCCESS: {
            return {
                ...state,
                isSprintOpen: true,
                detailedSprint : {...state.detailedSprint, status: action.payload.toUpperCase()}
            }
        }

        case UPDATE_SPRINT_STATUS_FAILURE: {
            return {
                ...state,
                messageFailure: action.payload,

            }
        }

        case FETCH_SPRINT_BACKLOG: {
            return {
                ...state,
                loading: true
            }
        }

        case GET_SPRINT_BACKLOG_SUCCESS: {
            return {
                ...state,
                userStoriesInSprint : action.payload.data, loading: false
            }
        }

        case POST_USER_STORY_TO_SPRINT_SUCCESS: {
            return {...state,
                userStoriesInSprint:[...state.userStoriesInSprint, action.payload]
            }
        }

        case SET_CURRENT_PROJECT_CODE_FROM_URL:
            return {
                ...state,
                routerProjectCode: action.payload.projectCode,
            };

        case SET_CURRENT_SPRINT_NUMBER_FROM_URL:
            return {
                ...state,
                routerSprintNumber: action.payload.sprintNumber,
            };

        default:
            return state;
    }


};

export default reducer;