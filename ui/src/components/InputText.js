import React from 'react';

/**
 * @param title prop is the title of the imput text.
 * @param contentOfImputText prop is the current text inside the text area.
 * @param handleTextChange prop is a function that gets called whenever the text area's value changes.
 * @param htmlFor attribute to associate the label element with the imput element.
 */
const InputText = ({title, handleTextChange, contentOfImputText}) => {
    return (
        <>
            <label htmlFor="myInputText"> {title} </label>
            <br/>
            <br/>
            <input
                type="text"
                name="myInputText"
                id="myInputText"
                value={contentOfImputText}
                onChange={handleTextChange}/>
        </>
    )
}

export default InputText;