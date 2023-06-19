import React, {useContext, useEffect} from 'react';
import AppContext from "../../context/AppContext";
import {getProjects, setCurrentProjectCodeFromURL} from "../../context/Actions";
import {Outlet, useParams} from "react-router-dom";

const ProjectsRoute = () => {
    const {dispatch} = useContext(AppContext);
    const {projectCode} = useParams();

    useEffect(() => {
        if (projectCode) {
            dispatch(setCurrentProjectCodeFromURL(projectCode));
        }
        getProjects(dispatch);
    }, [dispatch, projectCode]);

    return <div>
        <Outlet/>
    </div>
}

export default ProjectsRoute;