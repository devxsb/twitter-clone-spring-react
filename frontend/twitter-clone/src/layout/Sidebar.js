import React, {useState} from "react";
import SideLink from "../components/SideLink";
import UserBox from "../components/UserBox";
import {
    BookmarksIcon,
    ExploreIcon,
    HomeIcon,
    ListsIcon,
    MessagesIcon,
    MoreIcon,
    NotificationsIcon,
    ProfileIcon,
} from "../icons/Icon";
import twitterLogo from "../images/twitter.svg";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";

const sideLinks = [
    {
        name: "Home",
        icon: HomeIcon,
    },
    {
        name: "Explore",
        icon: ExploreIcon,
    },
    {
        name: "Notifications",
        icon: NotificationsIcon,
    },
    {
        name: "Messages",
        icon: MessagesIcon,
    },
    {
        name: "Bookmarks",
        icon: BookmarksIcon,
    },
    {
        name: "Lists",
        icon: ListsIcon,
    },
    {
        name: "Profile",
        icon: ProfileIcon,
    },
    {
        name: "More",
        icon: MoreIcon,
    },
];

const Sidebar = () => {
    const [active, setActive] = useState("Home");

    const username = useSelector(state => state.reduxSlice.username)

    const navigate = useNavigate()

    const handleMenuItemClick = (name) => {
        setActive(name);
        switch (name) {
            case "Home":
                navigate("/");
                break;
            case "Explore":
                navigate("/");
                break;
            case "Notifications":
                navigate("/");
                break;
            case "Messages":
                navigate("/");
                break;
            case "Bookmarks":
                navigate("/");
                break;
            case "Lists":
                navigate("/");
                break;
            case "Profile":
                navigate("/" + username);
                break;
            case "More":
                navigate("/");
                break;
            default:
                navigate("/");
        }
    };

    return (
        <div className="h-screen sticky top-0 flex flex-col justify-between w-72 px-2">
            <div>
                <div
                    className="mt-1 mb-4 ml-1 flex items-center justify-center w-12 h-12 rounded-full hover:bg-gray-lightest transform transition-colors duration-200">
                    <img src={twitterLogo} alt="Twitter Logo" className="w-9 h-9"/>
                </div>
                <nav className="mb-4">
                    <ul>
                        {sideLinks.map(({name, icon}) => (
                            <SideLink
                                key={name}
                                name={name}
                                Icon={icon}
                                active={active}
                                onMenuItemClick={handleMenuItemClick}
                            />
                        ))}
                    </ul>
                </nav>
                <button
                    className="bg-primary-base hover:bg-primary-dark text-white shadow-lg rounded-full py-3 px-8 w-11/12 transform transition-colors duration-200">
                    Tweet
                </button>
            </div>
            <UserBox/>
        </div>
    );
};

export default Sidebar;
