"use client";

import clsx from "clsx";
import { useContext } from "react";
import { useState } from "react";
import { IoMdWarning } from "react-icons/io";
import { CmsContext } from "../context/CmsContext";

const Profile = () => {

  const { user } = useContext(CmsContext);
  const [update, setUpdate] = useState(false);

  return (
    <div>
      {user != null && (
        <div className="mt-10 grid grid-cols-2 gap-4">
          <input
            type="text"
            name="name"
            id="name"
            placeholder="Name"
            value={user.name}
            disabled={!update}
            className={clsx(
              `border p-4 ${user.roles[0] == "TEACHER" && "col-span-full"}`
            )}
          />
          {user.roles[0] == "STUDENT" && (
            <input
              type="text"
              name="rollNumber"
              id="rollNumber"
              placeholder="Roll Number"
              value={user?.rollNumber}
              disabled={!update}
              className="border p-4"
            />
          )}
          <input
            type="number"
            name="age"
            id="age"
            placeholder="Age"
            value={user.age}
            disabled={!update}
            className="border p-4"
          />
          <input
            type="text"
            name="dateOfBirth"
            id="dateOfBirth"
            placeholder="Date of Birth"
            value={user.dateOfBirth}
            disabled={!update}
            className="border p-4"
          />
          <input
            type="text"
            name="collegeMailId"
            id="collegeMailId"
            placeholder="College Mail ID"
            value={user.collegeMailId}
            disabled={!update}
            className="border p-4 col-span-full"
          />

          <div className="col-span-full bg-gray-300 p-5 border-2 mt-10">
            {!update ? (
              <div>
                <p className="flex items-center text-red-600 font-bold gap-2 text-xl">
                  Account Alteration Zone
                  <IoMdWarning />
                </p>
                <div className="flex justify-between mt-5">
                  <button
                    className="px-6 py-3 bg-blue-700 text-white"
                    onClick={() => setUpdate(true)}
                  >
                    Update
                  </button>
                  <button className="px-6 py-3 bg-red-700 text-white">
                    Delete
                  </button>
                </div>
              </div>
            ) : (
              <div className="flex flex-col">
                <p className="flex items-center text-blue-600 font-bold gap-2 text-xl">
                  Cancel the updations
                </p>
                <button className="px-6 py-3 bg-red-700 text-white self-end" onClick={()=>{
                    setUpdate(false)
                    
                }}>Cancel</button>
              </div>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default Profile;
