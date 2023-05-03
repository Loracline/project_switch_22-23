import React, {useContext} from "react";
import AppContext from "../context/AppContext";
import Button from "../components/Button/Button";
import {selectMenu} from "../context/Actions";

const Project = () => {
    const {state, dispatch} = useContext(AppContext);
    const data = state.detailedProject;
    return (
        <div className='page'>
            <h1>{data['name']}</h1>
            <p>Code: {data['code']}</p>
            <p>Customer: {data['customer']}</p>
            <p>Status: {data['status']}</p>
            <p>Start date: {data['startDate']}</p>
            <p>End date: {data['endDate']}</p>
            <Button onClick={() => dispatch(selectMenu('createUserStory'))} text="Create user story"/>
            <Button onClick={() => dispatch(selectMenu('createSprint'))} text="Create sprint"/>
            <Button onClick={() => dispatch(selectMenu('productBacklog'))} text="Consult product backlog"/>
            <Button onClick={() => dispatch(selectMenu('projects'))} text="Return to projects"/>
        </div>
    )
}

export default Project;