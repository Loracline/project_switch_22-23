import {createContext} from 'react';

/**
 The AppContext object that is used to create a context for the app.
 */
const AppContext = createContext();

export const {Provider} = AppContext;


export default AppContext;