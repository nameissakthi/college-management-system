"use client";

import { usePathname, useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { useReducer } from "react";
import { fetchWithAuth } from "../api/fetchWithAuth";

const { createContext } = require("react");

export const CmsContext = createContext();

export const BACKEND_URL = process.env.NEXT_PUBLIC_BACKEND_URL;

function CmsContextProvider({ children }) {
  const protectedRouters = ["/", "/profile", "/semester-marks"];
  const roleBasedRoutes = {
    "/semester-marks": ["student"],
    "profile" : ["student", "teacher"]
  };
  const navigate = useRouter();
  const pathname = usePathname();
  const [user, setUser] = useState();
  const [credential, dispatch] = useReducer(reducer, {
    collegeMailId: "",
    password: "",
    userType: "",
  });
  const [login, setLogin] = useState(null);
  const [userType, setUserType] = useState([]);
  const [isLoggingOut, setIsLoggingOut] = useState(false);



  useEffect(() => {
    const storedLogin = localStorage.getItem("login");
    let storedUserType = localStorage.getItem("user-type");
    const storedUser = localStorage.getItem("user");
    const token = localStorage.getItem("token");

    if (storedLogin) {
      setLogin(storedLogin);
    }

    if(storedUserType !== null) {
      storedUserType = JSON.parse(storedUserType);
      storedUserType = storedUserType.map(value => value.authority.substring(5).toLowerCase());
      setUserType(storedUserType);
    }

    if(storedUser) {
      setUser(JSON.parse(storedUser));
    } 
    else if (token && storedUserType && storedLogin) {
      const email = localStorage.getItem("user-email");
      if(email) {
        fetchUserDetails(email, storedUserType);
      }
    }
  }, []);

  useEffect(() => {
    
    if (protectedRouters.includes(pathname)) {
      const token =
        typeof window !== "undefined" ? localStorage.getItem("token") : null;
      if (token == null) {
        if (login == undefined || login == null || login == false) {
          if (pathname !== "/login" && !isLoggingOut) {
            toast.warning("You need to login!!! Before accessing that page.");
            navigate.push("/login");
          }
        }
      } else {
        if (roleBasedRoutes[pathname]) {
          const allowedRoles = roleBasedRoutes[pathname];
          const hasAccess = userType.some(role => allowedRoles.includes(role));
          
          if (!hasAccess && userType.length > 0) {
            toast.error("You don't have permission to access this page.");
            navigate.push("/");
          }
        }
      }
    }
  }, [login, navigate, pathname, isLoggingOut, userType]);
  



  const fetchUserDetails = async (email, userTypeArray = userType) => {

    if(userTypeArray.length <= 0) {
      toast.error("Error while fetching the data. Please login again");
      return;
    }

    const basePath = userTypeArray[0]==="student" ? "/student/get" : "/teacher/get";
    const path = `${basePath}?email=${email}`;

    const response = await fetchWithAuth(path, 'GET');

    if(response && response.data) {
      setUser(response.data);
      // Persist user data in localStorage
      localStorage.setItem("user", JSON.stringify(response.data));
      localStorage.setItem("user-email", email);
    }
  }

  const logout = () => {
    setIsLoggingOut(true);
    
    localStorage.removeItem('token');
    localStorage.removeItem('login');
    localStorage.removeItem('user-type');
    localStorage.removeItem('user');
    localStorage.removeItem('user-email');
    
    setLogin(null);
    setUserType([]);
    setUser(null);
    
    dispatch({ field: "clear" });
    
    toast.success("Logout Successful!!!");
    
    navigate.push("/login");
    
    setTimeout(() => setIsLoggingOut(false), 100);
  }

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
    fetchUserDetails, setUserType,
    logout
  };

  return <CmsContext.Provider value={value}>{children}</CmsContext.Provider>;
}

export default CmsContextProvider;
