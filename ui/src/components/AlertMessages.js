import * as React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

/**
 * Component that allows to create alert messages.
 *
 * Message can be of successful/unsuccessful action.
 * @returns the alert message correspondent to the action performed.
 */

function AlertMessages({ severity }) {
    return (
        <div>
            <Stack sx={{width: '100%'}} spacing={2}>
                {severity === 'success' && (
                    <Alert variant="filled" severity="success">
                        This is a successful action!
                    </Alert>
                )}
                {severity === 'error' && (
                    <Alert variant="filled" severity="error">
                        Oops! Something went wrong. Please try again later.
                    </Alert>
                )}
            </Stack>
        </div>
    );
}

export default AlertMessages;

