import React, {useContext, useEffect, useState} from 'react';
import TableBody from "../../components/TableBody/TableBody";
import TableHeader from "../../components/TableHeader/TableHeader";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";
import {getSprintBacklog} from "../../context/Actions";

/**
 * A functional component that displays the sprint backlog.
 * @returns {JSX.Element} A div element containing a table header and a table body element.
 */

function ConsultSprintBacklog() {
    const { state, dispatch } = useContext(AppContext);
    const { usHeadersSprintBacklog, userStoriesInSprint } = state;

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
            <table className='table' >
                <TableHeader headers={usHeadersSprintBacklog} />
                <TableBody body={data} />
            </table>
        );
    } else {
        tableData = (
            <Alert
                style={{ marginTop: '1.5rem', marginBottom: '2.5rem' }}
                variant="filled"
                severity="info"
            >
                The sprint selected has no user stories!
            </Alert>
        );
    }

    return (
        <div>
            <h2 className="pageH2">Sprint Backlog</h2>
            {tableData}
        </div>
    );
}

export default ConsultSprintBacklog;
