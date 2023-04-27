import React, {useContext} from 'react';
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody";
import AppContext from "../../context/AppContext";
import "./ListProjects.css";
/**
 * A functional component that displays a list of all projects.
 * @returns {JSX.Element} A div element containing an h1 element and a table.
 */

const ListProjects = () => {
    const {state} = useContext(AppContext);
    const headers = state.headersProjects;
    const data = state.bodyProjects;
    return (
        <div>
            <h1>Projects</h1>
            <table className="table">
                <TableHeader headers={headers}/>
                <TableBody body={data}/>
            </table>
        </div>
    );
}

export default ListProjects;