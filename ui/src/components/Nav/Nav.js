import React from "react";
import NavItem from "../NavItem/NavItem";
import './Nav.css';
import {Link} from "react-router-dom";

/**
 * A component that renders a navigation menu with clickable items.
 *
 * @param {Object} props - The component props.
 * @param {Array} props.items - An array of objects representing the menu items to display.
 * @param {Function} props.dispatch - A function to dispatch actions for state management.
 * @returns {JSX.Element} A React component that renders the navigation menu.
 */

const Nav = ({items}) => {
    return (
        <div>
            <nav>
                <ul className="navBar">
                    <Link to="/home">
                        <img src='/logoNav.svg' alt="G4 logo"/>
                    </Link>
                    {
                        items.map(item => (
                            <Link to={`/${item.key}`} key={item.key}>
                                <NavItem item={item} />
                            </Link>
                        ))
                    }
                </ul>
            </nav>
        </div>
    )
}
export default Nav;