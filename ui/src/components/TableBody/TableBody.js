import React from 'react';
import './TableBody.css';
import {selectMenu, setCurrentProject} from "../../context/Actions";

/**
 * @param body array passed to create table body
 * This component takes in an array of body as a prop and maps over the array to create a td element for each column.
 * @returns {JSX.Element} a body created accordingly with the props passed.
 * */

function TableBody({ isClickable, body, dispatch }) {
    return (
        <tbody>
        {body.map((item, index) => (
            <tr key={index} className={isClickable? "tableBody clickRow":"tableBody"} onClick={() => {dispatch(setCurrentProject(item));
                dispatch(selectMenu('project'))}}>
                {Object.values(item).map((value, i) => (
                    <td key={i}>{value}</td>
                ))}
            </tr>
        ))}
        </tbody>
    );
}

TableBody.defaultProps = {
    isClickable: false
}

export default TableBody;

