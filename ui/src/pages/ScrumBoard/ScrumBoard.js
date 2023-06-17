import React, {useContext} from 'react';
import AppContext from '../../context/AppContext';
import './ScrumBoard.css';
import {useGetScrumBoard} from "./useGetScrumBoard";
import Button from "../../components/Button/Button";
import {selectMenu} from "../../context/Actions";

function ScrumBoard() {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject} = state;
    const projectCode = detailedProject.code;
    const [scrumBoard, setScrumBoard] = useGetScrumBoard(projectCode);


    const handleDragStart = (event, cardId, sourceColumnIndex) => {
        event.dataTransfer.setData('text/plain', JSON.stringify({cardId, sourceColumnIndex}));
    };

    const handleDragOver = (event) => {
        event.preventDefault();
    };

    const handleDrop = (event, targetColumnIndex) => {

        event.stopPropagation()

        const {cardId, sourceColumnIndex} = JSON.parse(event.dataTransfer.getData('text/plain'));


        if (targetColumnIndex !== sourceColumnIndex && sourceColumnIndex >= 0 && sourceColumnIndex < scrumBoard.length) {

            const updatedColumns = [...scrumBoard];

            const draggedCardIndex = updatedColumns[sourceColumnIndex].items.findIndex(card => card.userStoryNumber === cardId);


            if (draggedCardIndex !== -1) {

                const draggedCard = updatedColumns[sourceColumnIndex].items[draggedCardIndex];

                updatedColumns[sourceColumnIndex].items.splice(draggedCardIndex, 1);

                draggedCard.status = updatedColumns[targetColumnIndex].status;

                updatedColumns[targetColumnIndex].items.push(draggedCard);

                console.log(draggedCard)

                setScrumBoard(updatedColumns);
            }
        }

    };


    const handleDragEnd = () => {

    };

    return (
    <div className="page">
        <div className="scrum-board">
            <h2>Scrum Board</h2>
             <Button retun-button={true}
              onClick={() => dispatch(selectMenu('project'))}
              text="Return"
              variant="outlined" />
            <div className="board">
                {scrumBoard.map((column, columnIndex) => (
                    <div
                        key={column.status}
                        className="column"
                        onDragOver={handleDragOver}
                        onDrop={(e) => handleDrop(e, columnIndex)}
                    >
                        <h3>{column.status}</h3>
                        <ul className="card-list">
                            {column.items.map((item) => (
                                <li
                                    key={item.userStoryNumber}
                                    className="card"
                                    draggable
                                    onDragStart={(e) => handleDragStart(e, item.userStoryNumber, columnIndex)}
                                    onDragEnd={handleDragEnd}
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
                <Button
                    isSecundary={true}
                    onClick={() => dispatch(selectMenu('project'))}
                    text="Return"
                />
            </div>
        </div>
     </div>
    );
}

export default ScrumBoard;