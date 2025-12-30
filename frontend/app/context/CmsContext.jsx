'use client'

import { useRouter } from "next/navigation";
import { useState } from "react";

const { createContext } = require("react");

export const CmsContext = createContext();

function CmsContextProvider({children}) {

    const navigate = useRouter()

    const [user, setUser] = useState({
        name: "sakthivel",
        age: 21,
        rollNumber: "720822103138",
        dateOfBirth: "03/10/2004",
        collegeMailId: "720822103138@hit.edu.in",
        roles: ["STUDENT"],
    });

    const value = {
        user, navigate,
    }

    return (
        <CmsContext.Provider value={value}>
            {children}
        </CmsContext.Provider>
    )
}

export default CmsContextProvider;