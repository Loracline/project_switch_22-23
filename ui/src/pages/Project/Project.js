import React, {useContext} from "react";
import AppContext from "../../context/AppContext";
import Button from "../../components/Button/Button";
import './Project.css';
import {Link} from "react-router-dom";

const Project = () => {
    const {state} = useContext(AppContext);
    const data = state.detailedProject;

    const isProjectEditable = data?.status === "inception";

    return (
        <div className='page'>
            <section className='projectCard'>
                <div className='projectInfo'>
                    <div className='projectContent'>
                        <h2>{data?.['projectName']}</h2>
                        <p>Code: {data?.['code']}</p>
                        <p>Customer: {data?.['customerName']}</p>
                        <p>Status: {data?.['status']}</p>
                        <p>Start date: {data?.['startDate']}</p>
                        <p>End date: {data?.['endDate']}</p>
                    </div>
                    <div className='buttons-project'>
                        <Link to={"/projects/" + data?.code + "/create-us"}>
                            <Button text="Create user story" isDisabled={!isProjectEditable}/>
                        </Link>
                        <Link to={"/sprints/" + data?.code}>
                            <Button pageClass="projectButton" text="Sprints"/>
                        </Link>
                        <Link to={"/projects/" + data?.code + "/product-backlog"}>
                            <Button pageClass="projectButton" text="Product backlog"/>
                        </Link>
                        <Link to={"/projects/" + data?.code + "/allocate-resource"}>
                            <Button pageClass="projectButton" text="Add Resource"
                                    isDisabled={!isProjectEditable}/>
                        </Link>
                        <Link to={"/projects/" + data?.code + "/scrum-board"}>
                            <Button pageClass="projectButton" text="Scrum Board"/>
                        </Link>


                    </div>
                </div>
                <Link to={"/projects"}>
                    <Button isSecundary={true}  text="Return"/>
                </Link>
            </section>
        </div>
    )
}

export default Project;