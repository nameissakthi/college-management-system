'use client'

import Link from "next/link";
import { useReducer } from "react";
import { useState } from "react";
import { IoIosEyeOff, IoIosEye } from "react-icons/io";

function reducer (credential, action) {
    switch(action.field) {
        case "collegeMailId":
            return { ...credential, collegeMailId : action.payload };
        case "password":
            return { ...credential, password : action.payload };
    }
    throw new Error("Action Type is Not Found!!!");
}

const LoginForm = () => {

    const [showPassword, setShowPassword] = useState(false)
    const [credential, dispatch] = useReducer(reducer, {
        collegeMailId : "",
        password : ""
    });

    const handleSubmission = (e) => {
        e.preventDefault();

        console.log(credential)
    }

    return (
        <form onSubmit={handleSubmission} className="w-[50%] border p-5 mt-10">
            <div className="flex flex-col gap-4">
                <input 
                    type="text"
                    placeholder="College Mail Id"
                    className="w-full px-2 py-4 border outline-0"
                    onChange={(e)=>dispatch({ field : "collegeMailId", payload : e.target.value })}
                    value={credential.collegeMailId}
                />
                <div className="flex">
                    <input 
                        type={showPassword ? "text" : "password"}
                        placeholder="Password"
                        className="w-full px-2 py-4 border border-r-0 outline-0"
                        onChange={(e)=>dispatch({ field : "password", payload : e.target.value })}
                        value={credential.password}
                    />
                    <div className="flex items-center px-4 border-l-0 border text-2xl" onClick={() => setShowPassword(!showPassword)}>
                        { showPassword ? <IoIosEyeOff/> : <IoIosEye/> }
                    </div>
                </div>

                <button
                    type="submit"
                    className="bg-black text-white w-full py-4 mt-4"
                >
                    Login
                </button>
            </div>

            <div className="flex flex-col gap-4 mt-10">
                <p>If you don&rsquo;t have an account!!!</p>

                <Link href={"/register"} className="bg-blue-800 text-white w-full py-4 mt-4 text-center">
                    Register For Account
                </Link>
            </div>
        </form>
    )
}

export default LoginForm;