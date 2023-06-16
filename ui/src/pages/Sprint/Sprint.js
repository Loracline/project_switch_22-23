import React, {useContext, useState} from 'react';
import Button from '../../components/Button/Button';
import {closeButton, selectMenu, updateSprintStatus} from '../../context/Actions';
import AppContext from '../../context/AppContext';
import './Sprint.css';
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";
import ConsultSprintBacklog from "../ConsultSprintBacklog/ConsultSprintBacklog";
import AddUserStoryToSprint from "../SprintBacklog/AddUserStoryToSprint";

/**
 * Sprint component.
 *
 * @returns {JSX.Element} A React component that displays sprint information and allows
 * updating the sprint status.
 */
const Sprint = () => {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject, detailedSprint, messageSuccess, messageFailure, userStoriesInSprint} = state;
    const data = detailedSprint;
    const projectName = detailedProject.projectName;

    const [showConfirmation, setShowConfirmation] = useState(false);
    const isOpen = data?.status === "open";
    const isClosed = data?.status === "close" || data?.status === "planned" || data?.status === "CLOSED";

    const handleConfirmation = () => {
        setShowConfirmation(true);
    }

    const handleCancel = () => {
        setShowConfirmation(false);
    };

    const handleUpdateSprintStatus = (status) => {
        updateSprintStatus(dispatch, data?.id, status);
        setShowConfirmation(false);
    };

    const handleUpdateSprintButton = (status) => {
        //&& hasUnfinishedUserStories
        if (status === "close" && unfinishedUserStories.length > 0) {
            console.log(userStoriesInSprint);
            console.log(unfinishedUserStories);
            handleConfirmation(status);
        } else {
            handleUpdateSprintStatus(status);
        }
    }

    const handleClearSprint = (_) => {
        //setSprintToSubmit(initialSprintState);
        dispatch(closeButton());
    }

    const unfinishedUserStories = userStoriesInSprint.filter(isUserStoryUnfinished);

    function isUserStoryUnfinished(userStory) {
        return (userStory.status === "blocked" || userStory.status === "running");
    }

    const dialogContent = () => {
        return (
            <div>
                <h2 style={{marginBottom: '1rem', fontSize: '2rem', textAlign: "center"}}>Alert!</h2>
                <p>You have unfinished user stories, are you sure you want to close the sprint?</p>
            </div>
        )
    }

    return (
        <div className="page">
            <section className="sprintCard">
                <div className="sprintInfo">
                    <div className="sprintContent">
                        <h2>Sprint Number: {data?.['number']}</h2>
                        <p>Project Name: {projectName}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
                    </div>
                    <div className="statusContainer">
                        <h3>Status: {data?.status || 'planned'}</h3>
                    </div>
                    <div className="sprintButtons">
                        <Button
                            onClick={() => handleUpdateSprintButton('open')}
                            text="Open"
                            isDisabled={isOpen}
                        />
                        <Button
                            onClick={() => handleUpdateSprintButton('close')}
                            text="Close"
                            isDisabled={isClosed}
                        />
                    </div>
                    <div className='start'>
                    </div>
                </div>
                <AddUserStoryToSprint/>
                <ConsultSprintBacklog/>
            </section>
            <div className="returnButtonContainer">
                <Button
                    isSecundary={true}
                    onClick={() =>
                        dispatch(selectMenu('sprints'))
                    }
                    text='Return'
                />
            </div>
            <SuccessMessage
                handleOpen={messageSuccess.length > 0}
                title="Sprint status updated!"
                handleClose={handleClearSprint}
            />

            <FailureMessage
                handleOpen={messageFailure.length > 0}
                title="Sprint can't be opened!"
                message={messageFailure}
                handleClose={handleClearSprint}
            />
            <ConfirmationPage
                handleOpen={showConfirmation}
                dialogContent={dialogContent()}
                handleCancel={handleCancel}
                handleConfirm={() => handleUpdateSprintStatus("close")}
            />
        </div>

    )
}
export default Sprint;



