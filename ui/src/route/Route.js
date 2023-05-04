import About from "../pages/About";
import ListProjects from "../pages/ListProjects/ListProjects";
import ConsultProductBacklog from "../pages/ConsultProductBacklog";
import CreateSprint from "../pages/CreateSprint";
import Project from "../pages/Project";
import CreateUserStory from "../pages/CreateUserStory/CreateUserStory";

const Route = ({ selected }) => {
    return (
        <div>
            {selected === 'projects' && <ListProjects />}
            {selected === 'productBacklog' && <ConsultProductBacklog />}
            {selected === 'createSprint' && <CreateSprint />}
            {selected === 'about' && <About />}
            {selected === 'createUserStory' && <CreateUserStory />}
            {selected === 'project' && <Project/>}
        </div>
    )
}
export default Route;