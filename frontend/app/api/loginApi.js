import { BACKEND_URL } from "../context/CmsContext";

export async function login(credentials) {
    try {
        const response = await fetch(BACKEND_URL+"/login", {
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(credentials)
        });

        if(response.status != 200) {
            return { data : null, success : false, message : "username or password doen't match!!!" }
        }

        const data = response.json()
        return data;
    } catch (error) {
        console.log(error);
        throw error;
    }
}