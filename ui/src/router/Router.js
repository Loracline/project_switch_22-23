import About from "../pages/About/About";
import CreateProject from "../pages/CreateProject/CreateProject";
import ListProjects from "../pages/ListProjects/ListProjects";
import ConsultProductBacklog from "../pages/ConsultProductBacklog/ConsultProductBacklog";
import CreateSprint from "../pages/CreateSprint/CreateSprint";
import Project from "../pages/Project/Project";
import CreateUserStory from "../pages/CreateUserStory/CreateUserStory";
import Home from "../pages/Home/Home";

const Router = ({ selected }) => {
    return (
        <div>
            {selected === 'createProject' && <CreateProject />}
            {selected === 'projects' && <ListProjects />}
            {selected === 'productBacklog' && <ConsultProductBacklog />}
            {selected === 'createSprint' && <CreateSprint />}
            {selected === 'about' && <About />}
            {selected === 'createUserStory' && <CreateUserStory />}
            {selected === 'project' && <Project/>}
            {selected === 'home' && <Home/>}
        </div>
    )
}
export default Router;