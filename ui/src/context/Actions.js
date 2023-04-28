/**
 Action types.
 */
//the following is just a dummy action, needs to be updated with proper implementation
export const CREATE_USER_STORY = 'CREATE_USER_STORY';
export const CHECK_PROJECT = 'CHECK_PROJECT';
export const SELECT_MENU = 'SELECT_MENU';

/**
 Action for creating a new user story.
 */
export const createUserStory = () => {
    return {
        type: CREATE_USER_STORY
    }
}

export const checkProject = (code) => {
    return {
        type: CHECK_PROJECT,
        payload: {
            projectToCheck: code,
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