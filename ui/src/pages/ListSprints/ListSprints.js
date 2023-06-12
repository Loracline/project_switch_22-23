import React, {useContext, useEffect} from 'react';
import AppContext from "../../context/AppContext";
import './ListSprints.css';
import Loading from "../../components/Loading/Loading";
import {getSprintsFromProject, selectMenu, setCurrentSprint} from "../../context/Actions";
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody/TableBody";
import Alert from "@mui/material/Alert";
import Button from "../../components/Button/Button";

/**
 * Renders a list of sprints for a selected project.
 */
const ListSprints = () => {
    // Access the global app state and dispatch actions.
    const {state, dispatch} = useContext(AppContext);

    // Represents the header and body of the sprints table.
    const tableHeader = state.sprintsTableHeader;
    const tableBody = state.sprintsTableBody;

    // Holds the currently selected project.
    const selectedProject = state.detailedProject;

    // Checks if the project's end date has already passed.
    const isProjectEndDatePassed = new Date(selectedProject?.endDate) < new Date();

    // Stores the loading state of the page.
    const loadingPage = state.loading;

    // Fetch the sprints data when the 'dispatch' function changes, allowing the display of updated info.
    useEffect(() => {
        getSprintsFromProject(dispatch)
    }, [dispatch]);

    /**
     * Renders the sprints table body.
     * @returns {JSX.Element} The table body JSX element.
     */
    const sprintsTable = () => {
        let table;
        // If there are sprints in the selected project...
        if (tableBody.length > 0) {
            // Allows the selection of a specific sprint from list.
            const onClickSelectSprint = (sprintIndex) => {
                if (tableBody.length > sprintIndex) {
                    const selectedSprint = tableBody[sprintIndex];
                    dispatch(setCurrentSprint(selectedSprint));
                }
                dispatch(selectMenu('sprint'));
            }

            table = (
                <table className='table'>
                    <TableHeader headers={tableHeader}/>
                    <TableBody body={tableBody} onClick={onClickSelectSprint}/>
                </table>)

        } else {
            table = <Alert variant="filled" severity="info">
                The selected project doesn't have sprints yet!
            </Alert>
        }
        return table;
    };

    // Conditional rendering based on loading state.
    if (loadingPage === true) {
        return (
            <div>
                <p style={{height: "calc(100vh - 172px - 2rem)"}}/>
                <Loading handleLoading={loadingPage}/>
            </div>
        )
    } else {
        return (
            <div className='page pageTable'>
                <h2 className="pageH2">Sprints</h2>

                {sprintsTable()}

                <Button isSecundary={true} onClick={() => {
                    dispatch(selectMenu('project'))
                }}
                        text='Return'/>

                <Button onClick={() => {
                    dispatch(selectMenu('createSprint'))
                }}
                        text='Create sprint'
                        isDisabled={
                            !selectedProject?.startDate ||
                            !selectedProject?.endDate ||
                            isProjectEndDatePassed}/>
            </div>
        );
    }
}

export default ListSprints;