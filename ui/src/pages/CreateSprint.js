import {useContext, useMemo, useState} from "react";
import AppContext from "../context/AppContext";
import {createSprint} from "../context/Actions";
import InputText from "../components/InputText/InputText";
import Button from "../components/Button/Button";
import SelectProjectSTextInput from "../components/SelectProjectSTextInput";
import Calendar from "../components/Calendar";


/**This component provides a form for creating a new sprint.
 It allows the user to select a project, enter a start date, and specify the duration
 of the sprint in days.
 @requires AppContext - This component requires access to the AppContext in order to
 retrieve a list of available projects and update the selected project, start date,
 and duration state variables.
 @exports CreateSprintForm */

function CreateSprintForm() {
    const {state, dispatch} = useContext(AppContext);

    const [sprintToSubmit, setSprintSubmit] = useState({
        projectCode: "",
        startDate: "",
        duration: "",
    });

    const selectedProject = state.selectedProject;

    const handleStartDate = (event) => {
        setSprintSubmit({
            ...sprintToSubmit,
            startDate: event.target.value,
        });
    };

    const handleDuration = (event) => {
        setSprintSubmit({
            ...sprintToSubmit,
            duration: event.target.value,
        });
    };

    const handleSubmitForm = (event) => {
        event.preventDefault();
        dispatch(createSprint(sprintToSubmit));
        setSprintSubmit({
            projectCode: "",
            startDate: "",
            duration: "",
        });
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (!sprintToSubmit.startDate || !sprintToSubmit.duration) {
            alert("Please, enter a project name, insert initial date, and specify the duration.");
        } else {
            handleSubmitForm(event);
        }
    };

    const form = useMemo(() => {
        let formS;
        if (selectedProject) {
            formS = (
                <form onSubmit={handleSubmit}>
                    <div>
                        <label>Start Date:</label>
                        <Calendar onChange={handleStartDate}/>
                    </div>
                    <br/>
                    <br/>
                    <InputText
                        title={"Duration:"}
                        contentOfInputText={sprintToSubmit.duration}
                        handleTextChange={handleDuration}
                    />
                    <br/>
                    <br/>
                    <Button type="submit" text={"Submit"}/>
                </form>
            );
        } else {
            formS = <h1> No Project </h1>;
        }
        return formS;
    }, [selectedProject]);

    return (
        <div>
            <h2>Create a Sprint</h2>
            <SelectProjectSTextInput/>
            {form}
        </div>
    );
}

export default CreateSprintForm;