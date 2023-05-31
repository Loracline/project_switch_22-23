import React from 'react';
import Button from "../Button/Button";
import {Dialog} from "@mui/material";

const ConfirmationPage = ({handleOpen, dialogContent, handleCancel, handleConfirm}) => {
    return (
        <Dialog className="confirmation-dialog" open={handleOpen}>
            {dialogContent}
            <Button text="Cancel" isSecundary={true} onClick={handleCancel}/>
            <Button text="Confirm" onClick={handleConfirm}/>
        </Dialog>
    );
};

export default ConfirmationPage;