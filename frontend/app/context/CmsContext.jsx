'use client'

import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";

const { createContext } = require("react");

export const CmsContext = createContext();

export const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

function CmsContextProvider({children}) {

    const protectedRouters = ["/", "/profile", "/semester-marks"];

    const navigate = useRouter()

    const pathname = usePathname();

    const [user, setUser] = useState({
        name: "sakthivel",
        age: 21,
        rollNumber: "720822103138",
        dateOfBirth: "03/10/2004",
        collegeMailId: "720822103138@hit.edu.in",
        roles: ["STUDENT"],
    });

    const [login, setLogin] = useState(null);

    useEffect(() => {
        const storedLogin = localStorage.getItem("login");
        if (storedLogin) {
            setLogin(storedLogin);
        }
    }, []);

    useEffect(() => {
        if(protectedRouters.includes(pathname)) {
            const token = typeof window !== 'undefined' ? localStorage.getItem("token") : null;
            if(token == null) {
                if(login == undefined || login == null || login == false) {
                    toast.warning("You need to login!!! Before accessing that page.");
                    navigate.push("/login");
                }
            }
        }
    }, [login, navigate, pathname])
    
    const value = {
        user, navigate,
        login, setLogin
    }

    return (
        <CmsContext.Provider value={value}>
            {children}
        </CmsContext.Provider>
    )
}

export default CmsContextProvider;