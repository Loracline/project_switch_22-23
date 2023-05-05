import React, {useContext} from 'react';
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody/TableBody";
import AppContext from "../../context/AppContext";
import "./ListProjects.css";
import Alert from "@mui/material/Alert";
import {selectMenu} from "../../context/Actions";
import Button from "../../components/Button/Button";

/**
 * A functional component that displays a list of all projects.
 * @returns {JSX.Element} A div element containing a h1 element and a table.
 */

const ListProjects = () => {
    const {state, dispatch} = useContext(AppContext);
    const headers = state.headersProjects;
    const data = state.bodyProjects;
    const tableData = () => {
        let tableData;
        if (data.length > 0) {
            tableData = (
                <table>
                    <TableHeader headers={headers}/>
                    <TableBody body={data} dispatch={dispatch}/>
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
            <h2 className="pageH1">Projects</h2>
            <div className="tableWrap">
                {tableData()}
                <Button onClick={() => {dispatch(selectMenu('productBacklog'))}} text='Create project'/>
            </div>
        </div>
    );
}

export default ListProjects;