"use client";

import clsx from "clsx";
import { useState } from "react";
import RegistrationForm from "../components/RegistrationForm";

const Register = () => {
  const [userType, setUserType] = useState("student");

  return (
    <div className="mt-5">
      <div className="flex flex-col gap-2">
        <p className="text-center text-xl">Account Registration</p>
        <hr className="border-1.5" />
      </div>

      <div>
        <div className="grid grid-cols-2">
          <button
            className={clsx(
              `w-full py-4 ${userType == "student" && "bg-gray-300 border-b-2 font-semibold"}`
            )}
            onClick={() => setUserType("student")}
          >
            Student
          </button>
          <button
            className={clsx(
              `w-full py-4 ${(userType == "teacher") && "bg-gray-300 border-b-2 font-semibold"}`
            )}
            onClick={() => setUserType("teacher")}
          >
            Teacher
          </button>
        </div>

        <div>
          <RegistrationForm userType={userType} />
        </div>
      </div>
    </div>
  );
};

export default Register;
