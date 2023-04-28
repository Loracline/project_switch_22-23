import './App.css';
import Nav from "./components/Nav";
import Route from "./route/Route";
import Footer from "./components/Footer/Footer";
import {useContext} from "react";
import AppContext from "./context/AppContext";

function App() {
    const {state, dispatch} = useContext(AppContext);
    const { nav } = state;
    const {selectedMenu, menu} = nav;
    return (
        <div className="App">
            <Nav items={menu} dispatch={dispatch}/>
            <Route selected={selectedMenu.key}/>
            <Footer/>
        </div>
    );
}

export default App;
