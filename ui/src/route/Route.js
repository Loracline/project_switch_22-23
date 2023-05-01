import About from "../pages/About";
import ListProjects from "../pages/ListProjects/ListProjects";
import ConsultProductBacklog from "../pages/ConsultProductBacklog";
import CreateSprint from "../pages/CreateSprint";

const Route = ({ selected }) => {
    return (
        <div>
            {selected === 'projects' && <ListProjects />}
            {selected === 'productBacklog' && <ConsultProductBacklog />}
            {selected === 'createSprint' && <CreateSprint />}
            {selected === 'about' && <About />}
        </div>
    )
}
export default Route;