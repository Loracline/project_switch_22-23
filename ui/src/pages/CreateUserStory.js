import React, {useContext, useMemo, useState} from 'react';
import Button from "../components/Button/Button";
import {TextField} from "@mui/material";
import AppContext from "../context/AppContext";
import SelectProjectTextInput from "../components/SelectProjectTextInput";
import {createUserStory} from "../context/Actions";



/**This component provides a form for creating a new project.*/

function CreateUserStory(){
        const {state, dispatch} = useContext(AppContext);
        const selectedProject = state.selectedProject;

// estado inicial
            const initialUsState = {
            projectCode: selectedProject,
            userStoryNumber: "",
            userStoryText: "",
            actor: "",
            acceptanceCriteria: "",
            priority: -1,
            };


//estado final UserStory
           const [userStory, setUserStory] = useState(initialUsState);

           const handleChange = (event)=>{
            const {name, value} = event.target;
            const newUserStory = {...userStory}
            newUserStory[name]=value
            setUserStory(newUserStory)
           }

           const handleSubmit = (event)=>{
            event.preventDefault();
            dispatch(createUserStory(userStory));
            //setUserStory(initialUsState);
           }

        return(
            <div>
             <h2>Create User Story</h2>
              <SelectProjectTextInput/>
              <br/>
              <br/>
                <form onClick = {handleSubmit}>
                    <TextField
                         name = "userStoryNumber"
                         label = "User Story Number"
                         value = {userStory.userStoryNumber}
                         onChange={handleChange}
                         disabled = {!selectedProject}
                         variant="outlined" />
                         <br/>
                         <br/>
                    <TextField
                         name = "userStoryText"
                         label = "User Story Text"
                         value = {userStory.userStoryText}
                         onChange={handleChange}
                         disabled = {!selectedProject}
                         variant="outlined" />
                         <br/>
                         <br/>
                    <TextField
                         name = "actor"
                         label = "Actor"
                         value = {userStory.actor}
                         onChange={handleChange}
                         disabled = {!selectedProject}
                         variant="outlined" />
                         <br/>
                         <br/>
                    <TextField
                         name = "acceptanceCriteria"
                         label = "Acceptance Criteria"
                         value = {userStory.acceptanceCriteria}
                         onChange={handleChange}
                         disabled = {!selectedProject}
                         variant="outlined" />
                         <br/>
                         <br/>
                    <TextField
                         name = "priority"
                         label = "Priority"
                         value = {userStory.priority}
                         onChange={handleChange}
                         disabled = {!selectedProject}
                         type = "number"
                         variant="outlined" />
                         <br/>
                         <br/>

                    <Button text="Create US" />



                </form> </div>)

}
export default CreateUserStory;