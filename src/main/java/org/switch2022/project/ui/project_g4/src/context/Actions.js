/**
 Action types.
 */
//the following is just a dummy action, needs to be updated with proper implementation
export const CREATE_USER_STORY = 'CREATE_USER_STORY';

/**
 Action for creating a new user story.
 */
export const createUserStory = () => {
    return {
        type: CREATE_USER_STORY
    }
}
