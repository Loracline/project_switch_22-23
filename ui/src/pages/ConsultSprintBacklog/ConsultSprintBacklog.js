import React, {useContext, useEffect, useState} from 'react';
import TableBody from "../../components/TableBody/TableBody";
import TableHeader from "../../components/TableHeader/TableHeader";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";

/**
 * A functional component that displays the sprint backlog.
 * @returns {JSX.Element} A div element containing a table header and a table body element.
 */

function ConsultSprintBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {usHeaders, detailedSprint} = state;

    const [sprintBacklog, setSprintBacklog] = useState([]);
    //const sprintId = detailedSprint.sprintNumber;

    const sprintId = [
        {
            sprintId: 'p001_s021',
            endDate: '2023-06-15',
            projectCode: 'p001',
            sprintNumber: 's021',
            startDate: '2023-06-01',
            status: 'planned',
            userStories: [
                {
                    userStoryNumber: 'us001',
                    userStoryText: 'Como utilizador, quero poder efetuar login no sistema',
                    status: 'open'
                },
                {
                    userStoryNumber: 'us002',
                    userStoryText: 'Como utilizador, quero poder registar novos produtos',
                    status: 'closed'
                },
                {
                    userStoryNumber: 'us003',
                    userStoryText: 'Como utilizador, quero poder adicionar itens ao carrinho de compras',
                    status: 'closed'
                },
                {
                    userStoryNumber: 'us004',
                    userStoryText: 'Como utilizador, quero poder visualizar o histórico de pedidos',
                    status: 'open'

                }
            ]
        }
    ];


/*    useEffect(() => {
        fetch(`http://localhost:8080/sprints/${sprintId}/userStoriesInSprint`, { //`http://localhost:8080/sprints?sprintId=${detailedProject.sprintNumber}/userStoriesInSprint`, {
            method: 'GET',
            headers,
        })
            .then(res => res.json())
            .then(res => {
                console.log(res);
                setSprintBacklog(res);
            })

    })*/


    const data = sprintId[0].userStories.map(userStory => {
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
        tableData = <Alert style={{marginTop: "1.5rem", marginBottom: "2.5rem"}} variant="filled" severity="info"> The sprint selected has no user stories! </Alert>;
    }
    return (
        <div className='page'>
            <h2 className="pageH2">Sprint Backlog</h2>
            {tableData}
        </div>);
}

export default ConsultSprintBacklog;