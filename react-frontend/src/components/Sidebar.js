import React, { useState } from "react";
import { 
  AiOutlineClose, 
  AiOutlineMenu, 
  AiOutlineHome
} from "react-icons/ai";

import {
  CgProfile
} from "react-icons/cg"

import {
  TbCategory
} from "react-icons/tb"

import {
  BiLogOut
} from "react-icons/bi"

import { Link } from "react-router-dom";
import useAuthenticationContext from "../hooks/useAuthentication";

function Sidebar() {
  const [sidebarOpen, setSidebarOpen] = useState(false);
  const { userAuthenticated, setAuthentication } = useAuthenticationContext();

  const handleSidebarToggle = () => {
    setSidebarOpen(!sidebarOpen);
  };

  const handleSidebarLogout = () => {
    setAuthentication({});
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
            <Link to={"/categories"} className="flex gap-2 items-center p-4">
              <TbCategory size={20} />Categories
            </Link>
          </li>
          <li>
            <Link to={"/login"} className="flex gap-2 items-center p-4">
              <CgProfile size={20} />Profile
            </Link>
          </li>
          
          { userAuthenticated ? (
            <li>
              <Link onClick={handleSidebarLogout} to={"/logout"} className="flex gap-2 items-center p-4">
                <BiLogOut size={20} />Logout
              </Link>
            </li>
          ) : (<div></div>)}
        </ul>
          <div onClick={handleSidebarToggle} className="block md:hidden">
            {sidebarOpen ? <AiOutlineClose size={20} className="cursor-pointer" /> : <AiOutlineMenu size={20} className="cursor-pointer" />}
          </div>
        <ul className={sidebarOpen ? "bg-white fixed left-0 top-20 w-[100%] h-full border-r ease-in-out duration-500 md:hidden" : "ease-in-out duration-500 fixed left-[-100%] top-20"}>
          <li className="flex gap-2 items-center p-4 border-b "><AiOutlineHome size={20} />Home</li>
          <li className="flex gap-2 items-center p-4 border-b "><TbCategory size={20} />Categories</li>
          <li className="flex gap-2 items-center p-4 border-b "><CgProfile size={20} />Profile</li>
        </ul>
    </div>
  );
}

export default Sidebar;