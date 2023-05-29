import {
    CHECK_PROJECT_SPRINT,
    CREATE_SPRINT,
    CREATE_USER_STORY,
    SELECT_MENU,
    SELECT_PROJECT,
    CREATE_PROJECT,
    GET_PROJECTS_SUCCESS,
    FETCH_FAILURE,
    POST_SPRINT_SUCCESS,
    CLOSE_BUTTON,
    GET_PROJECT_SUCCESS
} from "./Actions";

/** Reducer function that updates the app state based on the dispatched actions.
 * @param state - The current state of the app.
 * @param action - The action that was dispatched by the action creator.
 * @returns The new state of the app.
 */

const reducer = (state, action) => {
    switch (action.type) {

        case CREATE_PROJECT: {
            const project = action.payload.projectToAdd;
            return {
                ...state,
                projects: [...state.projects, project]
            };

        }

        case CREATE_USER_STORY: {
            const {userStory} = action.payload;
            const updatedProjects = state.projects.map((project) => {
                if (project.basicInfo.code === userStory.projectCode) {
                    return {...project, userStories: [...project.userStories, userStory]}
                }
                return project;
            });
            /*console.log(updatedProjects)*/
            const updatedDetailedProject = updatedProjects.find((project) => project.basicInfo.code === state.detailedProject.basicInfo.code);
            return {...state, projects: updatedProjects, detailedProject: updatedDetailedProject}
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

        case CLOSE_BUTTON: {
            return {...state, messageSuccess: '', messageFailure: ''}
        }

        case GET_PROJECTS_SUCCESS: {
            const projectsBE = action.payload.project;
            return {...state, projects: projectsBE};
        }

        case GET_PROJECT_SUCCESS: {
            const project = action.payload.project;
            return {...state, detailedProject: project};
        }

        case FETCH_FAILURE: {
            return {...state, messageFailure: action.payload.message};
        }

        case POST_SPRINT_SUCCESS: {
            return {...state, messageSuccess: action.payload.message};
        }

        default:
            return state;
    }
};
export default reducer;