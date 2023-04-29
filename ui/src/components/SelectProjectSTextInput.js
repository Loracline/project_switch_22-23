import {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {checkProjectSprint} from "../context/Actions";
import InputText from "./InputText";
import Button from "./Button";
/**
 * A component that allows the user to select a project by entering its name.
 * @returns {JSX.Element} A React component that renders a form with an input field and a
 * submit button.
 * @example
 * <SelectProjectSTextInput />
 */

const SelectProjectSTextInput = () => {
    const {dispatch} = useContext(AppContext);
    const [selectedProject, setSelectedProject] = useState("");

    return (
        <div>
            <InputText
                title={"Insert Project:"}
                contentOfInputText={selectedProject}
                handleTextChange={(e) => setSelectedProject(e.target.value)}
            />
            <Button
                onClick={() => dispatch(checkProjectSprint(selectedProject))}
                text={"Submit"}/>
        </div>
    )
}
export default SelectProjectSTextInput;