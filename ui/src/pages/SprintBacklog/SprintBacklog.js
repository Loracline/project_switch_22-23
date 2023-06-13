import {Autocomplete, TextField} from "@mui/material";
import React, {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext";
import CheckBoxOutlineBlankIcon from '@mui/icons-material/CheckBoxOutlineBlank';
import CheckBoxIcon from '@mui/icons-material/CheckBox';
import Checkbox from '@mui/material/Checkbox';
import Button from "../../components/Button/Button";
import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";


function SprintBacklog() {

    const {state, dispatch} = useContext(AppContext);
    const {detailedSprint, detailedProject} = state;
    // sprintId = detailedSprint.sprintId;
    const code = detailedProject.code;
    const sprintId = "p001_s001";


    let newSprintBacklog = [];


    //fetch para get Product Backlog

    const [productBacklog, setProductBacklog] = useState([]);
    useEffect(() => {
        fetch(`${API_URL}${API_ROUTES.PROJECTS}/${code}${API_ROUTES.PRODUCTBACKLOG}`, {
            method: 'GET',
            headers,
        })
            .then(response => response.json())
            .then(response => {
                setProductBacklog(response);
            })

    }, [])


//fetch para post UserStoryToAdd
    const [userStoriesToAdd, setUserStoriesToAdd] = useState([]);
    useEffect(() => {
        for (let i = 0; i < userStoriesToAdd.length; i++) {
            fetch(`${API_URL}${API_ROUTES.SPRINTS}/${sprintId}${API_ROUTES.SPRINTBACKLOG}`, {
                method: 'POST',
                body: JSON.stringify({userStoryId: code+ "_"+ userStoriesToAdd[i].userStoryNumber, sprintId: sprintId}),
                headers,
            }).then(async response => {
                if (!response.ok) {
                    throw await response.json()
                }
                return {}
            })
        }
    }, [userStoriesToAdd])


    const icon = <CheckBoxOutlineBlankIcon fontSize="small"/>;
    const checkedIcon = <CheckBoxIcon fontSize="small"/>


    const handleSprintBacklogChange = (event, value) => {
        newSprintBacklog = value;
    }

    const handleConfirmation = (event) => {
        console.log(newSprintBacklog);
        setUserStoriesToAdd(newSprintBacklog);
    }

    return (
        <>
            <table style={{background: '#f2f2f2'}}>
                <caption style={{fontWeight: 'bold', marginBottom: '1rem'}}>
                    SPRINT BACKLOG
                </caption>
                <thead>
                <tr>
                    <th>US NUMBER</th>
                    <th>US DESCRIPTION</th>
                </tr>
                </thead>
                <tbody>
                {userStoriesToAdd.length === 0 ? (
                    <tr>
                        <td colSpan="2">No UserStories added</td>
                    </tr>
                ) : (
                    userStoriesToAdd.map((item) => (
                        <tr key={item.userStoryNumber}>
                            <td>{item.userStoryNumber}</td>
                            <td>{item.userStoryText}</td>
                        </tr>
                    ))
                )}
                </tbody>
            </table>
            <br/>
            <Autocomplete
                multiple
                disableCloseOnSelect
                sx={{width: 600}}
                options={productBacklog}
                getOptionLabel={(option) => option.userStoryNumber}
                onChange={handleSprintBacklogChange}
                renderOption={(props, option, {selected}) => (
                    <li {...props}>
                        <Checkbox
                            icon={icon}
                            checkedIcon={checkedIcon}
                            style={{marginRight: 8}}
                            checked={selected}
                        />
                        <span><b>{option.userStoryNumber}</b>  - {option.userStoryText}</span>
                    </li>
                )}
                style={{width: 500}}
                renderInput={(params) => (
                    <TextField{...params}
                              label="Sprint Backlog"
                              required
                    />
                )}
                filterOptions={(options, state) => options.filter((option) => option.userStoryNumber.toLowerCase().includes(state.inputValue.toLowerCase()) || option.userStoryText.toLowerCase().includes(state.inputValue.toLowerCase()))}
            />
            <Button
                text="Update"
                type="button"
                onClick={handleConfirmation}
            />

            <br/>


        </>
    );
}


export default SprintBacklog;