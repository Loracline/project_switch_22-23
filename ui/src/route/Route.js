import About from "../pages/About/About";
import ListProjects from "../pages/ListProjects/ListProjects";
import ConsultProductBacklog from "../pages/ConsultProductBacklog";
import CreateSprint from "../pages/CreateSprint/CreateSprint";
import Project from "../pages/Project/Project";
import CreateUserStory from "../pages/CreateUserStory/CreateUserStory";
import Home from "../pages/Home/Home";

const Route = ({ selected }) => {
    return (
        <div>
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
export default Route;