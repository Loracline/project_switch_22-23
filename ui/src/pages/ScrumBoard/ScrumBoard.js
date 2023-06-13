import React from 'react';
import './ScrumBoard.css';

const ScrumBoard = () => {
    const columns = [
        { title: 'Planned', cards: [{ id: 'US 1', status: 'In Progress' }, { id: 'US 2', status: 'In Progress' }, { id: 'US 3', status: 'To Do' }] },
        { title: 'Running', cards: [{ id: 'US 4', status: 'In Progress' }, { id: 'US 5', status: 'To Do' }] },
        { title: 'Blocked', cards: [{ id: 'US 6', status: 'Blocked' }] },
        { title: 'Finished', cards: [{ id: 'US 7', status: 'Done' }, { id: 'US 8', status: 'Done' }, { id: 'US 9', status: 'Done' }] },
    ];

    return (
        <div className="scrum-board">
            <h2>Scrum Board</h2>
            {columns.map((column, index) => (
                <div key={index} className="column">
                    <h3>{column.title}</h3>
                    <div className="cards">
                        {column.cards.map((card, cardIndex) => (
                            <div key={cardIndex} className="card">
                                <div>{card.id}</div>
                                <div>{card.status}</div>
                            </div>
                        ))}
                    </div>
                </div>
            ))}
        </div>
    );
};

export default ScrumBoard;