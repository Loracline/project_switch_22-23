import React, {useContext, useState} from "react";
import Button from "../../components/Button/Button";
import {createProject, selectMenu} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import {TextField} from "@mui/material";
import DateInput from "../../components/DateInput";

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
        },
        additionalInfo: {
            businessSector: undefined,
            typology: undefined,
            description: undefined,
        }
    };

    const {dispatch} = useContext(AppContext);
    const [basicInfo, setBasicInfo] = useState(initialProject.basicInfo);
    const [additionalInfo, setAdditionalInfo] = useState(initialProject.additionalInfo);

    const handleChangeBasic = (event) => {
        const {name, value} = event.target;
        const newBasicInfo = {...basicInfo};
        newBasicInfo[name] = value;
        setBasicInfo(newBasicInfo);
    }

    const handleChangeAdditional = (event) => {
        const {name, value} = event.target;
        const newAdditionalInfo = {...additionalInfo};
        newAdditionalInfo[name] = value;
        setAdditionalInfo(newAdditionalInfo);
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        const projectToAdd = {
            basicInfo,
            additionalInfo,
            userStories: [],
            sprints: []
        }

        dispatch(createProject({...projectToAdd}));
        window.alert(`The project "${basicInfo.name}" was successfully created.`);
        setBasicInfo(initialProject.basicInfo);
        setAdditionalInfo(initialProject.additionalInfo);
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
                    <TextField
                        name="businessSector"
                        label="Business Sector"
                        value={additionalInfo.businessSector}
                        onChange={handleChangeAdditional}
                        variant="outlined"
                        required
                        helperText="* Required"
                    />
                    <TextField
                        name="typology"
                        label="Typology"
                        value={additionalInfo.typology}
                        onChange={handleChangeAdditional}
                        variant="outlined"
                        required
                        helperText="* Required"
                    />
                    <TextField
                        name="description"
                        label="Description"
                        value={additionalInfo.description}
                        onChange={handleChangeAdditional}
                        variant="outlined"
                        required
                        helperText="* Required"
                        multiline
                        rows={4}
                    />
                    <br/>
                    <br/>
                    <DateInput
                        name="startDate"
                        title="Start Date"
                        value={basicInfo.startDate}
                        onChange={handleChangeBasic}
                    />
                    <DateInput
                        name="endDate"
                        title="End Date"
                        value={basicInfo.endDate}
                        onChange={handleChangeBasic}
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