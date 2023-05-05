import {
    CHECK_PROJECT,
    CHECK_PROJECT_SPRINT,
    CREATE_SPRINT,
    CREATE_USER_STORY,
    SELECT_MENU,
    SELECT_PROJECT
} from "./Actions";


/**
 Reducer function that updates the app state based on the dispatched actions.

 @param state - The current state of the app.
 @param action - The action that was dispatched by the action creator.
 @returns The new state of the app.
 */
const reducer = (state, action) => {
    switch (action.type) {
        case CREATE_USER_STORY: {
            const {userStory} = action.payload;

            const updatedProjects = state.projects.map(
                (project) => {
                    if (project.basicInfo.code === userStory.projectCode) {
                        return {
                            ...project,
                            userStories: [...project.userStories, userStory]
                        }
                    }
                    return project;
                })
            /*console.log(updatedProjects)*/
            return {
                ...state, projects: updatedProjects,
            }
        }


        case SELECT_MENU: {
            const key = action.payload.key;
            const {nav} = state;
            const menu = nav.menu.find((item) => (item.key === key));
            const newNav = {...nav, selectedMenu: menu};
            return {
                ...state, nav: newNav,
            }
        }


        case CHECK_PROJECT: {
            const code = action.payload.projectToCheck;
            return {
                ...state, selectedProject: state.bodyProjectsUserStories.find((project, _) => {
                    return project.code === code;
                })
            }
        }


        case CHECK_PROJECT_SPRINT: {
            const projectCode = action.payload.projectToCheck;

            return {
                ...state, selectedProject: state.bodyProjectsSprints.find((project, _) => {
                    return project.code === projectCode;
                })
            }
        }


        case CREATE_SPRINT: {
            const sprint = action.payload.sprintToAdd;
            const updatedBodyProjectsSprints = [...state.bodyProjectsSprints];
            const bodyProjectSprintIndex = state.bodyProjectsSprints.findIndex(projectSprint => {
                return projectSprint.code === state.selectedProject.code;
            });
            updatedBodyProjectsSprints[bodyProjectSprintIndex].sprints.push(sprint);
            return {...state, bodyProjectsSprints: updatedBodyProjectsSprints}
        }

        case SELECT_PROJECT: {
            const project = action.payload.project;
            return{...state, detailedProject: project};
        }

        default:
            return state;
    }
};

export default reducer;
