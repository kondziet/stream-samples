import React, { useState } from "react";
import { 
  AiOutlineClose, 
  AiOutlineMenu, 
  AiOutlineHome
} from "react-icons/ai";

import {
  CgProfile
} from "react-icons/cg"
import { Link } from "react-router-dom";

function Side() {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const handleSidebarClick = () => {
    setSidebarOpen(!sidebarOpen);
  };

  return(
    <div className="flex justify-between items-center h-20 max-w-[1240px] mx-auto px-4">
      <h1 className="text-3xl font-bold text-[#14551f]">Stream Samples</h1>
        <div className="hidden md:flex">
          Search
        </div>
        <ul className="hidden md:flex">
          <li>
            <Link to={"/"} className="flex gap-2 items-center p-4">
              <AiOutlineHome size={20} />Home
            </Link>
          </li>
          <li>
            <Link to={"/profile"} className="flex gap-2 items-center p-4">
              <CgProfile size={20} />Profile
            </Link>
          </li>
        </ul>
          <div onClick={handleSidebarClick} className="block md:hidden">
            {sidebarOpen ? <AiOutlineClose size={20} className="cursor-pointer" /> : <AiOutlineMenu size={20} className="cursor-pointer" />}
          </div>
        <ul className={sidebarOpen ? "fixed left-0 top-20 w-[100%] h-full border-r ease-in-out duration-500 md:hidden" : "ease-in-out duration-500 fixed left-[-100%] top-20"}>
          <li className="flex gap-2 items-center p-4 border-b "><AiOutlineHome size={20} />Home</li>
          <li className="flex gap-2 items-center p-4 border-b "><CgProfile size={20} />Profile</li>
        </ul>
    </div>
  );
}

export default Side;