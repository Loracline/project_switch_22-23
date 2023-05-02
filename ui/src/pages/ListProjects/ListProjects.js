import React, {useContext} from 'react';
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody/TableBody";
import AppContext from "../../context/AppContext";
import "./ListProjects.css";
import Alert from "@mui/material/Alert";

/**
 * A functional component that displays a list of all projects.
 * @returns {JSX.Element} A div element containing an h1 element and a table.
 */

const ListProjects = () => {
    const {state} = useContext(AppContext);
    const headers = state.headersProjects;
    const data = state.bodyProjects;
    const tableData = () => {
        let tableData;
        if (data.length > 0) {
            tableData = (
                <table>
                    <TableHeader headers={headers}/>
                    <TableBody body={data}/>
                </table>)
        } else {
            tableData = <Alert variant="filled" severity="info">
                There are no projects!
            </Alert>;
        }
        return tableData;
    };

    return (
        <div className='page'>
            <h1 className="pageH1">Projects</h1>
            <div className="tableWrap">
                {tableData()}
            </div>
        </div>
    );
}

export default ListProjects;