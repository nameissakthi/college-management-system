const { toast } = require("react-toastify");

export async function fetchWithAuth(path, method, body = null) {

    const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

    try {

        if(BACKEND_URL === "") throw new Error('The backend url is empty');

        const token = localStorage.getItem('token');
        
        if(token === "") throw new Error('Token was empty');
        
        const headers = {
            'Authorization' : `Bearer ${token}`
        };

        const options = {
            method,
            headers
        };

        if (body && method !== 'GET') {
            headers['Content-Type'] = 'application/json';
            options.body = JSON.stringify(body);
        }

        const response = await fetch(BACKEND_URL+path, options);

        if(response.status!==200) throw new Error('There is an error while fetching the data');

        return response.json();

    } catch(error) {
        toast.error("Oops!! There is an exception : " + error.message);
        console.log(error);
    }
}