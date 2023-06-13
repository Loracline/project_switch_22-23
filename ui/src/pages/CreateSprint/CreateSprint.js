import React, {useContext, useState} from 'react';
import {TextField} from '@mui/material';
import {closeButton, createSprint, selectMenu} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import './CreateSprint.css';
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";

/** This component provides a form for creating a new Sprint for a Project.
 - It allows the user to select a start date for the new Sprint and submits the form. If the form is
 submitted with an empty date or a past date, it should display an error message ("Please select a future date for the start date.")
 - @requires AppContext - This component requires access to the AppContext to retrieve the detailedProject
 and dispatch the createSprint action.
 - @exports CreateSprint */

function CreateSprint() {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject, messageSuccess, messageFailure} = state;
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [showConfirmation, setShowConfirmation] = useState(false);
    const initialSprintState = {
        projectCode: detailedProject.code,
        sprintNumber: '',
        startDate: '',
        endDate: '',
        userStories: []
    };
    const [sprintToSubmit, setSprintToSubmit] = useState(initialSprintState);
    const handleDateChange = (event) => {
        setSelectedDate(event.target.value);
        setSprintToSubmit({
            ...sprintToSubmit,
            startDate: event.target.value,
        });
    };
    const handleSubmit = (event) => {
        event.preventDefault();
        if (sprintToSubmit.startDate.length === 0) {
            alert('Please, insert initial date.');
            setSelectedDate(new Date());
            setSprintToSubmit(initialSprintState);
        } else if (new Date(sprintToSubmit.startDate) < new Date()) {
            setSelectedDate(new Date());
            alert('Please select a future date for the start date.');
            setSprintToSubmit(initialSprintState);
        } else {
            createSprint(dispatch, sprintToSubmit);
            setSprintToSubmit(initialSprintState);
            setSelectedDate(new Date());
            setShowConfirmation(false);
        }
    };

    const handleConfirmation = () => {
        setShowConfirmation(true);
    }

    const handleCancel = () => {
        setShowConfirmation(false);
    };

    const handleClearSprint = (_) => {
        setSprintToSubmit(initialSprintState);
        dispatch(closeButton());
    }

    const dialogContent = () => {
        return (
            <div>
                <h2 style={{marginBottom: '1rem', fontSize: '2rem', textAlign: "center"}}>Please confirm:</h2>
                <table style={{width: '100%'}}>
                    <tbody>
                    <tr>
                        <td><strong>Start date:</strong></td>
                        <td>{new Date(sprintToSubmit.startDate).toLocaleDateString('en-US', { month: 'short', day: 'numeric', year: 'numeric' })}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        )}

    if (!detailedProject) return null;

    return (
        <div className="page">
            <section className="formCard">
                <h2> Create Sprint </h2>
                <form className="sprint-form">
                    <TextField label="Start Date" type="date" value={selectedDate}
                               onChange={handleDateChange}
                               variant="outlined"/>
                    <div className="sprint-buttons">
                        <Button isSecundary={true} onClick={() => dispatch(selectMenu('project'))} text="Return"/>
                        <Button text="Submit" type="button" isDisabled={!sprintToSubmit.startDate} onClick={handleConfirmation}/>
                    </div>

                    <SuccessMessage
                        handleOpen={messageSuccess.length > 0}
                        title="Sprint created!"
                        handleClose={handleClearSprint}
                    />

                    <FailureMessage
                        handleOpen={messageFailure.length > 0}
                        title="Sprint not created!"
                        message={messageFailure}
                        handleClose={handleClearSprint}
                    />
                </form>
            </section>
            <ConfirmationPage
                handleOpen={showConfirmation}
                dialogContent={dialogContent()}
                handleCancel={handleCancel}
                handleConfirm={handleSubmit}
            />
        </div>
    );
}

export default CreateSprint;