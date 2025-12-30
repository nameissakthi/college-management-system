"use client";
import Link from "next/link";

import { FiAlertCircle } from "react-icons/fi";
import { otp_verify } from "../api/registrationApi";
import { useRef } from "react";
import { useSearchParams } from "next/navigation";
import { useContext } from "react";
import { CmsContext } from "../context/CmsContext";
import Loading from "../loading";
import { useState } from "react";
import { toast } from "react-toastify";

const OtpVerification = () => {
  const { navigate } = useContext(CmsContext);
  const searchParams = useSearchParams();
  const email = searchParams.get("email");
  const otpFieldBox = useRef();
  const [loading, setLoading] = useState(false);

  const handleOtpSubmission = async (e) => {
    e.preventDefault();
    if (email == null) throw new Error("Oops there is an exception!!!");

    const request = {
      email: email,
      otp: otpFieldBox.current.value,
    };

    setLoading(true)
    const response = await otp_verify(request);
    setLoading(false)

    console.log(response)
    if (response.success) {
        toast.success(response.message)
        navigate.push("/login")
    } else{
        toast.error(response.message)
    }
  };

  return (
    <div>
      {loading ? (
        <Loading />
      ) : (
        <div className="mt-5 flex justify-center items-center">
          <div className="border p-4 max-w-xl">
            <h1 className="text-2xl mb-10">College Management System</h1>
            <form className="flex flex-col items-center">
              <p className="self-start mb-2">Enter your OTP here</p>
              <input
                className="border self-stretch py-2 text-center"
                type="text"
                ref={otpFieldBox}
              />

              <p className="text-xs self-start flex gap-1 items-center">
                <FiAlertCircle />
                The OTP will only last for 5 minutes.
              </p>

              <p className="mt-5">
                If the OTP has not been received. Please{" "}
                <Link href={"/register"} className="text-blue-700 underline">
                  register
                </Link>{" "}
                your account again. Before that please check with your internet
                connection.
              </p>

              <div>
                <button
                  type="submit"
                  className="border px-8 py-2 bg-slate-800 text-white font-bold mt-10"
                  onClick={handleOtpSubmission}
                >
                  Verify
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  );
};

export default OtpVerification;
