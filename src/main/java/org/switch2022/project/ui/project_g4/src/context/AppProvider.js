import React, {useReducer} from 'react';
import PropTypes from 'prop-types';
import {Provider} from './AppContext.js';
import appReducer from './Reducer';

/**
 Component that provides the AppContext to its child components using useReducer hook.

 @param children - The child components that will have access to AppContext.
 @returns The provider component that wraps the child components.
 */
const AppProvider = ({children}) =>{
    const initialState = {
        //this is just a dummy initial state, needs to be updated with proper implementation
        userStories: []
    }

    const [state, dispatch] = useReducer(appReducer, initialState);

    return (
        <Provider value={{
            state,
            dispatch,
        }}>
            {children}
        </Provider>
    );
};

/**
 Defines the prop types for the AppProvider component.

 @memberof AppProvider
 @property {Object} propTypes - The prop types that are passed to the component.
 @property {ReactNode} propTypes.children - The child components that will have access to AppContext.
 */
AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;
