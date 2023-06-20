import React from 'react';
import "./Button.css";

/**
 * A functional component that displays a button element with an onClick event handler.
 * @param {Object} props - An object containing the properties passed to the component.
 * @param {function} props.onClick - The function to be called when the button is clicked.
 * @param {string} props.text - The text to be displayed on the button.
 * @returns {JSX.Element} A button element with an onClick event handler.
 */

function Button(props) {
    let isSecondary = props.isSecundary;
    return (
        <button className={isSecondary ? 'secondaryButton' : 'button '+ props.pageClass} onClick={props.onClick}
                type={props.type || "submit"} disabled={props.isDisabled}>
            {props.text}
        </button>
    );

}

Button.defaultProps = {
    isSecondary: false
}

export default Button;
