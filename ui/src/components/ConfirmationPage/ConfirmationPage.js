import React from 'react';
import Button from "../Button/Button";
import {Dialog} from "@mui/material";
import './ConfirmationPage.css';

const ConfirmationPage = ({handleOpen, dialogContent, handleCancel, handleConfirm}) => {
    return (
        <Dialog className="confirmation-dialog" open={handleOpen}>
            {dialogContent}
            <Button text="Confirm" onClick={handleConfirm}/>
            <Button text="Cancel" isSecundary={true} onClick={handleCancel}/>
        </Dialog>
    );
};

export default ConfirmationPage;