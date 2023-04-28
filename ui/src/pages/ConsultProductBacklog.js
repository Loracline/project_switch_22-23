import React, {useContext, useMemo} from 'react';
import TableBody from "../components/TableBody/TableBody";
import TableHeader from "../components/TableHeader/TableHeader";
import AppContext from "../context/AppContext";
import SelectProjectTextInput from "../components/SelectProjectTextInput";
import Alert from "@mui/material/Alert";

/**
 * A functional component that displays the product backlog.
 * @returns {JSX.Element} A div element containing a h1 element, an input text component, a table header and
 * a table body element.
 */

function ConsultProductBacklog() {
    const {state} = useContext(AppContext);
    const {usHeaders, selectedProject} = state;
    const tableData = useMemo(() => {
        let tableData;
        if (selectedProject) {
            if (selectedProject.userStories.length > 0) {
                tableData = (
                    <table>
                        <TableHeader headers={usHeaders}/>
                        <TableBody body={selectedProject.userStories}/>
                    </table>)
            } else {
                tableData = <Alert variant="filled" severity="info">
                    The project selected has no user stories!
                </Alert>;
            }
        } else {
            tableData = <Alert variant="filled" severity="info">
                The project selected does not exist!
            </Alert>;
        }
        return tableData;
    }, [selectedProject, usHeaders]);
    return (
        <div>
            <h1>Consult Product Backlog</h1>
            <SelectProjectTextInput/>
            {tableData}
        </div>
    );
}

export default ConsultProductBacklog;