import {CREATE_USER_STORY} from "./Actions";

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
        default:
            return state;
    }
};

export default reducer;