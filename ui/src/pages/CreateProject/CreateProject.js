import React, {useContext, useEffect, useState} from "react";
import Button from "../../components/Button/Button";
import {
    createProject,
    fetchBusinessSectors,
    fetchCustomers,
    fetchTypologies,
    resetCreateProject,
    selectMenu
} from "../../context/Actions";
import AppContext from "../../context/AppContext";
import {CircularProgress, Dialog, FormControl, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import './CreateProject.css';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ErrorIcon from '@mui/icons-material/Error';
import {strings} from "../../strings";

/**
 * Form component in React.
 *
 * @returns a React form component with three input fields and a submit button.
 */
function CreateProject() {

    const initialProject = {
        name: '',
        customer: '',
        businessSector: '',
        typology: '',
        description: ''
    };

    const {state, dispatch} = useContext(AppContext);
    const customers = state.customers;
    const typologies = state.typologies;
    const businessSectors = state.businessSectors;
    const loading = state.loading;
    const messageSuccess = state.messageSuccess;
    const messageFailure = state.messageFailure;
    const [project, setProject] = useState(initialProject);

    useEffect(() => {
        fetchCustomers(dispatch);
        fetchTypologies(dispatch);
        fetchBusinessSectors(dispatch);
    }, [dispatch]);

    const handleChangeProjectInfo = (event) => {
        const {name, value} = event.target;
        const newInfo = {...project};
        newInfo[name] = value;
        setProject(newInfo);
    }

    const handleChangeCustomer = (event) => {
        setProject({
            ...project, ...{
                customer: event.target.value
            }
        });
    }

    const handleChangeBusinessSector = (event) => {
        setProject({
            ...project, ...{
                businessSector: event.target.value
            }
        });
    }

    const handleChangeTypology = (event) => {
        setProject({
            ...project, ...{
                typology: event.target.value
            }
        });
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        createProject(dispatch, project);
    }

    const handleReturnToProjects = (_) => {
        dispatch(selectMenu('projects'));
    }

    const handleClearProject = (_) => {
        setProject(initialProject);
        dispatch(resetCreateProject());
    }

    return (
        <div className="page">
            <section className="formCard">
                <h2>Create Project</h2>
                <form className="project-form" onSubmit={handleSubmit}>
                    <FormControl fullWidth>
                        <TextField
                            name="name"
                            label="Project Name"
                            value={project.name}
                            onChange={handleChangeProjectInfo}
                            variant="outlined"
                            required
                            className="textField"
                        />
                    </FormControl>

                    <FormControl fullWidth>
                        <InputLabel id={'projectCustomerInputLabel'}>Customer</InputLabel>
                        <Select
                            labelId={'projectCustomerInputLabel'}
                            id={'projectCustomerSelect'}
                            autoWidth
                            value={project.customer}
                            label={"Customer"}
                            onChange={handleChangeCustomer}>
                            {customers && customers.map(({id, name}, index) => (
                                <MenuItem key={index} value={id}>{name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <FormControl fullWidth>
                        <InputLabel id={'projectBusinessSectorInputLabel'}>Business Sector</InputLabel>
                        <Select
                            labelId={'projectBusinessSectorInputLabel'}
                            id={'projectBusinessSectorSelect'}
                            value={project.businessSector}
                            label={"Business Sector"}
                            onChange={handleChangeBusinessSector}>
                            {businessSectors && businessSectors.map(({id, name}, index) => (
                                <MenuItem key={index} value={id}>{name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <FormControl fullWidth>
                        <InputLabel id={'projectTypologyInputLabel'}>Typology</InputLabel>
                        <Select
                            labelId={'projectTypologyInputLabel'}
                            id={'projectTypologySelect'}
                            value={project.typology}
                            label={"Typology"}
                            onChange={handleChangeTypology}>
                            {typologies && typologies.map(({id, name}, index) => (
                                <MenuItem key={index} value={id}>{name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <TextField
                        name="description"
                        label="Description"
                        value={project.description}
                        onChange={handleChangeProjectInfo}
                        variant="outlined"
                        required
                        multiline
                        rows={4}
                        className="textField"
                    />
                    <Button
                        text="Create Project"
                        onClick={handleSubmit}
                        isDisabled={project.name.length === 0 ||
                            project.customer.length === 0 ||
                            project.businessSector.length === 0 ||
                            project.typology.length === 0 ||
                            project.description.length === 0}
                    />
                </form>
                <Button
                    isSecundary={true}
                    onClick={handleReturnToProjects}
                    text="Return to projects"
                />
            </section>

            <Dialog open={loading}>
                <CircularProgress style={{color: "#6145AF"}} sx={{m: 5}}/>
            </Dialog>

            <Dialog className="success-dialog" open={messageSuccess}>
                <CheckCircleIcon style={{color: "green", alignSelf: "center", width: 80, height: 80, margin: 10}} />
                <h3>{strings.projectCreatedSuccessMessage}</h3>
                <h4>{messageSuccess}</h4>
                <Button text="Close" onClick={handleClearProject} />
            </Dialog>

            <Dialog className="failure-dialog" open={messageFailure}>
                <ErrorIcon style={{color: "red", alignSelf: "center", width: 80, height: 80, margin: 10}} />
                <h3>{strings.genericServerError}</h3>
                <Button text="Close" onClick={handleClearProject} />
            </Dialog>

        </div>
    );
}

export default CreateProject;