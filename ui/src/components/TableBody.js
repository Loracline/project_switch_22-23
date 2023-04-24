import React, {useContext} from 'react';
import AppContext from "../context/AppContext";

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

