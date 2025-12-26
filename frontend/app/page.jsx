import clsx from "clsx";
import { bbh_bogle } from "./ui/fonts";
import Link from "next/link";
import { ImProfile } from "react-icons/im";
import { CgFileDocument } from "react-icons/cg";

const Home = () => {

  const userOptions = [
    { name : "Semester Marks", link : "#", icon : <ImProfile /> },
    { name : "My Profile", link : "#", icon : <CgFileDocument /> }
  ]

  return (
    <div className="mt-10">
      <div>
        <p className={clsx(`text-4xl ${bbh_bogle.className} antialiased tracking-widest`)}>Welcome <span className="text-orange-500">{"sakthivel"}</span></p>

        <div className="mt-10 grid grid-cols-4 gap-4">
          {
            userOptions.map((value, index) => {
              return (
                <Link href={value.link} key={index} 
                  className="px-4 py-8 border flex flex-col scale-95 items-center justify-center hover:border-0 hover:bg-slate-700 hover:text-white hover:scale-100 transition-all delay-100 active:bg-slate-700 active:text-white active:scale-100"
                >
                  <span className="text-4xl">{value.icon}</span>
                  <span className="mt-3 text-2xl">{value.name}</span>
                </Link>
              )
            })
          }
        </div>
      </div>
    </div>
  );
}

export default Home;