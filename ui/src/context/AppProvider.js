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
    {key: 'sprint', label: "sprint", hidden: false},
    {key: 'sprintBacklog', label: "sprint Backlog", hidden: true},
    {key: 'scrumBoard', label: "scrum board", hidden: true}
]
const nav = {selectedMenu: menu[0], menu: menu,}
const detailedProject = null;
const detailedSprint = null;
const AppProvider = ({children}) => {
    const headersProjects = ["Project code", "Project name", "Customer", "Status", "Start date", "End date"];
    const headersSprints = ["Sprint number", "Status", "Start date", "End date"];
    const projects = [{
        basicInfo: {
            code: "P01",
            name: "Project1",
            customer: "ISEP",
            status: "planned",
            startDate: "-",
            endDate: "-",
        },
        additionalInfo:
            {
                businessSector: "",
                typology: "",
                description: ""
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
            startDate: "2010-02-23",
            endDate: "2022-12-31",
        },
        additionalInfo:
            {
                businessSector: "",
                typology: "",
                description: ""
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
            startDate: "2010-02-21",
            endDate: "2022-12-30",
        },
        additionalInfo:
            {
                businessSector: "",
                typology: "",
                description: ""
            },
        userStories: [],
        sprints: []
    }]

    const sprints = [
        {
            id: 1,
            name: "Sprint 1",
            startDate: "2023-05-01",
            endDate: "2023-05-07",
            status: "close"
        },
        {
            id: 2,
            name: "Sprint 2",
            startDate: "2023-05-07",
            endDate: "2023-05-27",
            status: "close"
        },
        {
            id: 3,
            name: "Sprint 3",
            startDate: "2023-06-15",
            endDate: "2023-06-27",
            status: "planned"
        },


    ];


    const usHeaders = ["US Number", "US Description", "US Status"]

    const customers = [];
    const businessSectors = [];
    const typologies = [];

    const initialState = {
        nav,
        headersProjects,
        headersSprints,
        usHeaders,
        projects: [],
        sprints: [],
        detailedProject,
        detailedSprint,
        customers,
        businessSectors,
        typologies,
        loading: false,
        messageFailure: '',
        messageSuccess: '',
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
