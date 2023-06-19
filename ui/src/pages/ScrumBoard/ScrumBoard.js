import React, {useContext} from 'react';
import AppContext from '../../context/AppContext';
import './ScrumBoard.css';
import {useGetScrumBoard} from "./useGetScrumBoard";
import Button from "../../components/Button/Button";
import {updateUserStoryStatus} from "../../services/ScrumBoardService";
import {Link} from "react-router-dom";

function ScrumBoard() {
    const {state} = useContext(AppContext);
    const {detailedProject} = state;
    const projectCode = detailedProject?.code;
    const [scrumBoard, setScrumBoard] = useGetScrumBoard(projectCode);


    const handleDragStart = (event, cardId, sourceColumnIndex) => {
        event.dataTransfer.setData('text/plain', JSON.stringify({cardId, sourceColumnIndex})
        );
    };

    const handleDragOver = (event) => {
        event.preventDefault();
    };

    const handleDrop = (event, targetColumnIndex) => {
        event.stopPropagation()

        const {cardId, sourceColumnIndex} = JSON.parse(event.dataTransfer.getData('text/plain')
        );

        const isValidColumn = targetColumnIndex !== sourceColumnIndex && sourceColumnIndex >= 0 && sourceColumnIndex < scrumBoard.length;
        if (isValidColumn) {
            const updatedColumns = [...scrumBoard];
            const draggedCardIndex = updatedColumns[sourceColumnIndex].items.findIndex(card => card.userStoryNumber === cardId);
            const isDraggedCardIndexFound = draggedCardIndex !== -1;
            if (isDraggedCardIndexFound) {
                const draggedCard = updatedColumns[sourceColumnIndex].items[draggedCardIndex];
                updatedColumns[sourceColumnIndex].items.splice(draggedCardIndex, 1);
                updatedColumns[targetColumnIndex].items.push(draggedCard);
                draggedCard.status = updatedColumns[targetColumnIndex].status;
                setScrumBoard(updatedColumns);
                const usId = `${projectCode}_${draggedCard.userStoryNumber}`
                const requestBody = {
                    usId,
                    projectCode,
                    status: draggedCard.status
                }
                updateUserStoryStatus(requestBody).then();
            }
        }

    }

    return (
        <div className="page">
            <div className="scrum-board">
                <h2>Scrum Board</h2>
                <Link to={"/projects/" + detailedProject?.code}>
                    <Button return-button={true}
                            text="Return"
                            variant="outlined"/>
                </Link>
                <div className="board">
                    {scrumBoard.map((column, columnIndex) => (
                        <div
                            key={column.status}
                            className="column"
                            onDragOver={handleDragOver}
                            onDrop={(event) => handleDrop(event, columnIndex)}
                        >
                            <h3>{column.status}</h3>
                            <ul className="card-list">
                                {column.items.map((item) => (
                                    <li
                                        key={item.userStoryNumber}
                                        className="card"
                                        draggable
                                        onDragStart=
                                            {(event) => handleDragStart(event, item.userStoryNumber, columnIndex)}
                                    >
                                        <div>
                                            <div>{item.userStoryNumber}</div>
                                            <div>{item.userStoryText}</div>
                                        </div>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ))}
                </div>
                <div className="buttons-backlog">
                    <Link to={"/projects/" + detailedProject?.code}>
                        <Button
                            isSecundary={true}
                            text="Return"
                        />
                    </Link>
                </div>
            </div>
        </div>
    );
}

export default ScrumBoard;