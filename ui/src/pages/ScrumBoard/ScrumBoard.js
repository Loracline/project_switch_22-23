import React, { useContext, useState } from 'react';
import AppContext from '../../context/AppContext';
import Button from "../../components/Button/Button";
import { selectMenu } from "../../context/Actions";
import './ScrumBoard.css';
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd';

function ScrumBoard() {
  const { state, dispatch } = useContext(AppContext);
  //const { usHeaders, detailedSprint } = state;

  const [columns, setColumns] = useState([
    {
      title: 'Planned',
      cards: [
        { id: 'US 1', status: 'In Progress' },
        { id: 'US 2', status: 'In Progress' },
        { id: 'US 3', status: 'To Do' },
      ],
    },
    {
      title: 'Running',
      cards: [
        { id: 'US 4', status: 'In Progress' },
        { id: 'US 5', status: 'To Do' },
      ],
    },
    {
      title: 'Blocked',
      cards: [{ id: 'US 6', status: 'Blocked' }],
    },
    {
      title: 'Finished',
      cards: [
        { id: 'US 7', status: 'Done' },
        { id: 'US 8', status: 'Done' },
        { id: 'US 9', status: 'Done' },
      ],
    },
  ]);

  const onDragEnd = (result) => {
    const { source, destination } = result;

    if (!destination) {
      return;
    }

    const sourceColumn = columns.find(
      (col) => col.title === source.droppableId
    );
    const destinationColumn = columns.find(
      (col) => col.title === destination.droppableId
    );

    const sourceCards = Array.from(sourceColumn.cards);
    const destinationCards = Array.from(destinationColumn.cards);

    const [removedCard] = sourceCards.splice(source.index, 1);
    destinationCards.splice(destination.index, 0, removedCard);

    setColumns((prevState) =>
      prevState.map((col) =>
        col.title === source.droppableId
          ? { ...col, cards: sourceCards }
          : col
      )
    );
  };

  return (
    <div className="scrum-board">
      <h2>Scrum Board</h2>
      <DragDropContext onDragEnd={onDragEnd}>
        <table>
          <thead>
            <tr>
              <th>Planned</th>
              <th>Running</th>
              <th>Blocked</th>
              <th>Finished</th>
            </tr>
          </thead>
          <tbody>
            {columns.map((column, columnIndex) => (
              <Droppable droppableId={column.title} key={column.title}>
                {(provided, snapshot) => (
                  <td
                    ref={provided.innerRef}
                    {...provided.droppableProps}
                    className={`column ${snapshot.isDraggingOver ? 'dragging-over' : ''}`}
                  >
                    <ul className="card-list">
                      {column.cards.map((card, cardIndex) => (
                        <Draggable
                          draggableId={card.id}
                          index={cardIndex}
                          key={card.id}
                        >
                          {(provided) => (
                            <li
                              ref={provided.innerRef}
                              {...provided.draggableProps}
                              {...provided.dragHandleProps}
                              className="card"
                            >
                              <div>{card.id}</div>
                              <div>{card.status}</div>
                            </li>
                          )}
                        </Draggable>
                      ))}
                      {provided.placeholder}
                    </ul>
                  </td>
                )}
              </Droppable>
            ))}
          </tbody>
        </table>
      </DragDropContext>
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