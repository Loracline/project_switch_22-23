import * as React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';

/**
 * Component that allows to create alert messages.
 *
 * Message can be of successful/unsuccessful action.
 * @returns the alert message correspondent to the action performed.
 */

function AlertMessages({severity}) {

    const messages = {
        success: 'Great success!',
        error: 'Oops! Something went wrong. Please try again later.',
        info_project: 'The project that you searched for does not exist.',
        info_userStories: 'The project that you searched does not have user stories.',
    };

    return (
        <Stack spacing={2} sx={{width: '100%'}}>
            <Alert variant="filled" severity={severity}>
                {messages[severity]}
            </Alert>
        </Stack>
    );
}

export default AlertMessages;

