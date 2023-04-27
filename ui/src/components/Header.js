import * as React from 'react';

/**
 * Header component is responsible for rendering the top section of a webpage or application.
 * It typically contains a logo or title, navigation links, and possibly other user interface
 * elements such as buttons or search bars.
 * Can be a useful way to keep the top section of the application consistent across multiple pages or views.
 * By creating a separate component for the header, it's possible to make updates to the design or functionality
 * of the header without having to modify each individual page or view where it appears.
 * @return the Header.
 */

function Header() {
    return (
        <header>
            <h1> SWitCH G4 </h1>
            <nav>
                <ul>
                    <li><a href="#">Home</a></li>
                    <li><a href="#">US023: Create Project</a></li>
                    <li><a href="#">US024: List Projects</a></li>
                    <li><a href="#">US025: Create User Story</a></li>
                    <li><a href="#">US026: Consult Product Backlog</a></li>
                    <li><a href="#">US027: Create Sprint</a></li>
                    <li><a href="#">About</a></li>
                </ul>
            </nav>
        </header>
    );
}

export default Header;