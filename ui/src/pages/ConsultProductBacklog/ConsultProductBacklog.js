import React, {useContext, useEffect, useState} from 'react';
import TableBody from "../../components/TableBody/TableBody";
import TableHeader from "../../components/TableHeader/TableHeader";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import Button from "../../components/Button/Button";
import './ConsultProductBacklog.css';
import {API_HEADERS as headers} from "../../services/api";
import {Link} from "react-router-dom";

/**
 * A functional component that displays the product backlog.
 * * @returns {JSX.Element} A div element containing a h1 element, an input text component, a table header and * a table body element. */function ConsultProductBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {usHeaders, detailedProject} = state;

    const [backlog, setBacklog] = useState([]);
    const projectCode = detailedProject?.code;

    useEffect(() => {
        if(projectCode) {
            fetch(`http://localhost:8080/projects/${projectCode}/productBacklog`, {
                method: 'GET',
                headers,
            })
                .then(res => res.json())
                .then(res => {
                    console.log(res);
                    setBacklog(res);
                })
        }
    }, [projectCode, dispatch])


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
        tableData =
            <Alert style={{marginTop: "1.5rem", marginBottom: "2.5rem"}} variant="filled" severity="info"> The project
                selected has no user stories! </Alert>;
    }
    return (
        <div className='page'>
            <h2 className="pageH2">Consult Product Backlog</h2>
            {tableData}
            <div className="buttons-backlog">
                <Link to={"/projects/" + projectCode}>
                    <Button isSecundary={true} text="Return"/>
                </Link>
                <Link to={"/projects/" + projectCode + "/create-us"}>
                    <Button text="Create user story"/>
                </Link>


            </div>
        </div>);
}

export default ConsultProductBacklog;