import React from "react";
import './DateInput.css';

/**
 * Renders an input field for selecting a date.
 * @param value the current value of the input field.
 * @param onChange a function to be called when the value of the input field changes.
 * @returns {JSX.Element} an element representing an input field for selecting a date.
 */
let DateInput;
DateInput = ({title, name, value, onChange}) => {
    return (
        <div>
            <label className='label' htmlFor="myDate"> {title} </label>
            <input className='input' type="date" id="date" name={name} value={value} onChange={onChange}/>
        </div>
    )
};

export default DateInput;