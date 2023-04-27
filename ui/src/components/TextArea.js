import React from 'react';

/**
 * @param title prop is the title of the text area.
 * @param contentOfTextArea prop is the current text inside the text area.
 * @param handleTextChange prop is a function that gets called whenever the text area's value changes.
 * @param htmlFor attribute to associate the label element with the text area element.
 */
const TextArea = ({contentOfTextArea, handleTextChange, title}) => {
    return (
        <>
            <label htmlFor="myTextArea"> {title} </label>
            <br/>
            <br/>
            <textarea
                name="myTextArea"
                id="myTextArea"
                value={contentOfTextArea}
                onChange={handleTextChange}/>
        </>
    );
};

export default TextArea;