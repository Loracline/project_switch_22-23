import React from 'react';
import "./InputText.css";

/**
 * @param title prop is the title of the imput text.
 * @param contentOfImputText prop is the current text inside the text area.
 * @param handleTextChange prop is a function that gets called whenever the text area's value changes.
 * 
 */
const InputText = ({title, handleTextChange, contentOfInputText}) => {
    return (
        <>
            <label htmlFor="myInputText"> {title} </label>
            <br/>
            <br/>
            <input
                className="inputText"
                type="text"
                name="myInputText"
                id="myInputText"
                value={contentOfInputText}
                onChange={handleTextChange}/>
        </>
    )
}

export default InputText;