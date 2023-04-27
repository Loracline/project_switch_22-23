import React from "react";
import './TableHeader.css';
/**
 *
 * @param headers array passed to create table header
 * This component takes in an array of headers as a prop and maps over the array to create a th element for each header.
 * The key prop is set to the header itself since each header will be unique.
 * @returns {JSX.Element} a header created accordingly with the props passed
 */

const TableHeader = ({headers}) => {
    return (
        <thead className="tableHeader">
        <tr>
            {headers.map((header) => (
                <th key={header}>{header}</th>
            ))}
        </tr>
        </thead>
    );
};

export default TableHeader;