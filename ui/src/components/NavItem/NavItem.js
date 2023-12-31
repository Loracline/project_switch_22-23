import React from "react";
import './NavItem.css';

/**
 * Represents a navigation item in a menu.
 * @param item the navigation item.
 * @param onClick the function to call when the navigation item is clicked.
 * @returns {JSX.Element} a list item containing a button element with the navigation label.
 */
let NavItem;
NavItem = ({item}) => {
    const {label} = item;

    return (
        <li>
            <button className='navItem'>
                {label}
            </button>
        </li>
    )
};

export default NavItem;