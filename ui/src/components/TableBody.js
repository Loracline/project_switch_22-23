import React, {useContext} from 'react';
import AppContext from "../context/AppContext";

/**
 * Component that allows to create a table body.
 *
 * 'data' fetched from AppProvider to be inserted in the table body.
 * @returns the body of a table with the data.
 */

function TableBody() {
    const { state } = useContext(AppContext);
    const { data } = state;
    return (
        <tbody>
        {data.map((item, index) => (
            <tr key={index}>
                <td>{item.name}</td>
                <td>{item.age}</td>
                <td>{item.gender}</td>
            </tr>
        ))}
        </tbody>
    );
}

export default TableBody;

