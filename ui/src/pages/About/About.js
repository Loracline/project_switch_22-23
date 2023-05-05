import React from 'react';
import './About.css';

/**
 * A functional component that displays information about the group.
 * @returns {JSX.Element} A div element containing an h1 element, a p element, and an img element.
 */

function About() {
    return (
        <div className='page aboutPage'>
            <div>
                <h2>About</h2>
                <p>Welcome to our group! We are a team of students who are passionate about building great software.
                    Our goal is to create innovative solutions that solve problems. This software was developed by the
                    Group 4 during the 6th edition of the Switch specialization, during 2022 and 2023.</p>
                <h3>Proudly brought to you by:</h3>
                <ul>
                    <li>Ana Sofia Oliveira</li>
                    <li>Bárbara Oliveira</li>
                    <li>Caroline Silva</li>
                    <li>Cristiana Moreira</li>
                    <li>Jussara Germano</li>
                    <li>João Magalhães</li>
                    <li>João Serra</li>
                    <li>Margarida Ferreira</li>
                    <li>Mariana Zancheta</li>
                    <li>Rui Pinho</li>
                </ul>
            </div>
            <img src="FotoDeGrupo.jpg" alt="Group photo"/>
        </div>
    );
}

export default About;
