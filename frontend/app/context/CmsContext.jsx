"use client";

import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { useReducer } from "react";
import { fetchData } from "../api/fetchWithAuth";

const { createContext } = require("react");

export const CmsContext = createContext();

export const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

function CmsContextProvider({ children }) {
  const protectedRouters = ["/", "/profile", "/semester-marks"];
  const navigate = useRouter();
  const pathname = usePathname();
  const [user, setUser] = useState({
    name: "sakthivel",
    age: 21,
    rollNumber: "720822103138",
    dateOfBirth: "03/10/2004",
    collegeMailId: "720822103138@hit.edu.in",
    roles: ["STUDENT"],
  });
  const [credential, dispatch] = useReducer(reducer, {
    collegeMailId: "",
    password: "",
    userType: "",
  });
  const [login, setLogin] = useState(null);
  const [userType, setUserType] = useState([]);



  useEffect(() => {
    const storedLogin = localStorage.getItem("login");
    let storedUserType = localStorage.getItem("user-type");

    if (storedLogin) {
      setLogin(storedLogin);
    }

    if(storedUserType !== null) {
      storedUserType = JSON.parse(storedUserType);
      storedUserType.map(value => value.authority.substring(4).toLowerCase());

      if(storedUserType.length >= 1) setUserType(storedUserType)
    }
  }, []);

  useEffect(() => {
    if (protectedRouters.includes(pathname)) {
      const token =
        typeof window !== "undefined" ? localStorage.getItem("token") : null;
      if (token == null) {
        if (login == undefined || login == null || login == false) {
          toast.warning("You need to login!!! Before accessing that page.");
          navigate.push("/login");
        }
      }
    }
  }, [login, navigate, pathname]);



  

  function reducer(credential, action) {
    switch (action.field) {
      case "collegeMailId":
        return { ...credential, collegeMailId: action.payload };
      case "password":
        return { ...credential, password: action.payload };
      case "user-type":
        return { ...credential, userType: action.payload };
      case "clear":
        return {
          collegeMailId: "",
          password: "",
        };
    }
    throw new Error("Action Type is Not Found!!!");
  }

  const value = {
    user, userType,
    navigate,
    login, setLogin,
    credential, dispatch,
  };

  return <CmsContext.Provider value={value}>{children}</CmsContext.Provider>;
}

export default CmsContextProvider;
