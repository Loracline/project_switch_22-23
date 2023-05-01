import React, {useState} from 'react';

/**
 * Calendar component for selecting a date.
 * @param {object} props - Component props.
 * @param {function} [props.onDateChange] - Function to be called when the selected date changes.
 * @returns {JSX.Element} - Rendered component.
 */

function Calendar(props) {
    const [selectedDate, setSelectedDate] = useState(null);
    const handleDateChange = (event) => {
        setSelectedDate(event.target.value);
        if (props.onDateChange) {
            props.onDateChange(event.target.value);
        }
    };
    return (<div>
        <label htmlFor="calendar">
        </label>
        <input type="date" id="calendar" name="calendar" value={selectedDate} onChange={handleDateChange}
        />
    </div>);
}

export default Calendar;