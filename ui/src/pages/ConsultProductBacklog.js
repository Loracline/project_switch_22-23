import React, {useContext, useMemo} from 'react';
import TableBody from "../components/TableBody";
import TableHeader from "../components/TableHeader";
import AppContext from "../context/AppContext";
import SelectProjectTextInput from "./SelectProjectTextInput";
import AlertMessages from "../components/AlertMessages";

/**
 *  A functional component that displays the product backlog.
 *  @returns {JSX.Element} A div element containing a h1 element, an input text component, a table header and
 *  a table body element.
 */

function ConsultProductBacklog() {
    const {state} = useContext(AppContext);
    const {usHeaders, selectedProject} = state;
    const tableData = useMemo(() => {
        let tableData;
        if (selectedProject) {
            if (selectedProject.userStories.length > 0) {
                tableData = (
                    <div>
                        <TableHeader headers={usHeaders}/>
                        <TableBody body={selectedProject.userStories}/>
                    </div>)
            } else {
                tableData = <AlertMessages severity="info_userStories"/>;
            }
        } else {
            tableData = <AlertMessages severity="info_projects"/>
        }
        return tableData;
    }, [selectedProject, usHeaders]);
    return (
        <div>
            <h1>Consult Product Backlog</h1>
            <SelectProjectTextInput/>
            {tableData}
        </div>);
}

export default ConsultProductBacklog;
