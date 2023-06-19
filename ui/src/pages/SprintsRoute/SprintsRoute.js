import React, {useContext, useEffect} from 'react';
import AppContext from "../../context/AppContext";
import {
    getProject,
    getSprintsFromProject,
    setCurrentProjectCodeFromURL,
    setCurrentSprintNumberFromURL
} from "../../context/Actions";
import {Outlet, useParams} from "react-router-dom";

const SprintsRoute = () => {
    const {dispatch} = useContext(AppContext);
    const {projectCode, sprintNumber} = useParams();
    useEffect(() => {
        if(sprintNumber) {
            dispatch(setCurrentSprintNumberFromURL(sprintNumber));
        }

        if (projectCode) {
            dispatch(setCurrentProjectCodeFromURL(projectCode));
            getSprintsFromProject(dispatch, projectCode);
            getProject(dispatch, projectCode);
        }

    }, [dispatch, projectCode, sprintNumber]);

    return <div>
        <Outlet/>
    </div>
}

export default SprintsRoute;