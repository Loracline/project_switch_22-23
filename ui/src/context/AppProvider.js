import React, {useReducer} from 'react';
import PropTypes from 'prop-types';
import {Provider} from './AppContext.js';
import reducer from './Reducer';

/** Component that provides the AppContext to its child components using useReducer hook.
 * @param children - The child components that will have access to AppContext.
 * @returns The provider component that wraps the child components.
 */
const menu = [{
    key: 'home',
    label: 'home',
    hidden: true
}, {key: 'createProject', label: "create project", hidden: true}, {
    key: 'projects',
    label: "projects"
}, {key: 'createUserStory', label: "create user story", hidden: true}, {
    key: 'productBacklog',
    label: "product backlog",
    hidden: true
}, {key: 'createSprint', label: "create sprint", hidden: true}, {
    key: 'project',
    label: "project",
    hidden: true
}, {key: 'about', label: "about"}]
const nav = {selectedMenu: menu[0], menu: menu,}
const detailedProject = null;
const AppProvider = ({children}) => {
    const headersProjects = ["Project code", "Project name", "Customer", "Status", "Start date", "End date"];
    const projects = [{
        basicInfo: {
            code: "P01",
            name: "Project1",
            customer: "ISEP",
            status: "planned",
            startDate: "21/05/2023",
            endDate: "31/12/2038",
        },
        userStories: [{
            projectCode: "P01",
            userStoryNumber: "US001",
            userStoryText: "I want to create a profile",
            actor: "",
            acceptanceCriteria: [],
            status: "Planned",
            priority: undefined,
        }, {
            projectCode: "P01",
            userStoryNumber: "US002",
            userStoryText: "I want to create a project",
            actor: "",
            acceptanceCriteria: [],
            status: "Finished",
            priority: undefined,
        }, {
            projectCode: "P01",
            userStoryNumber: "US003",
            userStoryText: "I want to create an account",
            actor: "",
            acceptanceCriteria: [],
            status: "Blocked",
            priority: undefined,
        },],
        sprints: []
    }, {
        basicInfo: {
            code: "P02",
            name: "Project2",
            customer: "ISEP",
            status: "finished",
            startDate: "21/02/2010",
            endDate: "31/12/2022",
        },
        userStories: [{
            projectCode: "P01",
            userStoryNumber: "US001",
            userStoryText: "I want to create a profile",
            actor: "",
            acceptanceCriteria: [],
            status: "Planned",
            priority: undefined,
        }, {
            projectCode: "P01",
            userStoryNumber: "US002",
            userStoryText: "I want to create a project",
            actor: "",
            acceptanceCriteria: [],
            status: "Finished",
            priority: undefined,
        }, {
            projectCode: "P01",
            userStoryNumber: "US003",
            userStoryText: "I want to create an account",
            actor: "",
            acceptanceCriteria: [],
            status: "Blocked",
            priority: undefined,
        },],
        sprints: []
    }, {
        basicInfo: {
            code: "P03",
            name: "Project3",
            customer: "Águas do Porto",
            status: "inception",
            startDate: "21/02/2010",
            endDate: "31/12/2022",
        }, userStories: [], sprints: []
    }]

    const usHeaders = ["US ID", "US Description", "US Status"]
    const initialState = {
        nav,
        headersProjects,
        projects,
        usHeaders,
        selectedProject: undefined,
        detailedProject
    }
    const [state, dispatch] = useReducer(reducer, initialState);
    return (<Provider value={{state, dispatch}}> {children} </Provider>);
};
/** Defines the prop types for the AppProvider component. @memberof AppProvider @property {Object} propTypes - The prop types that are passed to the component. @property {ReactNode} propTypes.children - The child components that will have access to AppContext. */AppProvider.propTypes = {children: PropTypes.node,};
export default AppProvider;