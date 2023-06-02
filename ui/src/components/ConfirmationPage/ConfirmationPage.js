import React from 'react';
import Button from "../Button/Button";
import {Box, Dialog} from "@mui/material";
import './ConfirmationPage.css';

const ConfirmationPage = ({handleOpen, dialogContent, handleCancel, handleConfirm}) => {
    return (
        <Dialog className="confirmation-dialog" open={handleOpen}>
            {dialogContent}
            <Box display="flex" justifyContent="space-between">
                <Button text="Cancel" isSecundary={true} onClick={handleCancel}/>
                <Button text="Confirm" onClick={handleConfirm}/>
            </Box>
        </Dialog>
    );
};

export default ConfirmationPage;