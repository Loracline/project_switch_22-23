import {Autocomplete, TextField} from "@mui/material";
import React, {useContext, useState} from "react";
import AppContext from "../../context/AppContext";
import CheckBoxOutlineBlankIcon from '@mui/icons-material/CheckBoxOutlineBlank';
import CheckBoxIcon from '@mui/icons-material/CheckBox';
import Checkbox from '@mui/material/Checkbox';
import Button from "../../components/Button/Button";
import {useGetProductBacklog} from "./useGetProductBacklog";
import {postUserStoryToSprint} from "../../context/Actions";
import './AddUserStoryToSprint.css';


function AddUserStoryToSprint() {

    const {state, dispatch} = useContext(AppContext);
    const {detailedSprint, detailedProject} = state;
    const sprintId = detailedSprint?.id;
    const code = detailedProject?.code;


    const [userStoriesToAdd, setUserStoriesToAdd] = useState([]);
    const [productBacklog] = useGetProductBacklog(code);


    const handleChange = (event, value) => {
        setUserStoriesToAdd(value);
    }

    const handleSubmit = () => {
        for (let i = 0; i < userStoriesToAdd.length; i++) {
            postUserStoryToSprint(sprintId, userStoriesToAdd[i], code, dispatch)
        }
    }

    return (
        <div className="addUserStoryToSprint">
            <h2>Add a user story to the sprint backlog</h2>
            <div className="addUserStoryToSprintContent">
                <Autocomplete
                    multiple
                    disableCloseOnSelect
                    sx={{width: 600}}
                    options={productBacklog}
                    getOptionLabel={(option) => option.userStoryNumber.toUpperCase() + " - " + option.userStoryText}
                    getOptionDisabled={(option) => option.status.toUpperCase() !== "PLANNED"}
                    onChange={handleChange}
                    renderOption={(props, option, {selected}) => (
                        <li {...props}>
                            <Checkbox
                                icon={<CheckBoxOutlineBlankIcon fontSize="small"/>}
                                checkedIcon={<CheckBoxIcon fontSize="small"/>}
                                style={{marginRight: 8}}
                                checked={selected}
                            />
                            <span><b>{option.userStoryNumber.toUpperCase()}</b>  - {option.userStoryText}</span>
                        </li>
                    )}
                    style={{width: "90vw", maxWidth: 500}}
                    renderInput={(params) => (<TextField{...params} label="User Stories"/>)}
                    filterOptions={(options, state) => options.filter((option) => option.userStoryNumber.toLowerCase().includes(state.inputValue.toLowerCase()) || option.userStoryText.toLowerCase().includes(state.inputValue.toLowerCase()))}
                />
                <Button
                    text="Add"
                    type="button"
                    onClick={handleSubmit}
                    isDisabled={userStoriesToAdd.length === 0}

                />
            </div>
        </div>
    );
}

export default AddUserStoryToSprint;