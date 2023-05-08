import React, {useContext, useState} from 'react';
import {TextField} from '@mui/material';
import {createSprint, selectMenu} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import './CreateSprint.css';

function CreateSprint() {
    const { state, dispatch} = useContext(AppContext);
    const { detailedProject } = state;
    const [selectedDate, setSelectedDate] = useState(new Date());
    const initialSprintState = {
        projectCode: detailedProject.code,
        date: '',
    };
    const [sprintToSubmit, setSprintToSubmit] = useState(initialSprintState);
    const handleDateChange = (event) => {
        setSelectedDate(event.target.value);
        setSprintToSubmit({
            ...sprintToSubmit,
            date: event.target.value,
        });
    };
    const handleSubmit = (event) => {
        event.preventDefault();
        if (sprintToSubmit.date.length === 0) {
            alert('Please, insert initial date.');
        } else if (new Date(sprintToSubmit.date) < new Date()) {
            alert('Please select a future date for the start date.');
        } else {
            dispatch(createSprint({
                ...sprintToSubmit
            }));
            window.alert('The sprint was successfully created.');
            setSprintToSubmit(initialSprintState);
        }
    };

    if (!detailedProject) return null;

    return (
        <div className="page">
            <section className="formCard">
                <h2> Create Sprint </h2>
                <form className="sprint-form" onSubmit={handleSubmit}>
                    <TextField label="Start Date" type="date" value={selectedDate}
                               onChange={handleDateChange}
                               variant="outlined" required helperText="* Required"/>
                    <div className="buttons">
                        <Button text="Submit" isdisabled={!sprintToSubmit.date}/>
                        <Button onClick={() => dispatch(selectMenu('projects'))} text="Return"/>
                    </div>
                </form>
            </section>
        </div>
    );
}

export default CreateSprint;