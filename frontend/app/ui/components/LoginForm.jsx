"use client";

import Link from "next/link";
import { useState } from "react";
import { IoIosEyeOff, IoIosEye } from "react-icons/io";

import { login } from "@/app/api/loginApi";
import { toast } from "react-toastify";
import Loading from "@/app/loading";
import { useContext } from "react";
import { CmsContext } from "@/app/context/CmsContext";

const LoginForm = () => {
  const { navigate, setLogin, credential, dispatch, fetchUserDetails, setUserType } =
    useContext(CmsContext);

  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);

  const handleSubmission = async (e) => {
    e.preventDefault();

    if (credential.collegeMailId == "" || credential.password == "") {
      toast.warning("All fields are required!!!");
      return;
    }

    setLoading(true);
    const response = await login(credential);
    setLoading(false);

    if (response != null && response.success && response.data != null) {
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("login", true);
      localStorage.setItem(
        "user-type",
        JSON.stringify(response.data.user.authorities)
      );
      setLogin(localStorage.getItem("login"));

      toast.success("Login Successful!!!");
      toast.info("Please wait, The data is loading");

      const storedUserType = response.data.user.authorities.map((value) => value.authority.substring(5).toLowerCase());

      setUserType(storedUserType);

      setLoading(true);
      await fetchUserDetails(credential?.collegeMailId, storedUserType);
      setLoading(false);

      toast.success("The data loaded successfully!!")

      dispatch({ field: "clear" });
      navigate.push("/");
    } else {
      toast.error(response.message);
      dispatch({ field: "clear" });
    }
  };

  return (
    <div className="flex justify-center">
      {loading ? (
        <Loading />
      ) : (
        <form
          onSubmit={handleSubmission}
          className="w-[90%] md:w-[50%] border p-5 mt-10"
        >
          <div className="flex flex-col gap-4">
            <input
              type="email"
              placeholder="College Mail Id"
              className="w-full px-2 py-4 border outline-0"
              onChange={(e) =>
                dispatch({ field: "collegeMailId", payload: e.target.value })
              }
              value={credential?.collegeMailId}
              required
            />
            <div className="flex">
              <input
                type={showPassword ? "text" : "password"}
                placeholder="Password"
                className="w-full px-2 py-4 border border-r-0 outline-0"
                onChange={(e) =>
                  dispatch({ field: "password", payload: e.target.value })
                }
                value={credential?.password}
                required
              />
              <div
                className="flex items-center px-4 border-l-0 border text-2xl"
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? <IoIosEyeOff /> : <IoIosEye />}
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

            <Link
              href={"/register"}
              className="bg-blue-800 text-white w-full py-4 mt-4 text-center"
            >
              Register For Account
            </Link>
          </div>
        </form>
      )}
    </div>
  );
};

export default LoginForm;
