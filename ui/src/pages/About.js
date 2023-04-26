import React from 'react';

/**
 * A functional component that displays information about the group.
 * @returns {JSX.Element} A div element containing an h1 element, a p element, and an img element.
 */

function About() {
    return (
        <div>
            <h1>About</h1>
            <p>Welcome to our group! We are a team of students who are passionate about building great software.
                Our goal is to create innovative solutions that solve problems.</p>
            <img src="FotoDeGrupo.jpg" alt="Group photo" />
        </div>
    );
}

export default About;
