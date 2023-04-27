import React, {useContext} from 'react';
import TableBody from "../components/TableBody";
import TableHeader from "../components/TableHeader/TableHeader";
import AppContext from "../context/AppContext";
import InputText from "../components/InputText";

/**
 * A functional component that displays the product backlog.
 * @returns {JSX.Element} A div element containing a h1 element, an input text component, a table header and
 * a table body element.
 */

function ConsultProductBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {headers, bodies} = state;
    let tableData;
    if (bodies.length > 0) {
        tableData = (
            <div>
                <InputText/>
                <TableHeader headers={headers}/>
                <TableBody body={bodies}/>
            </div>
        )
    } else {
        tableData = <h1>No data.</h1>;
    }

    return (
        <div>
            <h1>Consult Product Backlog</h1>
            {tableData}
        </div>
    );
}

export default ConsultProductBacklog;
