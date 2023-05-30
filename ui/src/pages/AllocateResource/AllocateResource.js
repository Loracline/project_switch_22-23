import Button from "../../components/Button/Button";
import React, {useContext, useState} from "react";
import {selectMenu} from "../../context/Actions";
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


function AllocateResource() {

    const {state, dispatch} = useContext(AppContext);
    const {detailedProject} = state;

    const initialResource = {
        projectCode: 1, //detailedProject.projectCode,
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

    const [userAccounts] = useGetAccounts();


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

    const handleSubmit = (e) => {
        e.preventDefault();
        //Open modal with summary
        //if confirms, proceed
        //Call service to create resource
        //If succeeds showcase success message, clean form etc
        //If fails showcase inform the user
        //example:
        postResource(resource)
            .then((res) => console.log("sucesso yeah!"))
            .catch((err) => setBackendError({message: err.message, show: true}))
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
                <form className="resource-form" onSubmit={handleSubmit}>
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
                                <MenuItem value={"TEAM_MEMBER"}>Team Member</MenuItem>
                                <MenuItem value={"PRODUCT_OWNER"}>Product Owner</MenuItem>
                                <MenuItem value={"SCRUM_MASTER"}>Scrum Master</MenuItem>
                                <MenuItem value={"PROJECT_MANAGER"} disabled>Project
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
                            //minDate={detailedProject.startDate >= new Date() ? detailedProject.startDate : new Date()}
                            //maxDate={detailedProject.endDate}
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
                            //minDate={resource.startDate}
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

                    <Button text="Submit " isDisabled = {
                        !resource.accountEmail ||
                        !resource.accountRole ||
                        !resource.accountCostPerHour ||
                        !resource.accountPercentageOfAllocation ||
                        !resource.startDate ||
                        !resource.endDate
                    }
                    />

                </form>
            </section>
        </div>
    )
}

export default AllocateResource;
