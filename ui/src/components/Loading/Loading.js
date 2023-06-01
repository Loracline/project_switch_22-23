import {CircularProgress, Dialog} from "@mui/material";
import React from "react";

const Loading =  ({handleLoading}) => {
    return (
        <Dialog open={handleLoading}>
            <CircularProgress style={{color: "#6145AF"}} sx={{m: 5}}/>
        </Dialog>
    );
};

export default Loading
