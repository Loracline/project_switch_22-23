import About from "../pages/About/About";
import CreateProject from "../pages/CreateProject/CreateProject";
import ListProjects from "../pages/ListProjects/ListProjects";
import ConsultProductBacklog from "../pages/ConsultProductBacklog/ConsultProductBacklog";
import CreateSprint from "../pages/CreateSprint/CreateSprint";
import Project from "../pages/Project/Project";
import CreateUserStory from "../pages/CreateUserStory/CreateUserStory";
import Home from "../pages/Home/Home";
import AllocateResource from "../pages/AllocateResource/AllocateResource";
import Sprint from "../pages/Sprint/Sprint";
import ListSprints from "../pages/ListSprints/ListSprints";
import ScrumBoard from "../pages/ScrumBoard/ScrumBoard";

const Router = ({selected}) => {
    return (
        <div>
            {selected === 'createProject' && <CreateProject/>}
            {selected === 'projects' && <ListProjects/>}
            {selected === 'sprints' && <ListSprints/>}
            {selected === 'productBacklog' && <ConsultProductBacklog/>}
            {selected === 'createSprint' && <CreateSprint/>}
            {selected === 'about' && <About/>}
            {selected === 'createUserStory' && <CreateUserStory/>}
            {selected === 'project' && <Project/>}
            {selected === 'home' && <Home/>}
            {selected === 'allocateResource' && <AllocateResource/>}
            {selected === 'sprint' && <Sprint/>}
            {selected === 'scrumBoard' && <ScrumBoard/>}
        </div>
    )
}
export default Router;