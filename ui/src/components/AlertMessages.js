import * as React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

function AlertMessages() {
    const handleAlertClickSuccessful = () => {
        alert('Success!');
    };
    const handleAlertClickUnsuccessful = () => {
        alert('Error!');
    };
    return (
        <div>
            <Stack sx={{width: '100%'}} spacing={2}>
                <Alert variant="filled" severity="success" onClick={handleAlertClickSuccessful}>
                    This is a successful action!
                </Alert>
                <Alert variant="filled" severity="error" onClick={handleAlertClickUnsuccessful}>
                    Oops! Something went wrong. Please try again later.
                </Alert>
            </Stack>
        </div>
    );
}

export default AlertMessages;
