import React, {useContext, useEffect, useState} from 'react';
import Button from '../../components/Button/Button';
import {closeButton, updateSprintStatus} from '../../context/Actions';
import AppContext from '../../context/AppContext';
import './Sprint.css';
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";
import ConsultSprintBacklog from "../ConsultSprintBacklog/ConsultSprintBacklog";
import AddUserStoryToSprint from "../SprintBacklog/AddUserStoryToSprint";
import {Link} from "react-router-dom";

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
    const projectName = detailedProject?.projectName;

    const [showConfirmation, setShowConfirmation] = useState(false);
    const canNotOpenSprint = data?.status === "OPEN" || detailedProject.status === 'closed';
    const canNotCloseSprint = data?.status === "CLOSE" || data?.status === "PLANNED" || data?.status === "CLOSED";

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
        if (status === "closed" && unfinishedUserStories.length > 0) {
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

    const [showScrollButton, setShowScrollButton] = useState(false);

    useEffect(() => {
        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    const handleScroll = () => {
        if (window.pageYOffset > 20) {
            setShowScrollButton(true);
        } else {
            setShowScrollButton(false);
        }
    };
    const scrollToTop = () => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    };
    const scrollButtonStyle = {
        display: showScrollButton ? 'block' : 'none',
    };

    return (
        <div className="page">
            <section className="sprintCard">
                <div className="sprintHeader">
                    <h2>Sprint Number: {data?.['number']}</h2>
                    <Link to={`/sprints/${detailedProject?.code}`}>
                    <Button isSecundary={true}
                            text='Return'
                    />
                    </Link>
                </div>

                <div className="sprintInfo">
                    <div className="sprintContent">
                        <p>Project Name: {projectName}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
                    </div>
                    <div className="statusContainer">
                        <h3>Status: {data?.status || 'planned'}</h3>
                        <div className="sprintButtons">
                            <Button
                                onClick={() => handleUpdateSprintButton('open')}
                                text="Open"
                                isDisabled={canNotOpenSprint}
                            />
                            <Button
                                onClick={() => handleUpdateSprintButton('closed')}
                                text="Close"
                                isDisabled={canNotCloseSprint}
                            />
                        </div>
                    </div>
                </div>
            </section>
            <section className="sprintBacklogSection">
                <AddUserStoryToSprint/>
                <ConsultSprintBacklog/>
            </section>
            <div className="returnButtonContainer">

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
                handleConfirm={() => handleUpdateSprintStatus("closed")}
            />
            <button className="scroll-to-top-button" style={scrollButtonStyle} onClick={scrollToTop}>
                Scroll to Top
            </button>
        </div>

    )
}
export default Sprint;



