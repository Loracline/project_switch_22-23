import React, {useContext, useEffect} from 'react';
import TableBody from "../../components/TableBody/TableBody";
import TableHeader from "../../components/TableHeader/TableHeader";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import {getSprintBacklog} from "../../context/Actions";
import Loading from "../../components/Loading/Loading";

/**
 * A functional component that displays the sprint backlog.
 * @returns {JSX.Element} A div element containing a table header and a table body element.
 */

function ConsultSprintBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {usHeadersSprintBacklog, userStoriesInSprint, loading} = state;

    const selectedSprint = state.detailedSprint;
    const sprintId = selectedSprint.id;

    useEffect(() => {
        getSprintBacklog(dispatch, sprintId)
    }, [dispatch]);

    const data = userStoriesInSprint.map(userStory => {
        return {
            userStoryNumber: userStory.userStoryNumber,
            userStoryText: userStory.userStoryText,
        };
    });

    let tableData;
    if (data.length > 0) {
        tableData = (
            <table className='table'>
                <TableHeader headers={usHeadersSprintBacklog}/>
                <TableBody body={data}/>
            </table>
        );
    } else {
        tableData = (
            <Alert
                style={{marginTop: '1.5rem', marginBottom: '2.5rem'}}
                variant="filled"
                severity="info"
            >
                The sprint selected has no user stories!
            </Alert>
        );
    }
    if (loading === true) {
        return (
            <div>
                <p style={{height: "calc(100vh - 172px - 2rem)"}}></p>
                <Loading handleLoading={loading}/>
            </div>)
    } else {
        return (
            <div>
                <h2 className="pageH2">Sprint Backlog</h2>
                {tableData}
            </div>
        );
    }
}

export default ConsultSprintBacklog;
