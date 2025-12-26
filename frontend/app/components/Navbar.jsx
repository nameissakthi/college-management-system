import Link from 'next/link'
import React from 'react'
import Image from 'next/image'
import logo from '@/public/logo/logo.png'

const Navbar = () => {

    const navlinks = [
        { name : "Home", route : "/" },
        { name : "Login", route : "/login" },
        { name : "Register", route : "/register" }
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
                navlinks.map((value, index) => {
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