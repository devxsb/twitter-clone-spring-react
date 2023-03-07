import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import UserService from "../service/UserService";
import bg from "../images/defaul-bg.png"
import profile from "../images/default-profile.png"
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import Divider from "../components/Divider";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import FeedList from "../components/FeedList";
import {Close} from "@mui/icons-material";
import PlaceIcon from '@mui/icons-material/Place';
import LinkIcon from '@mui/icons-material/Link';
import MyDropzone from "../components/MyDropzone";

const User = () => {
    const [user, setUser] = useState()
    const [menu, setMenu] = useState(0) // 0 tweets 1 tweets & media 2 media 3 likes
    const [edit, setEdit] = useState(false)
    const [name, setName] = useState()
    const [bio, setBio] = useState()
    const [location, setLocation] = useState()
    const [webSite, setWebSite] = useState()

    const {username} = useParams()

    const navigate = useNavigate()

    const userService = new UserService()

    useEffect(() => {
        userService.getUserByUsername(username).then(res => setUser(res.data))
    }, [username])

    const month = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

    const saveProfile = () => {
        let body = {
            name,
            bio,
            location,
            webSite
        }
        userService.editProfile(body, user.id).then((res => {
            setUser(res.data)
            setEdit(false)
        }))
        window.location.reload(false)
    }
    return (
        <div className={"flex flex-col border-r border-l border-gray-extraLight w-1/2 mr-auto relative"}>
            {user &&
                <>
                    <header
                        className="sticky flex items-center top-0 z-10 bg-white p-2 h-16 border-b border-gray-extraLight ">
                        <ArrowBackIcon
                            style={{cursor: "pointer", marginRight: "10px", marginLeft: "5px"}}
                            fontSize={"small"}
                            onClick={() => navigate("/")}/>
                        <div className="flex flex-col">
                            <span className="font-medium text-lg text-gray-900">{user.name}</span>
                            <span className="font-light text-sm
                    ">{user.tweets.length} tweets</span>
                        </div>
                    </header>
                    <div className="h-52" style={{
                        backgroundImage: `url(${bg}`,
                        backgroundRepeat: "no-repeat",
                        backgroundSize: "cover"
                    }}>
                        <img
                            src={user.profileImageLink ? `http://localhost:8080/v1/users/${username}/image/download` : profile}
                            alt="Profile"
                            className="w-28 h-28 rounded-full mt-36 ml-3 absolute"
                        />
                    </div>
                    <div className="mb-3">
                        <button
                            className="float-right h-10 bg-transparent py-2 px-4 rounded-2xl mt-1 mr-1 font-bold text-sm"
                            style={{color: "#1DA1F2", border: `1px solid #1DA1F2`}}
                            onClick={() => setEdit(!edit)}>Edit profile
                        </button>
                        <div className="mt-16 ml-3">
                            <span className="font-bold block mb-0">{user.name}</span>
                            <span className="font-light block text-sm">@{user.username}</span>
                            {user.bio && <span className="block mt-2">{user.bio}</span>}
                            <div className="flex items-start mb-1">
                                {user.location && <span className="flex items-center font-light block mt-2 mr-2"
                                                        style={{fontSize: "13px"}}>
                                <PlaceIcon fontSize="small"
                                           style={{color: "lightslategrey", marginRight: "3px"}}/>
                                    {user.location}
                                </span>}
                                {user.webSite && <span className="flex items-center font-light block mt-2 mr-2"
                                                       style={{fontSize: "13px"}}>
                                <LinkIcon fontSize="small"
                                          style={{color: "lightslategrey", marginRight: "3px"}}/>
                                    {user.webSite}
                                </span>}
                                <span className="flex items-center font-light block mt-2 mr-2"
                                      style={{fontSize: "13px"}}>
                                <CalendarMonthIcon fontSize="small"
                                                   style={{color: "lightslategrey", marginRight: "3px"}}/>
                                Joined {month[new Date(user.creationTimestamp).getUTCMonth()]} {new Date(user.creationTimestamp).getFullYear()}</span>
                            </div>
                            <div className="flex items-center">
                                0 <span className="mr-2 p-1 text-sm font-light">Following</span>
                                0 <span className="text-sm p-1 font-light">Followers</span>
                            </div>
                        </div>
                    </div>
                    <div className="flex justify-around p-2">
                        <button
                            className="bg-transparent focus:outline-none text-sm"
                            onClick={() => setMenu(0)}>
                            Tweets
                        </button>
                        <button
                            className="bg-transparent focus:outline-none text-sm"
                            onClick={() => setMenu(1)}>
                            Tweets & replies
                        </button>
                        <button
                            className="bg-transparent focus:outline-none text-sm"
                            onClick={() => setMenu(2)}>
                            Media
                        </button>
                        <button
                            className="bg-transparent focus:outline-none text-sm"
                            onClick={() => setMenu(3)}>
                            Likes
                        </button>
                    </div>
                    <Divider/>
                    <FeedList tweets={user.tweets}/>
                    {edit &&
                        <>
                            <div
                                className="flex justify-center items-center overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
                                <div className="relative my-6 mx-auto w-96">
                                    <div
                                        className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                                        <div className="p-4 flex-auto" onSubmit={() => setEdit(false)}>
                                            <div className="flex justify-between items-start p-2">
                                                <>
                                                    <Close className="mb-1 cursor-pointer"
                                                           onClick={() => setEdit(false)}/>
                                                    <p className="ml-1 mr-auto font-medium">Edit Profile</p>
                                                </>
                                                <button
                                                    className="hover:bg-blue-700 text-white font-bold py-1 px-4 rounded-full"
                                                    style={{backgroundColor: "#1DA1F2"}}
                                                    onClick={saveProfile}>
                                                    Save
                                                </button>
                                            </div>
                                            <div className="h-36 flex relative" style={{
                                                backgroundImage: `url(${bg}`,
                                                backgroundRepeat: "no-repeat",
                                                backgroundSize: "cover"
                                            }}>
                                                <div className="w-16 h-16 mt-28 ml-1 relative">
                                                    <img
                                                        src={user.profileImageLink ? `http://localhost:8080/v1/users/${username}/image/download` : profile}
                                                        alt="Profile"
                                                        className="w-16 h-16 rounded-full"
                                                    />
                                                    <MyDropzone/>
                                                </div>
                                            </div>
                                            <div className="mt-10">
                                                <>
                                                    <label htmlFor="name"
                                                           className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</label>
                                                    <input type="text" id="name"
                                                           className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white focus:outline-none focus:shadow-outline focus:bg-white"
                                                           onChange={e => setName(e.target.value)}/>
                                                </>
                                                <>
                                                    <label htmlFor="bio"
                                                           className="block mb-1 mt-1 text-sm font-medium text-gray-900 dark:text-white">Bio</label>
                                                    <input type="text" id="bio"
                                                           className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white focus:outline-none focus:shadow-outline focus:bg-white"
                                                           onChange={e => setBio(e.target.value)}/>
                                                </>
                                                <>
                                                    <label htmlFor="location"
                                                           className="block mb-1 mt-1 text-sm font-medium text-gray-900 dark:text-white">Location</label>
                                                    <input type="text" id="location"
                                                           className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white focus:outline-none focus:shadow-outline focus:bg-white"
                                                           onChange={e => setLocation(e.target.value)}/>
                                                </>
                                                <>
                                                    <label htmlFor="web-site"
                                                           className="block mb-1 mt-1 text-sm font-medium text-gray-900 dark:text-white">Website</label>
                                                    <input type="text" id="web-site"
                                                           className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white focus:outline-none focus:shadow-outline focus:bg-white"
                                                           onChange={e => setWebSite(e.target.value)}/>
                                                </>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="opacity-10 fixed inset-0 z-40 bg-black"/>
                        </>
                    }
                </>
            }
        </div>
    );
};

export default User;