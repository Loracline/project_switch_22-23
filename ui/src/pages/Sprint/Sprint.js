import React, {useContext, useState} from 'react';
import Button from '../../components/Button/Button';
import {closeButton, selectMenu, updateSprintStatus} from '../../context/Actions';
import AppContext from '../../context/AppContext';
import './Sprint.css';
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";
import ConsultSprintBacklog from "../ConsultSprintBacklog/ConsultSprintBacklog";

/**
 * Sprint component.
 *
 * @returns {JSX.Element} A React component that displays sprint information and allows
 * updating the sprint status.
 */
const Sprint = () => {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject, detailedSprint, messageSuccess, messageFailure} = state;
    const data = detailedSprint;
    const projectName = detailedProject.projectName;

    const [showConfirmation, setShowConfirmation] = useState(false);
    const isOpen = data?.status === "open";
    const isClosed = data?.status === "closed" || data?.status === "planned";

    const handleConfirmation = () => {
        setShowConfirmation(true);
    }

    const handleCancel = () => {
        setShowConfirmation(false);
    };

    const handleUpdateSprintStatus = (status) => {
        updateSprintStatus(dispatch, data?.id, status);
    };

    const handleUpdateSprintButton = (status) => {
        //&& hasUnfinishedUserStories
        if (status === "close") {
            handleConfirmation();
        } else {
            handleUpdateSprintStatus(status);
        }
    }

    const handleClearSprint = (_) => {
        //setSprintToSubmit(initialSprintState);
        dispatch(closeButton());
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
                title="Sprint not created!"
                message={messageFailure}
                handleClose={handleClearSprint}
            />
            <ConfirmationPage
                handleOpen={showConfirmation}
                dialogContent={dialogContent()}
                handleCancel={handleCancel}
                handleConfirm={handleUpdateSprintStatus}
            />
        </div>

    )
}
export default Sprint;



