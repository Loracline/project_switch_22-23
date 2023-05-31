import React, {useContext, useState} from 'react';
import Button from "../../components/Button/Button";
import {IconButton, List, ListItem, ListItemText, TextField} from "@mui/material";
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import AppContext from "../../context/AppContext";
import {createUserStory, selectMenu} from "../../context/Actions";
import "./CreateUserStory.css";


/**This component provides a form for creating a new user story.*/

function CreateUserStory() {
    const {state, dispatch} = useContext(AppContext);
    const [newAcceptanceCriteria, setAcceptanceCriteria] = useState("");
    const {detailedProject} = state;

    const initialUsState = {
        // Hard-coded for validation purposes while
        projectCode: "p001",
        userStoryNumber: "",
        userStoryText: "",
        actor: "",
        acceptanceCriteria: [],
        priority: "",
    };

    const [userStory, setUserStory] = useState(initialUsState);


    const handleChange = (event) => {
        const {name, value} = event.target;
        const newUserStory = {...userStory}
        if (name === "priority") {
            newUserStory[name] = Number(value)
        } else {
            newUserStory[name] = value
        }
        setUserStory(newUserStory)
    }

    const handleChangeCriteria = (event) => {
        setAcceptanceCriteria(event.target.value);
    }

    const addCriteria = () => {
        const acceptanceCriteria = [...userStory.acceptanceCriteria, newAcceptanceCriteria];
        setUserStory({...userStory, acceptanceCriteria});
        setAcceptanceCriteria("");
    }

    const deleteCriteria = (indexToDelete) => {
        const acceptanceCriteria = userStory.acceptanceCriteria.filter((_, index) => index !== indexToDelete);
        setUserStory({...userStory, acceptanceCriteria});
        setAcceptanceCriteria("");
    }


    const handleSubmit = (event) => {
        event.preventDefault();
        createUserStory(dispatch, {...userStory, priority: userStory.priority || -1});
        setUserStory(initialUsState);
    }

    return (
        <div className="page">
            <section className="formCard">
                <h2>Create User Story</h2>
                <form className="user-story-form" onSubmit={handleSubmit}>
                    <TextField
                        name="userStoryNumber"
                        label="User Story Number"
                        value={userStory.userStoryNumber}
                        onChange={handleChange}
                        variant="outlined"
                        required
                        className="textField"
                    />
                    <TextField
                        name="userStoryText"
                        label="User Story Text"
                        value={userStory.userStoryText}
                        multiline
                        onChange={handleChange}
                        variant="outlined"
                        required
                        className="textField"/>
                    <TextField
                        name="actor"
                        label="Actor"
                        value={userStory.actor}
                        onChange={handleChange}
                        variant="outlined"
                        className="textField"/>
                    <div className="ac-container">
                        <TextField style={{width: "90%"}}
                                   name="acceptanceCriteria"
                                   label="Acceptance Criteria"
                                   value={newAcceptanceCriteria}
                                   onChange={handleChangeCriteria}
                                   variant="outlined"
                                   className="textField"
                        />
                        <IconButton style={{margin: "auto"}} aria-label="add" onClick={addCriteria}>
                            <AddIcon/>
                        </IconButton>
                    </div>
                    <List>
                        {userStory.acceptanceCriteria.map((acceptanceCriteria, index) => (
                            <ListItem key={index}
                                      secondaryAction={
                                          <IconButton edge="end" aria-label="delete"
                                                      onClick={() => deleteCriteria(index)}>
                                              <DeleteIcon/>
                                          </IconButton>
                                      }>
                                <ListItemText
                                    primary={`${index + 1}. ${acceptanceCriteria}`}> </ListItemText>
                            </ListItem>
                        ))}
                    </List>
                    <TextField
                        name="priority"
                        label="Priority"
                        helperText={'Default priority is the lowest one'}
                        value={userStory.priority}
                        onChange={handleChange}
                        type="number"
                        InputProps={{
                            inputProps: {
                                min: 0
                            }
                        }}
                        variant="outlined"
                        className="textField"/>
                    <div className="buttons-createUs">
                        <Button text="Create US"
                                isDisabled={!userStory.userStoryNumber || !userStory.userStoryText}/>
                        <Button isSecundary={true} onClick={() => dispatch(selectMenu('project'))}
                                text="Return to project"/>
                    </div>
                    <Button isSecundary={true}
                            onClick={() => dispatch(selectMenu('productBacklog'))}
                            text="Go to Product Backlog"/>
                </form>
            </section>
        </div>)

}

export default CreateUserStory;