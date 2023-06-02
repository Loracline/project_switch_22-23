import {LocalizationProvider} from "@mui/x-date-pickers/LocalizationProvider";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import {DatePicker} from "@mui/x-date-pickers";
import {FormHelperText} from "@mui/material";
import React from "react";

const DatePickerInput = ({width, label, disablePast, minDate, maxDate, format, required, helperText, value, onChange, isDisabled}) => {
    return (
        <div style={{display: "flex", flexDirection: "column"}}>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker
                    sx={{width: width}}
                    label={label}
                    disablePast={disablePast}
                    disabled = {isDisabled}
                    minDate={minDate}
                    maxDate={maxDate}
                    value={value}
                    onChange={onChange}
                    format={format}
                    slotProps={{
                        textField: {
                        required: required,
                        },
                    }}
                />
            </LocalizationProvider>
            <FormHelperText>{helperText}</FormHelperText>
        </div>
    )
}

export default DatePickerInput;
