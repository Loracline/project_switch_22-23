import Button from "../../components/Button/Button";
import React, {useContext, useState} from "react";
import {closeButton, selectMenu} from "../../context/Actions";
import {
    Autocomplete,
    Box,
    FormControl,
    InputAdornment,
    InputLabel,
    MenuItem,
    Select,
    Snackbar,
    TextField,
} from "@mui/material";
import AppContext from "../../context/AppContext";
import DatePickerInput from "../../components/DatePickerInput/DatePickerInput";
import {postResource} from "../../services/ResourceService";
import Alert from "@mui/material/Alert";
import {useGetAccounts} from "./useGetAccounts";
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";

function AllocateResource() {

    const {state, dispatch} = useContext(AppContext);
    const {detailedProject} = state;

    const initialResource = {
        projectCode: detailedProject.code,
        accountEmail: "",
        accountRole: "",
        accountCostPerHour: "",
        accountPercentageOfAllocation: "",
        startDate: null,
        endDate: null
    }

    const [resource, setResource] = useState(initialResource);
    const [percentageError, setPercentageError] = useState('');
    const [backendError, setBackendError] = useState({message: '', show: false});
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [userAccounts] = useGetAccounts();
    const messageSuccess = state.messageSuccess;
    const messageFailure = state.messageFailure;

    const handleConfirmation = () => {
        setShowConfirmation(true);
    }

    const handleCancel = () => {
        setShowConfirmation(false);
    };

    const handleChange = (event) => {
        const {name, value} = event.target;
        const newResource = {...resource};
        newResource[name] = value;
        setResource(newResource);
    }

    const handleAccountChange = (event, value) => {
        const newResource = {...resource};
        newResource["accountEmail"] = value.email;
        setResource(newResource);
    }

    const handleChangeForPercentageOfAllocation = (event) => {
        const {value} = event.target;
        const newValue = value.replace(/[^0-9.]/g, '');
        const floatValue = parseFloat(newValue);
        let inputError = '';

        if (newValue !== '') {
            if (floatValue < 0 || floatValue > 100) {
                inputError = 'Only numbers between 0 and 100 are allowed.';
            }
        }
        const newResource = {...resource};
        if (inputError === '') {
            newResource["accountPercentageOfAllocation"] = newValue;
        }
        setPercentageError(inputError);
        setResource(newResource);
    }
    const handleChangeForStartDate = (date) => {
        const newResource = {...resource};
        newResource["startDate"] = date;
        setResource(newResource);
    }
    const handleChangeForEndDate = (date) => {
        const newResource = {...resource};
        newResource["endDate"] = date;
        setResource(newResource);
    }

    const handleBannerClose = () => {
        setBackendError({message: '', show: false});
    }

    const handleSubmit = () => {
        postResource(resource)
            .then((res) => {
                console.log("sucesso yeah!");
            })
            .catch((err) => setBackendError({message: err.message, show: true}))
        setShowConfirmation(false);
    }

    const dialogContent = () => {
        return (
            <div>
                <h2 style={{marginBottom: '1rem', fontSize: '2rem', textAlign: "center"}}>Please confirm:</h2>
                <table style={{width: '100%'}}>
                    <tbody>
                    <tr>
                        <td><strong>Project Code:</strong></td>
                        <td>{resource.projectCode}</td>
                    </tr>
                    <tr>
                        <td><strong>Email:</strong></td>
                        <td>{resource.accountEmail}</td>
                    </tr>
                    <tr>
                        <td><strong>Role:</strong></td>
                        <td>{resource.accountRole}</td>
                    </tr>
                    <tr>
                        <td><strong>Cost Per Hour:</strong></td>
                        <td>{resource.accountCostPerHour}</td>
                    </tr>
                    <tr>
                        <td><strong>Percentage of Allocation:</strong></td>
                        <td>{resource.accountPercentageOfAllocation}</td>
                    </tr>
                    <tr>
                        <td><strong>Start Date:</strong></td>
                        <td>{new Date(resource.startDate).toLocaleDateString('en-US', {
                            month: 'short',
                            day: 'numeric',
                            year: 'numeric'
                        })}</td>
                    </tr>
                    <tr>
                        <td><strong>End Date:</strong></td>
                        <td>{new Date(resource.endDate).toLocaleDateString('en-US', {
                            month: 'short',
                            day: 'numeric',
                            year: 'numeric'
                        })}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        )
    }

    return (
        <div className="page">
            <Snackbar open={backendError.show} autoHideDuration={6000}>
                <Alert severity="error" onClose={handleBannerClose} sx={{width: '100%'}}>
                    {backendError.message}
                </Alert>
            </Snackbar>
            <section className="formCard">
                <h2>Allocate Resource</h2>
                <form className="resource-form">
                    <div className={"user-role"}>
                        <Autocomplete
                            sx={{width: 500}}
                            options={userAccounts}
                            getOptionLabel={(option) => option.email}
                            getOptionDisabled={(option) => option.status.toUpperCase() === "INACTIVE"}
                            renderOption={(props, option) => (
                                <Box component="li"
                                     sx={{'& > img': {mr: 2, flexShrink: 0}}} {...props}>
                                    <img loading="lazy" width="35" src={"/user.png"} alt=""/>
                                    <Box sx={{flex: 1}}>
                                        <span>{option.name} - {option.email}
                                            <br/>
                                            <span
                                                style={{color: option.status.toUpperCase() === 'ACTIVE' ? 'green' : 'red'}}>{option.status.toLowerCase()}</span>
                                        </span>
                                    </Box>
                                    <Box
                                        sx={{marginLeft: `calc(250px - ${option.name.length + option.email.length}ch)`}}>

                                        <img loading="lazy" width="50" alt=""
                                             src={option.status.toUpperCase() === "ACTIVE" ? "/active.png" : "/inactive.png"}/>
                                    </Box>
                                </Box>
                            )}
                            onChange={handleAccountChange}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="User"
                                    name="accountEmail"
                                    value={resource.accountEmail}
                                    required
                                />
                            )}
                            filterOptions={(options, state) =>
                                options.filter(
                                    (option) =>
                                        option.name.toLowerCase().includes(state.inputValue.toLowerCase()) ||
                                        option.email.toLowerCase().includes(state.inputValue.toLowerCase())
                                )
                            }
                        />

                        <br/>

                        <FormControl required sx={{width: 300}}>
                            <InputLabel>Role</InputLabel>
                            <Select
                                name="accountRole"
                                value={resource.accountRole}
                                label="Role"
                                onChange={handleChange}>
                                <MenuItem value={"Team Member"}>Team Member</MenuItem>
                                <MenuItem value={"Product Owner"}>Product Owner</MenuItem>
                                <MenuItem value={"Scrum Master"}>Scrum Master</MenuItem>
                                <MenuItem value={"Project Manager"} disabled>Project
                                    Manager</MenuItem>
                            </Select>
                        </FormControl>
                    </div>
                    <br/>

                    <div className="cost+percentage">
                        <TextField
                            sx={{width: 300}}
                            name="accountCostPerHour"
                            label="Cost"
                            type="number"
                            helperText={'Total paid for each hour of work'}
                            value={resource.accountCostPerHour}
                            onChange={handleChange}
                            required
                            InputProps={{
                                endAdornment: (<InputAdornment
                                    position="end">€/Hour</InputAdornment>),
                                inputProps: {min: 0}
                            }}
                        />

                        <br/>
                        <br/>

                        <TextField
                            sx={{width: 300}}
                            label="Percentage Of Allocation"
                            type="number"
                            error={Boolean(percentageError)}
                            helperText={percentageError || 'Between 0 - 100%'}
                            value={resource.accountPercentageOfAllocation}
                            onChange={handleChangeForPercentageOfAllocation}
                            required
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">%</InputAdornment>)
                            }}
                        />
                    </div>

                    <br/>


                    <div className="date pickers">
                        <DatePickerInput
                            width={300}
                            label="Start Date"
                            disablePast={true}
                            //minDate={detailedProject.startDate}
                            //maxDate={detailedProject.endDate || resource.endDate}
                            value={resource.startDate}
                            onChange={handleChangeForStartDate}
                            format="DD/MM/YYYY"
                            required={true}
                            helperText="DD/MM/YYYY"
                        />

                        <br/>

                        <DatePickerInput
                            width={300}
                            label="End Date"
                            disablePast={true}
                            //minDate={resource.startDate/* || detailedProject.startDate*/}
                            //maxDate={detailedProject.endDate}
                            value={resource.endDate}
                            onChange={handleChangeForEndDate}
                            format="DD/MM/YYYY"
                            required={true}
                            helperText="DD/MM/YYYY"
                        />
                    </div>
                    <Button
                        isSecundary={true}
                        onClick={() => dispatch(selectMenu('project'))}
                        text="Return"
                    />

                    <Button text="Submit "
                            type="button"
                            isDisabled={
                                !resource.accountEmail ||
                                !resource.accountRole ||
                                !resource.accountCostPerHour ||
                                !resource.accountPercentageOfAllocation ||
                                !resource.startDate ||
                                !resource.endDate
                            }
                            onClick={handleConfirmation}
                    />

                    {messageSuccess && (<div><p>Resource created!</p><button onClick={() => dispatch(closeButton())}>Close</button></div>)}
                    {messageFailure && (<div><p>Resource not created!</p><button onClick={() => dispatch(closeButton())}>Close</button></div>)}
                </form>
            </section>
            <ConfirmationPage
                handleOpen={showConfirmation}
                dialogContent={dialogContent()}
                handleCancel={handleCancel}
                handleConfirm={handleSubmit}
            />

        </div>
    )
}

export default AllocateResource;
