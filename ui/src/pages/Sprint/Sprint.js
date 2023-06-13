import React, {useContext} from 'react';
import Button from '../../components/Button/Button';
import { selectMenu, updateSprintStatus } from '../../context/Actions';
import AppContext from '../../context/AppContext';
import './Sprint.css';

/**
 * Sprint component.
 *
 * @returns {JSX.Element} A React component that displays sprint information and allows
 * updating the sprint status.
 */
const Sprint = () => {
    const {state, dispatch} = useContext(AppContext);
    const data = state.detailedSprint;
    const isEndDatePassed = new Date(data?.endDate) < new Date();
    const isEndDateNull = data?.endDate === '';

    const handleUpdateSprintStatus = (status) => {
        if (isEndDatePassed || isEndDateNull || !data) {
            return;
        }
        dispatch(updateSprintStatus(data?.id, status));
    };


    return (
        <div className="page">
            <section className="sprintCard">
                <div className="sprintInfo">
                    <div className="sprintContent">
                        <h2>Sprint Number: {data?.['sprintNumber']}</h2>
                        <p>Project Name: {data?.['projectName']}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
                    </div>
                    <div className="statusContainer">
                        <h3>Status: {data?.status || 'planned'}</h3>
                    </div>
                    <div className="sprintButtons">
                        <Button
                            onClick={() => handleUpdateSprintStatus('Open')}
                            text="Open"
                            isDisabled={isEndDatePassed || isEndDateNull}
                        />
                        <Button
                            onClick={() => handleUpdateSprintStatus('Close')}
                            text="Close"
                            isDisabled={isEndDatePassed || isEndDateNull}
                        />
                    </div>
                    <div className='start'>
                    </div>
                </div>
            </section>
            <div className="returnButtonContainer">
                <Button
                    className="returnButton"
                    isSecondary={true}
                    onClick={() =>
                        dispatch(selectMenu('sprints'))
                    }
                    text='Return'
                />
            </div>
        </div>

    )
}
export default Sprint;



