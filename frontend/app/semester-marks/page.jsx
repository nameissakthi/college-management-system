'use client'

import { useState, useRef } from "react";
import { fetchWithAuth } from "../api/fetchWithAuth";
import { useContext } from "react";
import { CmsContext } from "../context/CmsContext";
import { toast } from "react-toastify";
import { useEffect } from "react";
import { FiAlertCircle } from "react-icons/fi";

const SemesterMarks = () => {

    const { user } = useContext(CmsContext);

    const [semesterMarks, setSemesterMarks] = useState([]);
    const [loading, setLoading] = useState(false);
    const hasFetched = useRef(false);

    const getParticularStudentSemesterMarks = async (id) => { 

        if(loading || hasFetched.current) return;
        
        hasFetched.current = true;
        setLoading(true);
        const path = `/semester/get/semester-marks?id=${id}`

        const response = await fetchWithAuth(
            path,
            'GET'
        );

        setLoading(false);

        if(!response || !response.success) {
            toast.error(response?.message || "Failed to fetch semester marks");
            return;
        }

        if(response && response.data) {
            setSemesterMarks(response.data);
        }
    }

    useEffect(() => {

        if(!user || !user.id) {
            return;
        }

        getParticularStudentSemesterMarks(user.id);
    }, [user?.id])

    return (
        <div className="mt-10">
            {
                semesterMarks.length <= 0 ?
                <div className="h-52 flex justify-center items-center">
                    <p className="flex flex-col justify-center-safe items-center-safe gap-2">
                        <span className="text-4xl"><FiAlertCircle /></span>
                        <span>The semester marks list where empty!!!</span>
                    </p>
                </div>
                :
                <table>
                    <thead>
                        <tr>
                            <td>Semester</td> <td>GPA</td> <td>CGPA</td> <td>Course Code</td> <td>Course Name</td> <td>Grade</td> <td>Grade Point</td> <td>Result</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            semesterMarks.map((mark, index) => {
                                return (
                                    <tr key={index}>
                                        <td>{mark.semester}</td> <td>{mark.gpa}</td> <td>{mark.cgpa}</td> <td>{mark.courseCode}</td> <td>{mark.courseName}</td> <td>{mark.grade}</td> <td>{mark.gradePoint}</td> <td>{mark.result}</td>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            }
        </div>
    )
}

export default SemesterMarks;