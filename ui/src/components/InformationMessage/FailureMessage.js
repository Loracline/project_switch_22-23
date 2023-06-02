import React from 'react';
import {Dialog, IconButton} from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';
import ErrorIcon from "@mui/icons-material/Error";

const FailureMessage = ({handleOpen, title, message, handleClose}) => {
    return (
        <Dialog className="failure-dialog" open={handleOpen}>
            <IconButton style={{alignSelf: "end", margin: 0}}
                aria-label="close"
                color="inherit"
                onClick={handleClose}>
                <CloseIcon/>
            </IconButton>
            <div style={{display: "contents"}}>
                <ErrorIcon style={{color: "red", alignSelf: "center", width: 80, height: 80, margin: 10}}/>
                <h3>{title}</h3>
                <h3 style={{alignSelf: "center", marginBottom: 50}}>{message}</h3>
            </div>
        </Dialog>
    );
};

export default FailureMessage;