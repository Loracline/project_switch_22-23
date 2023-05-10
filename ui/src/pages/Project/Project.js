import React, {useContext} from "react";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import {selectMenu} from "../../context/Actions";
import './Project.css';

const Project = () => {
    const {state, dispatch} = useContext(AppContext);
    const data = state.detailedProject.basicInfo;
    return (
        <div className='page'>
            <section className='projectCard'>
                <div className='projectContent'>
                    <h2>{data['name']}</h2>
                    <p>Code: {data['code']}</p>
                    <p>Customer: {data['customer']}</p>
                    <p>Status: {data['status']}</p>
                    <p>Start date: {data['startDate']}</p>
                    <p>End date: {data['endDate']}</p>
                    <Button isSecundary={true} onClick={() => dispatch(selectMenu('projects'))} text="Return to projects"/>
                </div>
                <div className='buttons-project'>
                    <Button onClick={() => dispatch(selectMenu('createUserStory'))} text="Create user story"/>
                    <Button onClick={() => dispatch(selectMenu('createSprint'))} text="Create sprint"/>
                    <Button onClick={() => dispatch(selectMenu('productBacklog'))} text="Consult product backlog"/>
                </div>
            </section>
        </div>
    )
}

export default Project;