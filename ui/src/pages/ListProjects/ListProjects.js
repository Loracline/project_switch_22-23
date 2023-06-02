import React, {useContext, useEffect} from 'react';
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody/TableBody";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import {getProjects, selectMenu, setCurrentProject} from "../../context/Actions";
import Button from "../../components/Button/Button";
import './ListProjects.css';
import Loading from "../../components/Loading/Loading";

/**
 * A functional component that displays a list of all projects.
 * @returns {JSX.Element} A div element containing a h1 element and a table.
 */

const ListProjects = () => {
    const {state, dispatch} = useContext(AppContext);
    const headers = state.headersProjects;
    const loading = state.loading;
    const data = state.projects;
    useEffect(() => {
        getProjects(dispatch)
    }, [dispatch]);

    const tableData = () => {
        let tableData;
        if (data.length > 0) {
            const onClickTableBody = (index) => {
                if (state.projects.length > index) {
                    const selectedProject = state.projects[index];
                    dispatch(setCurrentProject(selectedProject));
                }
                dispatch(selectMenu('project'));
            };
            tableData = (
                <table className='table'>
                    <TableHeader headers={headers}/>
                    <TableBody body={data} onClick={onClickTableBody}/>
                </table>)
        } else {
            tableData = <Alert variant="filled" severity="info">
                There are no projects!
            </Alert>;
        }
        return tableData;
    };

    if (loading === true) {
        return (
            <div>
                <p style={{height: "calc(100vh - 172px - 2rem)"}}> </p>
                <Loading handleLoading={loading}/>
            </div>)
    } else {
        return (
            <div className='page pageTable'>
                <h2 className="pageH2">Projects</h2>
                {tableData()}
                <Button onClick={() => {
                    dispatch(selectMenu('createProject'))
                }} text='Create project'/>

            </div>
        );
    }
}

export default ListProjects;