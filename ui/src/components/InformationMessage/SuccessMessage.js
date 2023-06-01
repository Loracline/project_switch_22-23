import React from 'react';
import {Dialog, IconButton} from "@mui/material";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CloseIcon from '@mui/icons-material/Close';


const SuccessMessage =  ({handleOpen, title, message, handleClose}) => {
    return (
        <Dialog className="success-dialog" open={handleOpen}>
            <CheckCircleIcon style={{color: "green", alignSelf: "center", width: 80, height: 80, margin: 10}}/>
            <h3>{title}</h3>
            <h2 style={{alignSelf: "center"}}>{message}</h2>
            <IconButton
                aria-label="close"
                color="inherit"
                onClick={handleClose}>
                <CloseIcon/>
            </IconButton>
        </Dialog>
    );
};

export default SuccessMessage;