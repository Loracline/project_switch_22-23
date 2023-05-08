import React, {useContext} from 'react';
import TableBody from "../../components/TableBody/TableBody";
import TableHeader from "../../components/TableHeader/TableHeader";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import Button from "../../components/Button/Button";
import {selectMenu} from "../../context/Actions";

    /**
    * A functional component that displays the product backlog.
     * * @returns {JSX.Element} A div element containing a h1 element, an input text component, a table header and * a table body element. */function ConsultProductBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {usHeaders, detailedProject} = state;
    const data = detailedProject.userStories.map(userStory => {
        return {
            userStoryNumber: userStory.userStoryNumber,
            userStoryText: userStory.userStoryText,
            status: userStory.status,
        };
    });
    let tableData;
    if (data.length > 0) {
        tableData = (<table><TableHeader headers={usHeaders}/> <TableBody body={data}/></table>)
    } else {
        tableData = <Alert variant="filled" severity="info"> The project selected has no user stories! </Alert>;
    }
    return (<div className='page'><h1>Consult Product Backlog</h1> {tableData} <Button
        onClick={() => dispatch(selectMenu('createUserStory'))} text="Create user story"/> <Button isSecundary={true}
                                                                                                   onClick={() => dispatch(selectMenu('projects'))}
                                                                                                   text="Return to projects"/>
    </div>);
}

export default ConsultProductBacklog;