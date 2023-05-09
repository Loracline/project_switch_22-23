import React, {useContext, useState} from "react";
import Button from "../../components/Button/Button";
import {createProject, selectMenu} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import {TextField} from "@mui/material";

/**
 * Form component in React.
 *
 * @returns a React form component with three input fields and a submit button.
 */
function CreateProject() {

    const initialProject = {
        basicInfo: {
            code: undefined,
            name: undefined,
            customer: undefined,
            status: "planned",
            startDate: undefined,
            endDate: undefined
        }
    };

    const {dispatch} = useContext(AppContext);
    const [basicInfo, setBasicInfo] = useState(initialProject.basicInfo);

    const handleChangeBasic = (event) => {
        const {name, value} = event.target;
        const newBasicInfo = {...basicInfo};
        newBasicInfo[name] = value;
        setBasicInfo(newBasicInfo);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const projectToAdd = {
            basicInfo,
            userStories: [],
            sprints: []
        }

        dispatch(createProject({...projectToAdd}));
        window.alert(`The project "${basicInfo.name}" was successfully created.`);
        setBasicInfo(initialProject.basicInfo)
    }

    return (
        <div className="page">
            <section className="formCard">
                <h2>Create Project</h2>
                <form className="project-form" onSubmit={handleSubmit}>
                    <TextField
                        name="code"
                        label="Project Code"
                        value={basicInfo.code}
                        onChange={handleChangeBasic}
                        variant="outlined"
                        required
                        helperText="* Required"
                    />
                    <TextField
                        name="name"
                        label="Project Name"
                        value={basicInfo.name}
                        onChange={handleChangeBasic}
                        variant="outlined"
                        required
                        helperText="* Required"
                    />
                    <TextField
                        name="customer"
                        label="Customer"
                        value={basicInfo.customer}
                        onChange={handleChangeBasic}
                        variant="outlined"
                        required
                        helperText="* Required"
                    />
                    <Button
                        text="Create Project"
                    />
                </form>
                <Button
                    isSecundary={true}
                    onClick={() => dispatch(selectMenu('projects'))}
                    text="Return to projects"
                />
            </section>
        </div>
    );
}

export default CreateProject;