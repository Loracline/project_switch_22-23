import React from 'react';


/**
 * A functional component that displays a button element with an onClick event handler.
 * @param {Object} props - An object containing the properties passed to the component.
 * @param {function} props.onClick - The function to be called when the button is clicked.
 * @param {string} props.text - The text to be displayed on the button.
 * @returns {JSX.Element} A button element with an onClick event handler.
 */

function Button(props) {
    return (
        <button onClick={props.onClick}>
            {props.text}
        </button>
    );
}

export default Button;
