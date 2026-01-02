const { toast } = require("react-toastify");

export async function fetchWithAuth(path, method, body = null) {

    const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

    try {

        if(!BACKEND_URL || BACKEND_URL === "") throw new Error('The backend url is empty');

        const token = localStorage.getItem('token');
        
        if(!token || token === "") throw new Error('Token was empty');
        
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

        // if(response.status === 401) {
        //     localStorage.removeItem('token');
        //     localStorage.removeItem('login');
        //     localStorage.removeItem('user-type');
        //     localStorage.removeItem('user');
        //     localStorage.removeItem('user-email');
        //     toast.error("Session expired. Please login again.");
        //     window.location.href = '/login';
        //     return null;
        // }

        if(response.status !== 200) {
            throw new Error(`Server responded with status ${response.status}`);
        }

        return response.json();

    } catch(error) {
        toast.error("Oops!! There is an exception : " + error.message);
        console.log(error);
        return null;
    }
}