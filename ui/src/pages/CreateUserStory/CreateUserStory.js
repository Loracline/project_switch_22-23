import React, {useContext, useState} from 'react';
import Button from "../../components/Button/Button";
import {Box, IconButton, List, ListItem, ListItemText, TextField, Typography,} from "@mui/material";
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';
import AppContext from "../../context/AppContext";
import {createUserStory, resetPostUserStory, selectMenu} from "../../context/Actions";
import "./CreateUserStory.css";
import {strings} from "../../strings";
import Loading from "../../components/Loading/Loading";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import {Link} from "react-router-dom";

/**This component provides a form for creating a new user story.*/

function CreateUserStory() {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject} = state;
    const loading = state.loading;
    const messageSuccess = state.messageSuccess;
    const messageFailure = state.messageFailure;
    const [newAcceptanceCriteria, setAcceptanceCriteria] = useState("");
    const [showConfirmation, setShowConfirmation] = useState(false);

    const initialUsState = {
        projectCode: detailedProject?.code,
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
        createUserStory(dispatch, {...userStory, priority: userStory.priority || -1})
    };

    const handleClearUserStory = (_) => {
        setUserStory(initialUsState);
        dispatch(resetPostUserStory());
        setShowConfirmation(false);
    }

    const handleConfirmation = () => {
        setShowConfirmation(true);
    }

    const handleCancel = () => {
        setShowConfirmation(false);

    };

    const dialogContent = () => {
        return (
            <div>
                <h2 style={{
                    marginBottom: '1rem',
                    fontSize: '2rem',
                    textAlign: "center"
                }}>Please confirm:</h2>
                <table style={{width: '100%'}}>
                    <tbody>
                    <tr>
                        <td><strong>User Story Number:</strong></td>
                        <td>{userStory.userStoryNumber}</td>
                    </tr>
                    <tr>
                        <td><strong>User Story Text:</strong></td>
                        <td>{userStory.userStoryText}</td>
                    </tr>
                    <tr>
                        <td><strong>Actor:</strong></td>
                        <td>{userStory.actor}</td>
                    </tr>
                    <tr>
                        <td><strong>Acceptance Criteria:</strong></td>
                        <td>{userStory.acceptanceCriteria}</td>
                    </tr>
                    <tr>
                        <td><strong>Priority:</strong></td>
                        <td>{userStory.priority}</td>
                    </tr>
                    <tr>
                        <td><strong>Description:</strong></td>
                        <td>{userStory.description}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        )
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
                        type="number"
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
                    <Box display="flex" justifyContent="space-between">
                        <Link to={"/projects/" + detailedProject?.code}>
                            <Button
                                isSecundary={true}
                                text="Return"
                            />
                        </Link>
                        <Button
                            type="button"
                            text="Create US"
                            onClick={handleConfirmation}
                            isDisabled={!userStory.userStoryNumber || !userStory.userStoryText}
                        />
                    </Box>
                    <Link to={"/projects/" + detailedProject?.code + "/product-backlog"}>
                        <Button
                            isSecundary={true}
                            onClick={() => dispatch(selectMenu('productBacklog'))}
                            text="Go to Product Backlog"
                        />
                    </Link>
                </form>
            </section>

            <Loading handleLoading={loading}/>

            <ConfirmationPage
                handleOpen={showConfirmation}
                dialogContent={dialogContent()}
                handleCancel={handleCancel}
                handleConfirm={handleSubmit}
            />

            <SuccessMessage
                handleOpen={messageSuccess.length > 0}
                title={strings.userStoryCreatedSuccessMessage}
                message={messageSuccess}
                handleClose={handleClearUserStory}
            />

            <FailureMessage
                handleOpen={messageFailure.length > 0}
                title={strings.userStoryNumberAlreadyExistsMessage}
                handleClose={handleClearUserStory}
            />

        </div>
    )
}

export default CreateUserStory;