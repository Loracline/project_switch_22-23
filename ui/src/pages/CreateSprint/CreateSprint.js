import React, {useContext, useState} from 'react';
import {Dialog, TextField} from '@mui/material';
import {closeButton, createSprint, selectMenu} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import './CreateSprint.css';
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import ErrorIcon from "@mui/icons-material/Error";

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
                        <Button text="Submit" type="button" isdisabled={!sprintToSubmit.startDate} onClick={handleConfirmation}/>
                        <Button isSecundary={true} onClick={() => dispatch(selectMenu('project'))} text="Return to project"/>
                    </div>

                    <Dialog className="success-dialog"  open={messageSuccess.length > 0}>
                        <CheckCircleIcon style={{color: "green", alignSelf: "center", width: 80, height: 80, margin: 10}}/>
                        <h3>Sprint created!</h3>
                        <Button text="Close" onClick={handleClearSprint}/>
                    </Dialog>

                    <Dialog className="failure-dialog"  open={messageFailure?.length > 0}>
                        <ErrorIcon style={{color: "red", alignSelf: "center", width: 80, height: 80, margin: 10}}/>
                        <h3>Sprint not created!</h3>
                        <span>{messageFailure}</span>
                        <Button text="Close" onClick={handleClearSprint}/>
                    </Dialog>
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