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
import {Dialog, FormControl, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import './CreateProject.css';
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ErrorIcon from '@mui/icons-material/Error';
import {strings} from "../../strings";
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import Loading from "../../components/Loading/Loading";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";

/**
 * Form component in React.
 *
 * @returns a React form component with three input fields and a submit button.
 */
function CreateProject() {

    const initialProject = {
        name: '',
        customer: {
            name: '',
            taxIdNumber: ''
        },
        businessSector: {
            name: '',
            id: ''
        },
        typology: {
            typologyName: '',
            typologyId: ''
        },
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
    const [showConfirmation, setShowConfirmation] = useState(false);

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
        const selectedCustomer = customers.find((customer) => customer.taxIdNumber === event.target.value);
        setProject({
            ...project, ...{
                customer: selectedCustomer
            }
        });
    }

    const handleChangeBusinessSector = (event) => {
        const selectedBusinessSector = businessSectors.find((businessSector) => businessSector.id === event.target.value);
        setProject({
            ...project, ...{
                businessSector: selectedBusinessSector
            }
        });
    }

    const handleChangeTypology = (event) => {
        const selectedTypology = typologies.find((typology) => typology.typologyId === event.target.value);
        setProject({
            ...project, ...{
                typology: selectedTypology
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
                <h2 style={{marginBottom: '1rem', fontSize: '2rem', textAlign: "center"}}>Please confirm:</h2>
                <table style={{width: '100%'}}>
                    <tbody>
                    <tr>
                        <td><strong>Name:</strong></td>
                        <td>{project.name}</td>
                    </tr>
                    <tr>
                        <td><strong>Customer:</strong></td>
                        <td>{project.customer.name}</td>
                    </tr>
                    <tr>
                        <td><strong>Business Sector:</strong></td>
                        <td>{project.businessSector.name}</td>
                    </tr>
                    <tr>
                        <td><strong>Typology:</strong></td>
                        <td>{project.typology.typologyName}</td>
                    </tr>
                    <tr>
                        <td><strong>Description:</strong></td>
                        <td>{project.description}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        )
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
                            value={project.customer.taxIdNumber}
                            label={"Customer"}
                            onChange={handleChangeCustomer}>
                            {customers && customers.map(({taxIdNumber, name}, index) => (
                                <MenuItem key={index} value={taxIdNumber}>{name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <FormControl fullWidth>
                        <InputLabel id={'projectBusinessSectorInputLabel'}>Business Sector</InputLabel>
                        <Select
                            labelId={'projectBusinessSectorInputLabel'}
                            id={'projectBusinessSectorSelect'}
                            value={project.businessSector.id}
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
                            value={project.typology.typologyId}
                            label={"Typology"}
                            onChange={handleChangeTypology}>
                            {typologies && typologies.map(({typologyId, typologyName}, index) => (
                                <MenuItem key={index} value={typologyId}>{typologyName}</MenuItem>
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
                        type="button"
                        text="Create Project"
                        onClick={handleConfirmation}
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

            <Loading handleLoading={loading}/>

            <ConfirmationPage
                handleOpen={showConfirmation}
                dialogContent={dialogContent()}
                handleCancel={handleCancel}
                handleConfirm={handleSubmit}
            />

            <SuccessMessage
                handleOpen={messageSuccess.length > 0}
                title={strings.projectCreatedSuccessMessage}
                message={messageSuccess}
                handleClose={handleClearProject}
            />

            <FailureMessage
                handleOpen={messageFailure.length > 0}
                title={strings.genericServerError}
                handleClose={handleClearProject}
            />


        </div>
    );
}

export default CreateProject;