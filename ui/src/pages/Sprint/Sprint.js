import React, { useContext } from "react";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import { selectMenu, updateSprintStatus } from "../../context/Actions";
import './Sprint.css';

const Sprint = () => {
    const { state, dispatch } = useContext(AppContext);
    const data = state.detailedSprint;
    const isEndDatePassed = new Date(data?.endDate) < new Date();
    const isEndDateNull = data?.endDate === "";

    return (
        <div className='page'>
            <section className='sprintCard'>
                <div className='sprintInfo'>
                    <div className='sprintContent'>
                        <h2>Sprint Number: {data?.['sprintNumber']}</h2>
                        <p>Project Name: {data?.['projectName']}</p>
                        <p>Status: {data?.status || 'Planned'}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
                    </div>
                    <div className='buttons-project'>
                        <Button
                            onClick={() => dispatch(updateSprintStatus(data.id, 'Open'))}
                            text="Open"
                            isDisabled={isEndDatePassed || isEndDateNull}
                        />
                        <Button
                            onClick={() => dispatch(updateSprintStatus(data.id, 'Close'))}
                            text="Close"
                            isDisabled={isEndDatePassed || isEndDateNull}
                        />
                    </div>
                </div>
                <Button
                    isSecondary={true}
                    onClick={() => dispatch(selectMenu('sprints'))}
                    text="Return"
                />
            </section>
        </div>
    );
}

export default Sprint;

