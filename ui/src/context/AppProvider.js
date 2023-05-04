import React, {useReducer} from 'react';
import PropTypes from 'prop-types';
import {Provider} from './AppContext.js';
import reducer from './Reducer';


/**
 Component that provides the AppContext to its child components using useReducer hook.

 @param children - The child components that will have access to AppContext.
 @returns The provider component that wraps the child components.
 */

const menu = [
    {key: 'home', label: 'home', hidden: true},
    {key: 'createProject', label: "create project", hidden: true},
    {key: 'projects', label: "projects"},
    {key: 'createUserStory', label: "create user story", hidden: true},
    {key: 'productBacklog', label: "product backlog", hidden: true},
    {key: 'createSprint', label: "create sprint", hidden: true},
    {key: 'project', label: "project", hidden: true},
    {key: 'about', label: "about"}
]

const nav = {
    selectedMenu: menu[0],
    menu: menu,
}

const detailedProject = null;

const AppProvider = ({children}) => {
    const headersProjects = ["Project code", "Project name", "Customer", "Status", "Start date", "End date"];
    const bodyProjects = [
        {
            code: "P01",
            name: "Project1",
            customer: "ISEP",
            status: "planned",
            startDate: "21/05/2023",
            endDate: "31/12/2038",

        },
        {
            code: "P02",
            name: "Project2",
            customer: "ISEP",
            status: "finished",
            startDate: "21/02/2010",
            endDate: "31/12/2022",
        }
        ,
        {
            code: "P03",
            name: "Project3",
            customer: "Águas do Porto",
            status: "inception",
            startDate: "21/02/2010",
            endDate: "31/12/2022",
        }
    ]
    const bodyProjectsUserStories = [
        {
            code: "P01",
            userStories: [
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
                },]
        },
        {
            code: "P02",
            userStories: [
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
                },]
        }
        ,
        {
            code: "P03",
            userStories: []
        }
    ]
    const bodyProjectsSprints = [
        {
            code: "P01",
            sprints: []
        },
        {
            code: "P02",
            sprints: []
        },
        {
            code: "P03",
            sprints: []
        }
    ]
    const usHeaders = [
        "US ID",
        "US Description",
        "US Status"
    ]

    const initialState = {
        nav,
        headersProjects,
        bodyProjects,
        usHeaders,
        bodyProjectsUserStories,
        selectedProject: undefined,
        bodyProjectsSprints,
        detailedProject
    }

    const [state, dispatch] = useReducer(reducer, initialState);

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
