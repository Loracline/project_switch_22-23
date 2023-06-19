import {useEffect, useState} from "react";
import {getScrumBoard} from "../../services/ScrumBoardService";

const status = ['planned', 'running', 'blocked', 'finished'];

export function useGetScrumBoard(projectCode) {
    const [scrumBoard, setScrumBoard] = useState([]);
    useEffect(() => {
        if(projectCode){
            getScrumBoard(projectCode).then(
                (scrumBoardFromBackend) => {
                    const columns = status.map((status) => (
                            {
                                status,
                                items: scrumBoardFromBackend.filter((item) => item.status === status)
                            }
                        )
                    )
                    setScrumBoard(columns)
                }
            )
        }

    }, [projectCode])

    return [scrumBoard, setScrumBoard];

}