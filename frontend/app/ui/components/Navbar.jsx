'use client'
import Link from 'next/link'
import React from 'react'
import Image from 'next/image'
import logo from '@/public/logo/logo.png'
import { useContext } from 'react'
import { CmsContext } from '@/app/context/CmsContext'

const Navbar = () => {

    const { login } = useContext(CmsContext);

    const navlinksBeforeLogin = [
        { name : "Login", route : "/login" },
        { name : "Register", route : "/register" }
    ]

    const navlinksAfterLogin = [
        { name : "Home", route : "/" },
        { name : "Semester Marks", route : "/semester-marks" },
        { name : "Profile", route : "/profile" },
    ]

  return (
    <div className='flex justify-between py-2 text-xl bg-slate-600 px-4 text-white items-center'>
        <div>
            <Image
                src={logo}
                width={80}
                height={80}
                alt='Logo'
            />
        </div>

        <div className='flex gap-5'>
            {
                login ?
                navlinksAfterLogin.map((value, index) => {
                    return (
                        <Link href={value.route} key={index}>
                            {value.name}
                        </Link>
                    )
                })
                :
                navlinksBeforeLogin.map((value, index) => {
                    return (
                        <Link href={value.route} key={index}>
                            {value.name}
                        </Link>
                    )
                })
            }
        </div>
    </div>
  )
}

export default Navbar