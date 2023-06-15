import React, {useReducer} from 'react';
import PropTypes from 'prop-types';
import {Provider} from './AppContext.js';
import reducer from "./Reducer";

/** Component that provides the AppContext to its child components using useReducer hook.
 * @param children - The child components that will have access to AppContext.
 * @returns The provider component that wraps the child components.
 */
const menu = [
    {key: 'home', label: 'home', hidden: true},
    {key: 'createProject', label: "create project", hidden: true},
    {key: 'projects', label: "projects"},
    {key: 'sprints', label: "sprints", hidden: false},
    {key: 'createUserStory', label: "create user story", hidden: true},
    {key: 'productBacklog', label: "product backlog", hidden: true},
    {key: 'createSprint', label: "create sprint", hidden: true},
    {key: 'project', label: "project", hidden: true},
    {key: 'about', label: "about"},
    {key: 'allocateResource', label: "allocate resource", hidden: true},
    {key: 'sprint', label: "sprint", hidden: true},
    {key: 'scrumBoard', label: "scrum board", hidden: false}
]
const nav = {selectedMenu: menu[0], menu: menu,}
const detailedProject = null;
const detailedSprint = null;
const AppProvider = ({children}) => {
    const headersProjects = ["Project code", "Project name", "Customer", "Status", "Start date", "End date"];
    const usHeaders = ["US Number", "US Description", "US Status"]
    const usHeadersSprintBacklog = ["US Number", "US Description"]
    const customers = [];
    const businessSectors = [];
    const typologies = [];
    const sprintBacklog = [];

    // Represents the header and body of the sprints of a project table.
    const sprintsTableHeader = ["Sprint number", "Status", "Start date", "End date"];
    const sprintsTableBody = [];

    const initialState = {
        nav,
        headersProjects,
        sprintsTableHeader,
        sprintsTableBody,
        usHeaders,
        usHeadersSprintBacklog,
        projects: [],
        detailedProject,
        detailedSprint,
        customers,
        businessSectors,
        typologies,
        sprintBacklog,
        loading: false,
        messageFailure: '',
        messageSuccess: '',
        isSprintOpen: false
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
