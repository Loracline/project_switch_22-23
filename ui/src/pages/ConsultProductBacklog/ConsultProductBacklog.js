import React, {useContext, useEffect, useState} from 'react';
import TableBody from "../../components/TableBody/TableBody";
import TableHeader from "../../components/TableHeader/TableHeader";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import Button from "../../components/Button/Button";
import {selectMenu} from "../../context/Actions";
import './ConsultProductBacklog.css';
import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";

/**
 * A functional component that displays the product backlog.
 * * @returns {JSX.Element} A div element containing a h1 element, an input text component, a table header and * a table body element. */function ConsultProductBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {usHeaders, detailedProject} = state;

    const [backlog, setBacklog] = useState([]);
    const projectCode = detailedProject.code;

    useEffect(() => {
        fetch(`http://localhost:8080/projects/${projectCode}/productBacklog`, {
            method: 'GET',
            headers,
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                setBacklog(res);
            })

    },[])


    const data = backlog.map(userStory => {
        return {
            userStoryNumber: userStory.userStoryNumber,
            userStoryText: userStory.userStoryText,
            status: userStory.status,
        };
    });
    let tableData;
    if (data.length > 0) {
        tableData = (<table><TableHeader headers={usHeaders}/><TableBody body={data}/></table>)
    } else {
        tableData = <Alert style={{marginTop: "1.5rem", marginBottom: "2.5rem"}} variant="filled" severity="info"> The project selected has no user stories! </Alert>;
    }
    return (
        <div className='page'>
            <h2 className="pageH2">Consult Product Backlog</h2>
            {tableData}
            <div className="buttons-backlog">
                <Button isSecundary={true} onClick={() => dispatch(selectMenu('project'))} text="Return"/>
                <Button onClick={() => dispatch(selectMenu('createUserStory'))} text="Create user story"/>

            </div>
        </div>);
}

export default ConsultProductBacklog;