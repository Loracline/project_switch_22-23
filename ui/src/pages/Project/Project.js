import React, {useContext} from "react";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import {selectMenu} from "../../context/Actions";
import './Project.css';

const Project = () => {
    const {state, dispatch} = useContext(AppContext);
    const data = state.detailedProject;
    return (
        <div className='page'>
            <section className='projectCard'>
                <div className='projectInfo'>
                    <div className='projectContent'>
                        <h2>{data?.['projectName']}</h2>
                        <p>Code: {data?.['code']}</p>
                        <p>Customer: {data?.['customerName']}</p>
                        <p>Status: {data?.['status']}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
                    </div>
                    <div className='buttons-project'>
                        <Button onClick={() => dispatch(selectMenu('createUserStory'))} text="Create user story"/>
                        <Button onClick={() => dispatch(selectMenu('createSprint'))} text="Create sprint"/>
                        <Button onClick={() => dispatch(selectMenu('productBacklog'))} text="Consult product backlog"/>
                        <Button onClick={() => dispatch(selectMenu('allocateResource'))} text="Add User"/>
                    </div>
                </div>
                <Button isSecundary={true} onClick={() => dispatch(selectMenu('projects'))} text="Return to projects"/>
            </section>
        </div>
    )
}

export default Project;