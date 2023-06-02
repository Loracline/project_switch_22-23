import React from 'react';
import {Dialog, IconButton} from "@mui/material";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CloseIcon from '@mui/icons-material/Close';

const SuccessMessage = ({handleOpen, title, message, handleClose}) => {
    return (
        <Dialog className="success-dialog" open={handleOpen}>
            <IconButton style={{alignSelf: "end", margin: 0}}
                        aria-label="close"
                        color="inherit"
                        onClick={handleClose}>
                <CloseIcon/>
            </IconButton>
            <div style={{display: "contents"}}>
                <CheckCircleIcon style={{color: "green", alignSelf: "center", width: 80, height: 80, margin: 10}}/>
                <h3>{title}</h3>
                <h2 style={{alignSelf: "center", marginBottom: 50}}>{message}</h2>
            </div>
        </Dialog>
    );
};

export default SuccessMessage;