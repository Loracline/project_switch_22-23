import React, {useContext, useEffect} from 'react';
import AppContext from "../../context/AppContext";
import './ListSprints.css';
import Loading from "../../components/Loading/Loading";
import {selectMenu} from "../../context/Actions";
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody/TableBody";
import Alert from "@mui/material/Alert";
import Button from "../../components/Button/Button";

const ListSprints = () => {
    const {state, dispatch} = useContext(AppContext);
    const headers = state.headersSprints;
    const body = state.sprints;
    const loading = state.loading;

    const sprints = () => {
        let table;
        if (body.length > 0) {
            const onClickSelectSprint = (index) => {
                if (body.length > index) {
                    const selectedSprint = body[index];
                    //dispatch(setCurrentSprint(selectedSprint));
                }
                dispatch(selectMenu('sprint'));
            }
            table = (
                <table className='table'>
                    <TableHeader headers={headers}/>
                    <TableBody body={body} onClick={onClickSelectSprint}/>
                </table>)
        } else {
            table = <Alert variant="filled" severity="info">
                There are no sprints in this project!
            </Alert>
        }
        return table;
    };

    if (loading === true) {
        return (
            <div>
                <p style={{height: "calc(100vh - 172px - 2rem)"}}/>
                <Loading handleLoading={loading}/>
            </div>
        )
    } else {
        return (
            <div className='page pageTable'>
                <h2 className="pageH2">Sprints</h2>

                {sprints()}

                <Button isSecundary={true} onClick={() => {
                    dispatch(selectMenu('project'))
                }} text='Return to project'/>

                <Button onClick={() => {
                    dispatch(selectMenu('createSprint'))
                }} text='Create sprint'/>
            </div>
        );
    }
}

export default ListSprints;