import Button from "../../components/Button/Button";
import React, {useContext, useState} from "react";
import {selectMenu} from "../../context/Actions";
import {
    FormControl,
    InputAdornment,
    InputLabel,
    Select,
    MenuItem,
    TextField,
    Autocomplete,
    Box,
} from "@mui/material";
import AppContext from "../../context/AppContext";
import DatePickerInput from "../../components/DatePickerInput/DatePickerInput";

function AllocateResource() {

    const initialResource = {
        projectCode: "",
        accountEmail: "",
        accountRole: "",
        accountCostPerHour: "",
        accountPercentageOfAllocation: "",
        startDate: null,
        endDate: null
    }

    const {dispatch} = useContext(AppContext);
    const [resource, setResource] = useState(initialResource);
    const [inputError, setInputError] = useState('');

    const handleChange = (event) => {
        const {name, value} = event.target;
        const newResource = {...resource};
        newResource[name] = value;
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
        setInputError(inputError);
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

    const userAccounts = [
        {email: "john@example.com", name: "John Doe", status: "ACTIVE"},
        {email: "jane@example.com", name: "Jane Smith", status: "INACTIVE"},
        {email: "rui@example.com", name: "Rui", status: "ACTIVE"},
    ];


    return (
        <div className="page">
            <section className="formCard">
                <h2>Allocate Resource</h2>

                <form  /*onSubmit={handleSubmit}*/>
                    <div className="user+role">
                        <Autocomplete
                            sx={{width: 450}}
                            options={userAccounts}
                            getOptionLabel={(option) => option.email}
                            getOptionDisabled={(option) => option.status === "INACTIVE"}
                            renderOption={(props, option) => (
                                /*<li {...props}>
                                    <div>
                                        {option.name} - {option.email} ({option.status.toLowerCase()})
                                    </div>
                                </li>*/
                                <Box component="li"
                                     sx={{'& > img': {mr: 2, flexShrink: 0}}} {...props}>
                                    <img loading="lazy" width="35" src={"/user.png"} alt=""/>
                                    <Box sx={{flex: 1}}>
                                        <span>{option.name} - {option.email}
                                            <br/>
                                            <span style={{color: option.status === 'ACTIVE' ? 'green' : 'red'}}>{ option.status.toLowerCase()}</span>
                                        </span>
                                    </Box>
                                    <Box
                                        sx={{marginLeft: `calc(250px - ${option.name.length + option.email.length}ch)`}}>

                                        <img loading="lazy" width="50" alt=""
                                             src={option.status === "ACTIVE" ? "/active.png" : "/inactive.png"}/>
                                    </Box>
                                </Box>
                            )}
                            renderInput={(params) => (
                                <TextField
                                    {...params}
                                    label="User"
                                    name="accountEmail"
                                    value={resource.accountEmail}
                                    onChange={handleChange}
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
                                    position="end">€/Hour</InputAdornment>)
                            }}
                        />

                        <br/>
                        <br/>

                        <TextField
                            sx={{width: 300}}
                            label="Percentage Of Allocation"
                            type="number"
                            unit={5}

                            error={Boolean(inputError)}
                            helperText={inputError || 'Between 0 - 100%'}
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
                            maxDate={resource.endDate}
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
                            minDate={resource.startDate}
                            value={resource.endDate}
                            onChange={handleChangeForEndDate}
                            format="DD/MM/YYYY"
                            required={true}
                            helperText="DD/MM/YYYY"
                        />
                    </div>

                    <Button text="Submit "/>
                    <Button
                        isSecundary={true}
                        onClick={() => dispatch(selectMenu('project'))}
                        text="Return"
                    />
                </form>
            </section>
        </div>
    )
}

export default AllocateResource;
