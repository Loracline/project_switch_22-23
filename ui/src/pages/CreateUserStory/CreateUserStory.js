import React, {useContext, useState} from 'react';
import Button from "../../components/Button/Button";
import {
    CircularProgress,
    Dialog,
    IconButton,
    List,
    ListItem,
    ListItemText,
    TextField,
    Typography,
} from "@mui/material";
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import AppContext from "../../context/AppContext";
import {createUserStory, resetPostUserStory, selectMenu} from "../../context/Actions";
import "./CreateUserStory.css";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import {strings} from "../../strings";
import ErrorIcon from "@mui/icons-material/Error";

/**This component provides a form for creating a new user story.*/

function CreateUserStory() {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject} = state;
    const loading = state.loading;
    const messageSuccess = state.messageSuccess;
    const messageFailure = state.messageFailure;
    const [newAcceptanceCriteria, setAcceptanceCriteria] = useState("");

    const initialUsState = {
        projectCode: detailedProject.code,
        userStoryNumber: "",
        userStoryText: "",
        actor: "",
        acceptanceCriteria: [],
        priority: "",
        description: ''
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

    const handleClearUserStory = (_) => {
        dispatch(resetPostUserStory());
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
                        name="description"
                        label="Description"
                        value={userStory.description}
                        onChange={handleChange}
                        variant="outlined"
                        required
                        multiline
                        rows={4}
                        className="textField"
                    />
                    <TextField
                        name="actor"
                        label="Actor"
                        value={userStory.actor}
                        onChange={handleChange}
                        variant="outlined"
                        required
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
                        <IconButton style={{margin: "auto"}} aria-label="add"
                                    onClick={addCriteria}>
                            <AddIcon/>
                        </IconButton>
                    </div>
                    <Typography variant="body" color="gray">
                        Please enter the acceptance criteria and then click on "➕"
                    </Typography>
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
                                primaryTypographyProps={{
                                sx: {
                                    color: 'gray',
                                    fontSize: '0.05rem'
                                },
                            }}
                                />
                            </ListItem>
                        ))}
                    </List>
                    <TextField
                        name="priority"
                        label="Priority"
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
                        <Button isSecundary={true}
                                onClick={() => dispatch(selectMenu('project'))}
                                text="Return to project"/>
                    </div>
                    <Button isSecundary={true}
                            onClick={() => dispatch(selectMenu('productBacklog'))}
                            text="Go to Product Backlog"/>
                </form>
            </section>

            <Dialog open={loading}>
                <CircularProgress style={{color: "#6145AF"}} sx={{m: 5}}/>
            </Dialog>

            <Dialog className="success-dialog" open={messageSuccess.length > 0}>
                <CheckCircleIcon style={{
                    color: "green",
                    alignSelf: "center",
                    width: 80,
                    height: 80,
                    margin: 10
                }}/>
                <h3>{strings.userStoryCreatedSuccessMessage}</h3>
                <h4>{messageSuccess}</h4>
                <Button text="Close" onClick={handleClearUserStory}/>
            </Dialog>

            <Dialog className="failure-dialog" open={messageFailure.length > 0}>
                <ErrorIcon
                    style={{color: "red", alignSelf: "center", width: 80, height: 80, margin: 10}}/>
                <h3>{messageFailure}</h3>
                <Button text="Close" onClick={handleClearUserStory}/>
            </Dialog>
        </div>
    )
}

export default CreateUserStory;