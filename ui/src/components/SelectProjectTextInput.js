import {useContext, useState} from "react";
import AppContext from "../context/AppContext";
import {checkProject} from "../context/Actions";
import InputText from "./InputText/InputText";
import Button from "./Button/Button";

const SelectProjectTextInput = () => {
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
                onClick={() => dispatch(checkProject(selectedProject))}
                text={"Submit"}/>
        </div>
    )
}
export default SelectProjectTextInput;