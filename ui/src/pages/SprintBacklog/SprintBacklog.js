import {Autocomplete, TextField} from "@mui/material";
import React, {useContext, useState} from "react";
import AppContext from "../../context/AppContext";
import CheckBoxOutlineBlankIcon from '@mui/icons-material/CheckBoxOutlineBlank';
import CheckBoxIcon from '@mui/icons-material/CheckBox';
import Checkbox from '@mui/material/Checkbox';
import Button from "../../components/Button/Button";

function SprintBacklog() {
    const {state, dispatch} = useContext(AppContext);
    const {detailedProject} = state;


    let newSprintBacklog = [];
    const [sprintBacklog, setSprintBacklog] = useState([]);
    const icon = <CheckBoxOutlineBlankIcon fontSize="small"/>;
    const checkedIcon = <CheckBoxIcon fontSize="small"/>

    /*
    export function useGetProductBacklog() {
        const [productBacklog, setProductBacklog] = useState([]);

        useEffect(() => {
            fetch(`http://localhost:8080/${API_ROUTES.USERSTORIES}`, {method: 'GET',})
                .then(response => response.json())
                .then(response => {setUserStories(response);})
        }, [])
        return [userStories];
    }

    const [productBacklog] = useGetProductBacklog();
    */
    const productBaclog = [
        {
            userStoryNumber: 'US1',
            userStoryText: 'Como utilizador, quero poder efetuar login no sistema',
            status: 'Em progresso'
        },
        {
            userStoryNumber: 'US2',
            userStoryText: 'Como utilizador, quero poder registar novos produtos',
            status: 'Concluída'
        },
        {
            userStoryNumber: 'US3',
            userStoryText: 'Como utilizador, quero poder adicionar itens ao carrinho de compras',
            status: 'A fazer'
        },
        {
            userStoryNumber: 'US4',
            userStoryText: 'Como utilizador, quero poder visualizar o histórico de pedidos',
            status: 'A fazer'
        }
    ];


    const handleSprintBacklogChange = (event, value) => {
            newSprintBacklog=value;
    }

    const handleConfirmation = (event) => {
        console.log(newSprintBacklog);
        setSprintBacklog(newSprintBacklog);
    }

    return (
        <>
            <table style={{ background: '#f2f2f2' }}>
                <caption style={{ fontWeight: 'bold', marginBottom: '1rem' }}>
                    SPRINT BACKLOG
                </caption>
                <thead>
                <tr>
                    <th>US NUMBER</th>
                    <th>US DESCRIPTION</th>
                </tr>
                </thead>
                <tbody>
                {sprintBacklog.length === 0 ? (
                    <tr>
                        <td colSpan="2">No UserStories added</td>
                    </tr>
                ) : (
                    sprintBacklog.map((item) => (
                        <tr key={item.userStoryNumber}>
                            <td>{item.userStoryNumber}</td>
                            <td>{item.userStoryText}</td>
                        </tr>
                    ))
                )}
                </tbody>
            </table>
            <br/>
            <Autocomplete
                multiple
                disableCloseOnSelect
                sx={{width: 600}}
                options={productBaclog}
                getOptionLabel={(option) => option.userStoryNumber}
                onChange={handleSprintBacklogChange}
                renderOption={(props, option, {selected}) => (
                    <li {...props}>
                        <Checkbox
                            icon={icon}
                            checkedIcon={checkedIcon}
                            style={{marginRight: 8}}
                            checked={selected}
                        />
                        <span><b>{option.userStoryNumber}</b>  - {option.userStoryText}</span>
                    </li>
                )}
                style={{width: 500}}
                renderInput={(params) => (
                    <TextField{...params}
                              label="Sprint Backlog"
                              required
                    />
                )}
                filterOptions={(options, state) => options.filter((option) => option.userStoryNumber.toLowerCase().includes(state.inputValue.toLowerCase()) || option.userStoryText.toLowerCase().includes(state.inputValue.toLowerCase()))}
            />
            <Button
                text="Update"
                type="button"
                onClick={handleConfirmation}
            />

            <br/>


        </>
    );
}


export default SprintBacklog;