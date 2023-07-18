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

import {
  GrAddCircle
} from "react-icons/gr"

import { Link, useNavigate } from "react-router-dom";
import useAuthenticationContext from "../hooks/useAuthentication";

function Sidebar() {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  const { userAuthenticated, setAuthentication } = useAuthenticationContext();
  const navigate = useNavigate();

  const handleSidebarToggle = () => {
    setSidebarOpen(!sidebarOpen);
  };

  const handleSidebarProfile = () => {
    if (userAuthenticated) {
      navigate("/profile")
    } else {
      navigate("/login")
    }
  };

  const handleSidebarLogout = () => {
    setAuthentication({});
    navigate("/logout");
  };

  return(
    <div className="w-screen flex justify-between items-center h-20 max-w-[1240px] mx-auto px-4 border rounded">
      <h1 className="text-3xl font-bold text-[#14551f]">Stream Samples</h1>
          { userAuthenticated ? (
            <Link to={"/create-post"} className="hidden md:flex gap-2 items-center p-4 rounded-xl hover:bg-stone-200 cursor-pointer">
              <GrAddCircle size={20} />Create sample
            </Link>
          ) : (<></>)}
        <ul className="hidden md:flex">
          <li>
            <Link to={"/"} className="flex gap-2 items-center p-4 rounded-xl hover:bg-stone-200">
              <AiOutlineHome size={20} />Home
            </Link>
          </li>
          <li>
            <Link to={"/categories"} className="flex gap-2 items-center p-4 rounded-xl hover:bg-stone-200">
              <TbCategory size={20} />Categories
            </Link>
          </li>
          <li>
            <div onClick={handleSidebarProfile} className="flex gap-2 items-center p-4 rounded-xl hover:bg-stone-200 cursor-pointer">
              <CgProfile size={20} />Profile
            </div>
          </li>
          
          { userAuthenticated ? (
            <li>
              <div onClick={handleSidebarLogout} className="flex gap-2 items-center p-4 rounded-xl hover:bg-red-200 cursor-pointer">
                <BiLogOut size={20} />Logout
              </div>
            </li>
          ) : (<></>)}
        </ul>
          <div onClick={handleSidebarToggle} className="block md:hidden">
            {sidebarOpen ? <AiOutlineClose size={20} className="cursor-pointer" /> : <AiOutlineMenu size={20} className="cursor-pointer" />}
          </div>
        <ul className={sidebarOpen ? "bg-white fixed left-0 top-20 w-[100%] h-full border-r ease-in-out duration-500 md:hidden" : "ease-in-out duration-500 fixed left-[-100%] top-20"}>
          <li className="flex gap-2 items-center p-4 border-b "><AiOutlineHome size={20} />Home</li>
          <li className="flex gap-2 items-center p-4 border-b "><TbCategory size={20} />Categories</li>
          <li className="flex gap-2 items-center p-4 border-b "><CgProfile size={20} />Profile</li>
          <li className="flex gap-2 items-center p-4 border-b "><BiLogOut size={20} />Logout</li>
        </ul>
    </div>
  );
}

export default Sidebar;