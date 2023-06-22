import React from 'react';
import {Dialog, IconButton} from "@mui/material";
import CheckCircleIcon from '@mui/icons-material/CheckCircle';
import CloseIcon from '@mui/icons-material/Close';
import Button from "../Button/Button";

const SuccessMessageWithSelfLink = ({handleOpen, title, message, onButtonClick, handleClose}) => {

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
                <Button onClick={onButtonClick} text="Open Project" />
            </div>
        </Dialog>
    );
};

export default SuccessMessageWithSelfLink;