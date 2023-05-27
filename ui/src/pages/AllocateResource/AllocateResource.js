import Button from "../../components/Button/Button";
import React, {useContext, useState} from "react";
import {selectMenu} from "../../context/Actions";
import {FormControl, FormHelperText, InputAdornment, InputLabel, MenuItem, Select, TextField} from "@mui/material";
import AppContext from "../../context/AppContext";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {DemoContainer} from "@mui/x-date-pickers/internals/demo";
import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {DatePicker} from "@mui/x-date-pickers";

function AllocateResource() {

    const initialResource = {
        projectCode: "",
        accountEmail: "",
        accountRole: "",
        accountCostPerHour: "",
        accountPercentageOfAllocation: "",
        startDate: "",
        endDate: ""
    }

    const {dispatch} = useContext(AppContext);
    const [resource, setResource] = useState(initialResource);


    const handleChange = (event) => {
        const {name, value} = event.target;
        const newResource = {...resource};
        newResource[name] = value;
        setResource(newResource);
    }
    const handleStartDate = (date) => {
        const newResource = {...resource};
        newResource["startDate"] = date;
        setResource(newResource);
    }
    const handleEndDate = (date) => {
        const newResource = {...resource};
        newResource["endDate"] = date;
        setResource(newResource);
    }


    return (
        <div className="page">
            <section className="formCard">
                <h2>Allocate Resource</h2>
                <form className="resource-form" /*onSubmit={handleSubmit}*/>


                    <div className="resource-form-row">
                        <FormControl required fullWidth>
                            <InputLabel>Role</InputLabel>
                            <Select name="accountRole" value={resource.accountRole}
                                    label="Role" onChange={handleChange}>
                                <MenuItem value={"TEAM_MEMBER"}>Team Member</MenuItem>
                                <MenuItem value={"PRODUCT_OWNER"}>Product Owner</MenuItem>
                                <MenuItem value={"SCRUM_MASTER"}>Scrum Master</MenuItem>
                                <MenuItem value={"PROJECT_MANAGER"} disabled>Project
                                    Manager</MenuItem>
                            </Select>
                        </FormControl>
                        <br/>
                        <br/>
                    </div>


                    <div className="resource-form-row">
                        <TextField
                            required
                            label="Cost"
                            InputProps={{
                                endAdornment: (
                                    <InputAdornment position="end">€</InputAdornment>)
                            }}/>
                        <FormHelperText>total paid for each hour worked</FormHelperText>
                        <br/>
                        <br/>
                    </div>


                    <div className="resource-form-row">
                        <LocalizationProvider dateAdapter={AdapterDayjs} >
                            <DemoContainer components={['DatePicker']}>
                                <DatePicker
                                    label="Start Date"
                                    disablePast
                                    maxDate={resource.endDate}
                                    value={resource.startDate}
                                    onChange={handleStartDate}
                                    required
                                />
                            </DemoContainer>
                        </LocalizationProvider>
                        <FormHelperText>DD/MM/YYYY</FormHelperText>
                        <br/>
                        <br/>
                    </div>


                    <div>
                        <LocalizationProvider dateAdapter={AdapterDayjs}>
                            <DemoContainer components={['DatePicker']}>
                                <DatePicker
                                    label="End Date"
                                    disablePast
                                    minDate={resource.startDate}
                                    value={resource.endDate}
                                    onChange={handleEndDate}
                                    required
                                />
                            </DemoContainer>
                        </LocalizationProvider>
                        <FormHelperText>DD/MM/YYYY</FormHelperText>
                        <br/>
                        <br/>
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
