import {useContext, useMemo, useState} from "react";
import AppContext from "../context/AppContext";
import {createSprint} from "../context/Actions";
import InputText from "../components/InputText/InputText";
import {Button} from "@mui/material";
import SelectProjectSTextInput from "../components/SelectProjectSTextInput";

function CreateSprintForm(factory, deps) {
    const {
        state,
        dispatch
    } = useContext(AppContext);

    const [sprintToSubmit, setSprintSubmit] = useState({
        projectCode: "",
        startDate: "",
        duration: ""
    })

    const selectedProject = state;

    const handleStartDate = (event) => {
        console.log("handleChange: " + event.target);
        setSprintSubmit({
            ...sprintToSubmit,
            startDate: event.target.value
        })
    }

    const handleDuration = (event) => {
        console.log("handleChange: " + event.target);
        setSprintSubmit({
            ...sprintToSubmit,
            duration: event.target.value
        })
    }

    const handleSubmitForm = (sprintToSubmit) => {
        dispatch(createSprint(sprintToSubmit));
        setSprintSubmit({
            projectCode: "",
            startDate: "",
            duration: ""
        })
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (!sprintToSubmit.startDate || !sprintToSubmit.duration) {
            alert("Please, enter a project name, insert initial date, and specify the duration.");
        }
    }

    const form = useMemo(() => {
        let formS;
        if (selectedProject) {
            formS = (
                <form>
                    <InputText title={"Start Date:"}
                               contentOfInputText={sprintToSubmit.startDate}
                               handleTextChange={handleStartDate}/>
                    <br/>
                    <br/>
                    <InputText title={"Duration:"}
                               contentOfInputText={sprintToSubmit.duration}
                               handleTextChange={handleDuration}/>
                    <br/>
                    <br/>
                    <Button onClick={() => {handleSubmitForm(sprintToSubmit)}}
                            text={"Submit"}/>
                </form>
            )
        } else {
            formS = <h1> No Project </h1>
        }
        return formS;
        }, [selectedProject]);
    return (
        <div>
            <h2>Create a Sprint</h2>
            <SelectProjectSTextInput/>{form}
        </div>
    );
}

export default CreateSprintForm;