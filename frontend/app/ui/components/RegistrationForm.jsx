"use client";

import clsx from "clsx";
import { useReducer } from "react";
import { useEffect } from "react";
import { useState } from "react";
import { IoIosEye } from "react-icons/io";
import { IoIosEyeOff } from "react-icons/io";
import { toast } from "react-toastify";

import { addStudent, addTeacher } from "../../api/registrationApi";
import Link from "next/link";
import { useContext } from "react";
import { CmsContext } from "@/app/context/CmsContext";

function reducer(user, action) {
  switch (action.type) {
    case "name":
      return { ...user, name: action.payload };
    case "age":
      return { ...user, age: action.payload };
    case "rollNumber":
      return { ...user, rollNumber: action.payload };
    case "dateOfBirth":
      return { ...user, dateOfBirth: action.payload };
    case "collegeMailId":
      return { ...user, collegeMailId: action.payload };
    case "password":
      return { ...user, password: action.payload };
    case "clear":
      return {
        ...user,
        name: "",
        age: "",
        rollNumber: "",
        dateOfBirth: "",
        collegeMailId: "",
        password: "",
      };
    default:
      throw Error("Unknown Action Type: " + action.type);
  }
}

function passwordValidation(password) {
  const minLength = /.{8,}/.test(password);
  const hasUpperCase = /[A-Z]/.test(password);
  const hasLowerCase = /[a-z]/.test(password);
  const hasDigit = /[0-9]/.test(password);
  const hasSpecialChar = /[!@#$%^&*()]/.test(password);
  return (
    minLength && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar
  );
}

const RegistrationForm = ({ userType, setLoading }) => {
  const [showPassword, setShowPassword] = useState(false);
  const [showPasswordForConform, setShowPasswordForConform] = useState(false);

  const { navigate } = useContext(CmsContext);

  const [conformPassword, setConformPasword] = useState("");

  const [user, dispatch] = useReducer(reducer, {
    name: "",
    age: 0,
    rollNumber: "",
    dateOfBirth: "",
    collegeMailId: "",
    password: "",
    roles: [userType],
  });

  const handleSubmission = async (e) => {
    e.preventDefault();

    if (
      user.name == "" ||
      (userType == "student" && user.rollNumber == "") ||
      user.dateOfBirth == "" ||
      user.collegeMailId == "" ||
      user.password == ""
    ) {
      toast.error("All Field Need to be filled out!!!");
      return;
    }

    if (userType == "student" && Number.isNaN(Number(user.rollNumber))) {
      toast.error("Please enter valid roll number");
      return;
    }

    let dateArray = user.dateOfBirth.split("-");
    let dd = Number(dateArray[0]);
    let MM = Number(dateArray[1]);
    if (
      dd > 31 ||
      dd < 1 ||
      MM > 12 ||
      MM < 1 ||
      dateArray[2]?.length != 4 ||
      dateArray.length != 3
    ) {
      toast.error("The date you specified contains some mistakes");
      return;
    }

    if (user.age < 15 || user.age > 50) {
      toast.error("The age you have spcified can't be in college!!!");
      return;
    }

    if (user.password != conformPassword) {
      toast.error("Password and Conform Password Didn't Match!!!");
      return;
    }

    if (!passwordValidation(user.password)) {
      toast.error("Password doesn't comes under validation");
      return;
    }

    let response;
    setLoading(true);
    if (userType == "student") {
      response = await addStudent(user);
    } else {
      response = await addTeacher(user);
    }
    setLoading(false);
    if (response.success) {
      toast.success(response.message);
      dispatch({ type: "clear" });
      setConformPasword("");
      navigate.push(`/otp-verification?email=${user.collegeMailId}`);
    } else {
      toast.error(response.message);
    }
  };

  return (
    <form onSubmit={handleSubmission} className="mt-8">
      <div className="grid grid-cols-2 gap-4">
        <input
          type="text"
          placeholder="Name"
          className={clsx(
            `w-full px-2 py-4 border outline-0 ${
              userType == "teacher" && "col-span-full"
            }`
          )}
          onChange={(e) => dispatch({ type: "name", payload: e.target.value })}
          value={user.name}
        />
        {userType == "student" && (
          <input
            type="text"
            placeholder="Roll Number"
            className="w-full px-2 py-4 border outline-0"
            onChange={(e) =>
              dispatch({ type: "rollNumber", payload: e.target.value })
            }
            value={user.rollNumber}
          />
        )}
        <input
          type="number"
          placeholder="Age"
          className="w-full px-2 py-4 border outline-0"
          onChange={(e) => dispatch({ type: "age", payload: e.target.value })}
          value={user.age}
        />
        <input
          type="text"
          placeholder="Date of Birth (dd-MM-yyyy)"
          className="w-full px-2 py-4 border outline-0"
          onChange={(e) =>
            dispatch({ type: "dateOfBirth", payload: e.target.value })
          }
          value={user.dateOfBirth}
        />
        <input
          type="email"
          placeholder="College Mail Id"
          className="w-full px-2 py-4 border col-span-full outline-0"
          onChange={(e) =>
            dispatch({ type: "collegeMailId", payload: e.target.value })
          }
          value={user.collegeMailId}
        />
        <div className="flex">
          <input
            type={showPassword ? "text" : "password"}
            placeholder="Password"
            className="w-full px-2 py-4 border border-r-0 outline-0"
            onChange={(e) =>
              dispatch({ type: "password", payload: e.target.value })
            }
            value={user.password}
          />
          <div
            className="flex items-center px-4 border-l-0 border text-2xl"
            onClick={() => setShowPassword(!showPassword)}
          >
            {showPassword ? <IoIosEyeOff /> : <IoIosEye />}
          </div>
        </div>
        <div className="flex">
          <input
            type={showPasswordForConform ? "text" : "password"}
            placeholder="conform Password"
            className="w-full px-2 py-4 border border-r-0 outline-0"
            onChange={(e) => setConformPasword(e.target.value)}
            value={conformPassword}
          />
          <div
            className="flex items-center px-4 border-l-0 border text-2xl"
            onClick={() => setShowPasswordForConform(!showPasswordForConform)}
          >
            {showPasswordForConform ? <IoIosEyeOff /> : <IoIosEye />}
          </div>
        </div>
      </div>

      <button type="submit" className="bg-black text-white w-full py-4 mt-4">
        Register
      </button>

      <div className="flex flex-col gap-4 mt-10">
        <p>If you already have an account!!!</p>

        <Link
          href={"/login"}
          className="bg-blue-800 text-white w-full py-4 mt-4 text-center"
        >
          Login to Account
        </Link>
      </div>
    </form>
  );
};

export default RegistrationForm;
