import React from "react";
import TableBody from "./TableBody";
import TableHeader from "./TableHeader/TableHeader";

/**
  * Component that allows to create a table.
  *
  * 'data' fetched from AppProvider to be inserted in the table.
  * @returns the table with the data.
  */

const Table = (props) => {
  return (
    <table>
      <TableHeader headers={props.headers} />
      <TableBody data={props.data} />
    </table>
  );
};

export default Table;