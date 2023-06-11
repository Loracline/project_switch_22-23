import React, {useContext, useEffect, useState} from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import Box from '@mui/material/Box';
import Button from '../../components/Button/Button';
import {selectMenu, updateSprintStatus} from '../../context/Actions';
import AppContext from '../../context/AppContext';
import './Sprint.css';


const Sprint = () => {
    const {state, dispatch} = useContext(AppContext);
    const data = state.detailedSprint;
    const isEndDatePassed = new Date(data?.endDate) < new Date();
    const isEndDateNull = data?.endDate === '';

    const [selectedUserStory, setSelectedUserStory] = useState(null);
    const [sprintBacklog, setSprintBacklog] = useState([]);
    const {usHeaders, detailedProject} = state;

    const handleUpdateSprintStatus = (status) => {
        if (isEndDatePassed || isEndDateNull) {
            return;
        }
        dispatch(updateSprintStatus(data.id, status));
    };

    const handleUserStoryChange = (event, value) => {
        setSelectedUserStory(value);
    };

    const handleAddUserStory = () => {
        if (selectedUserStory) {
            setSprintBacklog((prevBacklog) => [...prevBacklog, selectedUserStory]);
            setSelectedUserStory(null);
        }
    };

    const sprintBacklogData = sprintBacklog.map(item => {
        return {
            usId: item.usId,
            usDescription: item.usDescription,
        };
    });

    const userStories = [
        {userStory: 'As a Product Owner, I want to create a sprint'},
        {userStory: 'As a Product Owner, I want to create a task'},
        {userStory: 'As a Product Owner, I want to create a user story'},
    ];

    return (
        <div className="page">
            <section className="sprintCard">
                <div className="sprintInfo">
                    <div className="sprintContent">
                        <h2>Sprint Number: {data?.['sprintNumber']}</h2>
                        <p>Project Name: {data?.['projectName']}</p>
                        <p>Status: {data?.status || 'Planned'}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
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
                </div>
                <div className="user-story">
                    <Autocomplete
                        sx={{width: 320}}
                        options={userStories}
                        getOptionLabel={(option) => option.userStory}
                        onChange={handleUserStoryChange}
                        renderOption={(props, option) => (
                            <Box component='li' sx={{'& > img': {mr: 2, flexShrink: 0}}} {...props}>
                                <img loading='lazy' width='35' src='/user.png' alt=''/>
                                <Box sx={{flex: 1}}>
                                    <span>
                                        {option.userStory}
                                    </span>
                                </Box>
                            </Box>
                        )}
                        renderInput={(params) => (
                            <TextField
                                {...params}
                                label='User Story'
                                name='userStory'
                                value={selectedUserStory?.userStory || ''}
                                required
                            />
                        )}
                        filterOptions={(options, state) =>
                            options.filter(
                                (option) =>
                                    option.userStory.toLowerCase().includes(state.inputValue.toLowerCase())
                            )
                        }
                    />
                    <div className='start'>
                        <Button
                            onClick={handleAddUserStory}
                            text="Add"
                            disabled={!selectedUserStory}
                        />
                    </div>
                </div>
                <Button
                    isSecondary={true}
                    onClick={() =>
                        dispatch(selectMenu('sprints'))
                    }
                    text='Return'
                />
                <section className='sprintBacklogSection'>
                    <h3>Sprint Backlog</h3>
                    <ul className='sprintBacklogList'>
                        {sprintBacklog.map((sprint) => (
                            <li key={sprint.sprintId}>
                                <span>usId: {sprint.usId}</span>
                                <span>usDescription: {sprint.usDescription}</span>
                            </li>
                        ))}
                    </ul>
                </section>
            </section>
            <Button
                isSecondary={true}
                onClick={() => dispatch(selectMenu('sprints'))}
                text='Return'
            />
        </div>


    )
}
export default Sprint;



