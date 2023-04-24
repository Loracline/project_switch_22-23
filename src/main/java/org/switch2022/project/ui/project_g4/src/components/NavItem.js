import React from "react";

/**
 * Represents a navigation item in a menu.
 * @param item the navigation item.
 * @param onClick the function to call when the navigation item is clicked.
 * @returns {JSX.Element} a list item containing a button element with the navigation label.
 */
let NavItem;
NavItem = ({item, onClick}) => {
    const {key, label} = item;

    return (
        <li>
            <button onClick={() => onClick(key)}>
                {label}
            </button>
        </li>
    )
};

export default NavItem;