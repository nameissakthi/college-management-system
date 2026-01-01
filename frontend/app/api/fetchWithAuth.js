const { toast } = require("react-toastify");

export async function fetchData(path, method, body={}) {

    const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

    try {

        if(BACKEND_URL === "") throw new Error('The backend url is empty');

        const token = localStorage.getItem('token');
        
        if(token === "") throw new Error('Token was empty');

        const response = await fetch(BACKEND_URL+path, {
            method : method,
            headers : {
                'Content-Type' : 'application/json',
                'Authorization' : `Bearer ${token}`
            },
            body : JSON.stringify(body)
        })

        if(response.status!==200) throw new Error('There is an error while fetching the data');

        return response.json();

    } catch(error) {
        toast.error("Oops!! There is an exception : ", error.message);
        console.log(error);
    }
}