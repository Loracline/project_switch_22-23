import React from "react";

/**
 * Renders an input field for selecting a date.
 * @param value the current value of the input field.
 * @param onChange a function to be called when the value of the input field changes.
 * @returns {JSX.Element} an element representing an input field for selecting a date.
 */
let DateInput;
DateInput = ({value, onChange}) => {
    return (
        <input type="date" id="date" name="date" value={value} onChange={onChange}/>
    )
};

export default DateInput;