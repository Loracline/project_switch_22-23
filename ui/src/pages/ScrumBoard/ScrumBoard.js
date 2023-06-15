import React, {useContext, useEffect, useState} from 'react';
import AppContext from '../../context/AppContext';
import Button from "../../components/Button/Button";
import { selectMenu } from "../../context/Actions";
import './ScrumBoard.css';

function ScrumBoard() {
  const { state, dispatch } = useContext(AppContext);

  const [columns, setColumns] = useState([
    {
      title: 'Planned',
      cards: [
        { id: 'US 1', text: 'Planned' },
        { id: 'US 2', text: 'Planned' },
        { id: 'US 3', text: 'Planned' },
      ],
    },
    {
      title: 'In Progress',
      cards: [
        { id: 'US 4', text: 'In Progress' },
        { id: 'US 5', text: 'In Progress' },
      ],
    },
    {
      title: 'Blocked',
      cards: [
        { id: 'US 6', text: 'Blocked' },
      ],
    },
    {
      title: 'Done',
      cards: [
        { id: 'US 7', text: 'Done' },
        { id: 'US 8', text: 'Done' },
        { id: 'US 9', text: 'Done' },
      ],
    },
  ]);

  const handleDragStart = (e, cardId, columnIndex) => {
    e.dataTransfer.setData('text/plain', JSON.stringify({ cardId, sourceColumnIndex: columnIndex }));
  };

  const handleDragOver = (e) => {
    e.preventDefault();
  };

  const handleDrop = (e, columnIndex) => {
    e.stopPropagation();

    const { cardId, sourceColumnIndex } = JSON.parse(e.dataTransfer.getData('text/plain'));

    if (columnIndex !== sourceColumnIndex && sourceColumnIndex >= 0 && sourceColumnIndex < columns.length) {
      const updatedColumns = [...columns];

      const draggedCardIndex = updatedColumns[sourceColumnIndex].cards.findIndex(card => card.id === cardId);

      if (draggedCardIndex !== -1) {
        const draggedCard = updatedColumns[sourceColumnIndex].cards[draggedCardIndex];
        updatedColumns[sourceColumnIndex].cards.splice(draggedCardIndex, 1);
        draggedCard.status = updatedColumns[columnIndex].title;
        updatedColumns[columnIndex].cards.push(draggedCard);

        setColumns(updatedColumns);
      }
    }
  };

  const handleDragEnd = () => {
  };

  return (
      <div className="scrum-board">
        <h2>Scrum Board</h2>
        <div className="board">
          {columns.map((column, columnIndex) => (
              <div
                  key={column.title}
                  className="column"
                  onDragOver={handleDragOver}
                  onDrop={(e) => handleDrop(e, columnIndex)}
              >
                <h3>{column.title}</h3>
                <ul className="card-list">
                  {column.cards.map((card, cardIndex) => (
                      <li
                          key={card.id}
                          className="card"
                          draggable
                          onDragStart={(e) => handleDragStart(e, card.id, columnIndex)}
                          onDragEnd={handleDragEnd}
                      >
                        <div>{card.id}</div>
                        <div>{card.text}</div>
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
  );
}

export default ScrumBoard;