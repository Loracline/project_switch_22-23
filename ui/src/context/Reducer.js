import {CHECK_PROJECT, CREATE_USER_STORY, SELECT_MENU} from "./Actions";

/**
 Reducer function that updates the app state based on the dispatched actions.

 @param state - The current state of the app.
 @param action - The action that was dispatched by the action creator.
 @returns The new state of the app.
 */
const reducer = (state, action) => {
    switch (action.type) {
        case CREATE_USER_STORY:
            //to be updated with proper implementation
            return;
        case SELECT_MENU:
            const key = action.payload.key;
            const {nav} = state;
            const menu = nav.menu.find((item) => (item.key === key));
            const newNav = {...nav, selectedMenu: menu};
            return {
                ...state, nav: newNav,
            };
        default:
            return state;
        case CHECK_PROJECT:
            const code = action.payload.projectToCheck;

            return {
                ...state, selectedProject: state.bodyProjectsUserStories.find((project, _) => {
                    return project.code === code;
                })
            };
    }
};

export default reducer;
