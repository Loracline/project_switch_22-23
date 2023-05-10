import './App.css';
import Nav from "./components/Nav/Nav";
import Router from "./router/Router";
import Footer from "./components/Footer/Footer";
import {useContext} from "react";
import AppContext from "./context/AppContext";

function App() {
    const {state, dispatch} = useContext(AppContext);
    const { nav } = state;
    const {selectedMenu, menu} = nav;
    return (
        <div className="App">
            <Nav items={menu.filter((item) => !item.hidden)} dispatch={dispatch}/>
            <Router selected={selectedMenu.key}/>
            <Footer/>
        </div>
    );
}

export default App;
