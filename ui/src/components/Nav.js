import React from "react";
import NavItem from "./NavItem";
import {selectMenu} from "../context/Actions";

/**
 * A component that renders a navigation menu with clickable items.
 *
 * @param {Object} props - The component props.
 * @param {Array} props.items - An array of objects representing the menu items to display.
 * @param {Function} props.dispatch - A function to dispatch actions for state management.
 * @returns {JSX.Element} A React component that renders the navigation menu.
 */

const Nav = ({items, dispatch}) => {
    const onClick = (label) => {
        const action = selectMenu(label);
        dispatch(action);
    }
    return (
        <div>
            <ul>
                {
                    items.map(item => (
                        <NavItem
                            key={item.key}
                            item={item}
                            onClick={onClick}
                        />
                    ))
                }
            </ul>
            <hr></hr>
        </div>
    )
}
export default Nav;