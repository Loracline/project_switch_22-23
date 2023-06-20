import React, {useContext, useEffect, useState} from 'react';
import TableHeader from "../../components/TableHeader/TableHeader";
import TableBody from "../../components/TableBody/TableBody";
import AppContext from "../../context/AppContext";
import Alert from "@mui/material/Alert";
import {setCurrentProject} from "../../context/Actions";
import Button from "../../components/Button/Button";
import './ListProjects.css';
import Loading from "../../components/Loading/Loading";
import {Link, Outlet, useNavigate} from "react-router-dom";

/**
 * A functional component that displays a list of all projects.
 * @returns {JSX.Element} A div element containing a h1 element and a table.
 */

const ListProjects = () => {
    const {state, dispatch} = useContext(AppContext);
    const headers = state.headersProjects;
    const loading = state.loading;
    const data = state.projects;
    const navigate = useNavigate();

    const [showScrollButton, setShowScrollButton] = useState(false);

    useEffect(() => {
        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    const handleScroll = () => {
        if (window.pageYOffset > 20) {
            setShowScrollButton(true);
        } else {
            setShowScrollButton(false);
        }
    };
    const scrollToTop = () => {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    };
    const scrollButtonStyle = {
        display: showScrollButton ? 'block' : 'none',
    };


    const tableData = () => {
        let tableData;
        if (data.length > 0) {
            const onClickTableBody = (index) => {
                if (state.projects.length > index) {
                    const selectedProject = state.projects[index];
                    dispatch(setCurrentProject(selectedProject));
                    navigate(`/projects/${selectedProject.code}`)
                }
            };
            tableData = (
                <table className='table'>
                    <TableHeader headers={headers}/>
                    <TableBody body={data} onClick={onClickTableBody} path={"/projects/"}/>
                </table>)
        } else {
            tableData = <Alert variant="filled" severity="info">
                There are no projects!
            </Alert>;
        }
        return tableData;
    };

    if (loading === true) {
        return (
            <div>
                <p style={{height: "calc(100vh - 172px - 2rem)"}}></p>
                <Loading handleLoading={loading}/>
            </div>)
    } else {
        return (
            <div>
                <div className='page pageTable'>
                    <h2 className="pageH2">Projects</h2>
                    {tableData()}
                    <Link to={"/projects/create-project"}>
                        <Button text='Create project'/>
                    </Link>
                    <button className="scroll-to-top-button" style={scrollButtonStyle} onClick={scrollToTop}>
                        Scroll to Top
                    </button>
                </div>
                <Outlet />
            </div>
        );
    }
}

export default ListProjects;