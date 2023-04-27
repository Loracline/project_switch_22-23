import React, {useReducer} from 'react';
import PropTypes from 'prop-types';
import {Provider} from './AppContext.js';
import appReducer from './Reducer';


/**
 Component that provides the AppContext to its child components using useReducer hook.

 @param children - The child components that will have access to AppContext.
 @returns The provider component that wraps the child components.
 */

const menu = [
    {key: 'about', label: "about"},
    {key:'projects', label: "projects"},
    {key:'productbacklog', label: "product backlog"},
    ]

const nav = {
    selectedMenu:menu[0],
    menus: menu,
}

const AppProvider = ({children}) => {
    const headersProjects = ["Project code", "Project name", "Customer", "Status", "Start date", "End date"];
    const bodyProjects = [
        {
            code: "P01",
            name: "Project1",
            customer: "ISEP",
            status: "planned",
            startDate: "21/05/2023",
            endDate: "31/12/2038"
        },
        {
            code: "P02",
            name: "Project2",
            customer: "ISEP",
            status: "finished",
            startDate: "21/02/2010",
            endDate: "31/12/2022"
        },
        {
            code: "P03",
            name: "Project3",
            customer: "Águas do Porto",
            status: "inception",
            startDate: "21/02/2010",
            endDate: "31/12/2022"
        }
    ]
    const headers = [
        "US ID",
        "US Description",
        "US Status"
    ]

    const bodies = [
        {
            USID: "US001",
            UsDescription: "I want to create a profile",
            UsStatus: "Planned"
        },
        {
            USID: "US002",
            UsDescription: "I want to create a project",
            UsStatus: "Planned"
        },
        {
            USID: "US003",
            UsDescription: "I want to create an account",
            UsStatus: "Planned"
        },


    ]
    const initialState = {
        nav,
        headersProjects,
        bodyProjects,
        headers,
        bodies
    }


    const [state, dispatch] = useReducer(appReducer, initialState);

    return (
        <Provider value={{
            state,
            dispatch
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
