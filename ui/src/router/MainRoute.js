import React, {useContext} from 'react';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Home from '../pages/Home/Home';
import About from '../pages/About/About';
import ListProjects from "../pages/ListProjects/ListProjects";
import Project from "../pages/Project/Project";
import ListSprints from "../pages/ListSprints/ListSprints";
import Sprint from "../pages/Sprint/Sprint";
import CreateProject from "../pages/CreateProject/CreateProject";
import ConsultProductBacklog from "../pages/ConsultProductBacklog/ConsultProductBacklog";
import CreateUserStory from "../pages/CreateUserStory/CreateUserStory";
import AllocateResource from "../pages/AllocateResource/AllocateResource";
import ScrumBoard from "../pages/ScrumBoard/ScrumBoard";
import CreateSprint from "../pages/CreateSprint/CreateSprint";
import Nav from "../components/Nav/Nav";
import Footer from "../components/Footer/Footer";
import AppContext from "../context/AppContext";
import ProjectsRoute from "../pages/ProjectsRoute/ProjectsRoute";
import SprintsRoute from "../pages/SprintsRoute/SprintsRoute";

function MainRoute() {
    const {state} = useContext(AppContext);
    const {nav} = state;
    const {menu} = nav;
    return (
        <Router>
            <div>
                <div className="App">
                    <Nav items={menu.filter((item) => !item.hidden)}/>
                </div>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="home" element={<Home />} />
                    <Route path="about" element={<About />} />
                    <Route path="projects" element={<ProjectsRoute />}>
                        <Route path="" element={<ListProjects />} />
                        <Route path="create-project" element={<CreateProject />} />
                        <Route path=":projectCode" element={<Project />} />
                        <Route path=":projectCode/create-us" element={<CreateUserStory />} />
                        <Route path=":projectCode/product-backlog" element={<ConsultProductBacklog />} />
                        <Route path=":projectCode/allocate-resource" element={<AllocateResource />} />
                        <Route path=":projectCode/scrum-board" element={<ScrumBoard />} />
                    </Route>
                    <Route path="sprints/:projectCode" element={<SprintsRoute />}>
                        <Route path="" element={<ListSprints />} />
                        <Route path="create-sprint" element={<CreateSprint />} />
                        <Route path=":sprintNumber" element={<Sprint />} />
                    </Route>
                </Routes>
                <div>
                    <Footer/>
                </div>
            </div>
        </Router>

    );
}

export default MainRoute;