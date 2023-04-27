import About from "../pages/About";
import ListProjects from "../pages/ListProjects";
import ConsultProductBacklog from "../pages/ConsultProductBacklog";

const Route = ({ selected }) => {
    return (
        <div>
            {selected === 'projects' && <ListProjects />}
            {selected === 'productBacklog' && <ConsultProductBacklog />}
            {selected === 'about' && <About />}
        </div>
    )
}
export default Route;