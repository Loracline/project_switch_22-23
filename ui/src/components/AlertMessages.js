import * as React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

/**
 * For now to use the alert messages you should copy the specific alert wanted and write your own
 * message.
 */

/**
 * Component that allows to create alert messages.
 *
 * Message can be of success/error/information/warning.
 * @returns the alert message correspondent to the action performed.
 */

function AlertMessages({}) {

    return (
        <Stack sx={{width: '100%'}} spacing={2}>
            <Alert variant="filled" severity="error">
                This is an error alert — check it out!
            </Alert>
            <Alert variant="filled" severity="warning">
                This is a warning alert — check it out!
            </Alert>
            <Alert variant="filled" severity="info">
                This is an info alert — check it out!
            </Alert>
            <Alert variant="filled" severity="success">
                This is a success alert — check it out!
            </Alert>
        </Stack>
    );
}

export default AlertMessages;

