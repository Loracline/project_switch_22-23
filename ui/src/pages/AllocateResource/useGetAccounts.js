import {useEffect, useState} from "react";
import {API_HEADERS as headers, API_ROUTES, API_URL} from "../../services/api";

export function useGetAccounts(){
    const [userAccounts, setUserAccounts] = useState([]);

    useEffect(() => {
        fetch(`${API_URL}/${API_ROUTES.ACCOUNTS}`, {
            method: 'GET',
            headers,
        })
            .then(res => res.json())
            .then(res => {
                setUserAccounts(res);
            })

    },[])

    return [userAccounts];

}