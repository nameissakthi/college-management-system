import Image from "next/image";

import logo from "@/public/logo/logo.png"
import Link from "next/link";

const Footer = () => {

    const usefullLinks = [
        { name : "Home", link : "/" },
        { name : "Login", link : "/login" }
    ]

    const contactUsWith = [
        { name : "Facbook", link : "#" },
        { name : "Twitter", link : "#" },
        { name : "Instagram", link : "#" }
    ]

    const helpSection = [
        { name : "Your Account", link : "#" },
        { name : "Privacy & security", link : "#" },
        { name : "Terms & Conditions", link : "#" }
    ]

    return (
        <div>
            <div className="mt-10 bg-slate-600 text-white p-5 min-h-52 flex justify-between">
                <div className="flex-4/12">
                    <Image
                        src={logo}
                        width={200}
                        alt="Logo"
                    />
                </div>

                <div className="flex-8/12 flex justify-between items-center">
                    <div>
                        <p className="font-bold mb-4">Usefull links</p>
                        <div className="flex flex-col gap-2">
                            {
                                usefullLinks.map((value, index) => {
                                    return (
                                        <Link href={value.link} key={index}>
                                            {value.name}
                                        </Link>
                                    )
                                })
                            }
                        </div>
                    </div>

                    <div>
                        <p className="font-bold mb-4">Connect with us</p>
                        <div className="flex flex-col gap-2">
                            {
                                contactUsWith.map((value, index) => {
                                    return (
                                        <Link href={value.link} key={index}>
                                            {value.name}
                                        </Link>
                                    )
                                })
                            }
                        </div>
                    </div>

                    <div>
                        <p className="font-bold mb-4">Let us help you</p>
                        <div className="flex flex-col gap-2">
                            {
                                helpSection.map((value, index) => {
                                    return (
                                        <Link href={value.link} key={index}>
                                            {value.name}
                                        </Link>
                                    )
                                })
                            }
                        </div>
                    </div>
                </div>
            </div>
            <div className="bg-slate-600 text-white text-right pr-5">
                @ For further details please contact college admin.
            </div>
        </div>
    );
}

export default Footer;