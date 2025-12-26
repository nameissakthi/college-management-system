const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

export async function addStudent(student) {
    try {
        const response = await fetch(BACKEND_URL+"/student/add", {
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(student)
        });

        const data = await response.json();
        return data;
    } catch(error) {
        console.error("Error adding student:", error);
        throw error;
    }
}

export async function addTeacher(teacher) {
        try {
        const response = await fetch(BACKEND_URL+"/teacher/add", {
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify(teacher)
        });

        const data = await response.json();
        return data;
    } catch(error) {
        console.error("Error adding teacher:", error);
        throw error;
    }
}