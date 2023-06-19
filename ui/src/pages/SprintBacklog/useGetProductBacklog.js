import {useEffect, useState} from "react";
import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";

export function useGetProductBacklog(code) {
    const [productBacklog, setProductBacklog] = useState([]);

    useEffect(() => {
        if(code){
            fetch(`${API_URL}${API_ROUTES.PROJECTS}/${code}${API_ROUTES.PRODUCTBACKLOG}`, {
                method: 'GET',
                headers,
            })
                .then(response => response.json())
                .then(response => {setProductBacklog(response)})
        }

    }, [code])

    return [productBacklog, setProductBacklog];
}