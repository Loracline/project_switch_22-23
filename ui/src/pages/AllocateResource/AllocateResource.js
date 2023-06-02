import Button from "../../components/Button/Button";
import React, {useContext, useState} from "react";
import { selectMenu} from "../../context/Actions";
import {
    Autocomplete,
    Box,
    FormControl,
    InputAdornment,
    InputLabel,
    MenuItem,
    Select,
    TextField,
} from "@mui/material";
import AppContext from "../../context/AppContext";
import DatePickerInput from "../../components/DatePickerInput/DatePickerInput";
import {postResource} from "../../services/ResourceService";
import {useGetAccounts} from "./useGetAccounts";
import ConfirmationPage from "../../components/ConfirmationPage/ConfirmationPage";
import SuccessMessage from "../../components/InformationMessage/SuccessMessage";
import FailureMessage from "../../components/InformationMessage/FailureMessage";
import {format} from "date-fns";
import dayjs from "dayjs";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import ErrorIcon from "@mui/icons-material/Error";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

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
    const [error, setError] = useState('');
    const [success, setSuccess] = useState({message: '', show: false});
    const [failure, setFailure] = useState({message: '', show: false});
    const [showConfirmation, setShowConfirmation] = useState(false);
    const [userAccounts] = useGetAccounts();

    const handleConfirmation = () => {
        setShowConfirmation(true);
    }

    const handleCancel = () => {
        setShowConfirmation(false);
    };

    const handleRoleChange = (event) => {
        const {name, value} = event.target;
        const newResource = {...resource};
        newResource[name] = value;
        setResource(newResource);
    }

    const handleCostChange = (event) => {
        const {value} = event.target;
        const newResource = {...resource};
        const floatValue = parseFloat(value);
        let valid = true;
        if (value !== '') {
            if (floatValue < 0) {
                valid = false;
            }
        }
        if (valid) {
            newResource["accountCostPerHour"] = value;
        }
        setResource(newResource);
    }

    const handleAccountChange = (event, value) => {
        const newResource = {...resource};
        newResource["accountEmail"] = value ? value.email : ""
        setResource(newResource);
    }

    const handleChangeForPercentageOfAllocation = (event) => {
        const {value} = event.target;
        const floatValue = parseFloat(value);
        const newResource = {...resource};
        let valid = true;
        if (value !== '') {
            if (floatValue < 0 || floatValue > 100) {
                valid = false;
            }
        }
        if (valid) {
            newResource["accountPercentageOfAllocation"] = value;
        }
        setResource(newResource);
    }
    const handleChangeForStartDate = (date) => {
        const formattedDate = date ? format(new Date(date), 'yyyy-MM-dd') : null;
        const newResource = {...resource};
        newResource["startDate"] = formattedDate;
        if(dayjs(resource.startDate).isBefore(dayjs(resource.endDate))){
            setError("End Date must be after Start Date.")
        }
        setResource(newResource);
    }
    const handleChangeForEndDate = (date) => {
        const formattedDate = date ? format(new Date(date), 'yyyy-MM-dd') : null;
        const newResource = {...resource};
        newResource["endDate"] = formattedDate;
        if(dayjs(resource.startDate).isAfter(dayjs(resource.endDate))){
            setError("")
        }
        setResource(newResource);
    }

    const handleSuccessClose = () => {
        setSuccess({message: '', show: false});
        setResource(initialResource);
    }

    const handleFailureClose = () => {
        setFailure({message: '', show: false});
    }

    const handleSubmit = () => {
        postResource(resource)
            .then(() => {
                setSuccess({message: "User allocated successfully", show: true});
            })
            .catch((err) => {
                setFailure({message: err.message, show: true});
            })

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
            <SuccessMessage
                handleOpen={success.show}
                title={success.message}
                handleClose={handleSuccessClose}
            />
            <FailureMessage
                handleOpen={failure.show}
                title={failure.message}
                handleClose={handleFailureClose}
            />
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
                                    <img loading="lazy" width="35" src={"/user.png"}
                                         alt=""/>
                                    <Box sx={{flex: 1}}>
                                        <span>{option.name} - {option.email}
                                            <br/>
                                            <span
                                                style={{color: option.status.toUpperCase() === 'ACTIVE' ? 'green' : 'red'}}>{option.status.toLowerCase()}</span>
                                        </span>
                                    </Box>
                                    <Box
                                        sx={{marginLeft: `calc(250px - ${option.name.length + option.email.length}ch)`}}>

                                        {option.status.toUpperCase() === "ACTIVE"
                                            ?(<CheckCircleIcon style={{color: "green", alignSelf: "center", width: 35, height: 35, margin: 5}}/>)
                                            :(<ErrorIcon style={{color: "red", alignSelf: "center", width: 35, height: 35, margin: 5}}/>)

                                        }
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
                            key={success.show}
                        />

                        <br/>

                        <FormControl required sx={{width: 300}}>
                            <InputLabel>Role</InputLabel>
                            <Select
                                name="accountRole"
                                value={resource.accountRole}
                                label="Role"
                                onChange={handleRoleChange}>
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
                            onChange={handleCostChange}
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
                            helperText={'Between 0 - 100%'}
                            value={resource.accountPercentageOfAllocation}
                            onChange={handleChangeForPercentageOfAllocation}
                            required
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">%</InputAdornment>),
                                inputProps: {min: 0, max:100}
                            }}
                        />
                    </div>

                    <br/>


                    <div className="date pickers">
                        <DatePickerInput
                            width={300}
                            label="Start Date"
                            disablePast={true}
                            minDate={dayjs(detailedProject.startDate)}
                            maxDate={dayjs(detailedProject.endDate)}
                            value={resource.startDate}
                            onChange={handleChangeForStartDate}
                            format="YYYY-MM-DD"
                            helperText="YYYY-MM-DD"
                            required={true}
                        />

                        <br/>

                        <DatePickerInput
                            width={300}
                            label="End Date"
                            disablePast={true}
                            isDisabled={!resource.startDate}
                            minDate = {dayjs(resource.startDate)}
                            maxDate={dayjs(detailedProject.endDate)}
                            value={resource.endDate}
                            onChange={handleChangeForEndDate}
                            format="YYYY-MM-DD"
                            helperText={error && <span style={{ color: 'red' }}>{error}</span> || "YYYY-MM-DD"}

                            required={true}
                        />
                        <br/>
                    </div>

                    <Box display="flex" justifyContent="space-between">
                        <Button
                            isSecundary={true}
                            onClick={() => dispatch(selectMenu('project'))}
                            text="Return"
                            startIcon={<ArrowBackIcon />}
                        />

                        <Button
                            text="Submit"
                            type="button"
                            isDisabled={
                                !resource.accountEmail ||
                                !resource.accountRole ||
                                !resource.accountCostPerHour ||
                                !resource.accountPercentageOfAllocation ||
                                !resource.startDate ||
                                !resource.endDate ||
                                dayjs(resource.startDate).isAfter(dayjs(resource.endDate))
                            }
                            onClick={handleConfirmation}
                        />
                    </Box>
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
